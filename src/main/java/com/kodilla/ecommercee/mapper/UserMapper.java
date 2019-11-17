package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
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
    UserRepository userRepository;

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getStatus(),
                user.getUserKey());

    }

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getStatus(),
                userDto.getUserKey());
    }


    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(n -> new UserDto(n.getId(), n.getUsername(), n.getStatus(), n.getUserKey()))
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