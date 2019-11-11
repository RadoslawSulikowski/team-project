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
    @Autowired
    private UserRepository userRepository;


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
        Assert.assertTrue(productsList.isEmpty());

        //CleanUp
        cartRepository.deleteById(cart.getCartId());

    }

    @Test
    public void testCartRemainsAfterProductDeletion() {
        //Given
       Product product = new Product();
        productRepository.save(product);
        Cart cart = new Cart();
        long productId = product.getId();
        long cartId = cart.getCartId();

        //When
        productRepository.deleteById(productId);

        //Then
        Assert.assertTrue(cartRepository.existsById(cartId));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());
    }

    @Test
    public void testProductRemainsAfterCartDeletion() {
        //Given
        Product product = new Product();
        productRepository.save(product);
        Cart cart = new Cart();
        long productId = product.getId();
        long cartId = cart.getCartId();

        //When
        cartRepository.deleteById(cartId);

        //Then
        Assert.assertTrue(productRepository.existsById(productId));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());
    }

    @Test
    public void testCartRemainsAfterUserDeletion() {
        //Given
        User user = new User();
        userRepository.save(user);
        Cart cart = new Cart();
        long userId = user.getId();
        long cartId = cart.getCartId();

        //When
        userRepository.deleteById(cartId);

        //Then
        Assert.assertTrue(cartRepository.existsById(cartId));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());
    }
}
