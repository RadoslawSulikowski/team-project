package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private static final String MESSAGE = "No product with id: ";

    @Autowired
    ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(final Long id) throws ProductNotFoundException {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        } else {
            LOGGER.error(MESSAGE + id);
            throw new ProductNotFoundException(MESSAGE + id);
        }
    }

    public void saveProduct(Product product) {
        repository.save(product);
    }

    public Product updateProduct(Product product) throws ProductNotFoundException {
        if (repository.existsById(product.getId())) {
            return repository.save(product);
        } else {
            LOGGER.error(MESSAGE + product.getId() + " to update.");
            throw new ProductNotFoundException(MESSAGE + product.getId() + " to update.");
        }
    }

    public void deleteProduct(final Long id) throws ProductNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            LOGGER.error(MESSAGE + id + " to delete.");
            throw new ProductNotFoundException(MESSAGE + id + " to delete.");
        }
    }
}
