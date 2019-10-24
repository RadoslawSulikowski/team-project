package com.kodilla.ecommercee;

public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;

    public UserDto(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}

