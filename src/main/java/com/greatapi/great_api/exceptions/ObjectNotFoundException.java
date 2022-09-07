package com.greatapi.great_api.exceptions;

import java.util.UUID;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(UUID id) {
        super("Object not found. Id: " + id);
    }
    public ObjectNotFoundException(String RgOrCpf) {
        super("Object not found. Rg or Cpf: " + RgOrCpf);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
