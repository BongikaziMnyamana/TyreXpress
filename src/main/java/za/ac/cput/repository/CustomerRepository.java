package za.ac.cput.repository;

import za.ac.cput.domain.*;
import java.util.*;

public class CustomerRepository implements ICustomer{

    private static ICustomer repository= null;
    private List<Customer> customerList;
    private CustomerRepository(){
        customerList =new ArrayList<>();
    }
    public static ICustomer getRepository(){
        if(repository==null){
            repository=new CustomerRepository();
        }
        return repository;
    }

    @Override
    public Customer create(Customer customer) {
        boolean success = customerList.add(customer);
        if(success){
            return customer;
        }
        return null;
    }
    
    @Override
    public Customer read(Customer customer_id) {
        for(Customer customer:customerList){
            if (customer.getCustomer_Id().equals(customer_id)){
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        return customer;
    }

    @Override
    public boolean delete(Customer t) {
        return false;
    }

    @Override
    public List<Customer> getAll() {
        return customerList;
    }
}
