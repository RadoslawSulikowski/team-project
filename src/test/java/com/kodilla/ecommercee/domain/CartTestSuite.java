package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

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
        cartRepository.deleteById(cart1.getCartId());
        cartRepository.deleteById(cart2.getCartId());
        productRepository.deleteById(product2.getId());

    }

    @Test
    public void testDeleteProductFromCart() {
        //Given
        Cart cart = new Cart();
        Product product = new Product();
        List<Product> productsList = cart.getProducts();
        productsList.add(product);

        cart.setProducts(productsList);

        //When
        cartRepository.save(cart);

        //Then
        productRepository.deleteById(product.getId());
        Assert.assertFalse(productRepository.existsById(product.getId()));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());

    }

    @Test
    public void testProductIntoCart() {
        //Given
        Cart cart = new Cart();
        Product product = new Product();
        List<Product> productsList = cart.getProducts();
        productsList.add(product);

        cart.setProducts(productsList);

        //When
        cartRepository.save(cart);

        //Then
        Assert.assertTrue(productRepository.existsById(product.getId()));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());

    }
}
