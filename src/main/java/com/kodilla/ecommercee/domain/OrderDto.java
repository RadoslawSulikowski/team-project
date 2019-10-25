package com.kodilla.ecommercee.domain;

public class OrderDto {
    private Long orderId;
    private Cart cart;

    public OrderDto(Long orderId, Cart cart) {
        this.orderId = orderId;
        this.cart = cart;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Cart getCart() {
        return cart;
    }
}
