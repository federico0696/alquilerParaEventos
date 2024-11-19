package com.quinchos.proyecto.repositorios;




import com.quinchos.proyecto.enumeraciones.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.enumeraciones.Rol;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,String> {
    List<Usuario> findByRol(Rol rol); //agrega Diego para AdminControlador

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);

}
