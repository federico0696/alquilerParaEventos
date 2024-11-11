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
@RequestMapping("/")
public class InmuebleControlador {
    @Autowired
    private InmuebleServicios inmuebleServicios;

    @GetMapping("/registroInmueble")
    public String registroInmueble() {

        return "registroInmueble.html";
    }

    @PostMapping("/registroInmueble")
    public String registrarInmueble(
                                    @RequestParam String categoria,
                                    @RequestParam String ubicacion,
                                    @RequestParam Integer capacidad,
                                    @RequestParam String descripcion,
                                    @RequestParam Integer superficie,
                                    ModelMap modelo,@RequestParam(required = false) MultipartFile imagen){
        try{
            inmuebleServicios.crearInmueble(imagen,categoria,ubicacion,capacidad,descripcion,superficie);
            modelo.put("excito", "Inmueble registrado correctamente");
        return "menu.html";
        }catch (MiException ex){
            modelo.put("error", ex.getMessage());
            modelo.put("categoria", categoria);
            modelo.put("ubicacion", ubicacion);
            modelo.put("descrpcion",descripcion);
            return "registroInmueble.html";
        }
    }

}


