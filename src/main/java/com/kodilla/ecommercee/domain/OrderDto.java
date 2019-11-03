package com.kodilla.ecommercee.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private Long id;
    private List<ProductDto> products = new ArrayList<>();//Before final implementation change to List<ProductDto>

    public OrderDto(Long id, List<ProductDto> products) {//Before final implementation change to List<ProductDto>
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
