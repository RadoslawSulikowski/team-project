package com.kodilla.ecommercee.domain;

import java.util.List;

public class CartDto {
    private Long cartId;
    private List<String> items;

    public CartDto(Long cartId, List<String> items) {
        this.cartId = cartId;
        this.items = items;
    }

    public Long getCartId() {
        return cartId;
    }

    public List<String> getItems() {
        return items;
    }
}
