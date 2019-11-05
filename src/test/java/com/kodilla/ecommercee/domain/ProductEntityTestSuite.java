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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void testProductSave() {
        //Given
        Product product = productRepository.save(new Product(NAME, DESCRIPTION, PRICE));

        //When & Then
        Optional<Product> foundProduct = productRepository.findById(product.getId());
        assertTrue(foundProduct.isPresent());

        //CleanUp
        productRepository.deleteById(product.getId());
    }

    @Test
    public void testProductDelete() {
        //Given
        Product product = productRepository.save(new Product(NAME, DESCRIPTION, PRICE));

        //When
        productRepository.deleteById(product.getId());

        //Then
        assertFalse(productRepository.findById(product.getId()).isPresent());
    }

    @Test
    public void testProductRelationshipWithGroup() {
        //Given
        Product product = productRepository.save(new Product(NAME, DESCRIPTION, PRICE));
        Product product1 = productRepository.save(new Product(NAME, DESCRIPTION, PRICE));
        Product product2 = productRepository.save(new Product(NAME, DESCRIPTION, PRICE));

        Group group = groupRepository.save(new Group("ubrania"));

        //When;
        product.setGroup(group);
        product1.setGroup(group);
        product2.setGroup(group);

        //Then
        Long id = product.getId();
        Long id1 = product1.getId();
        Long id2 = product2.getId();

        Optional<Product> foundProduct = productRepository.findById(id);
        Optional<Product> foundProduct1 = productRepository.findById(id1);
        Optional<Product> foundProduct2 = productRepository.findById(id2);

        assertTrue(foundProduct.isPresent());
        assertTrue(foundProduct1.isPresent());
        assertTrue(foundProduct2.isPresent());

        //CleanUp
        productRepository.deleteById(id);
        productRepository.deleteById(id1);
        productRepository.deleteById(id2);
        groupRepository.deleteById(group.getId());
    }

    @Test
    public void testProductRelationshipWithCartAndOrder() {
        //Given
        Product product = productRepository.save(new Product(NAME, DESCRIPTION, PRICE));
        Product product1 = productRepository.save(new Product(NAME, DESCRIPTION, PRICE));
        Product product2 = productRepository.save(new Product(NAME, DESCRIPTION, PRICE));

        Group group = groupRepository.save(new Group("ubrania"));
        Cart cart = cartRepository.save(new Cart());
        Order order = orderRepository.save(new Order());

        //When;
        product.setGroup(group);
        product1.setGroup(group);
        product2.setGroup(group);

        product.setCart(cart);
        product.setOrder(order);

        //Then
        Long id = product.getId();
        Long id1 = product1.getId();
        Long id2 = product2.getId();

        Optional<Product> foundProduct = productRepository.findById(id);
        Optional<Product> foundProduct1 = productRepository.findById(id1);
        Optional<Product> foundProduct2 = productRepository.findById(id2);

        assertTrue(foundProduct.isPresent());
        assertTrue(foundProduct1.isPresent());
        assertTrue(foundProduct2.isPresent());

        //CleanUp
        productRepository.deleteById(id);
        productRepository.deleteById(id1);
        productRepository.deleteById(id2);
        groupRepository.deleteById(group.getId());
        cartRepository.deleteById(cart.getCartId());
        orderRepository.deleteById(order.getOrderId());
    }
}
