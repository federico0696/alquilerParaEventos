package com.quinchos.proyecto.controladores;

import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.servicios.InmuebleServicio;
import com.quinchos.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private InmuebleServicio inmuebleServicio;

    // Mostrar usuarios según rol
    @GetMapping("/usuarios")
    public String listarUsuarios(Model modelo) {
        List<Usuario> propietarios = usuarioServicio.listarPorRol("PROPIETARIO");
        List<Usuario> inquilinos = usuarioServicio.listarPorRol("INQUILINO");
        List<Usuario> admin = usuarioServicio.listarPorRol("ADMIN");
        modelo.addAttribute("propietarios", propietarios);
        modelo.addAttribute("inquilinos", inquilinos);
        modelo.addAttribute("administrador", admin);
        return "administrador"; // Página HTML donde se mostrarán los usuarios
    }

    // Eliminar usuario
    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id) {
        try {
            usuarioServicio.eliminarUsuario(id);
            return "/admin/usuarios/"; // Redirige a la lista de usuarios después de eliminar
        } catch (MiException e) {
            return "error"; // Redirige a una página de error si ocurre una excepción
        }
    }

    @GetMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id){
        usuarioServicio.cambiarRol(id);

        return "redirect:/admin/usuarios";
    }

    // Listar inmuebles
    @GetMapping("/inmuebles")
    public String listarInmuebles(Model modelo) {
        List<Inmueble> inmuebles = inmuebleServicio.listarInmuebles();
        modelo.addAttribute("inmuebles", inmuebles);
        return "listaInmuebles"; // Página HTML donde se mostrarán los inmuebles
    }

    // Eliminar inmueble
    @PostMapping("/inmuebles/eliminar/{id}")
    public String eliminarInmueble(@PathVariable String id) {
        inmuebleServicio.eliminarInmueble(id);
        return "redirect:/admin/inmuebles"; // Redirige a la lista de inmuebles después de eliminar
    }
}