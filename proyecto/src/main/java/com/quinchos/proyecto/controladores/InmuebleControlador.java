package com.quinchos.proyecto.controladores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.entidades.Alquiler;
import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

import com.quinchos.proyecto.servicios.InmuebleServicio;

@Controller
@RequestMapping("/inmueble")
public class InmuebleControlador {

    @Autowired
    private InmuebleServicio inmuebleServicio;

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
            @RequestParam(value = "servicios", required = false) String[] servicios,
            @RequestParam("descripcion") String descripcion) {

        try {
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            inmuebleServicio.crearInmueble(usuario.getId(), categoria, localidad, ubicacion, capacidad, superficie,
                    precio, imagen, servicios, descripcion);
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

    // @PostMapping("/inmueble/formularioAlquilarInmueble")
    // public String registrarAlquiler(
    //         @RequestParam String idInmueble,
    //         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
    //         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
    //         @RequestParam Integer precioDia,
    //         @RequestParam Integer precioTotal) {

    //     // Aquí puedes usar esta información para guardar el alquiler en la base de
    //     // datos
    //     Alquiler alquiler = new Alquiler();
    //     alquiler.setIdInmueble(idInmueble);
    //     alquiler.setFechaInicio(fechaInicio);
    //     alquiler.setFechaFin(fechaFin);
    //     alquiler.setPrecioDia(precioDia);
    //     alquiler.setPrecioTotal(precioTotal);

    //     alquilerRepositorio.save(alquiler);

    //     return "redirect:/confirmacionAlquiler";
    // }

}
