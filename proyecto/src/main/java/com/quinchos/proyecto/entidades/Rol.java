package com.quinchos.proyecto.entidades;

//ES PRUDENTE USAR ROLES QUE HACEN MÁS SIMPLE EL ENLACE DEL PROEYECTO POR ESTO LO DEJO

public enum Rol {
    ADMINISTRADOR("Acceso completo"),
    PROPIETARIO("Acceso a sus propiedades"),
    INQUILINO("Acceso limitado");
    
    private final String descripcion;

    Rol(String descripcion) {
            this.descripcion=descripcion;
        }

     // Con este metodo obtenemos la descripción   
     public String getDescripcion(){
        return this.descripcion;
     }   
}

