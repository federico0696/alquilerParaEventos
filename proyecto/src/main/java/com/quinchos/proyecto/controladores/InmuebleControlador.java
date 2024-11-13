package com.quinchos.proyecto.controladores;

import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.servicios.InmuebleServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/inmueble")
public class InmuebleControlador {
    @Autowired
    private InmuebleServicios inmuebleServicios;
    
    @GetMapping("/registroInmueble")
    public String registroInmueble() {
        return "registroInmueble";
    }

    @PostMapping("/registroInmueble")
    public String registrarInmueble(
        @RequestParam String categoria,
        @RequestParam String localidad,
        @RequestParam String ubicacion,
        @RequestParam Integer capacidad,
        @RequestParam String descripcion,
        @RequestParam Integer superficie,
        @RequestParam Integer precioDia,
        ModelMap modelo,@RequestParam(required = false) MultipartFile imagen){
            try {
                inmuebleServicios.crearInmueble(imagen, categoria, localidad, ubicacion, capacidad, descripcion, superficie, precioDia);
                modelo.put("exito", "Inmueble registrado correctamente");
                return "menu.html";
            } catch (MiException ex) {
                modelo.put("error", ex.getMessage());
                modelo.put("categoria", categoria);
                modelo.put("localidad", localidad);
                modelo.put("ubicacion", ubicacion);
                modelo.put("descripcion", descripcion);
                modelo.put("precioDia", precioDia);
                return "registroInmueble.html";
            }

        }
}