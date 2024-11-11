package com.quinchos.proyecto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inmueble")
public class InmuebleControlador {
    
    @GetMapping("/registroInmueble")
    public String registroInmueble() {
        return "registroInmueble.html";
    }

    @PostMapping("/registroInmueble")
    public String registrInmueble() {
        return "";
    }




}
