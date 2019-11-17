package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(final Long id) throws GroupNotFoundException {
        if (groupRepository.findById(id).isPresent()) {
            return groupRepository.findById(id).get();
        } else {
            throw new GroupNotFoundException("No group with ID " + id);
        }
    }

    public void addGroup(final Group group) {
        groupRepository.save(group);
    }

    public Group updateGroup(final Group group) throws GroupNotFoundException {
        if (groupRepository.existsById(group.getId())) {
            return groupRepository.save(group);
        } else {
            throw new GroupNotFoundException("No group with ID " + group.getId() + " to update.");
        }
    }

    public void deleteGroup(final Long id) throws GroupNotFoundException {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
        } else {
            throw new GroupNotFoundException("No group with ID " + id + " to delete.");
        }
    }
}