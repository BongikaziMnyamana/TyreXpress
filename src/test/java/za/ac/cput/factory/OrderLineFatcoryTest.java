package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.OrderLine;

import static org.junit.jupiter.api.Assertions.*;

class OrderLineFatcoryTest {
    @Test
    void createOrderLine() {

        OrderLine orderLine = OrderLineFatcory.createOrderLine(
                11L,
                2,
                250.00,
                500.00,
                1L
        );

        System.out.println(orderLine);
    }

}