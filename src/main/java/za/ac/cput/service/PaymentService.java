/* PaymentService.java
   Payment Service Implementation
   Author: Bongikazi Mnyamana (222718404)
 */

package za.ac.cput.service;

import za.ac.cput.domain.Payment;
import za.ac.cput.repository.IPaymentRepository;
import za.ac.cput.repository.PaymentRepository;

import java.util.List;

public class PaymentService implements IPaymentService {

    private static PaymentService service = null;
    private IPaymentRepository repository;

    private PaymentService() {
        repository = PaymentRepository.getRepository();
    }

    public static PaymentService getService() {
        if (service == null) {
            service = new PaymentService();
        }
        return service;
    }

    @Override
    public Payment create(Payment payment) {
        return repository.create(payment);
    }

    @Override
    public Payment read(String paymentID) {
        return repository.read(paymentID);
    }

    @Override
    public Payment update(Payment payment) {
        return repository.update(payment);
    }

    @Override
    public boolean delete(String paymentID) {
        return repository.delete(paymentID);
    }

    @Override
    public List<Payment> getAll() {
        return repository.getAll();
    }
}