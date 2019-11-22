package com.kodilla.ecommercee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "There are products belonging to group. Group can't be deleted.")
public class GroupCantBeDeletedException extends Exception {
    public GroupCantBeDeletedException() {
        super();
    }

    public GroupCantBeDeletedException(String msg) {
        super(msg);
    }
}
