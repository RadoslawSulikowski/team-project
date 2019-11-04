package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuite {
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void testSaveOrder() {
        //Given
        Order order = new Order();

        //When
        orderRepository.save(order);

        //Then
        Assert.assertEquals(1, orderRepository.count());

        //CleanUp
        orderRepository.deleteAll();
    }

    @Test
    public void testFindOrderById() {
        //Given
        Order order = new Order();
        orderRepository.save(order);

        //When
        Optional<Order> testOrder = orderRepository.findById(order.getOrderId());

        //Then
        Assert.assertTrue(testOrder.isPresent());

        //CleanUp
        orderRepository.deleteAll();
    }

    @Test
    public void testFindAllOrders() {
        //Given
        Order order1 = new Order();
        Order order2 = new Order();
        orderRepository.save(order1);
        orderRepository.save(order2);

        //When
        List<Order> orders = orderRepository.findAll();

        //Then
        Assert.assertEquals(2, orders.size());

        //CleanUp
        orderRepository.deleteAll();
    }

    @Test
    public void testDeleteOrderById() {
        //Given
        Order order = new Order();
        orderRepository.save(order);

        //When
        orderRepository.deleteById(order.getOrderId());

        //Then
        Assert.assertEquals(0, orderRepository.count());

        //CleanUp
        orderRepository.deleteAll();
    }

    @Test
    public void testUserRelation() {
        //Given
        User user = new User();
        Order order = new Order();
        order.setUser(user);
        user.getOrders().add(order);

        //When
        Integer userId = order.getUser().getId();

        //Then
        Assert.assertEquals(user.getId(), userId);
        Assert.assertEquals(1, user.getOrders().size());
    }
}
