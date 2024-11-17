package com.quinchos.proyecto.servicios;

import com.quinchos.proyecto.entidades.*;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;

    @Transactional
    public Reserva crearReserva(Long usuarioId, Long inmuebleId, LocalDate fechaInicio, LocalDate fechaFin) throws MiException {
        // Validar entradas
        validarReserva(usuarioId, inmuebleId, fechaInicio, fechaFin);

        // Obtener usuario
        Usuario usuario = usuarioRepositorio.findById(usuarioId).orElseThrow(
            () -> new MiException("Usuario no encontrado.")
        );

        // Obtener inmueble
        Inmueble inmueble = inmuebleRepositorio.findById(inmuebleId).orElseThrow(
            () -> new MiException("Inmueble no encontrado.")
        );

        if (!inmueble.isDisponible()) {
            throw new MiException("El inmueble no está disponible para la reserva.");
        }

        // Calcular precio total
        long dias = fechaFin.toEpochDay() - fechaInicio.toEpochDay();
        if (dias <= 0) {
            throw new MiException("El rango de fechas es inválido.");
        }
        double precioTotal = dias * inmueble.getPrecioDia();

        // Crear reserva
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setInmueble(inmueble);
        reserva.setFechaInicio(fechaInicio);
        reserva.setFechaFin(fechaFin);
        reserva.setPrecioTotal(precioTotal);
        reserva.setEstado(Reserva.EstadoReserva.PENDIENTE);

        // Marcar inmueble como no disponible
        inmueble.setDisponible(false);
        inmuebleRepositorio.save(inmueble);

        return reservaRepositorio.save(reserva);
    }

    @Transactional
    public List<Reserva> listarReservas() {
        return reservaRepositorio.findAll();
    }

    @Transactional
    public void cancelarReserva(Long reservaId) throws MiException {
        Reserva reserva = reservaRepositorio.findById(reservaId).orElseThrow(
            () -> new MiException("Reserva no encontrada.")
        );

        if (reserva.getEstado() == Reserva.EstadoReserva.CANCELADA) {
            throw new MiException("La reserva ya está cancelada.");
        }

        reserva.setEstado(Reserva.EstadoReserva.CANCELADA);

        // Hacer que el inmueble vuelva a estar disponible
        Inmueble inmueble = reserva.getInmueble();
        inmueble.setDisponible(true);
        inmuebleRepositorio.save(inmueble);

        reservaRepositorio.save(reserva);
    }

    private void validarReserva(Long usuarioId, Long inmuebleId, LocalDate fechaInicio, LocalDate fechaFin) throws MiException {
        if (usuarioId == null) {
            throw new MiException("El ID del usuario no puede ser nulo.");
        }
        if (inmuebleId == null) {
            throw new MiException("El ID del inmueble no puede ser nulo.");
        }
        if (fechaInicio == null || fechaFin == null) {
            throw new MiException("Las fechas no pueden ser nulas.");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new MiException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }
    }
}