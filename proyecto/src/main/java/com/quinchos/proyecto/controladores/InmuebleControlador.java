package com.quinchos.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
                                @RequestParam("descripcion") String descripcion ) {                                               
       
       try {
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            inmuebleServicio.crearInmueble(usuario.getId(), categoria, localidad, ubicacion, capacidad, superficie, precio, imagen, servicios, descripcion);
            modelo.put("exito", "Inmueble registrado correctamente");
            return "menu.html";

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("categoria", categoria);
            
            return "registroInmueble.html";
        }
    }




}
