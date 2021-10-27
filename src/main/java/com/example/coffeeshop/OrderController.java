package com.example.coffeeshop;

import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public OrderController(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }


    @GetMapping("/order/all")
    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/order/from")
    public Iterable<Order> getFromCustomer(@RequestParam Long customerId) {
        return customerRepository.findByCustomerId(customerId).getOrders();
    }

    @GetMapping("/order/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @PostMapping("/order/place")
    public Order placeOrder(@RequestParam Long customerId, @RequestParam String productName) {
        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer == null) return null;

        return orderRepository.save(new Order(customer, productName));
    }

    @PostMapping("/order/fulfil")
    public boolean fulfilOrder(@RequestParam(value = "orderId") Long orderId) {
        if (!orderRepository.existsById(orderId)) return false;
        orderRepository.deleteById(orderId);
        return true;
    }
}
