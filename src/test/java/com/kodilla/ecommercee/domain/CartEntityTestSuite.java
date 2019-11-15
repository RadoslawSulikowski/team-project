package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ItemRepository;
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
    private ItemRepository itemRepository;


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
    public void testDeleteItemFromCart() {
        //Given
        Cart cart = new Cart();
        Item item = new Item();
        List<Item> itemsList = cart.getItems();
        itemsList.add(item);

        cart.setItems(itemsList);

        //When
        cartRepository.save(cart);

        //Then
        itemsList.clear();
        Assert.assertTrue(itemsList.isEmpty());

        //CleanUp
        cartRepository.deleteById(cart.getCartId());

    }

    @Test
    public void testDeleteItemFromRepository() {
        //Given
        Cart cart = new Cart();
        Item item = new Item();
        List<Item> itemsList = cart.getItems();
        itemsList.add(item);

        cart.setItems(itemsList);

        //When
        cartRepository.save(cart);

        //Then
        itemRepository.deleteById(item.getId());
        itemsList.clear();
        Assert.assertFalse(itemRepository.existsById(item.getId()));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());

    }

    @Test
    public void testItemIntoCart() {
        //Given
        Cart cart = new Cart();
        Item item = new Item();
        List<Item> itemsList = cart.getItems();
        itemsList.add(item);

        cart.setItems(itemsList);

        //When
        cartRepository.save(cart);

        //Then
        Assert.assertTrue(itemsList.contains(item));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());
        itemsList.clear();

    }

    @Test
    public void testCartRemainsAfterItemDeletion() {
        //Given
        Cart cart = new Cart();
        Item item = new Item();
        List<Item> itemsList = cart.getItems();
        itemsList.add(item);

        //When
        cartRepository.save(cart);
        itemRepository.deleteById(item.getId());

        //Then
        itemsList.clear();
        Assert.assertTrue(cartRepository.existsById(cart.getCartId()));
        Assert.assertFalse(itemRepository.existsById(item.getId()));

        //CleanUp
        cartRepository.deleteById(cart.getCartId());
    }

}
