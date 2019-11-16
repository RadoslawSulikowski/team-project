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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {


    private final static Logger LOGGER = LoggerFactory.getLogger(CartService.class);


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


            LOGGER.error("No cart with ID: " + cartId + " found");

            throw new CartNotFoundException();
        }
    }

    public Cart getCart(final Long cartId) throws CartNotFoundException{

        return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    public Cart save(final Cart cart) {

        if (cartRepository.findById(cartId).isPresent()) {
            return cartRepository.findById(cartId).get();
        } else {
            LOGGER.error("No cart with ID: " + cartId + " found");
            throw new CartNotFoundException();
        }
    }

    public Cart save(final Cart cart) {
        LOGGER.info("Successfully saved the cart");

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

                itemRepository.save(item);
                LOGGER.info("Successfully added new product to cart with ID: " + cartId);
            } else {
                LOGGER.error("No product with ID: " + productId + " found");
                throw new ProductNotFoundException();
            }
        } else {
            LOGGER.error("No cart with ID: " + cartId + " found");

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

                    productRepository.findById(item.getProduct().getId()).get().getItems().remove(item);
                    itemRepository.deleteById(itemId);
                    LOGGER.info("Successfully deleted item with ID: " + itemId + " from cart");
                } else {
                    LOGGER.warn("Cart ID: " + cart.getCartId() + " does not contain Item ID: " + item.getId());
                }
            } else {
                LOGGER.error("No item with ID: " + itemId + " found");
                throw new ItemNotFoundException();
            }
        } else {
            LOGGER.error("No cart with ID: " + cartId + " found");

            throw new CartNotFoundException();
        }
    }

    public void deleteCart(final Long cartId) throws CartNotFoundException {
        if (cartRepository.findById(cartId).isPresent()) {
            cartRepository.deleteById(cartId);

        } else {

            LOGGER.info("Successfully deleted cart with ID: " + cartId);
        } else {
            LOGGER.error("No cart with ID: " + cartId + " found");

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

            LOGGER.info("Successfully created order with ID: " + order.getOrderId());
        } else {
            LOGGER.error("No cart with ID: " + cartId + " found");
            throw new CartNotFoundException();
        }
    }
}

