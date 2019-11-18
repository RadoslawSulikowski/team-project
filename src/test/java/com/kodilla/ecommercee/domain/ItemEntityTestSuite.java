package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemEntityTestSuite {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;

    @Test
    public void testSaveItemIntoRepository() {
        //Given
        Item item = new Item();

        //When
        itemRepository.save(item);
        Optional<Item> testItem = itemRepository.findById(item.getId());

        //Then
        Assert.assertTrue(testItem.isPresent());

        //CleanUp
        itemRepository.deleteById(item.getId());
    }

    @Test
    public void testGetAllInstancesFromRepository() {
        //Given
        Item item = new Item();
        Item item2 = new Item();
        Item item3 = new Item();

        //When
        itemRepository.save(item);
        itemRepository.save(item2);
        itemRepository.save(item3);
        //Then
        Assert.assertEquals(3, itemRepository.findAll().size());

        //CleanUp
        itemRepository.deleteById(item.getId());
        itemRepository.deleteById(item2.getId());
        itemRepository.deleteById(item3.getId());
    }

    @Test
    public void testDeletingItemFromRepository() {
        //Given
        Item item = new Item();
        Item item2 = new Item();

        //When
        itemRepository.save(item);
        itemRepository.save(item2);
        itemRepository.deleteById(item.getId());
        itemRepository.delete(item2);

        //Then
        Assert.assertFalse(itemRepository.existsById(item.getId()));
        Assert.assertFalse(itemRepository.existsById(item2.getId()));
    }

    @Test
    public void testCartRelation() {
        //Given
        Cart cart = new Cart();
        Item item = new Item();
        List<Item> items = cart.getItems();

        //When
        itemRepository.save(item);
        cartRepository.save(cart);

        item.setCart(cart);
        items.add(item);
        cart.setItems(items);

        itemRepository.delete(item);
        items.remove(item);

        //Then
        Assert.assertFalse(itemRepository.existsById(item.getId()));
        Assert.assertTrue(cartRepository.existsById(cart.getCartId()));

        //Clean up
        cartRepository.delete(cart);
    }

    @Test
    public void testProductRelation() {
        //Given
        Item item = new Item();
        Product product = new Product("Kurtka", "Kurtka zimowa", new BigDecimal(100));
        List<Item> items = product.getItems();

        itemRepository.save(item);
        productRepository.save(product);

        item.setProduct(product);
        items.add(item);
        product.setItems(items);

        //When
        itemRepository.delete(item);
        items.remove(item);

        //Then
        Assert.assertFalse(itemRepository.existsById(item.getId()));
        Assert.assertTrue(productRepository.existsById(product.getId()));

        //Clean up
        productRepository.delete(product);
    }

    @Test
    public void testOrderRelation() {
        //Given
        Item item = new Item();
        Order order = new Order();
        List<Item> items = order.getItems();

        itemRepository.save(item);
        orderRepository.save(order);

        item.setOrder(order);
        items.add(item);
        order.setItems(items);

        //When
        itemRepository.delete(item);
        items.remove(item);

        //Then
        Assert.assertFalse(itemRepository.existsById(item.getId()));
        Assert.assertTrue(orderRepository.existsById(order.getOrderId()));

        //Clean up
        orderRepository.delete(order);
    }
}
