package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Item;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ItemNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ItemRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;

    public List<Item> getItemsFromCart(final Long cartId) throws CartNotFoundException{
        if (cartRepository.findById(cartId).isPresent()) {
            return cartRepository.findById(cartId).get().getItems();
        } else {
            throw new CartNotFoundException();
        }
    }

    public Cart getCart(final Long cartId) throws CartNotFoundException{
        return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    public Cart save(final Cart cart) {
        return cartRepository.save(cart);
    }

    public void addProductToCart(final Long productId, final double quantity, final Long cartId) throws CartNotFoundException, ProductNotFoundException {
        if (cartRepository.findById(cartId).isPresent()) {
            if (productRepository.findById(productId).isPresent()) {
                Item item = new Item(quantity, productRepository.findById(productId).get());
                item.setCart(cartRepository.findById(cartId).get());
                productRepository.findById(productId).get().getItems().add(item);
                cartRepository.findById(cartId).get().getItems().add(item);
            } else {
                throw new ProductNotFoundException();
            }
        } else {
            throw new CartNotFoundException();
        }
    }

    public void deleteProductFromCart(final Long itemId, final Long cartId) throws CartNotFoundException, ItemNotFoundException {
        if (cartRepository.findById(cartId).isPresent()) {
            if (itemRepository.findById(itemId).isPresent()) {
                Cart cart = cartRepository.findById(cartId).get();
                Item item = itemRepository.findById(itemId).get();
                if (cart.getItems().contains(item)) {
                    cart.getItems().remove(item);
                } else {
                    System.out.println("Cart ID: " + cart.getCartId() + " does not contain Item ID: " + item.getId());
                }
            } else {
                throw new ItemNotFoundException();
            }
        } else {
            throw new CartNotFoundException();
        }
    }

    public void deleteCart(final Long cartId) throws CartNotFoundException {
        if (cartRepository.findById(cartId).isPresent()) {
            cartRepository.deleteById(cartId);
        } else {
            throw new CartNotFoundException();
        }
    }

    public void createOrderFromCart(final Long cartId) throws CartNotFoundException {
        if (cartRepository.findById(cartId).isPresent()) {
            Order order = new Order();
            order.setUser(cartRepository.findById(cartId).get().getUser());
            order.setItems(cartRepository.findById(cartId).get().getItems());
            cartRepository.findById(cartId).get().setItems(new ArrayList<>());
            orderRepository.save(order);
        } else {
            throw new CartNotFoundException();
        }
    }
}