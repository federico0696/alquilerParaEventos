package com.quinchos.proyecto.repositorios;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quinchos.proyecto.entidades.Inquilino;

@Repository
public interface InquilinoRepositorio extends JpaRepository<Inquilino,String> {
    
    @Query("SELECT i FROM Inquilino i WHERE i.email = :email")
    public Inquilino buscarPorEmail(@Param("email") String email);

}








