package com.quinchos.proyecto.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.entidades.Inquilino;
import com.quinchos.proyecto.entidades.Propietario;
import com.quinchos.proyecto.servicios.AlquilerServicio;
import com.quinchos.proyecto.servicios.InmuebleServicio;
import com.quinchos.proyecto.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/perfil")
public class PerfilControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private AlquilerServicio alquilerServicio;

    @Autowired
    private InmuebleServicio inmuebleServicio;

    @GetMapping("/perfilUsuarioInquilino")
    public String perfilInquilino(HttpSession session, ModelMap modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        List<Inquilino> inquilinos = usuarioServicio.listarInquilinos();
        for (Inquilino aux : inquilinos) {
            if (aux.getIdInquilino().equals(usuario.getId())) {
                modelo.addAttribute("usuario", aux);

                String[] idAlquileres = aux.getIdAlquileres().split("\\s*,\\s*");
                List<String> tempList = new ArrayList<>();
                for (String id : idAlquileres) {
                    if (!id.isEmpty()) { // Verificar si el elemento no está vacío
                        tempList.add(id); // Agregar solo los elementos no vacíos
                    }
                }
                // Convertir la lista de vuelta a un arreglo
                idAlquileres = tempList.toArray(new String[0]);

                if (idAlquileres.length > 0) {
                    modelo.addAttribute("alquiler", alquilerServicio.buscarAlquilerPorId(idAlquileres[0]));
                    modelo.addAttribute("inmueble", inmuebleServicio.obtenerInmueblePorId(
                            alquilerServicio.buscarAlquilerPorId(idAlquileres[0]).getIdInmueble()));
                    modelo.addAttribute("propietario",
                            usuarioServicio.getOnePropietario(inmuebleServicio
                                    .obtenerInmueblePorId(
                                            alquilerServicio.buscarAlquilerPorId(idAlquileres[0]).getIdInmueble())
                                    .getIdPropietario()));
                }
                if (idAlquileres.length > 1) {
                    modelo.addAttribute("alquiler1", alquilerServicio.buscarAlquilerPorId(idAlquileres[1]));
                    modelo.addAttribute("inmueble1", inmuebleServicio.obtenerInmueblePorId(
                            alquilerServicio.buscarAlquilerPorId(idAlquileres[1]).getIdInmueble()));
                    modelo.addAttribute("propietario1",
                            usuarioServicio.getOnePropietario(inmuebleServicio
                                    .obtenerInmueblePorId(
                                            alquilerServicio.buscarAlquilerPorId(idAlquileres[1]).getIdInmueble())
                                    .getIdPropietario()));
                }
                if (idAlquileres.length > 2) {
                    modelo.addAttribute("alquiler2", alquilerServicio.buscarAlquilerPorId(idAlquileres[2]));
                    modelo.addAttribute("inmueble2", inmuebleServicio.obtenerInmueblePorId(
                            alquilerServicio.buscarAlquilerPorId(idAlquileres[2]).getIdInmueble()));
                    modelo.addAttribute("propietario2",
                            usuarioServicio.getOnePropietario(inmuebleServicio
                                    .obtenerInmueblePorId(
                                            alquilerServicio.buscarAlquilerPorId(idAlquileres[2]).getIdInmueble())
                                    .getIdPropietario()));
                }

            }
        }
        return "perfilUsuario.html";
    }

    @GetMapping("/perfilUsuarioPropietario")
    public String perfilPropietario(HttpSession session, ModelMap modelo) {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        List<Propietario> propietarios = usuarioServicio.listarPropietarios();
        for (Propietario aux : propietarios) {
            if (aux.getIdPropietario().equals(usuario.getId())) {
                modelo.addAttribute("usuario", aux);

                String[] idInmuebles = aux.getIdInmuebles().split("\\s*,\\s*");
                List<String> tempList = new ArrayList<>();

                for (String id : idInmuebles) {
                    if (!id.isEmpty()) { // Verificar si el elemento no está vacío
                        tempList.add(id); // Agregar solo los elementos no vacíos
                    }
                }
                // Convertir la lista de vuelta a un arreglo
                idInmuebles = tempList.toArray(new String[0]);

                if (idInmuebles.length > 0) {
                    modelo.addAttribute("inmueble", inmuebleServicio.obtenerInmueblePorId(idInmuebles[0]));
                }
                if (idInmuebles.length > 1) {
                    modelo.addAttribute("inmueble1", inmuebleServicio.obtenerInmueblePorId(idInmuebles[1]));
                }
                if (idInmuebles.length > 2) {
                    modelo.addAttribute("inmueble2", inmuebleServicio.obtenerInmueblePorId(idInmuebles[2]));
                }

            }
        }
        return "perfilUsuario.html";
    }

    @PostMapping("/cambiarFotoPerfilInquilino")
    public String cambiarFotoPerfilInquilino(@RequestParam(required = false) MultipartFile imagen, HttpSession session,
            ModelMap modelo) throws MiException {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
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
    public String cambiarFotoPerfilPropietario(@RequestParam(required = false) MultipartFile imagen,
            HttpSession session, ModelMap modelo) throws MiException {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
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
    public String cambiarNombreUsuarioInquilino(@RequestParam(required = false) String nombre, HttpSession session,
            ModelMap modelo) throws MiException {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");

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
    public String cambiarNombreUsuarioPropietario(@RequestParam(required = false) String nombre, HttpSession session,
            ModelMap modelo) throws MiException {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");

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
    public String cambiarNombreDomicilioPropietario(@RequestParam(required = false) String domicilio,
            HttpSession session, ModelMap modelo) throws MiException {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
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
    public String cambiarNombreEmailInquilino(@RequestParam(required = false) String email, HttpSession session,
            ModelMap modelo) throws MiException {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
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
    public String cambiarNombreEmailPropietario(@RequestParam(required = false) String email, HttpSession session,
            ModelMap modelo) throws MiException {
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        usuarioServicio.actualizarEmailPerfilPropietario(usuario.getId(), email);
        List<Propietario> propietarios = usuarioServicio.listarPropietarios();
        for (Propietario aux : propietarios) {
            if (aux.getIdPropietario().equals(usuario.getId())) {
                modelo.addAttribute("usuario", aux);
            }
        }
        return "perfilUsuario.html";
    }

    @PostMapping("/eliminarAlquiler/{idAlquiler}")
    public String eliminarAlquiler(@PathVariable(required = false) String idAlquiler, ModelMap modelo)
            throws MiException, IOException {

        alquilerServicio.eliminarAlquiler(idAlquiler);

        return "redirect:/perfil/perfilUsuarioInquilino";

    }

    @PostMapping("/eliminarInmueble/{idInmueble}")
    public String eliminarInmueble(@PathVariable(required = false) String idInmueble, ModelMap modelo)
            throws MiException, IOException {

        inmuebleServicio.eliminarInmueble(idInmueble);

        return "redirect:/perfil/perfilUsuarioPropietario";

    }

}
