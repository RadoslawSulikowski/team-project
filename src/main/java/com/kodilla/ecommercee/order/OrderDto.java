package com.kodilla.ecommercee.order;

public class OrderDto {
    private Long id;
    private String products;

    public OrderDto(Long id, String products) {
        this.id = id;
        this.products = products;
    }
    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public String getProducts() {
        return products;
    }
}
