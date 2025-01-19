package com.pets.all_pets.exceptions;


public class CustomerValidationException extends RuntimeException {
    public CustomerValidationException(String message) {
        super(message);
    }

}
