package com.quinchos.proyecto.servicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.entidades.Alquiler;
import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.entidades.Inquilino;
import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.repositorios.AlquilerRepositorio;
import com.quinchos.proyecto.repositorios.InmuebleRepositorio;
import com.quinchos.proyecto.repositorios.InquilinoRepositorio;

@Service
public class AlquilerServicio {

    @Autowired
    private AlquilerRepositorio alquilerRepositorio;

    @Autowired
    private InquilinoRepositorio inquilinoRepositorio;

    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private InmuebleServicio inmuebleServicio;

    public void crearAlquiler(Inmueble inmueble, String fechaInicio, String fechaFin, Integer precioDia,
            Integer precioTotal, Inquilino inquilino)
            throws MiException {

        Alquiler alquiler = new Alquiler();
        alquiler.setIdInmueble(inmueble.getIdInmueble());
        alquiler.setIdInquilino(inquilino.getIdInquilino());
        alquiler.setFechaInicio(fechaInicio);
        alquiler.setFechaFin(fechaFin);
        alquiler.setPrecioDia(precioDia);
        alquiler.setPrecioTotal(precioTotal);

        alquilerRepositorio.save(alquiler);

        if (inquilino.getIdAlquileres() != null) {
            inquilino.setIdAlquileres(inquilino.getIdAlquileres() + ", " + alquiler.getIdAlquiler());
        } else {
            inquilino.setIdAlquileres(alquiler.getIdAlquiler());
        }
        inquilinoRepositorio.save(inquilino);

        if (inmueble.getIdAlquileres() != null) {
            inmueble.setIdAlquileres(inmueble.getIdAlquileres() + ", " + alquiler.getIdAlquiler());
            inmuebleRepositorio.save(inmueble);
        } else {
            inmueble.setIdAlquileres(alquiler.getIdAlquiler());
            inmuebleRepositorio.save(inmueble);
        }

    }

    public void eliminarAlquiler(String idAlquiler) {
        Optional<Alquiler> respuesta = alquilerRepositorio.findById(idAlquiler);
        Inquilino inquilino = usuarioServicio
                .getOneInquilino(buscarAlquilerPorId(idAlquiler).getIdInquilino());
        Inmueble inmueble = inmuebleServicio
                .obtenerInmueblePorId(buscarAlquilerPorId(idAlquiler).getIdInmueble());

        if (respuesta.isPresent()) {
            Alquiler alquiler = respuesta.get();
            // elimino el idAlquiler de la lista de idsAlquiler de inquilino y de la lista de idsAlquiler de inmueble
            inquilino.setIdAlquileres(eliminarIdAlquiler(inquilino.getIdAlquileres(), alquiler.getIdAlquiler()));
            inquilinoRepositorio.save(inquilino);
            inmueble.setIdAlquileres(eliminarIdAlquiler(inmueble.getIdAlquileres(), alquiler.getIdAlquiler()));
            inmuebleRepositorio.save(inmueble);
            // ahora si ya elimino el alquiler
            alquilerRepositorio.delete(alquiler);
        } else {
            throw new IllegalArgumentException("Alquiler no encontrado");
        }
    }

    public String eliminarIdAlquiler(String idsAlquileres, String idAlquilerAEliminar) {
        
        List<String> listaIds = new ArrayList<>(Arrays.asList(idsAlquileres.split(",\\s*")));
        listaIds.remove(idAlquilerAEliminar);
    
        return String.join(",", listaIds);

    }

    public Alquiler buscarAlquilerPorId(String idAlquiler) {

        return alquilerRepositorio.findById(idAlquiler).orElse(null);
    }

}
