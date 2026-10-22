package za.ac.cput.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.*;
import za.ac.cput.repository.IOrderRepository;
import za.ac.cput.repository.IPaymentRepository;

import java.util.List;

@Service
public class OrderService implements IOrderService{

    private final IOrderRepository repository;
    private final IPaymentRepository paymentRepository;

    public OrderService(IOrderRepository repository, IPaymentRepository paymentRepository) {
        this.repository = repository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Order create(Order order) {
        if (order.getOrderLines() != null) {
            for (OrderLine line : order.getOrderLines()) {
                line.setOrder(order);
            }
        }

        return repository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order read(Long orderId) {
        Order order = repository.findById(orderId).orElse(null);

        if (order != null) {
            order.getOrderLines().size();
        }

        return order;
    }

    @Override
    public Order update(Order order) {
        return repository.save(order);
    }

    @Override
    public boolean delete(Long orderId) {
        if (repository.existsById(orderId)) {
            repository.deleteById(orderId);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAll() {
        List<Order> orders = repository.findAll();

        for (Order order : orders) {
            if (order.getOrderLines() != null) {
                order.getOrderLines().size();
            }
        }

        return orders;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrdersByCustomerId(String customerId) {
        List<Order> orders = repository.findByCustomerIdOrderByDateDesc(customerId);
        for (Order order : orders) {
            order.getOrderLines().size();
        }
        return orders;
    }


    @Override
    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Order status is required");
        }

        Order existingOrder = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (status == OrderStatus.SHIPPED || status == OrderStatus.DELIVERED) {
            if (existingOrder.getPaymentId() == null) {
                throw new IllegalStateException("Order has no linked payment");
            }

            Long paymentId = Long.valueOf(existingOrder.getPaymentId());

            Payment payment = paymentRepository.findById(paymentId)
                    .orElseThrow(() -> new RuntimeException("Payment not found"));

            if (!payment.getOrderID().equals(orderId.toString())) {
                throw new IllegalStateException("Payment does not belong to this order");
            }

            if (payment.getStatus() != PaymentStatus.PAID) {
                throw new IllegalStateException("Cannot ship or deliver an unpaid order");
            }
        }

        Order updatedOrder = new Order.Builder()
                .copy(existingOrder)
                .setStatus(status)
                .build();

        Order savedOrder = repository.save(updatedOrder);
        savedOrder.getOrderLines().size();

        return savedOrder;
    }


}
