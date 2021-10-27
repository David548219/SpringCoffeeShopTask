package com.example.coffeeshop;

import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer/all")
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{customerId}")
    public Customer getCustomer(@PathVariable Long customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    @PostMapping("/customer/register")
    public Customer registerCustomer(@RequestParam String firstName, @RequestParam String lastName) {
        return customerRepository.save(new Customer(firstName, lastName));
    }

}
