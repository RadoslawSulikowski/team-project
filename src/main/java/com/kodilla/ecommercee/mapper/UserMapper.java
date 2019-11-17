package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getStatus(),
                user.getUserKey(),
                orderMapper.mapToOrderDtoList(user.getOrders()),
                user.getCart());

    }

    public User mapToUser(final UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setStatus(userDto.getStatus());
        user.setUserKey(userDto.getUserKey());

        return user;
    }


    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public List<User> mapToUserList(final List<UserDto> userDtos) throws UserNotFoundException {
        List<User> users = new ArrayList<>();
        for (UserDto us : userDtos) {
            if (userRepository.findById(us.getId()).isPresent()) {
                users.add(userRepository.findById(us.getId()).get());
            } else {
                throw new UserNotFoundException();
            }
        }
        return users;
    }

}