/* PaymentRepository.java
   Payment Repository Implementation
   Author: Bongikazi Mnyamana (222718404)*/

package za.ac.cput.repository;

import za.ac.cput.domain.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements IPaymentRepository {

    private static IPaymentRepository repository = null;
    private final List<Payment> paymentList;

    private PaymentRepository() {
        paymentList = new ArrayList<>();
    }

    public static IPaymentRepository getRepository() {
        if (repository == null) {
            repository = new PaymentRepository();
        }
        return repository;
    }

    @Override
    public Payment create(Payment payment) {
        boolean success = paymentList.add(payment);
        if (success) {
            return payment;
        }
        return null;
    }

    @Override
    public Payment read(String paymentID) {
        for (Payment payment : paymentList) {
            if (payment.getPaymentID().equals(paymentID)) {
                return payment;
            }
        }
        return null;
    }

    @Override
    public Payment update(Payment payment) {
        String id = payment.getPaymentID();
        Payment oldPayment = read(id);

        if (oldPayment == null) {
            return null;
        }
        boolean success = paymentList.remove(oldPayment);
        if (!success) {
            return payment;
        }
        if (paymentList.add(payment)) {
            return payment;
        }
        return null;
    }

    @Override
    public boolean delee(String paymentID) {
        Payment paymentToDelete = read(paymentID);
        if (paymentToDelete == null) {
            return false;
        }
        return paymentList.remove(paymentToDelete);
    }

    @Override
    public List<Payment> getAll() {
        return new ArrayList<>(paymentList);
    }
}