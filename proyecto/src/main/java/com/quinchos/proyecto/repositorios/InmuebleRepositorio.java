package com.quinchos.proyecto.repositorios;

import com.quinchos.proyecto.entidades.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InmuebleRepositorio extends JpaRepository<Inmueble,Integer> {

    @Query("SELECT i FROM Inmueble i WHERE i.idInmueble = :idInmueble")
    public Inmueble buscarPorIdInmueble(@Param("idInmueble") String idInmueble);

    @Query("SELECT i FROM Inmueble i WHERE i.propietario.idPropietario = :idPropietario")
    public List<Inmueble> buscarPorPropietario(@Param("idPropietario") String idPropietario);

    
}
