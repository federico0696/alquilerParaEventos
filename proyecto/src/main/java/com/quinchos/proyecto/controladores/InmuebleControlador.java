package com.quinchos.proyecto.controladores;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.entidades.Alquiler;
import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.entidades.Inquilino;
import com.quinchos.proyecto.entidades.Propietario;
import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

import com.quinchos.proyecto.servicios.InmuebleServicio;
import com.quinchos.proyecto.servicios.AlquilerServicio;

@Controller
@RequestMapping("/inmueble")
public class InmuebleControlador {

    @Autowired
    private InmuebleServicio inmuebleServicio;

    @Autowired
    private AlquilerServicio alquilerServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/registroInmueble")
    public String registroInmueble() {
        return "registroInmueble.html";
    }

    @PostMapping("/registroInmueble")
    public String registrInmueble(ModelMap modelo, HttpSession session,
            @RequestParam("categoria") String categoria,
            @RequestParam("localidad") String localidad,
            @RequestParam("ubicacion") String ubicacion,
            @RequestParam("capacidad") Integer capacidad,
            @RequestParam("superficie") Integer superficie,
            @RequestParam("precio") Integer precio,
            @RequestParam(required = false) MultipartFile imagen,
            @RequestParam(value = "serviciosComoArreglo", required = false) String[] serviciosComoArreglo,
            @RequestParam("descripcion") String descripcion) {

        try {
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            Propietario propietario = usuarioServicio.getOnePropietario(usuario.getId());
            inmuebleServicio.crearInmueble(usuario.getId(), categoria, localidad, ubicacion, capacidad, superficie,
                    precio, imagen, serviciosComoArreglo, descripcion, propietario);
            modelo.put("exito", "Inmueble registrado correctamente");
            return "menu.html";

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("categoria", categoria);

            return "registroInmueble.html";
        }
    }

    @GetMapping("/alquilarInmueble")
    public String alquilarInmueble(ModelMap modelo, @RequestParam("idInmueble") String idInmueble) {
        Inmueble inmueble = inmuebleServicio.obtenerInmueblePorId(idInmueble);
        modelo.put("inmuebles", inmueble);

        return "alquilarInmueble.html";
    }

    @GetMapping("/formularioAlquilarInmueble")
    public String formularioAlquilarInmueble(ModelMap modelo, @RequestParam("idInmueble") String idInmueble) {
        Inmueble inmueble = inmuebleServicio.obtenerInmueblePorId(idInmueble);
        modelo.put("inmueble", inmueble);

        return "formularioAlquilarInmueble.html";
    }

    @PostMapping("/formularioAlquilarInmueble")
    public String registrarAlquiler(HttpSession session,
            @RequestParam String idInmueble,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin,
            @RequestParam Integer precioDia,
            @RequestParam Integer precioTotal) throws MiException {

        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        Inquilino inquilino = usuarioServicio.getOneInquilino(usuario.getId());
        Inmueble inmueble = inmuebleServicio.obtenerInmueblePorId(idInmueble);

        alquilerServicio.crearAlquiler(inmueble, fechaInicio, fechaFin, precioDia, precioTotal, inquilino);

        return "menu.html";
    }


}
