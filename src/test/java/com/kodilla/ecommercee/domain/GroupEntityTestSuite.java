package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupEntityTestSuite {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ProductRepository productRepository;

    private static final String NAME = "product";
    private static final String DESCRIPTION = "Pellentesque";
    private static final BigDecimal PRICE = new BigDecimal(500);

    @Test
    public void testSaveGroupIntoRepository() {
        //Given
        Group group = new Group();

        //When
        groupRepository.save(group);
        Optional<Group> testGroup = groupRepository.findById(group.getId());

        //Then
        Assert.assertTrue(testGroup.isPresent());

        //CleanUp
        groupRepository.deleteById(group.getId());
    }

    @Test
    public void testGetAllInstancesFromRepository() {
        //Given
        Group group = new Group();
        Group group2 = new Group();
        Group group3 = new Group();

        //When
        int initialSizeOfRepository = groupRepository.findAll().size();
        groupRepository.save(group);
        groupRepository.save(group2);
        groupRepository.save(group3);

        //Then
        Assert.assertEquals(3, groupRepository.findAll().size()-initialSizeOfRepository);

        //CleanUp
        groupRepository.deleteById(group.getId());
        groupRepository.deleteById(group2.getId());
        groupRepository.deleteById(group3.getId());
    }

    @Test
    public void testDeletingGroupFromRepository() {
        //Given
        Group group = new Group();
        Group group2 = new Group();

        //When
        groupRepository.save(group);
        groupRepository.save(group2);
        groupRepository.deleteById(group.getId());
        groupRepository.delete(group2);

        //Then
        Assert.assertFalse(groupRepository.existsById(group.getId()));
        Assert.assertFalse(groupRepository.existsById(group2.getId()));
    }

    @Test
    public void testProductRelation() {
        //Given
        Group group = new Group();
        Product product = new Product(NAME, DESCRIPTION, PRICE);
        List<Product> products = group.getProducts();

        //When
        groupRepository.save(group);
        productRepository.save(product);

        product.setGroup(group);
        products.add(product);
        group.setProducts(products);

        groupRepository.delete(group);

        //Then
        Assert.assertTrue(productRepository.existsById(product.getId()));
        Assert.assertFalse(groupRepository.existsById(group.getId()));

        //Clean up
        productRepository.deleteById(product.getId());
    }
}
