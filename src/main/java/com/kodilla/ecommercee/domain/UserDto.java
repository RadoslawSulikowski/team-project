package com.kodilla.ecommercee.domain;

public class UserDto {
    private Integer id;
    private String username;
    private String status;
    private Long userKey;

    public UserDto(Integer id, String username, String status, Long userKey) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }

    public UserDto() {

    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public Long getUserKey() {
        return userKey;
    }
}

