package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public String createUser(@RequestBody UserDto userDto) {
        return "Create new user: " +userDto.getId() +" "+userDto.getUsername();

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "blockUser")
    public String blockUser(@RequestParam String getUsername) {
        return getUsername + " is blocked";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "oneHourUserKey")
    public Long oneHourUserKey(@RequestBody UserDto userDto) {
        return userDto.getUserKey();

    }
}
