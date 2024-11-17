package com.quinchos.proyecto.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja las excepciones personalizadas (MiException) lanzadas en cualquier controlador.
     */
    @ExceptionHandler(MiException.class)
    public ResponseEntity<String> handleMiException(MiException e) {
        // Puedes personalizar el mensaje que quieres devolver en caso de MiException
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
    }

    /**
     * Maneja las excepciones generales no controladas.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        // Este método manejará cualquier otra excepción no esperada
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
    }
}
