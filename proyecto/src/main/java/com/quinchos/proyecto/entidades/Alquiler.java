package com.quinchos.proyecto.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Alquiler {
    
    @Id
    @GeneratedValue (generator = "uuid") 
    @GenericGenerator (name ="uuid", strategy = "uuid2")
    private String idAlquiler;

    private Integer fechaInicio;

    private Integer fechaFin;

    private Integer precioDia;

    private Integer precioTotal;

    public Alquiler() {
    }

    public String getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(String idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Integer getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Integer fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Integer fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(Integer precioDia) {
        this.precioDia = precioDia;
    }

    public Integer getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Integer precioTotal) {
        this.precioTotal = precioTotal;
    }



    
}
