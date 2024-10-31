package com.quinchos.proyecto.servicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.entidades.Propietario;
import com.quinchos.proyecto.enumeraciones.Rol;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.repositorios.PropietarioRepositorio;

import jakarta.transaction.Transactional;

@Service
public class PropietarioServicio {
    
    @Autowired
    private PropietarioRepositorio propietarioRepositorio;

    private void validar(String nombre, String direccion, String telefono, String email, String password, String password2) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacío");
        }
        if (direccion.isEmpty() || direccion == null) {
            throw new MiException("la direccion no puede ser nula o estar vacía");
        }
        if (telefono.isEmpty() || telefono == null) {
            throw new MiException("el telefono no puede ser nulo o estar vacío");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("el email no puede ser nulo o estar vacío");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
    }

    @Transactional
    public void registrar(MultipartFile imagen, String nombre, String direccion, String telefono, String email, String password, String password2) throws MiException{

        validar(nombre, direccion, telefono, email, password, password2);

        Path directorioImagenes = Paths.get("src/main/resources/static/img");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try {
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Propietario propietario = new Propietario();

        propietario.setNombre(nombre);
        propietario.setNombre(direccion);
        propietario.setNombre(telefono);
        propietario.setEmail(email);
        propietario.setRol(Rol.PROPIETARIO);
        propietario.setImagen("/img/" + imagen.getOriginalFilename());

        propietarioRepositorio.save(propietario);

    }





}
