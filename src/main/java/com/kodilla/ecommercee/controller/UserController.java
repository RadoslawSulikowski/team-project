package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public void createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "blockUser")
    public void blockUser(@RequestParam Long id) {
        try {
            userService.blockUser(id);
        } catch (UserNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "oneHourUserKey")
    public void oneHourUserKey(@RequestBody UserDto userDto) {
        try {
            userService.oneHourUserKey(userMapper.mapToUser(userDto));
        } catch (UserNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}