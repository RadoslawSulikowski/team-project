package com.kodilla.ecommercee.domain;

import java.util.List;

public class CartDto {
    private Long cartId;
    private List<ProductDto> items;

    public CartDto(Long cartId, List<ProductDto> items) {
        this.cartId = cartId;
        this.items = items;
    }

    public Long getCartId() {
        return cartId;
    }

    public List<ProductDto> getItems() {
        return items;
    }
}
