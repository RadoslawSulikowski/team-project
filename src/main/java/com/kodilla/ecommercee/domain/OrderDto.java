package com.kodilla.ecommercee.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private Long id;
    private List<ProductDto> products = new ArrayList<>();
    private Long userId;

    public OrderDto(Long id, List<ProductDto> products, Long userId) {
        this.id = id;
        this.products = products;
        this.userId = userId;
    }

    public OrderDto() {
    }

    public Long getUserId(){
        return userId;
    }
    public Long getId() {
        return id;
    }

    public List<ProductDto> getProducts() {
        return products;
    }
}
