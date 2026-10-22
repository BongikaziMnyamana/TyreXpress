/* PaymentService.java
   Payment Service Implementation
   Author: Bongikazi Mnyamana (222718404)*/
package za.ac.cput.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;
import za.ac.cput.repository.IOrderRepository;
import za.ac.cput.repository.IPaymentRepository;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {

    private final IPaymentRepository repository;
    private final IOrderRepository orderRepository;

    public PaymentService(IPaymentRepository repository,
                          IOrderRepository orderRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
    }


    @Override
    @Transactional
    public Payment create(Payment payment) {
        if (payment.getOrderID() == null) {
            throw new IllegalArgumentException("Order ID is required");
        }

        Long orderId = Long.valueOf(payment.getOrderID());

        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Payment savedPayment = repository.save(payment);

        Order updatedOrder = new Order.Builder()
                .copy(existingOrder)
                .setPaymentId(savedPayment.getPaymentID().toString())
                .build();

        orderRepository.save(updatedOrder);

        return savedPayment;
    }


    @Override
    public Payment read(Long paymentId) {
        return repository.findById(paymentId).get();
    }

    @Override
    public Payment update(Payment payment) {
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