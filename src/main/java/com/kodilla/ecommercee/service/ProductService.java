package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public void deleteRepository(final Product product){
        repository.delete(product);
    }
}
