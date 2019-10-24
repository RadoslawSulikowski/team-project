package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @RequestMapping(method = RequestMethod.POST, value = "createCart")
    public List createCart() {
        return new ArrayList<>();
    }
    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List getAllProducts() {
        return new ArrayList<>();
    }
    @RequestMapping(method = RequestMethod.PUT, value = "addProduct")
    public List addProduct() {
        return new ArrayList<>();
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public List deleteProduct() {
        return new ArrayList<>();
    }
    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public List createOrder() {
        return new ArrayList<>();
    }
}
