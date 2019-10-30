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
    private Long groupId;

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

   /* @OneToOne(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;*/

   /* @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orders;*/

    /*
    public Group getGroup() {
        return group;
     }

    public Order getOrder() {
        return orders;
      }

      public void setGroup(Group group) {
        this.group = group;
      }

      public void setOrder(Order orders) {
        this.orders = orders;
      }

     */


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getGroupId() {
        return groupId;
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

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
