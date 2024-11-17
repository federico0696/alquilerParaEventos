package com.quinchos.proyecto.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quinchos.proyecto.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    
    // Método para buscar un usuario por email
    Optional<Usuario> findByEmail(String email);
    
    // Método para verificar si un usuario con el email ya existe
    boolean existsByEmail(String email);  // Agregar esta línea
}

