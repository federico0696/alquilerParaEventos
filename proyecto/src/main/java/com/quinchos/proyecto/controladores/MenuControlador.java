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
import com.quinchos.proyecto.servicios.AdministradorServicio;
import com.quinchos.proyecto.servicios.InquilinoServicio;
import com.quinchos.proyecto.servicios.PropietarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MenuControlador {
    
    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private InquilinoServicio inquilinoServicio ;

    @Autowired
    private PropietarioServicio propietarioServicio;

    @GetMapping("/")
    public String menu() {
        return "menu.html";
    }


    @GetMapping("/registrarInquilino")
    public String registrarInquilino() {
        return "registroInquilino.html";
    }

    @PostMapping("/registroInquilino")
    public String registroInquilino(@RequestParam("nombre") String nombre, @RequestParam("telefono") String telefono, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("password2") String password2, ModelMap modelo,
            @RequestParam(required = false) MultipartFile imagen) {

        try {
            inquilinoServicio.registrar(imagen, nombre, telefono, email, password, password2);
            modelo.put("exito", "Inquilino registrado correctamente");
            return "menu.html";

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("telefono", telefono);
            return "registroInquilino.html";
        }
    }
/*Se comenta por falta de usa por ahora */
    /*public String registrarPropietario() {
        return "registroPropietario.html";
    }

    @PostMapping("/registroPropietario")
    public String registroPropietario(@RequestParam("nombre") String nombre, @RequestParam("direccion") String direccion, @RequestParam("telefono") String telefono, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("password2") String password2, ModelMap modelo,
            @RequestParam(required = false) MultipartFile imagen) {

        try {
            propietarioServicio.registrar(imagen, nombre, direccion, telefono, email, password, password2);
            modelo.put("exito", "Propietario registrado correctamente");
            return "login.html";

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("direccion", direccion);
            modelo.put("telefono", telefono);
            return "registroPropietario.html";
        }
    }*/

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o Contraseña inválidos!");
        }
        return "login.html";
    }

    @GetMapping("/publicaTuEspacio")
    public String publicaTuEspacio() {
        
        return "publicaTuEspacio.html";
    }
    @PostMapping("/publicaTuEspacio")
    public String registroPropietario(@RequestParam("nombre") String nombre, @RequestParam("direccion") String direccion, @RequestParam("telefono") String telefono, @RequestParam("email") String email,
                                      @RequestParam("password") String password, @RequestParam("password2") String password2, ModelMap modelo,
                                      @RequestParam(required = false) MultipartFile imagen) {

        try {
            propietarioServicio.registrar(imagen, nombre, direccion, telefono, email, password, password2);
            modelo.put("exito", "Propietario registrado correctamente");
            return "menu.html";

        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            modelo.put("direccion", direccion);
            modelo.put("telefono", telefono);
            return "publicaTuEspacio.html";
        }
    }



}
