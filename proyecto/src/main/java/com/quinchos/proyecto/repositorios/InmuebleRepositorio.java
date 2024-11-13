package com.quinchos.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quinchos.proyecto.entidades.Inmueble;

@Repository
public interface InmuebleRepositorio extends JpaRepository<Inmueble,String>{
    
}
