package com.kodilla.ecommercee.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
