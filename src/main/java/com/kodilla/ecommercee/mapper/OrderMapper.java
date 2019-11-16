package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class OrderMapper {

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;


    public Order mapToOrder(OrderDto orderDto) throws UserNotFoundException, ProductNotFoundException {
        if (orderRepository.findById(orderDto.getId()).isPresent()) {
            Order orderToUpdate = orderRepository.findById(orderDto.getId()).get();
            orderToUpdate.setItems(itemMapper.mapToItemsList(orderDto.getItems()));
            orderToUpdate.getItems().forEach(p -> p.setOrder(orderToUpdate));
            return orderToUpdate;
        } else {
            Order order = new Order();
            order.setUser(userRepository.findById(orderDto.getUserId()).orElseThrow(UserNotFoundException::new));
            order.setItems(itemMapper.mapToItemsList(orderDto.getItems()));
            order.getItems().forEach(p -> p.setOrder(order));
            return order;
        }
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                itemMapper.mapToItemsDtoList(order.getItems()),
                order.getUser().getId()
        );
    }

    public List<OrderDto> mapToOrderDtoList(final List<Order> orders) {
        if (orders != null) {
            return orders.stream()
                    .map(t -> new OrderDto(t.getOrderId(), itemMapper.mapToItemsDtoList(t.getItems()), t.getUser().getId()))
                    .collect(Collectors.toList());
        } else return new ArrayList<>();
    }

}
