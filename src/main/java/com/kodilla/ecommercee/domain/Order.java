
package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedQuery(
        name = "Order.retrieveOrdersByUserId",
        query = "FROM ORDERS WHERE user_id = :USER_ID"
        )
@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "JOIN_ORDER_PRODUCT",
            joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")}
    )
    private List<Product> products = new ArrayList<>();

    public Long getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public List<Product> getProduct() {
        return products;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setProduct(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", products=" + products +
                '}';
    }


}




