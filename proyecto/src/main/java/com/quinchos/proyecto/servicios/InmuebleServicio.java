package com.quinchos.proyecto.servicios;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.entidades.CategoriaInmueble;
import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.entidades.Rol;
import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.repositorios.InmuebleRepositorio;
import com.quinchos.proyecto.repositorios.UsuarioRepositorio;

@Service
public class InmuebleServicio {

    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ArchivoServicio archivoServicio;

    public void crearInmueble(Long propietarioId, String categoria, String localidad, String ubicacion,
            Integer capacidad, Integer superficie, Integer precio, MultipartFile imagen,
            String[] servicios, String descripcion) throws MiException {
        // Validar datos de entrada
        validar(categoria, localidad, ubicacion, capacidad, descripcion, superficie, precio);

        // Buscar al propietario
        Usuario propietario = usuarioRepositorio.findById(propietarioId)
                .orElseThrow(() -> new MiException("El propietario no existe."));

        // Validar que el usuario sea PROPIETARIO
        if (propietario.getRol() != Rol.PROPIETARIO) {
            throw new MiException("El usuario no tiene el rol de PROPIETARIO.");
        }

        // Guardar la imagen usando el servicio de archivos
        String rutaImagen = archivoServicio.guardarArchivo(imagen);

        // Crear el inmueble
        Inmueble inmueble = new Inmueble();
        inmueble.setPropietario(propietario);
        inmueble.setCategoria(CategoriaInmueble.valueOf(categoria.toUpperCase()));
        inmueble.setLocalidad(localidad);
        inmueble.setUbicacion(ubicacion);
        inmueble.setCapacidadPersonas(capacidad);
        inmueble.setSuperficie(superficie);
        inmueble.setPrecioDia(precio);
        inmueble.setDescripcion(descripcion);
        inmueble.setDisponible(true);
        inmueble.setImagen(rutaImagen);

        if (servicios != null) {
            inmueble.setServicios(Arrays.asList(servicios));
        }

        // Guardar en el repositorio
        inmuebleRepositorio.save(inmueble);
    }

    private void validar(String categoria, String localidad, String ubicacion, Integer capacidad,
            String descripcion, Integer superficie, Integer precioDia) throws MiException {
        if (categoria == null || categoria.isEmpty()) {
            throw new MiException("La categoría no puede ser nula o estar vacía.");
        }
        if (!Arrays.asList(CategoriaInmueble.values()).toString().contains(categoria.toUpperCase())) {
            throw new MiException("La categoría es inválida.");
        }
        if (localidad == null || localidad.isEmpty()) {
            throw new MiException("La localidad no puede ser nula o estar vacía.");
        }
        if (ubicacion == null || ubicacion.isEmpty()) {
            throw new MiException("La ubicación no puede ser nula o estar vacía.");
        }
        if (capacidad == null || capacidad <= 0) {
            throw new MiException("La capacidad debe ser mayor que cero.");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new MiException("La descripción no puede ser nula o estar vacía.");
        }
        if (superficie == null || superficie <= 0) {
            throw new MiException("La superficie debe ser mayor que cero.");
        }
        if (precioDia == null || precioDia <= 0) {
            throw new MiException("El precio debe ser mayor que cero.");
        }
    }
}
