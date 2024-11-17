package com.quinchos.proyecto.controladores;

import com.quinchos.proyecto.entidades.Rol;
import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    /**
     * Registrar un nuevo usuario.
     * @param nombre El nombre del usuario.
     * @param email El email del usuario.
     * @param password La contraseña del usuario.
     * @param password2 Confirmación de la contraseña.
     * @param rol El rol del usuario.
     * @return ResponseEntity con el estado de la operación.
     */
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestParam String nombre, 
                                              @RequestParam String email,
                                              @RequestParam String password, 
                                              @RequestParam String password2, 
                                              @RequestParam Rol rol) {
        try {
            usuarioServicio.registrarUsuario(nombre, email, password, password2, rol);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado correctamente");
        } catch (MiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el usuario: " + e.getMessage());
        }
    }

    /**
     * Obtener todos los usuarios.
     * @return Lista de usuarios.
     */
    @GetMapping("/")
    public ResponseEntity<?> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioServicio.listarUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los usuarios: " + e.getMessage());
        }
    }

    /**
     * Obtener un usuario por su ID.
     * @param id El ID del usuario.
     * @return ResponseEntity con el usuario encontrado o un mensaje de error.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioServicio.obtenerUsuario(id);
            return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el usuario: " + e.getMessage());
        }
    }

    /**
     * Actualizar un usuario.
     * @param id El ID del usuario a actualizar.
     * @param nombre Nuevo nombre del usuario.
     * @param email Nuevo email del usuario.
     * @param password Nueva contraseña del usuario.
     * @return ResponseEntity con el estado de la operación.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id,
                                               @RequestParam(required = false) String nombre,
                                               @RequestParam(required = false) String email,
                                               @RequestParam(required = false) String password) {
        try {
            usuarioServicio.actualizarUsuario(id, nombre, email, password);
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (MiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    /**
     * Actualizar la imagen de perfil de un usuario.
     * @param id El ID del usuario.
     * @param imagen El archivo de imagen para actualizar.
     * @return ResponseEntity con el estado de la operación.
     */
    @PutMapping("/{id}/imagen")
    public ResponseEntity<?> actualizarImagenPerfil(@PathVariable Long id, @RequestParam MultipartFile imagen) {
        try {
            usuarioServicio.actualizarImagenPerfil(id, imagen);
            return ResponseEntity.ok("Imagen de perfil actualizada correctamente");
        } catch (MiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la imagen de perfil: " + e.getMessage());
        }
    }

    /**
     * Eliminar un usuario por su ID.
     * @param id El ID del usuario.
     * @return ResponseEntity con un mensaje de éxito o error.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioServicio.eliminarUsuario(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}