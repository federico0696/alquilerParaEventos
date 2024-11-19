package com.quinchos.proyecto.servicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.repositorios.InmuebleRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InmuebleServicio {

    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;

    private void validar(String categoria, String localidad, String ubicacion, Integer capacidad, Integer superficie, Integer precioDia) throws MiException {
        if (categoria == null || categoria.isEmpty()) {
            throw new MiException("La categoria no puede ser nula o estar vacía");
        }
        if (localidad == null || localidad.isEmpty()) {
            throw new MiException("La localidad no puede ser nula o estar vacía");
        }
        if (ubicacion == null || ubicacion.isEmpty()) {
            throw new MiException("La ubicación no puede ser nula o estar vacía");
        }
        if (capacidad == null) {
            throw new MiException("La capacidad no puede ser nula");
        }
        if (superficie == null) {
            throw new MiException("La superficie no puede ser nula");
        }
        if (precioDia == null) {
            throw new MiException("El precio no puede ser nulo");
        }
    }

    

    public void crearInmueble(String id, String categoria, String localidad, String ubicacion, Integer capacidad, Integer superficie, Integer precio, MultipartFile imagen, String[] servicios, String descripcion) throws MiException {
        validar(categoria, localidad, ubicacion, capacidad, superficie, precio);


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
        inmueble.setLocalidad(localidad);
        inmueble.setUbicacion(ubicacion);
        inmueble.setCapacidad(capacidad);
        inmueble.setSuperficie(superficie);
        inmueble.setPrecioDia(precio);
        inmueble.setDescripcion(descripcion);
        inmueble.setDisponible(true);
        inmueble.setIdPropietario(id);
        inmueble.setImagen("/img/" + imagen.getOriginalFilename());

        if (servicios != null) {
            inmueble.setServicios(servicios);
        }

        inmuebleRepositorio.save(inmueble);
    }

    public List<Inmueble> buscarInmueble(String categoria, String localidad, Integer capacidad, Integer superficie) throws MiException {
        
        if (localidad != null && localidad.isEmpty()) {
            localidad = null;
        }
        if (categoria != null && categoria.isEmpty()) {
            categoria = null;
        }
        
        return inmuebleRepositorio.buscarInmuebles(categoria, localidad, capacidad, superficie);
    }

    public Inmueble obtenerInmueblePorId(String idInmueble) {
        return inmuebleRepositorio.findById(idInmueble)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el inmueble con id: " + idInmueble));
    }

//Agrega diego linea 95 a 101
    public List<Inmueble> listarInmuebles() {
        return inmuebleRepositorio.findAll(); // Obtén todos los inmuebles
    }

    public void eliminarInmueble(String id) {
        inmuebleRepositorio.deleteById(id); // Elimina el inmueble por ID
    }

    //Agrega diego linea 95 a 101
    public List<Inmueble> listarInmuebles() {
        return inmuebleRepositorio.findAll(); // Obtén todos los inmuebles
    }

    public void eliminarInmueble(String id) {
        inmuebleRepositorio.deleteById(id); // Elimina el inmueble por ID
    }

}
