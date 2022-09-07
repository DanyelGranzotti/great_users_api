package com.greatapi.great_api.exceptions;

public class RgAlreadyExistsException extends RuntimeException{
    public RgAlreadyExistsException(String rg) {
        super("The rg: " + rg + " already exists");
    }
    public RgAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
