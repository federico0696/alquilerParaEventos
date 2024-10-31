package com.quinchos.proyecto.repositorios;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quinchos.proyecto.entidades.Propietario;

@Repository
public interface PropietarioRepositorio extends JpaRepository<Propietario,String> {

    @Query("SELECT p FROM Propietario p WHERE p.email = :email")
    public Propietario buscarPorEmail(@Param("email") String email);

}



