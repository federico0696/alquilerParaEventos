package com.quinchos.proyecto.servicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.entidades.Inquilino;
import com.quinchos.proyecto.enumeraciones.Rol;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.repositorios.InquilinoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class InquilinoServicio {
    
    @Autowired
    private InquilinoRepositorio inquilinoRepositorio;

    private void validar(String nombre, String telefono, String email, String password, String password2) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacío");
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
    public void registrar(MultipartFile imagen, String nombre, String telefono, String email, String password, String password2) throws MiException{

        validar(nombre, telefono, email, password, password2);

        Path directorioImagenes = Paths.get("src/main/resources/static/img");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try {
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Inquilino inquilino = new Inquilino();

        inquilino.setNombre(nombre);
        inquilino.setNombre(telefono);
        inquilino.setEmail(email);
        inquilino.setRol(Rol.INQUILINO);
        inquilino.setImagen("/img/" + imagen.getOriginalFilename());

        inquilinoRepositorio.save(inquilino);

    }




}
