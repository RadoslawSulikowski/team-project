package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @RequestMapping(method = RequestMethod.POST, value = "createCart")
    public CartDto createCart() {
        return new CartDto(123L, new ArrayList<>());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getAllProducts(@RequestParam Long cartId) throws CartNotFoundException {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProduct")
    public void addProduct(@RequestParam Long productId, @RequestParam Long cartId) throws CartNotFoundException {
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId, @RequestParam Long cartId) throws CartNotFoundException {
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder(@RequestParam Long cartId) throws CartNotFoundException {
    }
}
