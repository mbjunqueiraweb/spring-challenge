package com.springchallenge.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.springchallenge.exceptions.RepositoryExceptions;

/**
 *
 * Classe para tratamento de Erros
 *
 * @author Meli - Wave 4 - Grupo 11
 *
 * @version 0.0.1
 */
@ControllerAdvice
public class PersistenceExceptionsAdvice {

    @ExceptionHandler(value = RepositoryExceptions.class)
    protected ResponseEntity<Object> handlePersistencia(RepositoryExceptions e, WebRequest request) {
        String bodyOfResponse = e.getMessage();
        return ResponseEntity.internalServerError().body(bodyOfResponse);
    }

    @ExceptionHandler(value = NullPointerException.class)
    protected ResponseEntity<Object> handleNullPointer(NullPointerException e, WebRequest request) {
        String bodyOfResponse = e.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }
}
