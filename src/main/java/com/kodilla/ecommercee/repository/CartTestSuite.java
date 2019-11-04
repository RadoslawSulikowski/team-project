package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
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
        Product product2 = new Product ("Płaszcz", "Płaszcz zimowy, męski", new BigDecimal(150));
        cartRepository.save(cart1);
        cartRepository.save(cart2);

        //When
        Optional<Cart> showCart = cartRepository.findById(cart2.getCartId());
        Optional<Product> showProduct = productRepository.findById(product2.getId());
        List<Product> productsList = new ArrayList<>();
        productsList.add(product1);
        productsList.add(product2);

        cart2.setProducts(productsList);

        //Then
        int sizeBeforeDelete = cartRepository.findAll().size();
        cartRepository.deleteById(cart2.getCartId());
        int sizeAfterDelete = cartRepository.findAll().size();

        Assert.assertEquals(sizeBeforeDelete, 2);
        Assert.assertEquals(sizeAfterDelete, 1);
        Assert.assertFalse(showCart.isPresent());
        Assert.assertFalse(showProduct.isPresent());

        //CleanUp
        cartRepository.deleteById(cart1.getCartId());
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());

    }

    @Test
    public void testFindProductById() {
        //Given
        Product product = new Product();
        productRepository.save(product);

        //When
        Optional<Product> testProduct = productRepository.findById(product.getId());

        //Then
        Assert.assertTrue(testProduct.isPresent());

        //CleanUp
        productRepository.deleteById(product.getId());
    }
}
