package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderLineFatcory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static za.ac.cput.domain.PaymentStatus.PENDING;

class OrderServiceTest {
 private OrderService service;
    private static Order order;
    private static Long generatedId;

    @Test
    void create() {
     Order newOrder = OrderFactory.createOrder(null,
             1200.00,
             LocalDate.of(2026, 8, 25),
             OrderStatus.PENDING,
             12L,
             20L,
             7L);
            Order created = service.create(newOrder);
            assertNotNull(created);
        assertNotNull(created.getOrderId());

        generatedId = created.getOrderId();
        order = created;
        System.out.println("Created Order: " + created.toString());
    }

    @Test
    void read() {
        Order found = service.read(order.getOrderId());
        assertNotNull(found);
        assertEquals(generatedId, found.getOrderId());
        System.out.println("Read Order ID: " + found);
        System.out.println("Read Order : " + found.toString());
    }

    @Test
    void update() {
        Order newOrder = new Order.Builder().copy(order).
                setStatus(OrderStatus.DELIVERED).build();

        Order updated = service.update(newOrder);
        assertNotNull(updated);
        assertEquals(PENDING,updated.getOrderId());
        System.out.println("Update Order: " + updated);

    }

    @Test
    @Disabled
    void delete() {
        boolean delete = service.delete(order.getOrderId());
        assertNotNull(delete);
    }

    @Test
    void getAll() {
        List<Order> orderList = service.getAll();
        assertNotNull(orderList);
        System.out.println("All orders: " + service.getAll());
    }
}