package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserEntityTestSuite {
    private static final String TEST_USERNAME = "Test Username";
    private static final String TEST_USER_STATUS = "Test User status";
    private static final Long TEST_USER_KEY = 321L;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testUserAllSettersAndGetters() {
        //Given
        User user = new User();
        Cart testCart = new Cart();
        List<Order> testOrders = new ArrayList<>();

        //When
        user.setId(121L);
        user.setUsername(TEST_USERNAME);
        user.setStatus(TEST_USER_STATUS);
        user.setUserKey(TEST_USER_KEY);
        user.setCart(testCart);
        user.setOrders(testOrders);

        Long resultId = user.getId();
        String resultUsername = user.getUsername();
        String resultStatus = user.getStatus();
        Long resultKey = user.getUserKey();
        Cart resultCart = user.getCart();
        List<Order> resultOrders = user.getOrders();

        //Then
        assertEquals((Long) 121L, resultId);
        assertEquals(TEST_USERNAME, resultUsername);
        assertEquals(TEST_USER_STATUS, resultStatus);
        assertEquals(TEST_USER_KEY, resultKey);
        assertEquals(testCart, resultCart);
        assertEquals(testOrders, resultOrders);
    }

    @Test
    public void testSaveUser() {
        //Given
        User user = new User(TEST_USERNAME, TEST_USER_STATUS, TEST_USER_KEY);

        //When
        assertNull(user.getId());
        userRepository.save(user);
        assertNotNull(user.getId());
        long userId = user.getId();

        //Then
        assertTrue(userRepository.existsById(userId));

        //Clean up DB
        userRepository.deleteById(userId);
    }

    @Test
    public void testDeleteUser() {
        //Given
        User user = new User(TEST_USERNAME, TEST_USER_STATUS, TEST_USER_KEY);
        userRepository.save(user);
        long userId = user.getId();

        //When
        userRepository.deleteById(userId);

        //Then
        assertFalse(userRepository.existsById(userId));

        //Clean up DB
    }

    @Test
    public void testCartCreationWithUser() {
        //Given
        User user = new User(TEST_USERNAME, TEST_USER_STATUS, TEST_USER_KEY);
        userRepository.save(user);
        long userId = user.getId();

        //When
        Cart cart = user.getCart();
        long cartId = cart.getCartId();

        //Then
        assertTrue(cartRepository.existsById(cartId));

        //Clean up DB
        userRepository.deleteById(userId);
    }

    @Test
    public void testCartDeletionWithUserDeletion() {
        //Given
        User user = new User(TEST_USERNAME, TEST_USER_STATUS, TEST_USER_KEY);
        userRepository.save(user);
        Cart cart = user.getCart();
        long userId = user.getId();
        long cartId = cart.getCartId();

        //When
        userRepository.deleteById(userId);

        //Then
        assertFalse(cartRepository.existsById(cartId));

        //Clean up DB
    }

    @Test
    public void testUserRemainsAfterCartDeletion() {
        //Given
        User user = new User("name", "status", 1L);
        userRepository.save(user);
        Cart cart = user.getCart();
        long userId = user.getId();
        long cartId = cart.getCartId();

        //When
        cartRepository.deleteById(cartId);

        //Then
        assertTrue(userRepository.existsById(userId));

        //Clean up DB
        userRepository.deleteById(userId);
    }

    @Test
    public void testAddOrderToRepositoryViaUser() {
        //Given
        User user = new User(TEST_USERNAME, TEST_USER_STATUS, TEST_USER_KEY);
        Order order = new Order();
        userRepository.save(user);
        long userId = user.getId();

        //When
        user.getOrders().add(order);
        int orderIndex = user.getOrders().indexOf(order);
        userRepository.save(user);
        long orderId = user.getOrders().get(orderIndex).getOrderId();

        //Then
        assertTrue(orderRepository.existsById(orderId));

        //Clean up DB
        orderRepository.deleteById(orderId);
        userRepository.deleteById(userId);
    }

    @Test
    public void testUserRemainsAfterOrderDeletion() {
        //Given
        User user = new User(TEST_USERNAME, TEST_USER_STATUS, TEST_USER_KEY);
        Order order = new Order();
        userRepository.save(user);
        long userId = user.getId();
        user.getOrders().add(order);
        int orderIndex = user.getOrders().indexOf(order);
        userRepository.save(user);
        long orderId = user.getOrders().get(orderIndex).getOrderId();

        //When
        orderRepository.deleteById(orderId);

        //Then
        assertTrue(userRepository.existsById(userId));

        //Clean up DB
        userRepository.deleteById(userId);
    }

    @Test
    public void shouldRemoveOrderFromRepoAfterRemovingFromUserOrders() {
        //Given
        User user = new User(TEST_USERNAME, TEST_USER_STATUS, TEST_USER_KEY);
        Order order = new Order();
        userRepository.save(user);
        long userId = user.getId();
        user.getOrders().add(order);
        int orderIndex = user.getOrders().indexOf(order);
        userRepository.save(user);
        long orderId = user.getOrders().get(orderIndex).getOrderId();

        //When;
        assertTrue(orderRepository.existsById(orderId));
        user.getOrders().remove(orderRepository.findById(orderId).get());
        userRepository.save(user);

        //Then
        assertFalse(orderRepository.existsById(orderId));

        //Clean up DB
        userRepository.deleteById(userId);
    }

    @Test
    public void shouldRemoveOrderFromUserOrdersAfterDeletionOrderFromRepo() {
        //Given
        User user = new User(TEST_USERNAME, TEST_USER_STATUS, TEST_USER_KEY);
        Order order = new Order();
        userRepository.save(user);
        long userId = user.getId();
        user.getOrders().add(order);
        int orderIndex = user.getOrders().indexOf(order);
        userRepository.save(user);
        long orderId = user.getOrders().get(orderIndex).getOrderId();

        //When
        assertTrue(orderRepository.existsById(orderId));
        orderRepository.deleteById(orderId);
        assertFalse(orderRepository.existsById(orderId));

        //Then
        assertFalse(userRepository.findById(userId).get().getOrders().contains(order));

        //Clean up DB
        userRepository.deleteById(userId);
    }
}
