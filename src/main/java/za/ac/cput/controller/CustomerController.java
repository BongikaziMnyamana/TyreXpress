package za.ac.cput.controller;

import za.ac.cput.domain.*;
import za.ac.cput.service.*;
import java.util.*;

public class CustomerController {
    private static CustomerController controller = null;
    private ICustomerService service;

    private CustomerController() {
        service = CustomerService.getService();
    }
    public static CustomerController getController() {
        if (controller == null) {
            controller = new CustomerController();
        }
        return controller;
    }

    public Customer create(Customer customer) {
        return service.create(customer);
    }

    public Customer read(String customerId) {
        return service.read(customerId);
    }

    public Customer update(Customer customer) {
        return service.update(customer);
    }

    public boolean delete(String customerId) {
        return service.delete(customerId);
    }

    public List<Customer> getAll() {
        return service.getAll();
    }
}
