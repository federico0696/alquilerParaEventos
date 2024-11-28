package com.quinchos.proyecto.controladores;

import com.quinchos.proyecto.entidades.Inmueble;
import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.enumeraciones.Rol;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.servicios.AlquilerServicio;
import com.quinchos.proyecto.servicios.InmuebleServicio;
import com.quinchos.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private InmuebleServicio inmuebleServicio;

    @Autowired
    private AlquilerServicio alquilerServicio;

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
            if (usuarioServicio.getOneUsuario(id).getRol() == Rol.INQUILINO) {
                List<String> listaIds = new ArrayList<>(
                        Arrays.asList(usuarioServicio.getOneInquilino(id).getIdAlquileres().split(",\\s*")));
                listaIds.removeIf(String::isEmpty); // Eliminar los IDs vacíos

                // Recorremos la lista y llamamos a la función eliminarAlquiler por cada id
                for (String idAlquiler : listaIds) {
                    alquilerServicio.eliminarAlquiler(idAlquiler); // Llamada a la función eliminarAlquiler, pasando el
                                                                   // idAlquiler
                }
            }
            if (usuarioServicio.getOneUsuario(id).getRol() == Rol.PROPIETARIO) {
                
            }

            usuarioServicio.eliminarUsuario(id);
            return "/admin/usuarios/"; // Redirige a la lista de usuarios después de eliminar
        } catch (MiException e) {
            return "error"; // Redirige a una página de error si ocurre una excepción
        }
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
    public String eliminarInmueble(@PathVariable(required = false) String id) {

        List<String> listaIds = new ArrayList<>(
                Arrays.asList(inmuebleServicio.obtenerInmueblePorId(id).getIdAlquileres().split(",\\s*")));
        listaIds.removeIf(String::isEmpty); // Eliminar los IDs vacíos

        // Recorremos la lista y llamamos a la función eliminarAlquiler por cada id
        for (String idAlquiler : listaIds) {
            alquilerServicio.eliminarAlquiler(idAlquiler); // Llamada a la función eliminarAlquiler, pasando el
                                                           // idAlquiler
        }

        List<String> listaIdsInmuebles = new ArrayList<>(
                Arrays.asList(usuarioServicio.getOnePropietario(inmuebleServicio.obtenerInmueblePorId(id).getIdPropietario()).getIdInmuebles().split(",\\s*")));
        listaIdsInmuebles.removeIf(String::isEmpty); // Eliminar los IDs vacíos

        
        usuarioServicio.eliminarInmuebleDePropietario(id, listaIdsInmuebles, usuarioServicio.getOnePropietario(inmuebleServicio.obtenerInmueblePorId(id).getIdPropietario()));
        
        inmuebleServicio.eliminarInmueble(id);
        return "redirect:/admin/inmuebles"; // Redirige a la lista de inmuebles después de eliminar
    }
}