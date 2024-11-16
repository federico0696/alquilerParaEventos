package com.quinchos.proyecto.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quinchos.proyecto.entidades.Inmueble;

@Repository
public interface InmuebleRepositorio extends JpaRepository<Inmueble, String> {

       @Query("SELECT i FROM Inmueble i WHERE "
                     + "(:categoria IS NULL OR i.categoria = :categoria) "
                     + "AND (:localidad IS NULL OR i.localidad = :localidad) "
                     + "AND (:capacidad IS NULL OR i.capacidad >= :capacidad) "
                     + "AND (:superficie IS NULL OR i.superficie >= :superficie)")
       List<Inmueble> buscarInmuebles(
                     @Param("categoria") String categoria,
                     @Param("localidad") String localidad,
                     @Param("capacidad") Integer capacidad,
                     @Param("superficie") Integer superficie);

}
