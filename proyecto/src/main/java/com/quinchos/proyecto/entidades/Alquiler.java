package com.quinchos.proyecto.entidades;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "alquiler_id", nullable = false, updatable = false) // Nombre de la columna
    private Long alquilerId; //

    // Cambié de Integer a LocalDate para representar correctamente fechas de calendario
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private Integer precioDia;

    // El precio total es mejor hacer el cálculo para evitar inconsistencias; abajo el cálculo
    
    public Alquiler() {
    }

    @ManyToOne
    @JoinColumn(name = "inmueble_id", nullable = false) // Foreign key en la tabla Alquiler
    private Inmueble inmueble;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // GETTERS Y SETTERS
    // //////////////////////////////////////////////////////////////////////////////////////////
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
        if (this.fechaFin != null && fechaInicio.isAfter(this.fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser después de la fecha de fin");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        if (this.fechaInicio != null && fechaFin.isBefore(this.fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser antes de la fecha de inicio");
        }
        this.fechaFin = fechaFin;
    }

    public Integer getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(Integer precioDia) {
        this.precioDia = precioDia;
    }




    // Calculo del precio total basado en el precio por día, considerando las fechas de inicio y fin
    public Integer getPrecioTotal() {
        if (fechaInicio != null && fechaFin != null && precioDia != null) {
            long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
            return (int) dias * precioDia;
        }
        return 0;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }
    
    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Alquiler alquiler = (Alquiler) o;
        return alquilerId.equals(alquiler.alquilerId);
    }

    @Override
    public int hashCode() {
        return alquilerId.hashCode();
    }
}