package com.quinchos.proyecto.entidades;

import jakarta.persistence.Entity;

@Entity
public class Inmueble {

    private String idInmueble;

    private String idPropietario;

    private String categoria;

    private String ubicacion;

    private Integer capacidad;

    private boolean disponible;

    private String descripcion;

    private Integer precioDia;

    private Integer superficie;

    private String imagenes;

    private String Servicios;

    private String comentarios;

    private String fotoComentario;

    public Inmueble() {
    }

    public String getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(String idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(Integer precioDia) {
        this.precioDia = precioDia;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }

    public String getServicios() {
        return Servicios;
    }

    public void setServicios(String servicios) {
        Servicios = servicios;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getFotoComentario() {
        return fotoComentario;
    }

    public void setFotoComentario(String fotoComentario) {
        this.fotoComentario = fotoComentario;
    }

    


    
}
