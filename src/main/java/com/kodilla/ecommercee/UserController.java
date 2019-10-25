package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public String createUser(@RequestBody UserDto userDto) {
        return "Create new user: " +userDto.getId() +" "+userDto.getUsername();

    }

    @RequestMapping(method = RequestMethod.GET, value = "blockUser")
    public String blockUser(@RequestParam String getUsername) {
        return getUsername + " blocked";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "oneHourAPI")
    public UserDto oneHourAPI(@RequestBody UserDto userDto) {
        return userDto;

    }
}
