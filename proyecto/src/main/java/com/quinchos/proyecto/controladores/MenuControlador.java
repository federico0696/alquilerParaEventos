package com.quinchos.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.excepciones.MiException;

import com.quinchos.proyecto.servicios.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MenuControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String menu() {
        return "menu.html";
    }

    @PostMapping("/registroInquilino")
    public String registroInquilino(@RequestParam("nombre") String nombre, @RequestParam("telefono") String telefono,
            @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("password2") String password2, ModelMap modelo,
            @RequestParam(required = false) MultipartFile imagen) {

        try {
            usuarioServicio.registrarInquilino(imagen, nombre, telefono, email, password, password2);
            modelo.put("exito", "Inquilino registrado correctamente");
            return "login.html";

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("telefono", telefono);
            return "menu.html";
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // INICIO DE SISON//

    // @GetMapping("/login")
    // public String login(@RequestParam(required = false) String error, ModelMap
    // modelo) {
    // if (error != null) {
    // modelo.put("error", "Usuario o Contraseña inválidos!");
    // }
    // return "login.html";
    // }

    @PostMapping("/logincheck")
    public String loginCheck(@RequestParam String email, @RequestParam String password, ModelMap modelo) {
        boolean autenticado = verificarCredenciales(email, password); // Llamado para autenticar el usuario (email) y su password //

        if (autenticado) {
            return "redirect:/menu"; // Redirigir a la página principal (menu.html) si la autenticación es correcta//
        } else {
            modelo.put("error", "Usuario o Contraseña inválidos!");
            return "login.html"; // Si no pasa la autenticación entonces vuelve a la página de inicio de sesión con el mensaje de error//
        }
    }

    // Ejemplo de método de autenticación
    private boolean verificarCredenciales(String email, String password) {
        // Implementa aquí la lógica de autenticación, como validación en la base de
        // datos
        return true; // Cambia esto según el resultado de la autenticación
    }

    @GetMapping("/publicaTuEspacio")
    public String publicaTuEspacio() {

        return "publicaTuEspacio.html";
    }

    @PostMapping("/publicaTuEspacio")
    public String registroPropietario(@RequestParam("nombre") String nombre,
            @RequestParam("direccion") String direccion, @RequestParam("telefono") String telefono,
            @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("password2") String password2, ModelMap modelo,
            @RequestParam(required = false) MultipartFile imagen) {

        try {
            usuarioServicio.registrarPropietario(imagen, nombre, direccion, telefono, email, password, password2);
            modelo.put("exito", "Propietario registrado correctamente");
            return "login.html";

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("direccion", direccion);
            modelo.put("telefono", telefono);
            return "publicaTuEspacio.html";
        }
    }

    @GetMapping("/buscarQuinchos")
    public String buscarQuinchos() {
        return "buscarQuinchos.html";
    }

    @GetMapping("/registroInmueble")
    public String registroInmueble() {
        return "registroInmueble.html";
    }

    @PostMapping("/registroInmueble")
    public String registrInmueble() {
        return "";
    }

}
