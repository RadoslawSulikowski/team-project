package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    UserRepository userRepository;

    public CartDto mapToCartDto(final Cart cart) {
        List<ProductDto> productDtos = productMapper.mapToProductDtoList(cart.getProducts());
        return new CartDto(cart.getCartId(), productDtos, cart.getUser().getId());
    }

    public Cart mapToCart(final CartDto cartDto) throws UserNotFoundException {
        Cart cart = new Cart();
        cart.setCartId(cartDto.getCartId());
        cart.setUser(userRepository.findById(cartDto.getUserId()).orElseThrow(UserNotFoundException::new));
        try {
            cart.setProducts(productMapper.mapToProductList(cartDto.getItems()));
        } catch (GroupNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return cart;
    }
}
