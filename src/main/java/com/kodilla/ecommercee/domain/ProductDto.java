package com.kodilla.ecommercee.domain;

public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private Long price;
    private Long groudId;

    public ProductDto(Long productId, String name, String description, Long price, Long groudId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.groudId = groudId;
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

    public Long getPrice() {
        return price;
    }

    public Long getGroudId() {
        return groudId;
    }
}
