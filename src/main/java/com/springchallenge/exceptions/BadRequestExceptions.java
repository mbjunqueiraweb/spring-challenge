package com.springchallenge.exceptions;

public class BadRequestExceptions extends RuntimeException {

    public BadRequestExceptions(String messages) {
        super(messages);

    }
}