package com.greatapi.great_api.exceptions;

public class NameAlreadyExistsException extends RuntimeException{
    public NameAlreadyExistsException(String name) {
        super("The name: " + name + " already exists");
    }
    public NameAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
