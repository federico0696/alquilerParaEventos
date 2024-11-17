package com.quinchos.proyecto.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false) // Relación con Usuario
    private Usuario usuario; // Usuario que realiza la reserva

    @ManyToOne(optional = false)
    @JoinColumn(name = "inmueble_id", nullable = false) // Relación con Inmueble
    private Inmueble inmueble; // Inmueble reservado

    @Column(nullable = false)
    private LocalDate fechaInicio; // Fecha de inicio de la reserva

    @Column(nullable = false)
    private LocalDate fechaFin; // Fecha de finalización de la reserva

    @Column(nullable = false)
    private Double precioTotal; // Precio total de la reserva

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado; // Pendiente, Confirmada, Cancelada

    public enum EstadoReserva {
        PENDIENTE,
        CONFIRMADA,
        CANCELADA
    }

    // Constructor vacío obligatorio para JPA
    public Reserva() {}

    // Constructor completo
    public Reserva(Usuario usuario, Inmueble inmueble, LocalDate fechaInicio, LocalDate fechaFin, Double precioTotal, EstadoReserva estado) {
        this.usuario = usuario;
        this.inmueble = inmueble;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = precioTotal;
        this.estado = estado;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
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

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }
}
