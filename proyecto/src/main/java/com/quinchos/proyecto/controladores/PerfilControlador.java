package com.quinchos.proyecto.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quinchos.proyecto.entidades.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/perfil")
public class PerfilControlador {
    
    @GetMapping("/perfilUsuario")
    public String perfil(HttpSession session, ModelMap modelo) {
        
        return "perfilUsuario.html";
    }







    
}
