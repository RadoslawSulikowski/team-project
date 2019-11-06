package com.kodilla.ecommercee.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private Long id;
    private List<ProductDto> products = new ArrayList<>();

    public OrderDto(Long id, List<ProductDto> products) {
        this.id = id;
        this.products = products;
    }

    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public List<ProductDto> getProducts() {
        return products;
    }
}
