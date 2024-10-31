package com.quinchos.proyecto.entidades;

import jakarta.persistence.Entity;

@Entity
public class Inquilino {
    
    private String idInquilino;

    private String idInmueble;

    private String nombre;

    private String apellido;

    private String email;

    private String contraseña;

    public Inquilino() {
    }

    public String getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(String idInquilino) {
        this.idInquilino = idInquilino;
    }

    public String getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }






}
