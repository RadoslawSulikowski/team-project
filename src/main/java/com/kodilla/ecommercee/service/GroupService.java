package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exceptions.GroupAlreadyExistsException;
import com.kodilla.ecommercee.exceptions.GroupCantBeDeletedException;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupService.class);
    private static final String MESSAGE = "No group with id: ";

    @Autowired
    GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(final Long id) throws GroupNotFoundException {
        if (groupRepository.findById(id).isPresent()) {
            return groupRepository.findById(id).get();
        } else {
            LOGGER.error(MESSAGE + id);
            throw new GroupNotFoundException(MESSAGE + id);
        }
    }

    public void addGroup(final Group group) throws GroupAlreadyExistsException {
        if (!groupRepository.findById(group.getId()).isPresent()) {
            LOGGER.info("Group was successful added with id " + groupRepository.save(group).getId());
        } else {
            LOGGER.error("Group with id " + group.getId() + " already exists");
            throw new GroupAlreadyExistsException();
        }
    }

    public Group updateGroup(final Group group) throws GroupNotFoundException {
        if (groupRepository.existsById(group.getId())) {
            LOGGER.info("Group with id " + group.getId() + " was successful updated.");
            return groupRepository.save(group);
        } else {
            LOGGER.error(MESSAGE + group.getId() + " to update.");
            throw new GroupNotFoundException(MESSAGE + group.getId() + " to update");
        }
    }

    public void deleteGroup(final Long id) throws GroupNotFoundException, GroupCantBeDeletedException{
        if (groupRepository.findById(id).isPresent()) {
            if(groupRepository.findById(id).get().getProducts().isEmpty()) {
                groupRepository.deleteById(id);
                LOGGER.info("Group with id " + id + " was successful deleted");
            } else {
                LOGGER.error("There are products belonging to group with id " + id + ". Group can't be deleted");
                throw new GroupCantBeDeletedException("There are products belonging to group with id " + id);
            }
        } else {
            LOGGER.error(MESSAGE + id + " to delete.");
            throw new GroupNotFoundException(MESSAGE + id + " to delete.");
        }
    }
}
