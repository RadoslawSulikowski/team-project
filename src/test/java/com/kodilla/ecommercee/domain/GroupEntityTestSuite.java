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

    @Test
    public void testSaveAndDeleteGroup() {
        //Given
        Group group1 = new Group("Ubrania");
        Group group2 = new Group("Zdrowie");
        Group group3 = new Group("Elektronika");
        Group group4 = new Group("Dom");
        Group group5 = new Group("Ogród");

        Product product1 = new Product("Bluza", "Wełniana bluza", new BigDecimal(79.99));
        Product product2 = new Product("Spodnie", "Dżinsowe czarne spodnie", new BigDecimal(99.99));
        Product product3 = new Product("T-shirt", "Czerwony T-shirt", new BigDecimal(74.99));
        Product product4 = new Product("Buty", "Czarne adidasy", new BigDecimal(179.99));
        Product product5 = new Product("Kurtka", "Kurtka zimowa", new BigDecimal(109.99));

        List<Product> listOfProducts = new ArrayList<>();
        listOfProducts.add(product1);
        listOfProducts.add(product2);
        listOfProducts.add(product3);
        listOfProducts.add(product4);
        listOfProducts.add(product5);

        group1.setProducts(listOfProducts);

        //When
        int sizeOfRepository = groupRepository.findAll().size();                            // Checking the initial size of the repository
        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        groupRepository.save(group4);
        groupRepository.save(group5);

        //Then
        int sizeOfResultListBeforeDeleting = groupRepository.findAll().size();              // Checking the size of the rep. before adding objects
        groupRepository.deleteById(group1.getId());                                         // Deleting an instance and then checking the size again
        int sizeOfResultListAfterDeleting = groupRepository.findAll().size();

        Optional<Group> readGroup1 = groupRepository.findById(group1.getId());              // Checking if the deleted instance has in fact been deleted
        Optional<Product> readProduct1 = productRepository.findById(product1.getId());      // After deleting the group, checking if products have been deleted as well

        Assert.assertEquals(5, sizeOfResultListBeforeDeleting - sizeOfRepository);
        Assert.assertEquals(4, sizeOfResultListAfterDeleting - sizeOfRepository);
        Assert.assertFalse(readGroup1.isPresent());
        Assert.assertTrue(readProduct1.isPresent());

        //CleanUp
        groupRepository.deleteById(group2.getId());
        groupRepository.deleteById(group3.getId());
        groupRepository.deleteById(group4.getId());
        groupRepository.deleteById(group5.getId());
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
        productRepository.deleteById(product3.getId());
        productRepository.deleteById(product4.getId());
        productRepository.deleteById(product5.getId());
    }
}
