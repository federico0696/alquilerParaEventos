package com.quinchos.proyecto.servicios;

import com.quinchos.proyecto.entidades.Alquiler;
import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.entidades.Inquilino;
import com.quinchos.proyecto.entidades.Propietario;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.repositorios.AlquilerRepositorio;
import com.quinchos.proyecto.repositorios.InmuebleRepositorio;
import com.quinchos.proyecto.repositorios.InquilinoRepositorio;
import com.quinchos.proyecto.repositorios.PropietarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class InmuebleServicios {
    @Autowired
    InmuebleRepositorio inmuebleRepositorio;
    @Autowired
    PropietarioRepositorio propietarioRepositorio;
    @Autowired
    InquilinoRepositorio inquilinoRepositorio;
    @Autowired
    AlquilerRepositorio alquilerRepositorio;

    @Transactional
    public void crearInmueble(MultipartFile imagen, String categoria, String ubicacion, Integer capacidad, String descripcion, Integer superficie) throws MiException {

        validar( categoria, ubicacion, capacidad, descripcion, superficie);

        /*Propietario propietario = propietarioRepositorio.findById(idPropietario).get();
        Inquilino inquilino = inquilinoRepositorio.findById(idInquilino).get();
        Alquiler alquiler = alquilerRepositorio.findById(idAlquiler).get();*/

        Path directorioImagenes = Paths.get("src/main/resources/static/img");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try {
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Inmueble inmueble = new Inmueble();


        inmueble.setCategoria(categoria);
        inmueble.setUbicacion(ubicacion);
        inmueble.setCapacidad(capacidad);
        inmueble.setDescripcion(descripcion);
        inmueble.setSuperficie(superficie);


        inmuebleRepositorio.save(inmueble);
    }


    private void validar(String categoria, String ubicacion, Integer capacidad, String descripcion, Integer superficie) throws MiException {

        /*if (idInmueble.isEmpty() || idInmueble == null) {
            throw new MiException("El idInmueble no puede ser nulo o estar vacío");
        }
        if (idPropietario.isEmpty() || idPropietario == null) {
            throw new MiException("El idPropietario no puede ser nulo o estar vacío");
        }
        if (idInquilino.isEmpty() || idInquilino == null) {
            throw new MiException("El idInquilino no puede ser nulo o estar vacío");
        }
        if (idAlquiler.isEmpty() || idAlquiler == null) {
            throw new MiException("El idAlquiler no puede ser nulo o estar vacío");
        }*/
        if (categoria.isEmpty() || categoria == null) {
            throw new MiException("La categoria no puede ser nulo o estar vacío");
        }
        if (ubicacion.isEmpty() || ubicacion == null) {
            throw new MiException("La ubicacion no puede ser nulo o estar vacío");
        }
        if (capacidad == null) {
            throw new MiException("La capacidad no puede ser nulo o estar vacío");
        }
        if (descripcion.isEmpty() || descripcion == null) {
            throw new MiException("La descripcion no puede ser nulo o estar vacio");
        }
        if (superficie == null) {
            throw new MiException("La superficie no puede ser nulo o estar vacio");
        }
    }
}
