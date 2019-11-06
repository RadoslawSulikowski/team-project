package com.kodilla.ecommercee.domain;

import java.util.List;

public class CartDto {
    private Long cartId;
    private List<ProductDto> items;
    private Long userId;

    public CartDto(Long cartId, List<ProductDto> items, Long userId) {
        this.cartId = cartId;
        this.items = items;
        this.userId = userId;
    }

    public Long getCartId() {
        return cartId;
    }

    public List<ProductDto> getItems() {
        return items;
    }

    public Long getUserId() { return userId; }

    public void setCartId(Long cartId) { this.cartId = cartId; }

    public void setItems(List<ProductDto> items) { this.items = items; }

    public void setUserId(Long userId) { this.userId = userId; }
}
