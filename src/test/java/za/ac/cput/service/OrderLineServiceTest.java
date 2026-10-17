package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.OrderLine;
import za.ac.cput.factory.OrderLineFatcory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderLineServiceTest {
    @Autowired
    private OrderLineService service;
    private static OrderLine orderLine;
    private static Long generatedId;

    @Test
    void a_create() {
        OrderLine newOrderLine = OrderLineFatcory.createOrderLine(
                null,
                2,
                12.00,
                12.000,
                null);
        OrderLine created = service.create(newOrderLine);
        assertNotNull(created);
        assertNotNull(created.getOrderLineId());

        generatedId = created.getOrderLineId();
        orderLine = created;
        System.out.println("created OrderLine: " + orderLine.toString());
    }

    @Test
    void b_read() {
        OrderLine found = service.read(generatedId);
        assertNotNull(found);
        assertEquals(generatedId, found.getOrderLineId());
        System.out.println("Read OrderLine ID: " + found.getOrderLineId());
        System.out.println("Read OrderLine: " + found.toString());
    }

    @Test
    void c_update() {
        OrderLine updatedOrderL = new OrderLine.Builder().copy(orderLine)
                .setUnitPrice(270.00).build();

        OrderLine updated = service.update(updatedOrderL);

        assertNotNull(updated);
        assertEquals(270.00, updated.getUnitPrice());
        System.out.println("Updated OrderLine: " + updated.getOrderLineId() + ", new price: " + updated.getUnitPrice());
    }

    @Test
    @Disabled
    void d_delete() {
        boolean delete = service.delete(generatedId);
        assertTrue(delete);
    }

    @Test
    void e_getAll() {
        List<OrderLine> orderLineList = service.getAll();
        assertNotNull(orderLineList);
        System.out.println("All order lines: " + orderLineList);
    }
}