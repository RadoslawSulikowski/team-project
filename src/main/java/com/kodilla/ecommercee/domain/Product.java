package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;


    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product() {

    }


    public Long getId() {
        return id;
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

    public Group getGroup() {
        return group;
    }

    public Order getOrder() {
        return order;
    }

    public Cart getCart() {
      return cart;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
