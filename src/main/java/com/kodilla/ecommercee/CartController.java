package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.*;
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
    public List<String> getAllProducts(Cart cart) {
        return new ArrayList<>();
    }  //String to Product

    @RequestMapping(method = RequestMethod.PUT, value = "addProduct")
    public String addProduct(@RequestParam Long productId, @RequestParam String cartId) {
        return "The product has been successfully added to the cart.";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public String deleteProduct(@RequestParam Long productId, @RequestParam String cartId) {
        return "The product has been successfully deleted from the cart.";
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public String createOrder(@RequestParam String cartId) { // String to Order
        return "The order has been created.";
    }
}
