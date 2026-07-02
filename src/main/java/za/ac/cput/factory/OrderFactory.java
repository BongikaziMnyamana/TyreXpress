package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class OrderFactory {
    public static Order createOrder(String orderID, double totalAmount, LocalDate date, OrderStatus status,
                                    String customerID, String addressID, String paymentID) {
        if (Helper.isNullOrEmpty(orderID) || Helper.isNullOrEmpty(addressID) || Helper.isNullOrEmpty(paymentID)) {
            return null;
        }
        if (totalAmount <= 0) {
            System.out.println("Amount must be greater than zero");
            return null;
        }
        return new Order.Builder().setOrderID(orderID).setTotalAmount(totalAmount).setDate(date).setStatus(status).
                setCustomerID(customerID).setAddressID(addressID).setPaymentID(paymentID).build();
    }

}
