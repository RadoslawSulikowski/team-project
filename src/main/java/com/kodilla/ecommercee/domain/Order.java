package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

//    @OneToMany(
//            targetEntity = Product.class,
//            mappedBy = "order",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private List<Product> products;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    public Order() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

//    public List<Product> getProduct() {
//        return products;
//    }
//
//    public void setProduct(List<Product> product) {
//        this.product = products;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
