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
import com.quinchos.proyecto.entidades.Propietario;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.repositorios.InmuebleRepositorio;
import com.quinchos.proyecto.repositorios.PropietarioRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InmuebleServicio {

    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;

    @Autowired
    private PropietarioRepositorio propietarioRepositorio;

    private void validar(String categoria, String localidad, String ubicacion, Integer capacidad, Integer superficie,
            Integer precioDia) throws MiException {
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

    public void crearInmueble(String idPropietario, String categoria, String localidad, String ubicacion, Integer capacidad,
            Integer superficie, Integer precio, MultipartFile imagen, String[] servicios, String descripcion, Propietario propietario)
            throws MiException {
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
        inmueble.setIdPropietario(idPropietario);
        inmueble.setImagen("/img/" + imagen.getOriginalFilename());

        if (servicios != null) {
            // Convertir el arreglo de servicios en una cadena separada por comas
            String serviciosComoTexto = String.join(", ", servicios);
            inmueble.setServicios(serviciosComoTexto);
        } else {
            String serviciosComoTexto = "";
            inmueble.setServicios(serviciosComoTexto);
        }
        inmuebleRepositorio.save(inmueble);
        if (propietario.getIdInmuebles() != null){
            propietario.setIdInmuebles(propietario.getIdInmuebles()+", "+inmueble.getIdInmueble());
            propietarioRepositorio.save(propietario);
        }else{
            propietario.setIdInmuebles(inmueble.getIdInmueble());
            propietarioRepositorio.save(propietario);
        }

        
    }

    public List<Inmueble> buscarInmueble(String categoria, String localidad, Integer capacidad, Integer superficie,
            String servicios) throws MiException {

        if (localidad != null && localidad.isEmpty()) {
            localidad = null;
        }
        if (categoria != null && categoria.isEmpty()) {
            categoria = null;
        }

        // Dividir el String servicios en un array
        String[] serviciosArray = servicios != null ? servicios.split(",") : new String[0];

        // Crear parámetros nulos por defecto
        String servicio1 = serviciosArray.length > 0 ? serviciosArray[0] : null;
        String servicio2 = serviciosArray.length > 1 ? serviciosArray[1] : null;
        String servicio3 = serviciosArray.length > 2 ? serviciosArray[2] : null;
        String servicio4 = serviciosArray.length > 3 ? serviciosArray[3] : null;
        String servicio5 = serviciosArray.length > 4 ? serviciosArray[4] : null;
        String servicio6 = serviciosArray.length > 5 ? serviciosArray[5] : null;
        String servicio7 = serviciosArray.length > 6 ? serviciosArray[6] : null;
        String servicio8 = serviciosArray.length > 7 ? serviciosArray[7] : null;
        String servicio9 = serviciosArray.length > 8 ? serviciosArray[8] : null;
        String servicio10 = serviciosArray.length > 9 ? serviciosArray[9] : null;

        return inmuebleRepositorio.buscarInmuebles(categoria, localidad, capacidad, superficie,
                servicio1, servicio2, servicio3, servicio4, servicio5, servicio6, servicio7, servicio8, servicio9,
                servicio10);
    }

    public Inmueble obtenerInmueblePorId(String idInmueble) {
        return inmuebleRepositorio.findById(idInmueble)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el inmueble con id: " + idInmueble));
    }

    // Agrega diego linea 95 a 101
    public List<Inmueble> listarInmuebles() {
        return inmuebleRepositorio.findAll(); // Obtén todos los inmuebles
    }

    public void eliminarInmueble(String id) {
        
        inmuebleRepositorio.deleteById(id); // Elimina el inmueble por ID
    }

}
