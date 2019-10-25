package com.kodilla.ecommercee.domain;

import java.util.List;

public class Cart {
    private Long cartId;
    private List<Product> items;

    public Cart(Long cardId, List<Product> items) {
        this.cartId = cardId;
        this.items = items;
    }

    public Cart() {
    }

    public Long getCardId() {
        return cartId;
    }

    public List<Product> getItems() {
        return items;
    }
}
