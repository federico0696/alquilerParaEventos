package com.quinchos.proyecto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.servicios.ComparacionServicio;

@RestController
@RequestMapping("/comparaciones")
public class ComparacionControlador {

    @Autowired
    private ComparacionServicio comparacionServicio;

    /**
     * Comparar inmuebles por precio (ascendente).
     * @return Lista de inmuebles ordenados por precio ascendente.
     */
    @GetMapping("/por-precio")
    public ResponseEntity<?> compararPorPrecio() {
        try {
            List<Inmueble> inmuebles = comparacionServicio.compararPorPrecio();
            return ResponseEntity.ok(inmuebles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al comparar los inmuebles por precio: " + e.getMessage());
        }
    }

    /**
     * Comparar inmuebles por capacidad (descendente).
     * @return Lista de inmuebles ordenados por capacidad descendente.
     */
    @GetMapping("/por-capacidad")
    public ResponseEntity<?> compararPorCapacidad() {
        try {
            List<Inmueble> inmuebles = comparacionServicio.compararPorCapacidad();
            return ResponseEntity.ok(inmuebles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al comparar los inmuebles por capacidad: " + e.getMessage());
        }
    }

    /**
     * Comparar inmuebles por localidad.
     * @param localidad La localidad por la que filtrar.
     * @return Lista de inmuebles en la localidad especificada.
     */
    @GetMapping("/por-localidad/{localidad}")
    public ResponseEntity<?> compararPorLocalidad(@PathVariable String localidad) {
        try {
            List<Inmueble> inmuebles = comparacionServicio.compararPorLocalidad(localidad);
            return ResponseEntity.ok(inmuebles);
        } catch (MiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al comparar los inmuebles por localidad: " + e.getMessage());
        }
    }

    /**
     * Comparar inmuebles por servicio.
     * @param servicio El servicio a buscar.
     * @return Lista de inmuebles que contienen el servicio especificado.
     */
    @GetMapping("/por-servicio/{servicio}")
    public ResponseEntity<?> compararPorServicio(@PathVariable String servicio) {
        try {
            List<Inmueble> inmuebles = comparacionServicio.compararPorServicio(servicio);
            return ResponseEntity.ok(inmuebles);
        } catch (MiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al comparar los inmuebles por servicio: " + e.getMessage());
        }
    }

    /**
     * Comparar dos inmuebles por su ID.
     * @param inmuebleId1 El ID del primer inmueble.
     * @param inmuebleId2 El ID del segundo inmueble.
     * @return ResponseEntity con los resultados de la comparación.
     */
    @GetMapping("/inmuebles/{inmuebleId1}/{inmuebleId2}")
    public ResponseEntity<?> compararInmuebles(@PathVariable Long inmuebleId1, @PathVariable Long inmuebleId2) {
        try {
            List<String> comparacion = comparacionServicio.compararInmuebles(inmuebleId1, inmuebleId2);
            return ResponseEntity.ok(comparacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al comparar los inmuebles: " + e.getMessage());
        }
    }

    /**
     * Comparar múltiples inmuebles por sus IDs.
     * @param inmueblesIds Lista de IDs de los inmuebles a comparar.
     * @return ResponseEntity con los resultados de la comparación.
     */
    @PostMapping("/inmuebles")
    public ResponseEntity<?> compararMultiplesInmuebles(@RequestBody List<Long> inmueblesIds) {
        try {
            List<String> comparacion = comparacionServicio.compararMultiplesInmuebles(inmueblesIds);
            return ResponseEntity.ok(comparacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al comparar los inmuebles: " + e.getMessage());
        }
    }

    /**
     * Obtener el historial de comparaciones.
     * @return ResponseEntity con el historial de comparaciones previas.
     */
    @GetMapping("/historial")
    public ResponseEntity<?> obtenerHistorialComparaciones() {
        try {
            List<String> historial = comparacionServicio.obtenerHistorialComparaciones();
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el historial de comparaciones: " + e.getMessage());
        }
    }
}
