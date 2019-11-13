package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "CARTS")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @OneToMany(
            targetEntity = Item.class,
            mappedBy = "cart",
            cascade = ALL,
            fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    @OneToOne(cascade = {PERSIST, REFRESH, MERGE}, fetch = FetchType.EAGER)
    private User user;
}
