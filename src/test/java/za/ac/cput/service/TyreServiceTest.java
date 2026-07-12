package za.ac.cput.service;

import za.ac.cput.domain.Tyre;
import za.ac.cput.factory.TyreFactory;

public class TyreServiceTest {

    public static void main(String[] args) {

        TyreServiceImpl service = TyreServiceImpl.getService();

        Tyre tyre =  TyreFactory.createTyre(
                "P001",
                "Pilot Sport 5",
                "Michelin",
                2899.99,
                10,
                "225/45R17",
                "Summer",
                "94",
                "W",
                true
        );

        service.create(tyre);

        System.out.println("Create:");
        System.out.println(tyre);

        System.out.println("\nRead:");
        System.out.println(service.read("P001"));

        Tyre updatedTyre = TyreFactory.createTyre(
                "P001",
                "Pilot Sport 5",
                "Michelin",
                3100.00,
                10,
                "225/45R17",
                "Summer",
                "94",
                "W",
                true
        );

        service.update(updatedTyre);

        System.out.println("\nUpdated:");
        System.out.println(service.read("P001"));

        System.out.println("\nGet All:");
        System.out.println(service.getAll());

        service.delete("P001");

        System.out.println("\nAfter Delete:");
        System.out.println(service.getAll());
    }
}