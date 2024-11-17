package com.quinchos.proyecto.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quinchos.proyecto.entidades.CategoriaInmueble;
import com.quinchos.proyecto.entidades.Inmueble;

@Repository
public interface InmuebleRepositorio extends JpaRepository<Inmueble, Long> {

    // Para consultar inmuebles por propietario
    List<Inmueble> findByPropietarioId(Long propietarioId);

    // Para consultar inmuebles disponibles
    List<Inmueble> findByDisponibleTrue();

    // Para consultar inmuebles por categoría
    List<Inmueble> findByCategoria(CategoriaInmueble categoria);

    // Consultas avanzadas con JPQL
    @Query("SELECT i FROM Inmueble i WHERE i.localidad = :localidad AND i.disponible = true")
    List<Inmueble> findDisponiblesPorLocalidad(String localidad);

    // Buscar inmuebles que contienen un servicio específico
    @Query("SELECT i FROM Inmueble i WHERE :servicio MEMBER OF i.servicios AND i.disponible = true")
    List<Inmueble> findByServicio(String servicio);

    // Consultar inmuebles ordenados por precio ascendente
    List<Inmueble> findAllByOrderByPrecioDiaAsc();

    // Consultar inmuebles ordenados por capacidad descendente
    List<Inmueble> findAllByOrderByCapacidadPersonasDesc();
}

