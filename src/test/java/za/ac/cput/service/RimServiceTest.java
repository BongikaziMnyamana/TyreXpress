package za.ac.cput.service;

import za.ac.cput.domain.Material;
import za.ac.cput.domain.Rim;
import za.ac.cput.factory.RimFactory;

public class RimServiceTest {

    public static void main (String[] args) {

        RimServiceImpl service = RimServiceImpl.getService();

        Rim rim = RimFactory.createRim(
                "P002",
                "RS Alloy",
                "BBS",
                3999.99,
                8,
                18.0,
                8.5,
                "5x112",
                35,
                Material.ALLOY,
                "Black"
        );

        service.create(rim);

        System.out.println("Create:");
        System.out.println(rim);

        System.out.println("\nRead:");
        System.out.println(service.read("P002"));

        Rim updatedRim = RimFactory.createRim(
                "P002",
                "RS Alloy",
                "BBS",
                4200.00,
                8,
                18.0,
                8.5,
                "5x112",
                35,
                Material.ALLOY,
                "Black"
        );

        service.update(updatedRim);

        System.out.println("\nUpdated:");
        System.out.println(service.read("P002"));

        System.out.println("\nGet All:");
        System.out.println(service.getAll());

        service.delete("P002");

        System.out.println("\nAfter Delete:");
        System.out.println(service.getAll());
    }
}