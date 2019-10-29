package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(@RequestParam Long orderId) {
        return new OrderDto(1L, new ArrayList<>());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder")
    ///before final implementation change to void
    public String createOrder(@RequestBody OrderDto orderDto) {
        return "Order created correctly: \nid: " + orderDto.getId() + "\nproducts: " + orderDto.getProducts();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderDto;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    //before final implementation change to void
    public String deleteOrder(@RequestParam Long orderId) {
        return "Order deleted correctly";
    }
}
