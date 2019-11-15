package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {

    private Integer id;
    private String username;
    private String status;
    private Long userKey;
    private List<OrderDto> orders;
    private Long cartId;
}

