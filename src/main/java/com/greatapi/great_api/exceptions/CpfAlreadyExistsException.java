package com.greatapi.great_api.exceptions;

public class CpfAlreadyExistsException extends RuntimeException{
    public CpfAlreadyExistsException(String cpf) {
        super("The cpf: " + cpf + " already exists");
    }
    public CpfAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
