package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    private List<ProductDto> products = new ArrayList<>();

    public Cart() {
    }

    public Long getCartId() {
        return cartId;
    }

    public List<ProductDto> getProducts() {
        return products;
    }
}
