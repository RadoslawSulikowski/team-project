package com.kodilla.ecommercee.domain;

public class UserDto {
    private Integer id;
    private String username;
    private Integer status;
    private Long userKey;

    public UserDto(Integer id, String username, Integer status, Long userKey) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }
    public UserDto(){

    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getUserKey() {
        return userKey;
    }
}

