package com.quinchos.proyecto.controladores;

import com.quinchos.proyecto.entidades.Alquiler;
import com.quinchos.proyecto.servicios.AlquilerServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alquileres")
public class AlquilerControlador {

    @Autowired
    private AlquilerServicio alquilerServicio;

    /**
     * Registrar un nuevo alquiler.
     * @param alquiler El alquiler a registrar.
     * @return ResponseEntity con el alquiler creado y el código de estado HTTP.
     */
    @PostMapping("/registro")
    public ResponseEntity<?> registrarAlquiler(@RequestBody Alquiler alquiler) {
        try {
            Alquiler alquilerRegistrado = alquilerServicio.crearAlquiler(alquiler);
            return ResponseEntity.status(HttpStatus.CREATED).body(alquilerRegistrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el alquiler: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el alquiler: " + e.getMessage());
        }
    }

    /**
     * Obtener un alquiler por su ID.
     * @param id El ID del alquiler.
     * @return ResponseEntity con el alquiler o error si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAlquiler(@PathVariable Long id) {
        try {
            Alquiler alquiler = alquilerServicio.obtenerAlquiler(id);
            if (alquiler == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alquiler no encontrado.");
            }
            return ResponseEntity.ok(alquiler);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el alquiler: " + e.getMessage());
        }
    }

    /**
     * Obtener todos los alquileres.
     * @return ResponseEntity con una lista de todos los alquileres.
     */
    @GetMapping("/")
    public ResponseEntity<?> obtenerTodosLosAlquileres() {
        try {
            return ResponseEntity.ok(alquilerServicio.obtenerTodosLosAlquileres());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los alquileres: " + e.getMessage());
        }
    }

    /**
     * Actualizar un alquiler existente.
     * @param id El ID del alquiler a actualizar.
     * @param alquiler Los datos actualizados del alquiler.
     * @return ResponseEntity con el alquiler actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAlquiler(@PathVariable Long id, @RequestBody Alquiler alquiler) {
        try {
            Alquiler alquilerActualizado = alquilerServicio.actualizarAlquiler(id, alquiler);
            if (alquilerActualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alquiler no encontrado.");
            }
            return ResponseEntity.ok(alquilerActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el alquiler: " + e.getMessage());
        }
    }

    /**
     * Eliminar un alquiler.
     * @param id El ID del alquiler a eliminar.
     * @return ResponseEntity con un mensaje de éxito o error.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAlquiler(@PathVariable Long id) {
        try {
            alquilerServicio.eliminarAlquiler(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Alquiler eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el alquiler: " + e.getMessage());
        }
    }
}

