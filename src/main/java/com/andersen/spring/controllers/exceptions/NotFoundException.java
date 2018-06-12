package com.andersen.spring.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data Not Found") //404
public class NotFoundException extends Exception {

    private static final long serialVersionUID = -3332292346834265371L;

    public NotFoundException(String msg) {
        super(msg);
    }

}