package com.kodilla.ecommercee.domain;

public class ProductDto {
    private Long productId;
    private String name;

    public ProductDto(Long productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }
}
