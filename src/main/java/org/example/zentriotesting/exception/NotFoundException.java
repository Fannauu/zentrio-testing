package org.example.zentriotesting.exception;



public class NotFoundException extends RuntimeException {
    private NotFoundException(String message) {
        super(message);
    }
}
