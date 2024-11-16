package com.quinchos.proyecto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.entidades.Rol;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.entidades.Inquilino;
import com.quinchos.proyecto.entidades.Propietario;
import com.quinchos.proyecto.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/perfil")
public class PerfilControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/perfilUsuarioInquilino")
    public String perfilInquilino(HttpSession session, ModelMap modelo) {
        Rol usuario = (Rol) session.getAttribute("usuariosession");
        List<Inquilino> inquilinos = usuarioServicio.listarInquilinos();
        for (Inquilino aux : inquilinos) {
            if (aux.getIdInquilino().equals(usuario.getId())) {
                modelo.addAttribute("usuario", aux);
            }
        }
        return "perfilUsuario.html";
    }

    @GetMapping("/perfilUsuarioPropietario")
    public String perfilPropietario(HttpSession session, ModelMap modelo) {
        Rol usuario = (Rol) session.getAttribute("usuariosession");
        List<Propietario> propietarios = usuarioServicio.listarPropietarios();
        for (Propietario aux : propietarios) {
            if (aux.getIdPropietario().equals(usuario.getId())) {
                modelo.addAttribute("usuario", aux);
            }
        }
        return "perfilUsuario.html";
    }

    @PostMapping("/cambiarFotoPerfilInquilino")
    public String cambiarFotoPerfilInquilino(@RequestParam(required = false) MultipartFile imagen, HttpSession session, ModelMap modelo) throws MiException {
        Rol usuario = (Rol) session.getAttribute("usuariosession");
        usuarioServicio.actualizarFotoPerfilInquilino(usuario.getId(), imagen);
        List<Inquilino> inquilinos = usuarioServicio.listarInquilinos();
        for (Inquilino aux : inquilinos) {
            if (aux.getIdInquilino().equals(usuario.getId())) {
                modelo.addAttribute("usuario", aux);
            }
        }
        return "perfilUsuario.html";
    }

    @PostMapping("/cambiarFotoPerfilPropietario")
    public String cambiarFotoPerfilPropietario(@RequestParam(required = false) MultipartFile imagen, HttpSession session, ModelMap modelo) throws MiException {
        Rol usuario = (Rol) session.getAttribute("usuariosession");
        usuarioServicio.actualizarFotoPerfilPropietario(usuario.getId(), imagen);
        List<Propietario> propietarios = usuarioServicio.listarPropietarios();
        for (Propietario aux : propietarios) {
            if (aux.getIdPropietario().equals(usuario.getId())) {
                modelo.addAttribute("usuario", aux);
            }
        }
        return "perfilUsuario.html";
    }

    @PostMapping("/cambiarNombreUsuarioInquilino")
    public String cambiarNombreUsuarioInquilino(@RequestParam(required = false) String nombre, HttpSession session, ModelMap modelo) throws MiException {
        Rol usuario = (Rol) session.getAttribute("usuariosession");

        usuarioServicio.actualizarNombrePerfilInquilino(usuario.getId(), nombre);
        List<Inquilino> inquilinos = usuarioServicio.listarInquilinos();
        for (Inquilino aux : inquilinos) {
            if (aux.getIdInquilino().equals(usuario.getId())) {
                modelo.addAttribute("usuario", aux);
            }
        }
        return "perfilUsuario.html";
    }

    @PostMapping("/cambiarNombreUsuarioPropietario")
    public String cambiarNombreUsuarioPropietario(@RequestParam(required = false) String nombre, HttpSession session, ModelMap modelo) throws MiException {
        Rol usuario = (Rol) session.getAttribute("usuariosession");
        
        usuarioServicio.actualizarNombrePerfilPropietario(usuario.getId(), nombre);
        List<Propietario> propietarios = usuarioServicio.listarPropietarios();
        for (Propietario aux : propietarios) {
            if (aux.getIdPropietario().equals(usuario.getId())) {
                modelo.addAttribute("usuario", aux);
            }
        }
        return "perfilUsuario.html";
    }

    @PostMapping("/cambiarNombreDomicilioPropietario")
    public String cambiarNombreDomicilioPropietario(@RequestParam(required = false) String domicilio, HttpSession session, ModelMap modelo) throws MiException {
        Rol usuario = (Rol) session.getAttribute("usuariosession");
        usuarioServicio.actualizarDomicilioPerfilPropietario(usuario.getId(), domicilio);
        List<Propietario> propietarios = usuarioServicio.listarPropietarios();
        for (Propietario aux : propietarios) {
            if (aux.getIdPropietario().equals(usuario.getId())) {
                modelo.addAttribute("usuario", aux);
            }
        }
        return "perfilUsuario.html";
    }

    @PostMapping("/cambiarNombreEmailInquilino")
    public String cambiarNombreEmailInquilino(@RequestParam(required = false) String email, HttpSession session, ModelMap modelo) throws MiException {
        Rol usuario = (Rol) session.getAttribute("usuariosession");
        usuarioServicio.actualizarEmailPerfilInquilino(usuario.getId(), email);
        List<Inquilino> inquilinos = usuarioServicio.listarInquilinos();
        for (Inquilino aux : inquilinos) {
            if (aux.getIdInquilino().equals(usuario.getId())) {
                modelo.addAttribute("usuario", aux);
            }
        }
        return "perfilUsuario.html";
    }

    @PostMapping("/cambiarNombreEmailPropietario")
    public String cambiarNombreEmailPropietario(@RequestParam(required = false) String email, HttpSession session, ModelMap modelo) throws MiException {
        Rol usuario = (Rol) session.getAttribute("usuariosession");
        usuarioServicio.actualizarEmailPerfilPropietario(usuario.getId(), email);
        List<Propietario> propietarios = usuarioServicio.listarPropietarios();
        for (Propietario aux : propietarios) {
            if (aux.getIdPropietario().equals(usuario.getId())) {
                modelo.addAttribute("usuario", aux);
            }
        }
        return "perfilUsuario.html";
    }

}
