package com.Glebson.Tastee.Infra;

import com.Glebson.Tastee.Exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestControllerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseException> notFoundExceptionHandler(NotFoundException f){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), f.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> defaultExceptionHandler(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal error."));
    }
}
