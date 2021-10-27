package com.example.coffeeshop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @Getter
    private Customer customer;
    @Getter
    private String productName;

    protected Order() {
    }

    public Order(Customer customer, String productName) {
        this.customer = customer;
        this.productName = productName;
    }

    @Override
    public String toString() {
        return String.format("%s%d ordered by %s", productName, orderId, customer.toString());
    }
}
