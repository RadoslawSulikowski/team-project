package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
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

    public List<Product> getProductsFromCart(Long cartId) throws CartNotFoundException{
        if (cartRepository.findById(cartId).isPresent()) {
            return cartRepository.findById(cartId).get().getProducts();
        } else {
            throw new CartNotFoundException();
        }
    }

    public Cart getCart(Long cartId) throws CartNotFoundException{
        return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void addProductToCart(Long productId, Long cartId) throws CartNotFoundException, ProductNotFoundException {
        if (cartRepository.findById(cartId).isPresent()) {
            if (productRepository.findById(productId).isPresent()) {
                cartRepository.findById(cartId).get().getProducts().add(productRepository.findById(productId).get());
            } else {
                throw new ProductNotFoundException();
            }
        } else {
            throw new CartNotFoundException();
        }
    }

    public void deleteProductFromCart(Long productId, Long cartId) throws CartNotFoundException, ProductNotFoundException {
        if (cartRepository.findById(cartId).isPresent()) {
            if (productRepository.findById(productId).isPresent()) {
                Cart cart = cartRepository.findById(cartId).get();
                Product product = productRepository.findById(productId).get();
                if (cart.getProducts().contains(product)) {
                    cart.getProducts().remove(product);
                } else {
                    System.out.println("Cart ID: " + cart.getCartId() + " does not contain product ID: " + product.getId());
                }
            } else {
                throw new ProductNotFoundException();
            }
        } else {
            throw new CartNotFoundException();
        }
    }

    public void deleteCart(Long cartId) throws CartNotFoundException {
        if (cartRepository.findById(cartId).isPresent()) {
            cartRepository.deleteById(cartId);
        } else {
            throw new CartNotFoundException();
        }
    }

    public Order createOrderFromCart(Long cartId) throws CartNotFoundException {
        if (cartRepository.findById(cartId).isPresent()) {
            Order order = new Order();
            order.setUser(cartRepository.findById(cartId).get().getUser());
            order.setProducts(cartRepository.findById(cartId).get().getProducts());
            cartRepository.findById(cartId).get().setProducts(new ArrayList<>());
            return orderRepository.save(order);
        } else {
            throw new CartNotFoundException();
        }
    }
}
