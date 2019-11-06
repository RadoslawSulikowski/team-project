package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ProductEntityTestSuite {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    private static final String NAME = "product";
    private static final String DESCRIPTION = "Pellentesque";
    private static final BigDecimal PRICE = new BigDecimal(500);

    @Test
    public void givenProductRepository_testSaveAndFindByIdMethods() {
        //Given
        Product product = productRepository.save(new Product(NAME, DESCRIPTION, PRICE));

        //When & Then
        Optional<Product> foundProduct = productRepository.findById(product.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals(product.getId(), foundProduct.get().getId());

        //CleanUp
        productRepository.deleteById(product.getId());
    }

    @Test
    public void givenProductRepository_TestDeleteMethod() {
        //Given
        Product product = productRepository.save(new Product(NAME, DESCRIPTION, PRICE));

        //When
        productRepository.deleteById(product.getId());

        //Then
        assertFalse(productRepository.findById(product.getId()).isPresent());

        //CleanUp
    }

    @Test
    public void shouldSaveGroupAfterProductDeletion() {
        //Given
        Group group =new Group("ubrania");
        Order order = new Order();
        Cart cart = new Cart();
        Product product = new Product(NAME, DESCRIPTION, PRICE);
        Product product1 = new Product(NAME, DESCRIPTION, PRICE);

        cart.getProducts().add(product);
        cart.getProducts().add(product1);
        order.getProducts().add(product);
        order.getProducts().add(product1);
        group.getProducts().add(product);
        group.getProducts().add(product1);

        product.setGroup(group);
        product1.setGroup(group);
        product.setOrder(order);
        product1.setOrder(order);
        product.setCart(cart);
        product1.setCart(cart);

        //When
        productRepository.save(product);
        productRepository.save(product1);
        cartRepository.save(cart);
        orderRepository.save(order);
        groupRepository.save(group);

        //Then
        productRepository.delete(product);
        productRepository.delete(product1);

        assertTrue(groupRepository.existsById(group.getId()));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());
        orderRepository.deleteById(order.getOrderId());
        groupRepository.deleteById(group.getId());    }

    @Test
    public void shouldSaveCartAfterProductDeletion() {
        //Given
        Group group =new Group("ubrania");
        Order order = new Order();
        Cart cart = new Cart();
        Product product = new Product(NAME, DESCRIPTION, PRICE);
        Product product1 = new Product(NAME, DESCRIPTION, PRICE);

        cart.getProducts().add(product);
        cart.getProducts().add(product1);
        order.getProducts().add(product);
        order.getProducts().add(product1);
        group.getProducts().add(product);
        group.getProducts().add(product1);

        product.setGroup(group);
        product1.setGroup(group);
        product.setOrder(order);
        product1.setOrder(order);
        product.setCart(cart);
        product1.setCart(cart);

        //When
        productRepository.save(product);
        productRepository.save(product1);
        cartRepository.save(cart);
        orderRepository.save(order);
        groupRepository.save(group);

        //Then
        productRepository.delete(product);
        productRepository.delete(product1);

        assertTrue(cartRepository.existsById(cart.getCartId()));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());
        orderRepository.deleteById(order.getOrderId());
        groupRepository.deleteById(group.getId());
    }

    @Test
    public void shouldSaveOrderAfterProductDeletion() {
        //Given
        Group group = new Group("ubrania");
        Order order = new Order();
        Cart cart = new Cart();
        Product product = new Product(NAME, DESCRIPTION, PRICE);
        Product product1 = new Product(NAME, DESCRIPTION, PRICE);

        cart.getProducts().add(product);
        cart.getProducts().add(product1);
        order.getProducts().add(product);
        order.getProducts().add(product1);
        group.getProducts().add(product);
        group.getProducts().add(product1);

        product.setGroup(group);
        product1.setGroup(group);
        product.setOrder(order);
        product1.setOrder(order);
        product.setCart(cart);
        product1.setCart(cart);

        //When
        productRepository.save(product);
        productRepository.save(product1);
        cartRepository.save(cart);
        orderRepository.save(order);
        groupRepository.save(group);

        //Then
        productRepository.delete(product);
        productRepository.delete(product1);

        assertTrue(orderRepository.existsById(order.getOrderId()));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());
        orderRepository.deleteById(order.getOrderId());
        groupRepository.deleteById(group.getId());
    }
}