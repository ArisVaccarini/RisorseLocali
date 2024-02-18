package com.unicam.risorseLocali.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<String> handleElementNotFoundException(ElementNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore: "
                + ex.getMessage());
    }

    @ExceptionHandler(RequestNotFoundException.class)
    public ResponseEntity<String> handleRequestNotFoundException(RequestNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore: "
                + ex.getMessage());
    }

    @ExceptionHandler(GeometryPositionException.class)
    public ResponseEntity<String> handleGeometryPositionException(GeometryPositionException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore: "
                + ex.getMessage());
    }

}
