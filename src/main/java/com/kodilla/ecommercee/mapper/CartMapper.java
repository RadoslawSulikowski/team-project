package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    UserRepository userRepository;

    public CartDto mapToCartDto(final Cart cart) {
        List<ItemDto> itemDtos = itemMapper.mapToItemsDtoList(cart.getItems());
        return new CartDto(cart.getCartId(), itemDtos, cart.getUser().getId());
    }

    public Cart mapToCart(final CartDto cartDto) throws UserNotFoundException {
        Cart cart = new Cart();
        cart.setCartId(cartDto.getCartId());
        cart.setUser(userRepository.findById(cartDto.getUserId()).orElseThrow(UserNotFoundException::new));
        try {
            cart.setItems(itemMapper.mapToItemsList(cartDto.getItems()));
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return cart;
    }
}