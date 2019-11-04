package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEntityTestSuite {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testSaveUser() {
        //Given
        User user = new User("name", "status", 1L);

        //When
        Assert.assertNull(user.getId());
        userRepository.save(user);
        Assert.assertNotNull(user.getId());
        long userId = user.getId();

        //Then
        Assert.assertTrue(userRepository.existsById(userId));

        //Clean up DB
        userRepository.deleteById(userId);
    }

    @Test
    public void testDeleteUser() {
        //Given
        User user = new User("name", "status", 1L);

        //When
        userRepository.save(user);
        long userId = user.getId();
        userRepository.deleteById(userId);

        //Then
        Assert.assertFalse(userRepository.existsById(userId));

        //Clean up DB
    }

    @Test
    public void testUserGetUsername() {
        //Given
        User user = new User("name", "status", 1L);

        //When
        userRepository.save(user);
        long userId = user.getId();
        Optional<User> optionalUser = userRepository.findById(userId);
        User resultUser = optionalUser.get();
        String userName = resultUser.getUsername();

        //Then
        Assert.assertEquals("name", userName);

        //Clean up DB
        userRepository.deleteById(userId);
    }

    @Test
    public void testUserGetStatus() {
        //Given
        User user = new User("name", "status", 1L);

        //When
        userRepository.save(user);
        long userId = user.getId();
        Optional<User> optionalUser = userRepository.findById(userId);
        User resultUser = optionalUser.get();
        String userStatus = resultUser.getStatus();

        //Then
        Assert.assertEquals("status", userStatus);

        //Clean up DB
        userRepository.deleteById(userId);
    }

    @Test
    public void testUserGetKey() {
        //Given
        User user = new User("name", "status", 1L);

        //When
        userRepository.save(user);
        long userId = user.getId();
        Optional<User> optionalUser = userRepository.findById(userId);
        User resultUser = optionalUser.get();
        Long userKey = resultUser.getUserKey();

        //Then
        Assert.assertEquals((Long) 1L, userKey);

        //Clean up DB
        userRepository.deleteById(userId);
    }

    @Test
    public void testCartCreationWithUser() {
        //Given
        User user = new User("name", "status", 1L);

        //When

        userRepository.save(user);
        Cart cart = user.getCart();
        long userId = user.getId();
        long cartId = cart.getCartId();

        //Then
        System.out.println(cartRepository.count());
        Assert.assertNotEquals(0, cartId);

        //Clean up DB
        userRepository.deleteById(userId);
    }
}
