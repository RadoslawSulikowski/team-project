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

    private static final String MESSAGE = "No User with id ";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public void saveUser(final User user) {
        userRepository.save(user);
    }

    public User blockUser(final Long id) throws UserNotFoundException {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            user.setStatus("blocked");
            return userRepository.save(user);
        } else {
            LOGGER.error(MESSAGE + id + " to block.");
            throw new UserNotFoundException(MESSAGE + id + " to block.");
        }
    }

    public Long oneHourUserKey(final Long userId) throws UserNotFoundException {
        if (userRepository.findById(userId).isPresent()) {
            User user = userRepository.findById(userId).get();
            Long userKey = generateUserKey();
            user.setUserKey(userKey);
            userRepository.save(user);
            resetUserKeyAfterOneHour(user);
            return userKey;
        } else {
            LOGGER.error(MESSAGE + userId + " to generate key.");
            throw new UserNotFoundException(MESSAGE + userId + " to generate key.");
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    private Long generateUserKey() {
        long leftLimit = 100000000L;
        long rightLimit = 1000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    private void resetUserKeyAfterOneHour(User user) {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        user.setUserKey(null);
                        userRepository.save(user);
                    }
                },
                3600000
        );
    }
}