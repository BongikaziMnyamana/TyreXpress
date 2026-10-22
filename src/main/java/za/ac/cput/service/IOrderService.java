package za.ac.cput.service;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderStatus;

import java.util.List;

public interface IOrderService extends IService<Order, Long>{
    List<Order> getOrdersByCustomerId(String customerId);
    Order updateOrderStatus(Long orderId, OrderStatus status);
}
