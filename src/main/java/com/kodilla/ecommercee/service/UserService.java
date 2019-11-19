package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String MESSAGE = "No User with id ";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public User blockUser(final Long id) throws UserNotFoundException{
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            user.setStatus("blocked");
            return userRepository.save(blockUser(id));
        } else {
            LOGGER.error(MESSAGE + id + " to block.");
            throw new UserNotFoundException(MESSAGE + id + " to block.");
        }
    }

    public Long oneHourUserKey(final User user) throws UserNotFoundException{
        if (userRepository.existsById(user.getId())) {
            Long userKey = generateUserKey();
            user.setUserKey(userKey);
            userRepository.save(user);
            resetUserKeyAfterOneHour(user);
            return userKey;
        } else {
            LOGGER.error(MESSAGE + user.getId());
            throw new UserNotFoundException(MESSAGE + user.getId());
        }
    }

    private Long generateUserKey(){
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
                    }
                },
                3600000
        );
    }
}