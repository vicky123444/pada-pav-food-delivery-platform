package com.padapav.customer.controller;

import com.padapav.customer.model.Customer;
import com.padapav.customer.model.Address;
import com.padapav.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/me")
    public Customer getCurrentCustomer() {
        return customerService.getCurrentCustomer();
    }

    @PostMapping("/addresses")
    public ResponseEntity<Address> addAddress(@Valid @RequestBody Address address) {
        Address saved = customerService.addAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/me/addresses")
    public List<Address> getAddresses() {
        return customerService.getCurrentCustomerAddresses();
    }
}
