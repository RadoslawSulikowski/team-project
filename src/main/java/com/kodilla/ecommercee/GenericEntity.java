package com.kodilla.ecommercee;

import javax.persistence.*;

@Entity
public class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "value")
    private String value;

    public GenericEntity() {}

    public GenericEntity(String value) { this.value = value; }

    public Long getId() { return id; }

    public String getValue() {
        return value;
    }

}
