package com.andersen.spring.exceptions;

public class InsufficientFunds extends Exception {

    public InsufficientFunds() {

    }

    public InsufficientFunds(String msg) {
        super(msg);
    }

}
