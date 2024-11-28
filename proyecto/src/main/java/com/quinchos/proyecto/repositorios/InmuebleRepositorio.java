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
                     + "AND (:superficie IS NULL OR i.superficie >= :superficie) "
                     + "AND (:servicio1 IS NULL OR i.servicios LIKE %:servicio1%) "
                     + "AND (:servicio2 IS NULL OR i.servicios LIKE %:servicio2%) "
                     + "AND (:servicio3 IS NULL OR i.servicios LIKE %:servicio3%) "
                     + "AND (:servicio4 IS NULL OR i.servicios LIKE %:servicio4%) "
                     + "AND (:servicio5 IS NULL OR i.servicios LIKE %:servicio5%) "
                     + "AND (:servicio6 IS NULL OR i.servicios LIKE %:servicio6%) "
                     + "AND (:servicio7 IS NULL OR i.servicios LIKE %:servicio7%) "
                     + "AND (:servicio8 IS NULL OR i.servicios LIKE %:servicio8%) "
                     + "AND (:servicio9 IS NULL OR i.servicios LIKE %:servicio9%) "
                     + "AND (:servicio10 IS NULL OR i.servicios LIKE %:servicio10%)")
       List<Inmueble> buscarInmuebles(
                     @Param("categoria") String categoria,
                     @Param("localidad") String localidad,
                     @Param("capacidad") Integer capacidad,
                     @Param("superficie") Integer superficie,
                     @Param("servicio1") String servicio1,
                     @Param("servicio2") String servicio2,
                     @Param("servicio3") String servicio3,
                     @Param("servicio4") String servicio4,
                     @Param("servicio5") String servicio5,
                     @Param("servicio6") String servicio6,
                     @Param("servicio7") String servicio7,
                     @Param("servicio8") String servicio8,
                     @Param("servicio9") String servicio9,
                     @Param("servicio10") String servicio10);

}
