package com.quinchos.proyecto.controladores;

import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.servicios.AdministrarServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administracion")
public class AdministracionControlador {

    @Autowired
    private AdministrarServicio administrarServicio;

    // **1. Administrar Usuarios**

    /**
     * Crear un nuevo usuario.
     * @param usuario El objeto usuario a crear.
     * @return ResponseEntity con el estado de la operación.
     */
    @PostMapping("/usuarios/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        // No es necesario capturar excepciones aquí, el GlobalExceptionHandler se encarga de la MiException
        Usuario nuevoUsuario = administrarServicio.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    /**
     * Obtener todos los usuarios.
     * @return Lista de usuarios.
     */
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = administrarServicio.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    /**
     * Obtener usuario por ID.
     * @param id El ID del usuario.
     * @return ResponseEntity con el usuario o mensaje de error.
     */
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = administrarServicio.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    /**
     * Actualizar un usuario.
     * @param id El ID del usuario a actualizar.
     * @param usuario El objeto usuario con los datos actualizados.
     * @return ResponseEntity con el estado de la operación.
     */
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = administrarServicio.actualizarUsuario(id, usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    /**
     * Eliminar un usuario.
     * @param id El ID del usuario a eliminar.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        administrarServicio.eliminarUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // **2. Administrar Inmuebles**

    /**
     * Crear un nuevo inmueble.
     * @param inmueble El objeto inmueble a crear.
     * @return ResponseEntity con el estado de la operación.
     */
    @PostMapping("/inmuebles/crear")
    public ResponseEntity<Inmueble> crearInmueble(@RequestBody Inmueble inmueble) {
        Inmueble nuevoInmueble = administrarServicio.crearInmueble(inmueble);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInmueble);
    }

    /**
     * Obtener todos los inmuebles.
     * @return Lista de inmuebles.
     */
    @GetMapping("/inmuebles")
    public ResponseEntity<List<Inmueble>> obtenerTodosLosInmuebles() {
        List<Inmueble> inmuebles = administrarServicio.obtenerTodosLosInmuebles();
        return ResponseEntity.ok(inmuebles);
    }

    /**
     * Obtener inmueble por ID.
     * @param id El ID del inmueble.
     * @return ResponseEntity con el inmueble o mensaje de error.
     */
    @GetMapping("/inmuebles/{id}")
    public ResponseEntity<Inmueble> obtenerInmueblePorId(@PathVariable Long id) {
        Inmueble inmueble = administrarServicio.obtenerInmueblePorId(id);
        return ResponseEntity.ok(inmueble);
    }

    /**
     * Actualizar un inmueble.
     * @param id El ID del inmueble a actualizar.
     * @param inmueble El objeto inmueble con los datos actualizados.
     * @return ResponseEntity con el estado de la operación.
     */
    @PutMapping("/inmuebles/{id}")
    public ResponseEntity<Inmueble> actualizarInmueble(@PathVariable Long id, @RequestBody Inmueble inmueble) {
        Inmueble inmuebleActualizado = administrarServicio.actualizarInmueble(id, inmueble);
        return ResponseEntity.ok(inmuebleActualizado);
    }

    /**
     * Eliminar un inmueble.
     * @param id El ID del inmueble a eliminar.
     * @return ResponseEntity con el estado de la operación.
     */
    @DeleteMapping("/inmuebles/{id}")
    public ResponseEntity<Void> eliminarInmueble(@PathVariable Long id) {
        administrarServicio.eliminarInmueble(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // **3. Gestionar permisos de administrador**

    /**
     * Verificar si un usuario es administrador.
     * @param usuarioId El ID del usuario.
     * @return Respuesta con el estado de la verificación.
     */
    @GetMapping("/usuarios/{usuarioId}/es-administrador")
    p
