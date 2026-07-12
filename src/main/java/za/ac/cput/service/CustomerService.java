package za.ac.cput.service;

import za.ac.cput.domain.*;
import za.ac.cput.repository.*;
import java.util.*;

public class CustomerService implements ICustomerService{

    private static CustomerService service = null;
    private ICustomer repository;

    private CustomerService() {
        repository = CustomerRepository.getRepository();
    }
    public static CustomerService getService() {
        if (service == null) {
            service = new CustomerService();
        }
        return service;
    }

    @Override
    public Customer create(Customer customer) {
        return null;
    }

    @Override
    public Customer read(String s) {
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<Customer> getAll() {
        return List.of();
    }
}
