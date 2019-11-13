package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderEntityTestSuite {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testSaveOrder() {
        //Given
        Order order = new Order();

        //When
        orderRepository.save(order);
        Optional<Order> testOrder = orderRepository.findById(order.getOrderId());

        //Then
        Assert.assertTrue(testOrder.isPresent());

        //CleanUp
        orderRepository.deleteById(order.getOrderId());
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
        orderRepository.deleteById(order.getOrderId());
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
        Assert.assertTrue(orders.stream().map(Order::getOrderId).anyMatch(n -> n.equals(order1.getOrderId())));
        Assert.assertTrue(orders.stream().map(Order::getOrderId).anyMatch(n -> n.equals(order2.getOrderId())));

        //CleanUp
        orderRepository.deleteById(order1.getOrderId());
        orderRepository.deleteById(order2.getOrderId());
    }

    @Test
    public void testDeleteOrderById() {
        //Given
        Order order = new Order();
        orderRepository.save(order);

        //When
        orderRepository.deleteById(order.getOrderId());

        //Then
        Assert.assertFalse(orderRepository.findById(order.getOrderId()).isPresent());
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
        userRepository.deleteById(user.getId());
    }

    @Test
    public void testProductRelation() {

        //Given
        Product product1 = new Product();
        Product product2 = new Product();

        Order order = new Order();
        Item item1 = new Item();
        Item item2 = new Item();

        item1.setProduct(product1);
        item1.setQuantity(3.0);

        item2.setProduct(product2);
        item2.setQuantity(2.0);

        item1.setOrder(order);
        item2.setOrder(order);

        product1.getItems().add(item1);
        product2.getItems().add(item2);

        order.getItems().add(item1);
        order.getItems().add(item2);

        productRepository.save(product1);
        productRepository.save(product2);
        itemRepository.save(item1);
        itemRepository.save(item2);
        orderRepository.save(order);


        //When
        Optional<Order> testOrder = orderRepository.findById(order.getOrderId());
        Optional<Item> testItem = itemRepository.findById(item1.getId());

        List<Item> items = testOrder.get().getItems();
        Long orderId = testItem.get().getOrder().getOrderId();

        //Then
        Assert.assertEquals(2, items.size());
        Assert.assertEquals(order.getOrderId(), orderId);

        //CleanUp
        itemRepository.deleteById(item1.getId());
        itemRepository.deleteById(item2.getId());
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
        orderRepository.deleteById(order.getOrderId());
    }
}
