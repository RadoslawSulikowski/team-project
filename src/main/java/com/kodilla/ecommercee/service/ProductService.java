package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(final Long id) throws ProductNotFoundException {
            return repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public Product updateProduct(Product product) throws ProductNotFoundException{
        if(repository.existsById(product.getId())){
            return saveProduct(product);
        } else {
            LOGGER.info("Product id: " + product.getId() + " not found.");
            throw new ProductNotFoundException();
        }
    }

    public void deleteRepository(final Long id) throws ProductNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }else {
            LOGGER.info("Product id: " + id + " not found.");
            throw new ProductNotFoundException();
        }
    }
}
