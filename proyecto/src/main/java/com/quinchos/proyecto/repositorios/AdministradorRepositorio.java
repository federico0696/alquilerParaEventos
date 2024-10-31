package com.quinchos.proyecto.repositorios;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quinchos.proyecto.entidades.Administrador;

@Repository
public interface AdministradorRepositorio extends JpaRepository<Administrador,String> {
    
    @Query("SELECT a FROM Administrador a WHERE a.email = :email")
    public Administrador buscarPorEmail(@Param("email") String email);

}


