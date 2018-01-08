package com.andersen.spring.exceptions;

public class InsufficientFunds extends RuntimeException {

    public InsufficientFunds() {

    }

    public InsufficientFunds(String msg) {
        super("Недостаточно денег для совершения операции.");
    }

}
