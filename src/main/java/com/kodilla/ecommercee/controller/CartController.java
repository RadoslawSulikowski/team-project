package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
<<<<<<< HEAD
import com.kodilla.ecommercee.exceptions.ItemNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ItemMapper;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    ItemMapper itemMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createCart")
    public void createCart(@RequestBody CartDto cartDto) {
        try {
            cartService.save(cartMapper.mapToCart(cartDto));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

<<<<<<< HEAD
    @RequestMapping(method = RequestMethod.GET, value = "getItems")
    public List<ItemDto> getAllItems(@RequestParam Long cartId) throws CartNotFoundException {
        return itemMapper.mapToItemsDtoList(cartService.getItemsFromCart(cartId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProduct")
    public void addProduct(@RequestParam Long productId, @RequestParam double quantity, @RequestParam Long cartId) throws CartNotFoundException, ProductNotFoundException {
        cartService.addProductToCart(productId, quantity, cartId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam Long itemId, @RequestParam Long cartId) throws CartNotFoundException, ItemNotFoundException {
        cartService.deleteProductFromCart(itemId, cartId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder(@RequestParam Long cartId) throws CartNotFoundException {
        cartService.createOrderFromCart(cartId);
=======
    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getAllProducts(@RequestParam Long cartId) throws CartNotFoundException {
        return productMapper.mapToProductDtoList(cartService.getProductsFromCart(cartId));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProduct")
    public void addProduct(@RequestParam Long productId, @RequestParam Long cartId) throws CartNotFoundException, ProductNotFoundException {
        cartService.addProductToCart(productId, cartId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId, @RequestParam Long cartId) throws CartNotFoundException, ProductNotFoundException {
        cartService.deleteProductFromCart(productId, cartId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public Order createOrder(@RequestParam Long cartId) throws CartNotFoundException {
        return cartService.createOrderFromCart(cartId);
>>>>>>> 99e82401545eda4dbeb08c517a6dfc861910782e
    }
}
