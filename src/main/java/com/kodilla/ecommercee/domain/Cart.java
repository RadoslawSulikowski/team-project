package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "cart",
            cascade = ALL,
            fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @OneToOne(cascade = {PERSIST, REFRESH, MERGE}, fetch = FetchType.EAGER)
    private User user;

    public Cart() {
    }


    public Long getCartId() {
        return cartId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public User getUser() {
        return user;
    }


    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setUser(User user){
        this.user = user;
    }
}
