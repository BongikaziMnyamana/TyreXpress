package za.ac.cput.domain;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private String orderId;
    private double totalAmount;
    private LocalDate date;
    private OrderStatus status;
    private String customerId;
    private String addressId;
    private String paymentId;

    // Composition → Order contains many OrderLines
    private List<OrderLine> orderLines;

    public Order() {

    }

    public Order(Builder builder) {
        this.orderId = builder.orderId;
        this.totalAmount = builder.totalAmount;
        this.date = builder.date;
        this.status = builder.status;
        this.customerId = builder.customerId;
        this.addressId = builder.addressId;
        this.paymentId = builder.paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderId + '\'' +
                ", totalAmount=" + totalAmount +
                ", date=" + date +
                ", status=" + status +
                ", customerID='" + customerId + '\'' +
                ", addressID='" + addressId + '\'' +
                ", paymentID='" + paymentId + '\'' +
                ", orderLines=" + orderLines +
                '}';
    }

    public static class Builder {
        private String orderId;
        private double totalAmount;
        private LocalDate date;
        private OrderStatus status;
        private String customerId;
        private String addressId;
        private String paymentId;

        public Builder setOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setAddressId(String addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder setPaymentId(String paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder copy(Order order) {
            this.orderId = order.orderId;
            this.totalAmount = order.totalAmount;
            this.date = order.date;
            this.status = order.status;
            this.customerId = order.customerId;
            this.addressId = order.addressId;
            this.paymentId = order.paymentId;
            return this;
        }
        public Order build(){
            return new Order(this);

        }
    }
}
