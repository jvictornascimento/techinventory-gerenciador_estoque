package com.techinventory.estoque.gerenciador_estoque.exceptions;

import com.techinventory.estoque.gerenciador_estoque.services.exceptions.CategoryNotFoundException;
import com.techinventory.estoque.gerenciador_estoque.services.exceptions.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice

public class ControllerExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<StandardError> categoryNotFound(CategoryNotFoundException e, HttpServletRequest request){
        String error = "Category not found ";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<StandardError> productNotFound(ProductNotFoundException e, HttpServletRequest request){
        String error = "Product not found ";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
