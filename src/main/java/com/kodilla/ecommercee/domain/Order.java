package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products = new ArrayList<>();


    public Order() {
    }


    public Long getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProduct() {
        return products;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setProduct(List<Product> products) {
        this.products = products;
    }
}