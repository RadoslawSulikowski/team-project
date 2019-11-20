package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderMapper orderMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUserOrders")
    public List<OrderDto> getUserOrders(@RequestParam Long userId) throws UserNotFoundException {
        return orderMapper.mapToOrderDtoList(orderService.getAllUserOrders(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(@RequestParam Long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.getOrder(orderId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    public void createOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException, ProductNotFoundException {
        orderService.saveOrder(orderMapper.mapToOrder(orderDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) throws UserNotFoundException, ProductNotFoundException, OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.updateOrder(orderMapper.mapToOrder(orderDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(@RequestParam Long orderId) throws OrderNotFoundException {
        orderService.deleteOrder(orderId);
    }
}
