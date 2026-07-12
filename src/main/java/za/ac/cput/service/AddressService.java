package za.ac.cput.service;

import za.ac.cput.domain.Address;
import za.ac.cput.repository.AddressRepository;
import za.ac.cput.repository.*;
import java.util.*;

public class AddressService implements IAddressService{

    private static AddressService service = null;
    private IAddress repository;

    private AddressService() {
        repository = AddressRepository.getRepository();
    }
    public static AddressService getService() {
        if (service == null) {
            service = new AddressService();
        }
        return service;
    }

    @Override
    public Address create(Address address) {
        return null;
    }

    @Override
    public Address read(String s) {
        return null;
    }

    @Override
    public Address update(Address address) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<Address> getAll() {
        return List.of();
    }
}
