package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String status;
    private Long userKey;
    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,fetch = FetchType.LAZY
    )
    private List<Order> orders = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cart cart;

    public User(String username, String status, Long userKey) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public Long getUserKey() {
        return userKey;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserKey(Long userKey) {
        this.userKey = userKey;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}