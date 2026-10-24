
/*
 * PaymentService.java
 * Payment Service Implementation
 * Author: Bongikazi Mnyamana (222718404)
 */
package za.ac.cput.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.repository.IOrderRepository;
import za.ac.cput.repository.IPaymentRepository;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    private final IPaymentRepository repository;
    private final IOrderRepository orderRepository;
    private final StockService stockService;

    public PaymentService(IPaymentRepository repository, IOrderRepository orderRepository, StockService stockService) {
        this.repository = repository;
        this.orderRepository = orderRepository;
        this.stockService = stockService;
    }

    @Override
    @Transactional
    public Payment create(Payment payment) {

        if (payment.getOrderID() == null) {
            throw new IllegalArgumentException("Order ID is required");
        }

        Long orderId = Long.valueOf(payment.getOrderID());

        // Lock the order to prevent duplicate stock deductions.
        Order existingOrder = orderRepository.findByIdForUpdate(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        if (existingOrder.getPaymentId() != null) {
            throw new IllegalStateException("This order already has a payment");
        }

        // Deduct stock only when payment is successful.
        if (payment.getStatus() == PaymentStatus.PAID) {
            stockService.deductStock(existingOrder);
        }

        Payment savedPayment = repository.save(payment);

        // Link the saved payment to the existing order.
        // No need to rebuild the Order with Builder.copy().
        existingOrder.setPaymentId(savedPayment.getPaymentID().toString());

        orderRepository.save(existingOrder);

        return savedPayment;
    }

    @Override
    public Payment read(Long paymentId) {
        return repository.findById(paymentId).orElse(null);
    }

    @Override
    @Transactional
    public Payment update(Payment payment) {

        if (payment.getPaymentID() == null) {
            throw new IllegalArgumentException("Payment ID is required");
        }

        Payment existingPayment = repository.findById(payment.getPaymentID()).orElseThrow(() -> new RuntimeException("Payment not found"));

        if (!existingPayment.getOrderID().equals(payment.getOrderID())) {
            throw new IllegalStateException("Cannot change the payment's order");
        }

        // Prevent bypassing stock deduction by changing payment status.
        if (existingPayment.getStatus() != payment.getStatus()) {
            throw new IllegalStateException("Payment status changes must use the payment processing flow");
        }

        return repository.save(payment);
    }

    @Override
    public boolean delete(Long paymentId) {
        repository.deleteById(paymentId);
        return true;
    }

    @Override
    public List<Payment> getAll() {
        return repository.findAll();
    }
}
