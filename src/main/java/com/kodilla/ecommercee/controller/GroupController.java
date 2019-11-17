package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/group")
public class GroupController {
    private final static Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
    @Autowired
    GroupService groupService;

    @Autowired
    GroupMapper groupMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<GroupDto> getGroups() {
        return groupMapper.mapToGroupDtoList(groupService.getAllGroups());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroup(@RequestParam Long groupId) {
        try {
            return groupMapper.mapToGroupDto(groupService.getGroupById(groupId));
        } catch(GroupNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return new GroupDto();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "addGroup", consumes = APPLICATION_JSON_VALUE)
    public void addGroup(@RequestBody GroupDto groupDto) {
        groupService.addGroup(groupMapper.mapToGroup(groupDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup", consumes = APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        try {
            return groupMapper.mapToGroupDto(groupService.updateGroup(groupMapper.mapToGroup(groupDto)));
        } catch(GroupNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            return groupDto;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteGroup")
    public void deleteGroup(@RequestParam Long id) {
        try {
            groupService.deleteGroup(id);
        } catch(GroupNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}