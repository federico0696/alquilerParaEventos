package com.quinchos.proyecto.entidades;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Alquiler {
    
    @Id
@GeneratedValue(strategy = GenerationType.AUTO) // Hibernate manejará la generación de UUID automáticamente
@Column(name = "alquiler_id", nullable = false, updatable = false) // Especificamos el nombre de la columna
private Long alquilerId; // Usamos UUID para mantener la integridad del tipo de dato


    // Cambié de Integer a LocalDate para representar correctamente fechas de calendario
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private Integer precioDia;

    // Manejaremos el precio total mediante un cálculo para evitar inconsistencias
    public Alquiler() {
    }

    public Long getAlquilerId() {
        return alquilerId;
    }

    public void setAlquilerId(Long alquilerId) {
        this.alquilerId = alquilerId;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(Integer precioDia) {
        this.precioDia = precioDia;
    }

    // Calcular el precio total basado en el precio por día, considerando las fechas de inicio y fin
    public Integer getPrecioTotal() {
        if (fechaInicio != null && fechaFin != null && precioDia != null) {
            long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
            return (int) dias * precioDia;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alquiler alquiler = (Alquiler) o;
        return alquilerId.equals(alquiler.alquilerId);
    }

    @Override
    public int hashCode() {
        return alquilerId.hashCode();
    }
}