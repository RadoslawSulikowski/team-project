package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.GenericEntity;

import javax.persistence.Entity;

@Entity(name = "products")
public class Product extends GenericEntity {
    public Product(String value) {
        super(value);
    }

    public Product() {
    }

    public Long getId() {
        return super.getId();
    }

    public String getValue() {
        return super.getValue();
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id: " + super.getId() + ", " +
                "Value: " + super.getValue() +
                "}";
    }
}
