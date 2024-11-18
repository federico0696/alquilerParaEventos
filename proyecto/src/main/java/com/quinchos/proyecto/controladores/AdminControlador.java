package com.quinchos.proyecto.controladores;

import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.excepciones.MiException;
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

    // Mostrar usuarios según rol
    @GetMapping("/usuarios")
    public String listarUsuarios(Model modelo) {
        List<Usuario> propietarios = usuarioServicio.listarPorRol("PROPIETARIO");
        List<Usuario> inquilinos = usuarioServicio.listarPorRol("INQUILINO");
        modelo.addAttribute("propietarios", propietarios);
        modelo.addAttribute("inquilinos", inquilinos);
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
}