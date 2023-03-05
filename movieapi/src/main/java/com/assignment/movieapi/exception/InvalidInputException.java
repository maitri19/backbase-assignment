package com.assignment.movieapi.exception;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message){
        super(message);
    }
}
