package com.quinchos.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quinchos.proyecto.entidades.Alquiler;

@Repository
public interface AlquilerRepositorio extends JpaRepository<Alquiler, String> {
    

    
}
