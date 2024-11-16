package com.quinchos.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quinchos.proyecto.entidades.Alquiler;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlquilerRepositorio extends JpaRepository<Alquiler, Long> {

    // Encuentra alquileres dentro de un rango de fechas
    List<Alquiler> findByFechaInicioBetween(LocalDate inicio, LocalDate fin);

    // Encuentra alquileres por precio diario dentro de un rango
    List<Alquiler> findByPrecioDiaBetween(Integer precioMin, Integer precioMax);

    // Encuentra alquileres que comienzan en una fecha específica
    List<Alquiler> findByFechaInicio(LocalDate fechaInicio);

    // Encuentra alquileres activos en una fecha específica
    List<Alquiler> findByFechaInicioBeforeAndFechaFinAfter(LocalDate fecha);

    // Ordena los alquileres por fecha de inicio en orden descendente
    List<Alquiler> findAllByOrderByFechaInicioDesc();
}
