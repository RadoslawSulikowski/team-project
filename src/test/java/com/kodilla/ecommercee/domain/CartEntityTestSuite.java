package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartEntityTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;


    @Test
    public void testSaveCart() {
        //Given
        Cart cart = new Cart();

        //When
        cartRepository.save(cart);
        Optional<Cart> testCart = cartRepository.findById(cart.getCartId());

        //Then
        Assert.assertTrue(testCart.isPresent());

        //CleanUp
        cartRepository.deleteById(cart.getCartId());

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
        productsList.remove(product.getId());
        Assert.assertFalse(cartRepository.existsById(product.getId()));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());

    }

    @Test
    public void testDeleteProductFromRespository() {
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
        Assert.assertTrue(productRepository.existsById(product.getId()));

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
        Assert.assertFalse(productsList.isEmpty());

        //CleanUp
        cartRepository.deleteById(cart.getCartId());

    }

    @Test
    public void testCartRemainsAfterProductDeletion() {
        //Given
        Cart cart = new Cart();
        Product product = new Product();
        List<Product> productsList = cart.getProducts();
        productsList.add(product);

        //When
        cartRepository.save(cart);
        productRepository.deleteById(product.getId());

        //Then
        Assert.assertTrue(cartRepository.existsById(cart.getCartId()));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());
    }

}
