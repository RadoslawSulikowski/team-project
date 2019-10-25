package com.kodilla.ecommercee.domain;

public class Product {
    private Long productId;
    private String name;

    public Product(Long productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }
}
