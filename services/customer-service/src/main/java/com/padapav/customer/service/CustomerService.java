package com.padapav.customer.service;

import com.padapav.customer.model.Address;
import com.padapav.customer.model.Customer;
import com.padapav.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCurrentCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFullName("Demo Customer");
        customer.setPhone("+91 98765 43210");
        customer.setEmail("customer@padapav.example");
        return customer;
    }

    public Address addAddress(Address address) {
        return address;
    }

    public List<Address> getCurrentCustomerAddresses() {
        return List.of();
    }
}
