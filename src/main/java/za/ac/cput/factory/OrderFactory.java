package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class OrderFactory {
    public static Order createOrder(Long orderId, double totalAmount, LocalDate date, OrderStatus status,
                                    Long customerId, Long addressId, Long paymentId) {
        if (orderId == null || addressId == null || paymentId == null) {
            return null;
        }
        if (totalAmount <= 0) {
            System.out.println("Amount must be greater than zero");
            return null;
        }
        return new Order.Builder().setOrderId(orderId).setTotalAmount(totalAmount).setDate(date).setStatus(status).
                setCustomerId(customerId).setAddressId(addressId).setPaymentId(paymentId).build();
    }

}
