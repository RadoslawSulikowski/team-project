package com.kodilla.ecommercee.domain;

public class Order {
    private Long orderId;
    private Cart cart;

    public Order(Long orderId, Cart cart) {
        this.orderId = orderId;
        this.cart = cart;
    }

    public Order() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public Cart getCart() {
        return cart;
    }
}
