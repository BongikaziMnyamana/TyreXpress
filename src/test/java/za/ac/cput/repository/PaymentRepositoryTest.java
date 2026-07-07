/* PaymentRepositoryTest.java
   Payment Repository Test class
   Author: Bongikazi Mnyamana (222718404)*/

package za.ac.cput.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.PaymentMethod;
import za.ac.cput.domain.PaymentStatus;
import za.ac.cput.factory.PaymentFactory;

class PaymentRepositoryTest {

    private IPaymentRepository repository;

    @BeforeEach
    void setUp() {
        repository = PaymentRepository.getRepository();
    }

    @Test
    void testCreate() {
        Payment payment = PaymentFactory.buildPayment(
                "PAY-001",
                1500.00,
                PaymentMethod.CARD,
                PaymentStatus.PAID,
                "order-001"
        );
        Payment created = repository.create(payment);
        System.out.println("Created: " + created);
    }

    @Test
    void testRead() {
        Payment payment = PaymentFactory.buildPayment(
                "PAY-002",
                500.00,
                PaymentMethod.EFT,
                PaymentStatus.PENDING,
                "order-002"
        );
        repository.create(payment);
        Payment read = repository.read("PAY-002");
        System.out.println("Read: " + read);
    }

    @Test
    void testUpdate() {
        Payment payment = PaymentFactory.buildPayment(
                "PAY-003",
                750.00,
                PaymentMethod.CASH,
                PaymentStatus.PENDING,
                "order-003"
        );
        repository.create(payment);

        Payment updated = new Payment.Builder()
                .copy(payment)
                .setStatus(PaymentStatus.PAID)
                .build();

        Payment result = repository.update(updated);
        System.out.println("Updated: " + result);
    }

    @Test
    void testDelete() {
        Payment payment = PaymentFactory.buildPayment(
                "PAY-004",
                250.00,
                PaymentMethod.CARD,
                PaymentStatus.PAID,
                "order-004"
        );
        repository.create(payment);
        boolean deleted = repository.delete("PAY-004");
        System.out.println("Deleted: " + deleted);
    }

    @Test
    void testGetAll() {
        Payment payment = PaymentFactory.buildPayment(
                "PAY-005",
                999.00,
                PaymentMethod.EFT,
                PaymentStatus.PAID,
                "order-005"
        );
        repository.create(payment);
        System.out.println("All payments: " + repository.getAll());
    }
}