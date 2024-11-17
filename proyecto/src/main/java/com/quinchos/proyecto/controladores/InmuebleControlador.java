package com.quinchos.proyecto.controladores;

import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.servicios.InmuebleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/inmuebles")
public class InmuebleControlador {

    @Autowired
    private InmuebleServicio inmuebleServicio;

    /**
     * Crear un nuevo inmueble.
     * 
     * @param propietarioId El ID del propietario.
     * @param categoria     La categoría del inmueble.
     * @param localidad     La localidad del inmueble.
     * @param ubicacion     La ubicación del inmueble.
     * @param capacidad     La capacidad del inmueble.
     * @param superficie    La superficie del inmueble.
     * @param precio        El precio por día del inmueble.
     * @param imagen        La imagen del inmueble.
     * @param servicios     Los servicios asociados al inmueble.
     * @param descripcion   La descripción del inmueble.
     * @return ResponseEntity con el estado de la operación.
     */
    @PostMapping("/crear")
    public ResponseEntity<?> crearInmueble(@RequestParam Long propietarioId,
            @RequestParam String categoria,
            @RequestParam String localidad,
            @RequestParam String ubicacion,
            @RequestParam Integer capacidad,
            @RequestParam Integer superficie,
            @RequestParam Integer precio,
            @RequestParam MultipartFile imagen,
            @RequestParam(required = false) String[] servicios,
            @RequestParam String descripcion) throws MiException { // Declarar 'throws MiException'
        inmuebleServicio.crearInmueble(propietarioId, categoria, localidad, ubicacion, capacidad, superficie, precio,
                imagen, servicios, descripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body("Inmueble creado correctamente");
    }

    /**
     * Obtener todos los inmuebles.
     * 
     * @return Lista de inmuebles.
     */
    @GetMapping("/")
    public ResponseEntity<?> listarInmuebles() {
        return ResponseEntity.ok(inmuebleServicio.listarInmuebles());
    }

    /**
     * Obtener un inmueble por su ID.
     * 
     * @param id El ID del inmueble.
     * @return ResponseEntity con el inmueble o un mensaje de error.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerInmueble(@PathVariable Long id) {
        Inmueble inmueble = inmuebleServicio.obtenerInmueble(id);
        return inmueble != null ? ResponseEntity.ok(inmueble)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inmueble no encontrado");
    }

    /**
     * Actualizar un inmueble.
     * 
     * @param id          El ID del inmueble a actualizar.
     * @param categoria   La nueva categoría del inmueble.
     * @param localidad   La nueva localidad del inmueble.
     * @param ubicacion   La nueva ubicación del inmueble.
     * @param capacidad   La nueva capacidad del inmueble.
     * @param superficie  La nueva superficie del inmueble.
     * @param precio      El nuevo precio por día del inmueble.
     * @param imagen      La nueva imagen del inmueble.
     * @param servicios   Los nuevos servicios asociados al inmueble.
     * @param descripcion La nueva descripción del inmueble.
     * @return ResponseEntity con el estado de la operación.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarInmueble(@PathVariable Long id,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String localidad,
            @RequestParam(required = false) String ubicacion,
            @RequestParam(required = false) Integer capacidad,
            @RequestParam(required = false) Integer superficie,
            @RequestParam(required = false) Integer precio,
            @RequestParam(required = false) MultipartFile imagen,
            @RequestParam(required = false) String[] servicios,
            @RequestParam(required = false) String descripcion) {
        inmuebleServicio.actualizarInmueble(id, categoria, localidad, ubicacion, capacidad, superficie, precio, imagen,
                servicios, descripcion);
        return ResponseEntity.ok("Inmueble actualizado correctamente");
    }

    /**
     * Eliminar un inmueble por su ID.
     * 
     * @param id El ID del inmueble.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarInmueble(@PathVariable Long id) {
        inmuebleServicio.eliminarInmueble(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Inmueble eliminado correctamente");
    }
}
