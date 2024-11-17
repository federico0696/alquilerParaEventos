package com.quinchos.proyecto.controladores;

import com.quinchos.proyecto.entidades.Reserva;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.servicios.ReservaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaServicio;

    /**
     * Crear una nueva reserva.
     * @param usuarioId El ID del usuario que realiza la reserva.
     * @param inmuebleId El ID del inmueble a reservar.
     * @param fechaInicio La fecha de inicio de la reserva.
     * @param fechaFin La fecha de fin de la reserva.
     * @return ResponseEntity con el estado de la operación.
     */
    @PostMapping("/crear")
    public ResponseEntity<?> crearReserva(@RequestParam Long usuarioId, 
                                          @RequestParam Long inmuebleId, 
                                          @RequestParam String fechaInicio, 
                                          @RequestParam String fechaFin) {
        try {
            LocalDate fechaInicioLocalDate = LocalDate.parse(fechaInicio);
            LocalDate fechaFinLocalDate = LocalDate.parse(fechaFin);
            Reserva reserva = reservaServicio.crearReserva(usuarioId, inmuebleId, fechaInicioLocalDate, fechaFinLocalDate);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reserva creada correctamente: " + reserva);
        } catch (MiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear la reserva: " + e.getMessage());
        }
    }

    /**
     * Listar todas las reservas.
     * @return Lista de todas las reservas.
     */
    @GetMapping("/")
    public ResponseEntity<List<Reserva>> listarReservas() {
        try {
            List<Reserva> reservas = reservaServicio.listarReservas();
            return ResponseEntity.ok(reservas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Cancelar una reserva.
     * @param reservaId El ID de la reserva a cancelar.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/{reservaId}")
    public ResponseEntity<?> cancelarReserva(@PathVariable Long reservaId) {
        try {
            reservaServicio.cancelarReserva(reservaId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Reserva cancelada correctamente.");
        } catch (MiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al cancelar la reserva: " + e.getMessage());
        }
    }
}

