package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/group")
public class GroupController {
    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<GroupDto> getGroups(){
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroup(@RequestParam Long groupId){
        return new GroupDto(1l, "Ubrania");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto group){
        System.out.println("Creating new group");
    }
  
    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup", consumes = APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto group){
        return new GroupDto(1l, "ubrania dla dzieci");
    }
}
