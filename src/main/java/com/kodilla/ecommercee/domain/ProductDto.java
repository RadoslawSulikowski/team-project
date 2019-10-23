package com.kodilla.ecommercee.domain;

public class ProductDto {
    private Long id;
    private String value;

    public ProductDto(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public ProductDto() {
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
