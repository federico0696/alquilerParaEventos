package com.quinchos.proyecto.servicios;

import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.repositorios.InmuebleRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComparacionServicio {

    @Autowired
    private InmuebleRepositorio inmuebleRepositorio;

    /**
     * Compara inmuebles por precio.
     *
     * @return Lista de inmuebles ordenados por precio ascendente.
     */
    public List<Inmueble> compararPorPrecio() {
        return inmuebleRepositorio.findAllByOrderByPrecioDiaAsc();
    }

    /**
     * Compara inmuebles por capacidad.
     *
     * @return Lista de inmuebles ordenados por capacidad descendente.
     */
    public List<Inmueble> compararPorCapacidad() {
        return inmuebleRepositorio.findAllByOrderByCapacidadPersonasDesc();
    }

    /**
     * Compara inmuebles por ubicación.
     *
     * @param localidad La localidad por la que filtrar.
     * @return Lista de inmuebles en la localidad especificada.
     * @throws MiException Si no hay inmuebles en la localidad.
     */
    public List<Inmueble> compararPorLocalidad(String localidad) throws MiException {
        // Usando el método correcto del repositorio
        List<Inmueble> inmuebles = inmuebleRepositorio.findDisponiblesPorLocalidad(localidad);
        if (inmuebles.isEmpty()) {
            throw new MiException("No hay inmuebles disponibles en la localidad especificada.");
        }
        return inmuebles;
    }

    /**
     * Compara inmuebles por servicios.
     *
     * @param servicio El servicio a buscar.
     * @return Lista de inmuebles que contienen el servicio especificado.
     * @throws MiException Si no hay inmuebles con el servicio.
     */
    public List<Inmueble> compararPorServicio(String servicio) throws MiException {
        List<Inmueble> inmuebles = inmuebleRepositorio.findByServicio(servicio);
        if (inmuebles.isEmpty()) {
            throw new MiException("No se encontraron inmuebles con el servicio especificado.");
        }
        return inmuebles;
    }

    public List<String> compararInmuebles(Long inmuebleId1, Long inmuebleId2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compararInmuebles'");
    }

    public List<String> compararMultiplesInmuebles(List<Long> inmueblesIds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compararMultiplesInmuebles'");
    }

    public List<String> obtenerHistorialComparaciones() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerHistorialComparaciones'");
    }
}
