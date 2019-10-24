package com.kodilla.ecommercee;

public class UserDto {
    private Long id;
    private String username;
    private Integer status;
    private Long userKey;

    public UserDto(Long id, String username, Integer status, Long userKey) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }

    public Long getId() {
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

