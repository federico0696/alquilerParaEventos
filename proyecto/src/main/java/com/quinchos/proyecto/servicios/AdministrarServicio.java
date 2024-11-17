package com.quinchos.proyecto.servicios;

import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.entidades.Rol;

import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.repositorios.InmuebleRepositorio;
import com.quinchos.proyecto.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrarServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;

    // 1. **Administrar Usuarios**

    /**
     * Crear un nuevo usuario
     */
    public Usuario crearUsuario(Usuario usuario) throws MiException {
        if (usuarioRepositorio.existsByEmail(usuario.getEmail())) {
            throw new MiException("Ya existe un usuario con el mismo correo.");
        }
        return usuarioRepositorio.save(usuario);
    }

    /**
     * Obtener todos los usuarios
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    /**
     * Obtener usuario por ID
     */
    public Usuario obtenerUsuarioPorId(Long id) throws MiException {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        if (usuario.isEmpty()) {
            throw new MiException("Usuario no encontrado.");
        }
        return usuario.get();
    }

    /**
     * Actualizar un usuario
     */
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) throws MiException {
        Usuario usuarioExistente = obtenerUsuarioPorId(id);
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setRol(usuarioActualizado.getRol());
        usuarioExistente.setActivo(usuarioActualizado.isActivo());
        return usuarioRepositorio.save(usuarioExistente);
    }

    /**
     * Eliminar un usuario
     */
    public void eliminarUsuario(Long id) throws MiException {
        Usuario usuario = obtenerUsuarioPorId(id);
        usuarioRepositorio.delete(usuario);
    }

    // 2. **Administrar Inmuebles**

    /**
     * Crear un nuevo inmueble
     */
    public Inmueble crearInmueble(Inmueble inmueble) throws MiException {
        return inmuebleRepositorio.save(inmueble);
    }

    /**
     * Obtener todos los inmuebles
     */
    public List<Inmueble> obtenerTodosLosInmuebles() {
        return inmuebleRepositorio.findAll();
    }

    /**
     * Obtener inmueble por ID
     */
    public Inmueble obtenerInmueblePorId(Long id) throws MiException {
        Optional<Inmueble> inmueble = inmuebleRepositorio.findById(id);
        if (inmueble.isEmpty()) {
            throw new MiException("Inmueble no encontrado.");
        }
        return inmueble.get();
    }

    /**
     * Actualizar un inmueble
     */
    public Inmueble actualizarInmueble(Long id, Inmueble inmuebleActualizado) throws MiException {
        Inmueble inmuebleExistente = obtenerInmueblePorId(id);
        inmuebleExistente.setCategoria(inmuebleActualizado.getCategoria());
        inmuebleExistente.setLocalidad(inmuebleActualizado.getLocalidad());
        inmuebleExistente.setDescripcion(inmuebleActualizado.getDescripcion());
        inmuebleExistente.setPrecioDia(inmuebleActualizado.getPrecioDia());
        inmuebleExistente.setCapacidadPersonas(inmuebleActualizado.getCapacidadPersonas());
        inmuebleExistente.setDisponible(inmuebleActualizado.isDisponible());
        inmuebleExistente.setServicios(inmuebleActualizado.getServicios());
        return inmuebleRepositorio.save(inmuebleExistente);
    }

    /**
     * Eliminar un inmueble
     */
    public void eliminarInmueble(Long id) throws MiException {
        Inmueble inmueble = obtenerInmueblePorId(id);
        inmuebleRepositorio.delete(inmueble);
    }

    // 3. **Gestionar Permisos según Roles**

    /**
     * Verificar si un usuario tiene permisos de administrador
     */
    public boolean esAdministrador(Usuario usuario) {
        return usuario.getRol() == Rol.ADMINISTRADOR;
    }
}