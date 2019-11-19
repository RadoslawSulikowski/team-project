package com.kodilla.ecommercee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GroupNotFoundException extends Exception {
    public GroupNotFoundException(){
        super();
    }

    public GroupNotFoundException(String msg){
        super(msg);
    }
}
