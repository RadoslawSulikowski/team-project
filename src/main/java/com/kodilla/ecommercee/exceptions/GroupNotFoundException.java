package com.kodilla.ecommercee.exceptions;

public class GroupNotFoundException extends Exception {
    public GroupNotFoundException(){
        super();
    }

    public GroupNotFoundException(String msg){
        super(msg);
    }
}
