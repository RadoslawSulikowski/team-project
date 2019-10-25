package com.kodilla.ecommercee.domain;

import java.util.List;

public class Cart {
    private Long cartId;
    private List<String> items;

    public Cart(Long cardId, List<String> items) {
        this.cartId = cardId;
        this.items = items;
    }

    public Cart() {
    }

    public Long getCardId() {
        return cartId;
    }

    public List<String> getItems() {
        return items;
    }
}
