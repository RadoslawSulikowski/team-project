package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
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

    @Autowired
    ItemRepository itemRepository;

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
        Group group = new Group("ubrania");
        Product product = new Product(NAME, DESCRIPTION, PRICE);
        Product product1 = new Product(NAME, DESCRIPTION, PRICE);

        group.getProducts().add(product);
        group.getProducts().add(product1);

        product.setGroup(group);
        product1.setGroup(group);

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
        Product product = new Product(NAME, DESCRIPTION, PRICE);
        Product product1 = new Product(NAME, DESCRIPTION, PRICE);

        cart.getProducts().add(product);
        cart.getProducts().add(product1);

        product.setCart(cart);
        product1.setCart(cart);


        //When
        productRepository.save(product);
        productRepository.save(product1);
        cartRepository.save(cart);

        //Then
        productRepository.delete(product);
        productRepository.delete(product1);
        cart.getProducts().clear();

        assertTrue(cartRepository.existsById(cart.getCartId()));
        assertFalse(productRepository.existsById(product.getId()));
        assertFalse(productRepository.existsById(product1.getId()));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());
    }

    @Test
    public void shouldSaveOrderAfterProductDeletion() {
        //Given
        Order order = new Order();
        Product product = new Product(NAME, DESCRIPTION, PRICE);
        Product product1 = new Product(NAME, DESCRIPTION, PRICE);

        Item item1 = new Item();
        item1.setProduct(product);
        Item item2 = new Item();
        item2.setProduct(product1);

        product.setItem(item1);
        product1.setItem(item2);

        order.getItems().add(item1);
        order.getItems().add(item2);

        item1.setOrder(order);
        item2.setOrder(order);

        //When
        productRepository.save(product);
        productRepository.save(product1);

        itemRepository.save(item1);
        itemRepository.save(item2);

        orderRepository.save(order);

        //Then
        productRepository.delete(product);
        productRepository.delete(product1);
        order.getItems().clear();

        assertTrue(orderRepository.existsById(order.getOrderId()));
        assertFalse(productRepository.existsById(product.getId()));
        assertFalse(productRepository.existsById(product1.getId()));

        //CleanUp
        orderRepository.deleteById(order.getOrderId());
    }
}
