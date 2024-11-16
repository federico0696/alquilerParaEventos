package com.quinchos.proyecto.entidades;



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



// import org.hibernate.annotations.GenericGenerator;

// import com.quinchos.proyecto.enumeraciones.Rol;

// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.Id;

// @Entity
// public class Usuario {
    
//     @Id 
//     private String id;

//     private String email;
//     private String password;
    
//     @Enumerated(EnumType.STRING)
//     private Rol rol;

//     public Usuario() {
//     }

//     public String getId() {
//         return id;
//     }
//     public void setId(String id) {
//         this.id = id;
//     }
//     public String getEmail() {
//         return email;
//     }
//     public void setEmail(String email) {
//         this.email = email;
//     }
//     public String getPassword() {
//         return password;
//     }
//     public void setPassword(String password) {
//         this.password = password;
//     }


//     public Rol getRol() {
//         return rol;
//     }


//     public void setRol(Rol rol) {
//         this.rol = rol;
//     }

    
// }
