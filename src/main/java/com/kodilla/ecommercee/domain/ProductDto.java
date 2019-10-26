package com.kodilla.ecommercee.domain;

import java.math.BigDecimal;

public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Long groupId;

    public ProductDto(Long productId, String name, String description, BigDecimal price, Long groupId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.groupId = groupId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getGroupId() {
        return groupId;
    }
}
