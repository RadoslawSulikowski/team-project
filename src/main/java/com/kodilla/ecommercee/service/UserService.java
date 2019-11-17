package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public User blockUser(final Long id) throws UserNotFoundException {
        if (userRepository.existsById(id)) {
            return userRepository.save(blockUser(id));
        } else {
            throw new UserNotFoundException();
        }
    }

    public void oneHourUserKey(final User user) throws UserNotFoundException {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }
}