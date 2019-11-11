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
    private static final Long GROUP_ID = 1L;


    @Test
    public void givenProductRepository_testSaveAndFindByIdMethods() {
        //Given
        Product product = productRepository.save(new Product(NAME, DESCRIPTION, PRICE, GROUP_ID));

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
        Product product = productRepository.save(new Product(NAME, DESCRIPTION, PRICE, GROUP_ID));

        //When
        productRepository.deleteById(product.getId());

        //Then
        assertFalse(productRepository.findById(product.getId()).isPresent());

        //CleanUp
    }

    @Test
    public void shouldSaveGroupAfterProductDeletion() {
        //Given
        Group group = new Group("ubrania");
        Product product = new Product(NAME, DESCRIPTION, PRICE, GROUP_ID);
        Product product1 = new Product(NAME, DESCRIPTION, PRICE, GROUP_ID);

        group.getProducts().add(product);
        group.getProducts().add(product1);

        product.getGroups().add(group);
        product1.getGroups().add(group);

        //When
        productRepository.save(product);
        productRepository.save(product1);
        groupRepository.save(group);

        //Then
        productRepository.delete(product);
        productRepository.delete(product1);
        group.getProducts().clear();

        assertTrue(groupRepository.existsById(group.getId()));
        assertFalse(productRepository.existsById(product.getId()));
        assertFalse(productRepository.existsById(product1.getId()));

        //CleanUp
        groupRepository.deleteById(group.getId());
    }

    @Test
    public void shouldSaveCartAfterProductDeletion() {
        //Given
        Cart cart = new Cart();
        Product product = new Product(NAME, DESCRIPTION, PRICE, GROUP_ID);
        Product product1 = new Product(NAME, DESCRIPTION, PRICE, GROUP_ID);

        cart.getProducts().add(product);
        cart.getProducts().add(product1);

        product.getCarts().add(cart);
        product1.getCarts().add(cart);


        //When
        productRepository.save(product);
        productRepository.save(product1);
        cartRepository.save(cart);

        //Then
        productRepository.delete(product);
        productRepository.delete(product1);
        cart.getProducts().clear();

        assertFalse(cartRepository.existsById(cart.getCartId()));
        assertFalse(productRepository.existsById(product.getId()));
        assertFalse(productRepository.existsById(product1.getId()));

        //CleanUp
    }

    @Test
    public void shouldSaveOrderAfterProductDeletion() {
        //Given
        Order order = new Order();
        Product product = new Product(NAME, DESCRIPTION, PRICE, GROUP_ID);
        Product product1 = new Product(NAME, DESCRIPTION, PRICE, GROUP_ID);

        order.getProducts().add(product);
        order.getProducts().add(product1);

        product.getOrders().add(order);
        product1.getOrders().add(order);

        //When
        productRepository.save(product);
        productRepository.save(product1);
        orderRepository.save(order);

        //Then
        productRepository.delete(product);
        productRepository.delete(product1);
        order.getProducts().clear();

        assertFalse(orderRepository.existsById(order.getOrderId()));
        assertFalse(productRepository.existsById(product.getId()));
        assertFalse(productRepository.existsById(product1.getId()));

        //CleanUp
    }
}
