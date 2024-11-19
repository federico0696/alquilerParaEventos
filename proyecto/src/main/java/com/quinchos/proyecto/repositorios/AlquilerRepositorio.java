package com.quinchos.proyecto.repositorios;

import com.quinchos.proyecto.entidades.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerRepositorio extends JpaRepository<Alquiler,String> {

}
