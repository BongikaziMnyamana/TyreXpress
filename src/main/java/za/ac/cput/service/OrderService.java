package za.ac.cput.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.repository.IOrderRepository;

import java.util.List;

@Service
public class OrderService implements IOrderService{

    private IOrderRepository repository;
    public OrderService(IOrderRepository repository) {
        this.repository = repository;
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
    public List<Order> getAll() {
        return repository.findAll();
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

}
