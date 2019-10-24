package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers() {
        return new ArrayList<>();
    }

    public UserDto getUser(int userId) {
        return new UserDto(1L,"firstname", "lastname");
    }

    public void blockUser(UserDto userDto) {

    }

    public void oneHourAPI(UserDto userDto) {

    }
}
