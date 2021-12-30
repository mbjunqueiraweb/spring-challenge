package com.springchallenge.controller.advice;

import com.springchallenge.exceptions.BadRequestExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.springchallenge.exceptions.RepositoryExceptions;

@ControllerAdvice
public class PersistenceExceptionsAdvice {

    @ExceptionHandler(value = RepositoryExceptions.class)
    protected ResponseEntity<Object> handlePersistencia(RepositoryExceptions e, WebRequest request) {
        String bodyOfResponse = e.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ExceptionHandler(value = BadRequestExceptions.class)
    protected ResponseEntity<Object> handlePersistencia(BadRequestExceptions e, WebRequest request) {
        String bodyOfResponse = e.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ExceptionHandler(value = NullPointerException.class)
    protected ResponseEntity<Object> handleNullPointer(NullPointerException e, WebRequest request) {
        String bodyOfResponse = e.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ExceptionHandler(value = ArithmeticException.class)
    protected ResponseEntity<Object> erroCalculo(ArithmeticException e, WebRequest request) {
        String bodyOfResponse = e.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

}
