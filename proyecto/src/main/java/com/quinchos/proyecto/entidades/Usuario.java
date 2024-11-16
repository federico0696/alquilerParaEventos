package com.quinchos.proyecto.entidades;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 150, nullable = false, unique = true)
    @Email
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    private boolean activo;
    
    // Constructor vacío obligatorio en JPA
    public Usuario() {}
    
    // Constructor con todos los atributos
    public Usuario(String nombre, String email, Rol rol, boolean activo) {
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.activo = activo;
    }
    
    // GETTERS - SETTERS
    public Long getUsuarioId() {
        return usuarioId;
    }
    
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Sobrescritura de equals() y hashCode() para comparar por usuarioId
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return usuarioId != null && usuarioId.equals(usuario.usuarioId);
    }

    @Override
    public int hashCode() {
        return 31 * (usuarioId != null ? usuarioId.hashCode() : 0);
    }
}