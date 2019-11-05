package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;


    @Test
    public void testSaveCart() {
        //Given
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Product product1 = new Product("Kurtka", "Kurtka zimowa", new BigDecimal(100));
        Product product2 = new Product("Płaszcz", "Płaszcz zimowy, męski", new BigDecimal(150));

        List<Product> productsList = new ArrayList<>();
        productsList.add(product1);
        productsList.add(product2);

        cart1.setProducts(productsList);

        //When
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        Optional<Cart> testCart = cartRepository.findById(cart1.getCartId());
        Optional<Product> testProduct = productRepository.findById(product2.getId());

        //Then
        Assert.assertTrue(testCart.isPresent());
        Assert.assertTrue(testProduct.isPresent());

        //CleanUp
        productRepository.deleteById(product2.getId());

    }

    @Test
    public void testDeleteProductFromCart() {
        //Given
        Cart cart = new Cart();
        cartRepository.save(cart);
        Product product = new Product();
        productRepository.save(product);
        long productId = product.getId();
        long cartId = cart.getCartId();

        //When
        productRepository.deleteById(productId);

        //Then
       Assert.assertTrue(cartRepository.existsById(cartId));

    }

    @Test
    public void testProductIntoCart() {
        //Given
        Cart cart = new Cart();
        cartRepository.save(cart);
        long cartId = cart.getCartId();

        //When
        Product product = new Product();
        productRepository.save(product);
        long productId = product.getId();

        //Then
        assertTrue(productRepository.existsById(productId));

        //CleanUp
        cartRepository.deleteById(cartId);

    }
}
