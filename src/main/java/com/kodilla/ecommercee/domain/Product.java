package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "GROUP_ID")
    private Long groupId;

    @ManyToMany(
            cascade = ALL,
            mappedBy = "products",
            targetEntity = Order.class)
    private List<Order> orders = new ArrayList<>();

    @ManyToMany(
            cascade = {MERGE, PERSIST, REFRESH},
            mappedBy = "products",
            targetEntity = Group.class)
    private List<Group> groups = new ArrayList<>();

    @ManyToMany(
            cascade = ALL,
            mappedBy = "products",
            targetEntity = Cart.class)
    private List<Cart> carts = new ArrayList<>();

    public Product(String name, String description, BigDecimal price, Long groupId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.groupId = groupId;
    }
}
