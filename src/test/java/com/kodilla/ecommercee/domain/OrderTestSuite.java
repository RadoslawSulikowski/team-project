package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
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
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

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
        userRepository.save(user);
        orderRepository.save(order);

        //When
        Optional<Order> testOrder = orderRepository.findById(order.getOrderId());
        Optional<User> testUser = userRepository.findById(user.getId());
        Long userId = testOrder.get().getUser().getId();

        //Then
        Assert.assertEquals(user.getId(), userId);
        Assert.assertEquals(1, testUser.get().getOrders().size());

        //CleanUp
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void testProductRelation() {
        //Given
        Product product1 = new Product();
        Product product2 = new Product();
        Order order = new Order();
        order.getProducts().add(product1);
        order.getProducts().add(product2);
        product1.setOrder(order);
        product2.setOrder(order);
        orderRepository.save(order);
        productRepository.save(product1);
        productRepository.save(product2);

        //When
        Optional<Order> testOrder = orderRepository.findById(order.getOrderId());
        Optional<Product> testProduct = productRepository.findById(product1.getId());
        List<Product> products = testOrder.get().getProducts();
        Long orderId = testProduct.get().getOrder().getOrderId();

        //Then
        Assert.assertEquals(2, products.size());
        Assert.assertEquals(order.getOrderId(), orderId);

        //CleanUp
        productRepository.deleteAll();
        orderRepository.deleteAll();
    }
}
