package com.greatapi.great_api.resoucers.exceptions;

import com.greatapi.great_api.exceptions.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<GenericError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        GenericError err = new GenericError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                "Not found", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

    }

    @ExceptionHandler(NameAlreadyExistsException.class)
    public ResponseEntity<GenericError> emailAlreadyExists(NameAlreadyExistsException e, HttpServletRequest request) {

        GenericError err = new GenericError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Name already exists", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<GenericError> emailAlreadyExists(CpfAlreadyExistsException e, HttpServletRequest request) {

        GenericError err = new GenericError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Cpf already exists", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(RgAlreadyExistsException.class)
    public ResponseEntity<GenericError> emailAlreadyExists(RgAlreadyExistsException e, HttpServletRequest request) {

        GenericError err = new GenericError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Rg already exists", e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class GenericError {
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
