package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDto {

    private Long id;
    private List<ProductDto> products = new ArrayList<>();
    private Long userId;


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
