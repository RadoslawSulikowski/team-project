package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(final Order order) {
       return orderRepository.save(order);
    }

    public Optional<Order> getOrder(final Long id) throws OrderNotFoundException {
        return orderRepository.findById(id);
    }

    public void deleteOrder(final Long id) {
        orderRepository.deleteById(id);
    }


}
