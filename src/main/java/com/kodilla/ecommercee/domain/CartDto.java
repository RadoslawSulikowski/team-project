package com.kodilla.ecommercee.domain;

import java.util.List;

public class CartDto {
    private Long cartId;
    private List<Product> items;

    public CartDto(Long cartId, List<Product> items) {
        this.cartId = cartId;
        this.items = items;
    }

    public Long getCartId() {
        return cartId;
    }

    public List<Product> getItems() {
        return items;
    }
}
