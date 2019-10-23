package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Product;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/ecommerce")
public class ProductController {
    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<Product> getProducts() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public Product getProduct(@RequestParam Long id) throws EntityNotFoundException {
        return new Product( "Test value");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody Product product) {
        System.out.println("Created new product  --->  " + product.toString());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public Product updateProduct(@RequestBody Product product) {
        return new Product(product.getValue());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam Long id) {
        System.out.println("Deleting product #" + id);
    }
}