package com.kodilla.ecommercee.domain;


import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductEntityTest {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testProductWithGroupIdSave() {
        //Given
        Product product = productRepository.save(new Product("kurtka zimowa", "Pellentesque", new BigDecimal(400)));
        Product product1 = productRepository.save(new Product("torebka", "Pellentesque", new BigDecimal(3000)));
        Product product2 = productRepository.save(new Product("Sandały", "Kubota", new BigDecimal(500)));
        Group group = groupRepository.save(new Group("ubrania"));
        Group group1 = groupRepository.save(new Group("elektronika"));
        Group group2 = groupRepository.save(new Group("obuwie"));

        //When


        //Then
        Long id = product.getId();
        Long id1 = product1.getId();
        Long id2 = product2.getId();

        Optional<Product> foundProduct = productRepository.findById(id);
        Optional<Product> foundProduct1 = productRepository.findById(id1);
        Optional<Product> foundProduct2 = productRepository.findById(id2);

        Assert.assertTrue(foundProduct.isPresent());
        Assert.assertTrue(foundProduct1.isPresent());
        Assert.assertTrue(foundProduct2.isPresent());
        System.out.println(productRepository.findById(id));
        //CleanUp
        productRepository.deleteById(id);
        productRepository.deleteById(id1);
        productRepository.deleteById(id2);
        groupRepository.deleteById(group.getId());
        groupRepository.deleteById(group1.getId());
        groupRepository.deleteById(group2.getId());

        System.out.println(productRepository.findById(id));

    }
}
