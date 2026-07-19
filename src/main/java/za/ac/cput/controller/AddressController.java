package za.ac.cput.controller;

import za.ac.cput.domain.*;
import za.ac.cput.service.*;
import java.util.*;

public class AddressController {
    private static AddressController controller = null;
    private IAddressService service;

    private AddressController() {
        service = AddressService.getService();
    }
    public static AddressController getController() {
        if (controller == null) {
            controller = new AddressController();
        }
        return controller;
    }

    public Address create(Address address) {
        return service.create(address);
    }

    public Address read(String addressId) {
        return service.read(addressId);
    }

    public Address update(Address address) {
        return service.update(address);
    }

    public boolean delete(String userId) {
        return service.delete(userId);
    }

    public List<Address> getAll() {
        return service.getAll();
    }
}
