package com.syed.reposervice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorMessage> handleInternalServerErrorException(InternalServerErrorException ex) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // had to make error desc more generic, as HttpMessageNotReadableException
        // was also thrown whenever request body is empty for Post req
//        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST,
//                "Category not accepted. Accepted values: UIT, API, IAC, SCRIPT, FUNCTION");
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST,
                "Bad request data");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorMessage> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST,
                "Missing auth token");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
