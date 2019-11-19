package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public User blockUser(final Long id) {
        if (userRepository.existsById(id)) {
            User user = userRepository.findById(id).get();
            user.setStatus("zablokowany");
        }
        return userRepository.save(blockUser(id));

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

    public Long oneHourUserKey(final User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
            Long userKey = generateUserKey();
            user.setUserKey(userKey);
            userRepository.save(user);
            resetUserKeyAfterOneHour(user);
            return userKey;

        }
        return null;
    }
}