package com.kodilla.ecommercee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Group with given id already exists")
public class GroupAlreadyExistsException extends Exception{
    public GroupAlreadyExistsException(){
        super();
    }

    public GroupAlreadyExistsException(String msg){
        super(msg);
    }
}
