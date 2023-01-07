package com.htw.kbe.rules.exceptions;

public class InvalidCardPlayedException extends Exception {

    public InvalidCardPlayedException(String errorMessage) {
        super(errorMessage);
    }
}
