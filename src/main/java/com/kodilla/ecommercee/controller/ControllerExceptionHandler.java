package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(GroupNotFoundException.class)
    public String groupNotFoundExceptionHandler(){
        LOGGER.error("GroupNotFound");
        return "No such group";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String productNotFoundExceptionHandler(){
        LOGGER.error("ProductNotFound");
        return "No such product";
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public String orderNotFoundExceptionHandler(){
        LOGGER.error("OrderNotFound");
        return "No such order";
    }

    @ExceptionHandler(CartNotFoundException.class)
    public String cartNotFoundExceptionHandler(){
        LOGGER.error("CartNotFound");
        return "No such cart";
    }
}
