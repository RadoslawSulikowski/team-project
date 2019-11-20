package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private static final String MESSAGE = "No order with id: ";

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getAllUserOrders(final Long userId) throws UserNotFoundException {
        if (userRepository.findById(userId).isPresent()) {
            return orderRepository.retrieveOrdersByUserId(userId);
        } else {
            LOGGER.error("No user with id " + userId + " to show orders.");
            throw new UserNotFoundException("No user with id " + userId + " to show orders.");
        }
    }

    public void saveOrder(final Order order) {
        orderRepository.save(order);
    }

    public Order getOrder(final Long orderId) throws OrderNotFoundException {
        if (orderRepository.findById(orderId).isPresent()) {
            return orderRepository.findById(orderId).get();
        } else {
            LOGGER.error(MESSAGE + orderId);
            throw new OrderNotFoundException(MESSAGE + orderId);
        }
    }

    public Order updateOrder(final Order order) throws OrderNotFoundException {
        if (orderRepository.findById(order.getOrderId()).isPresent()) {
            return orderRepository.save(order);
        } else {
            LOGGER.error(MESSAGE + order.getOrderId() + " to update.");
            throw new OrderNotFoundException(MESSAGE + order.getOrderId() + " to update.");
        }
    }

    public void deleteOrder(final Long id) throws OrderNotFoundException {
        if (orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
        } else {
            LOGGER.error(MESSAGE + id + " to delete.");
            throw new OrderNotFoundException(MESSAGE + id + " to delete.");
        }
    }
}
