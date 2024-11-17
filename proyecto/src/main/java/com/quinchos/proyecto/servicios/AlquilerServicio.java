import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quinchos.proyecto.entidades.Alquiler;
import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.repositorios.AlquilerRepositorio;
import com.quinchos.proyecto.repositorios.InmuebleRepositorio;

@Service
public class AlquilerServicio {

    private final AlquilerRepositorio alquilerRepositorio;
    private final InmuebleRepositorio inmuebleRepositorio;

    public AlquilerServicio(AlquilerRepositorio alquilerRepositorio, InmuebleRepositorio inmuebleRepositorio) {
        this.alquilerRepositorio = alquilerRepositorio;
        this.inmuebleRepositorio = inmuebleRepositorio;
    }

    public Alquiler crearAlquiler(Alquiler alquiler) {
        // Validar que el inmueble no sea nulo
        if (alquiler.getInmueble() == null || alquiler.getInmueble().getInmuebleId() == null) {
            throw new IllegalArgumentException("El inmueble no puede ser nulo.");
        }

        // Verificar que el inmueble existe en la base de datos
        Optional<Inmueble> inmueble = inmuebleRepositorio.findById(alquiler.getInmueble().getInmuebleId());
        if (inmueble.isEmpty()) {
            throw new IllegalArgumentException("El inmueble no existe.");
        }

        // Asociar el inmueble al alquiler (si no se hizo explícitamente)
        alquiler.setInmueble(inmueble.get());

        // Guardar el alquiler
        return alquilerRepositorio.save(alquiler);
    }

    public Alquiler registrarAlquiler(Alquiler alquiler) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarAlquiler'");
    }

    public Alquiler obtenerAlquiler(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerAlquiler'");
    }

    public Object obtenerTodosLosAlquileres() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodosLosAlquileres'");
    }

    public Alquiler actualizarAlquiler(Long id, Alquiler alquiler) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarAlquiler'");
    }

    public void eliminarAlquiler(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarAlquiler'");
    }
}


