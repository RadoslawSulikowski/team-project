package com.kodilla.ecommercee.exceptions;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException() {
        super();
    }

    public OrderNotFoundException(String msg) {
        super(msg);
    }
}
