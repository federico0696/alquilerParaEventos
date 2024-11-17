package com.quinchos.proyecto.servicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.entidades.Rol;
import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.repositorios.UsuarioRepositorio;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Validación de los datos al registrar un nuevo usuario
    private void validarUsuario(String nombre, String email, String password, String password2) throws MiException {
        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre no puede ser nulo o estar vacío");
        }
        if (email == null || email.isEmpty()) {
            throw new MiException("El email no puede ser nulo o estar vacío");
        }
        if (password == null || password.isEmpty() || password.length() <= 5) {
            throw new MiException("La contraseña debe tener más de 5 caracteres");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
    }

    @Transactional
    public void registrarUsuario(String nombre, String email, String password, String password2, Rol rol) throws MiException {
        // Validación de los datos
        validarUsuario(nombre, email, password, password2);

        // Verifica si ya existe un usuario con el mismo email
        Optional<Usuario> usuarioExistente = usuarioRepositorio.findByEmail(email);
        if (usuarioExistente.isPresent()) {
            throw new MiException("Ya existe un usuario con ese email");
        }

        // Crear un nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(passwordEncoder.encode(password)); // Codifica la contraseña
        usuario.setRol(rol); // Asigna el rol
        usuario.setActivo(true); // El usuario está activo por defecto

        // Guardar el usuario en la base de datos
        usuarioRepositorio.save(usuario);
    }

    // Actualizar los datos de un usuario
    @Transactional
    public void actualizarUsuario(Long usuarioId, String nombre, String email, String password) throws MiException {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(usuarioId);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            if (nombre != null && !nombre.isEmpty()) {
                usuario.setNombre(nombre);
            }
            if (email != null && !email.isEmpty()) {
                usuario.setEmail(email);
            }
            if (password != null && !password.isEmpty()) {
                usuario.setPassword(passwordEncoder.encode(password)); // Codifica la contraseña
            }
            usuarioRepositorio.save(usuario);
        } else {
            throw new MiException("Usuario no encontrado");
        }
    }

    // Actualizar la imagen de perfil del usuario
    @Transactional
    public void actualizarImagenPerfil(Long usuarioId, MultipartFile imagen) throws MiException {
        // Lógica para guardar la imagen
        String imagenUrl = guardarImagen(imagen);

        Optional<Usuario> respuesta = usuarioRepositorio.findById(usuarioId);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setImagen(imagenUrl);
            usuarioRepositorio.save(usuario);
        } else {
            throw new MiException("Usuario no encontrado");
        }
    }

    // Guardar la imagen en el servidor y devolver la URL accesible
    private String guardarImagen(MultipartFile imagen) throws MiException {
        String imagenUrl = "/img/" + imagen.getOriginalFilename();
        try {
            byte[] bytesImg = imagen.getBytes();
            // Construye la ruta absoluta donde se guardará la imagen
            java.nio.file.Path ruta = Paths.get("src/main/resources/static/img");
            java.nio.file.Path rutaCompleta = ruta.resolve(imagen.getOriginalFilename()); // Agrega el nombre del archivo a la ruta
            Files.write(rutaCompleta, bytesImg); // Usa rutaCompleta para escribir la imagen
        } catch (IOException e) {
            throw new MiException("Error al guardar la imagen: " + e.getMessage());
        }
        return imagenUrl; // Devuelve la URL relativa de la imagen
    }
    

    @Transactional
    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    // Método para cargar los detalles del usuario por su email (para autenticación)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        List<GrantedAuthority> permisos = new ArrayList<>();
        // Usamos el rol del usuario para crear permisos
        GrantedAuthority permiso = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
        permisos.add(permiso);

        return new User(usuario.getEmail(), usuario.getPassword(), permisos);
    }

    public Usuario registrarUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarUsuario'");
    }

    public Usuario obtenerUsuario(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerUsuario'");
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarUsuario'");
    }

    public void eliminarUsuario(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarUsuario'");
    }
}