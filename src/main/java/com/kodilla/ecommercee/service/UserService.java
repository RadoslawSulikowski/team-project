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

    public User blockUser(final Long id){
            userRepository.findById(id).isPresent();
            User user = new User();
            user.setStatus(userRepository.findById(id).get().getStatus());
            return userRepository.save(user);

    }

    private Long generateUserKey() {
        long leftLimit = 1001L;
        long rightLimit = 10001L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
    public void oneHourUserKey(User user) {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        user.setUserKey(null);
                    }
                },
                1234567
        );
        Long userKey = generateUserKey();
        user.setUserKey(userKey);
        userRepository.save(user);
        oneHourUserKey(user);
    }
}