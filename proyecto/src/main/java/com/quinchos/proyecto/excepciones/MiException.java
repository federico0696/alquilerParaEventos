package com.quinchos.proyecto.excepciones;

public class MiException extends Exception {

    // Constructor que acepta solo un mensaje
    public MiException(String mensaje) {
        super(mensaje);
    }

    // Constructor que acepta un mensaje y una excepción original
    public MiException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
