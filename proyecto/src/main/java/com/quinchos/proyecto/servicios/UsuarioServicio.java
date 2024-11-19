package com.quinchos.proyecto.servicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.entidades.Inquilino;
import com.quinchos.proyecto.entidades.Propietario;
import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.enumeraciones.Rol;
import com.quinchos.proyecto.excepciones.MiException;
import com.quinchos.proyecto.repositorios.InquilinoRepositorio;
import com.quinchos.proyecto.repositorios.PropietarioRepositorio;
import com.quinchos.proyecto.repositorios.UsuarioRepositorio;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    private InquilinoRepositorio inquilinoRepositorio ;

    @Autowired
    private PropietarioRepositorio propietarioRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    private void validarInquilino(String nombre, String telefono, String email, String password, String password2) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacío");
        }
        if (telefono.isEmpty() || telefono == null) {
            throw new MiException("el telefono no puede ser nulo o estar vacío");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("el email no puede ser nulo o estar vacío");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
    }

    

    @Transactional
    public void registrarInquilino(MultipartFile imagen, String nombre, String telefono, String email, String password, String password2) throws MiException{

        validarInquilino(nombre, telefono, email, password, password2);

        Path directorioImagenes = Paths.get("src/main/resources/static/img");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try {
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Inquilino inquilino = new Inquilino();

        inquilino.setNombre(nombre);
        inquilino.setTelefono(telefono);
        inquilino.setEmail(email);
        // Codifica la contraseña antes de guardarla
        String encodedPassword = passwordEncoder.encode(password);
        inquilino.setPassword(encodedPassword);
        inquilino.setRol(Rol.INQUILINO);
        inquilino.setImagen("/img/" + imagen.getOriginalFilename());

        inquilinoRepositorio.save(inquilino);

        Usuario usuario = new Usuario();

        usuario.setId(inquilino.getIdInquilino());
        usuario.setEmail(email);
        usuario.setPassword(encodedPassword);
        usuario.setRol(Rol.INQUILINO);

        usuarioRepositorio.save(usuario);

    }

    @Transactional
    public List<Usuario> listarUsuarios() {

        return usuarioRepositorio.findAll();
    }

    @Transactional
    public List<Inquilino> listarInquilinos() {

        return inquilinoRepositorio.findAll();
    }

    public void actualizarFotoPerfilInquilino(String idUsuario, MultipartFile imagen) throws MiException {

        Path directorioImagenes = Paths.get("src/main/resources/static/img");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try {
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Optional<Inquilino> respuesta = inquilinoRepositorio.findById(idUsuario);

        if (respuesta.isPresent()) {
            Inquilino inquilino = respuesta.get();
            inquilino.setImagen("/img/" + imagen.getOriginalFilename());
            inquilinoRepositorio.save(inquilino);
        }
    }

    public void actualizarNombrePerfilInquilino(String idUsuario, String nombre) throws MiException {
        Optional<Inquilino> respuesta = inquilinoRepositorio.findById(idUsuario);
        if (respuesta.isPresent()) {
            Inquilino inquilino = respuesta.get();
            inquilino.setNombre(nombre);
            inquilinoRepositorio.save(inquilino);
        }
    }

    public void actualizarEmailPerfilInquilino(String idUsuario, String email) throws MiException {
        Optional<Inquilino> respuesta = inquilinoRepositorio.findById(idUsuario);
        Optional<Usuario> respuestaUsuario = usuarioRepositorio.findById(idUsuario);
        if (respuesta.isPresent()) {
            Inquilino inquilino = respuesta.get();
            inquilino.setEmail(email);
            inquilinoRepositorio.save(inquilino);

            Usuario usuario = respuestaUsuario.get();
            usuario.setEmail(email);
            usuarioRepositorio.save(usuario);
        }
    }

    public Inquilino getOneInquilino(String id) {
        return inquilinoRepositorio.findById(id).orElse(null);
    }


    /*----------------------------------INQUILINO----------------------------------------------*/
    /*-----------------------------------------------------------------------------------------*/ 
    /*-----------------------------------------------------------------------------------------*/ 
    /*-----------------------------------------------------------------------------------------*/ 
    /*-----------------------------------------------------------------------------------------*/ 
    /*-----------------------------------------------------------------------------------------*/ 
    /*---------------------------------PROPIETARIO---------------------------------------------*/



    private void validarPropietario(String nombre, String direccion, String telefono, String email, String password, String password2) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("el nombre no puede ser nulo o estar vacío");
        }
        if (direccion.isEmpty() || direccion == null) {
            throw new MiException("la direccion no puede ser nula o estar vacía");
        }
        if (telefono.isEmpty() || telefono == null) {
            throw new MiException("el telefono no puede ser nulo o estar vacío");
        }
        if (email.isEmpty() || email == null) {
            throw new MiException("el email no puede ser nulo o estar vacío");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }
        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
    }

    @Transactional
    public void registrarPropietario(MultipartFile imagen, String nombre, String direccion, String telefono, String email, String password, String password2) throws MiException{

        validarPropietario(nombre, direccion, telefono, email, password, password2);

        Path directorioImagenes = Paths.get("src/main/resources/static/img");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try {
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Propietario propietario = new Propietario();

        propietario.setNombre(nombre);
        propietario.setDireccion(direccion);
        propietario.setTelefono(telefono);
        propietario.setEmail(email);
        // Codifica la contraseña antes de guardarla
        String encodedPassword = passwordEncoder.encode(password);
        propietario.setPassword(encodedPassword);
        propietario.setRol(Rol.PROPIETARIO);
        propietario.setImagen("/img/" + imagen.getOriginalFilename());

        propietarioRepositorio.save(propietario);

        Usuario usuario = new Usuario();

        usuario.setId(propietario.getIdPropietario());
        usuario.setEmail(email);
        usuario.setPassword(encodedPassword);
        usuario.setRol(Rol.PROPIETARIO);

        usuarioRepositorio.save(usuario);
    }

    @Transactional
    public List<Propietario> listarPropietarios() {

        return propietarioRepositorio.findAll();
    }

    public void actualizarFotoPerfilPropietario(String idUsuario, MultipartFile imagen) throws MiException {

        Path directorioImagenes = Paths.get("src/main/resources/static/img");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try {
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Optional<Propietario> respuesta = propietarioRepositorio.findById(idUsuario);

        if (respuesta.isPresent()) {
            Propietario propietario = respuesta.get();
            propietario.setImagen("/img/" + imagen.getOriginalFilename());
            propietarioRepositorio.save(propietario);
        }
    }

    public void actualizarNombrePerfilPropietario(String idUsuario, String nombre) throws MiException {
        Optional<Propietario> respuesta = propietarioRepositorio.findById(idUsuario);
        if (respuesta.isPresent()) {
            Propietario propietario = respuesta.get();
            propietario.setNombre(nombre);
            propietarioRepositorio.save(propietario);
        }
    }

    public void actualizarDomicilioPerfilPropietario(String idUsuario, String direccion) throws MiException {
        Optional<Propietario> respuesta = propietarioRepositorio.findById(idUsuario);
        if (respuesta.isPresent()) {
            Propietario propietario = respuesta.get();
            propietario.setDireccion(direccion);
            propietarioRepositorio.save(propietario);
        }
    }

    public void actualizarEmailPerfilPropietario(String idUsuario, String email) throws MiException {
        Optional<Propietario> respuesta = propietarioRepositorio.findById(idUsuario);
        Optional<Usuario> respuestaUsuario = usuarioRepositorio.findById(idUsuario);
        if (respuesta.isPresent()) {
            Propietario propietario = respuesta.get();
            propietario.setEmail(email);
            propietarioRepositorio.save(propietario);

            Usuario usuario = respuestaUsuario.get();
            usuario.setEmail(email);
            usuarioRepositorio.save(usuario);
        }
    }

    public Propietario getOnePropietario(String id) {
        return propietarioRepositorio.findById(id).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            return null;
        }
    }



    /*---------------------------------PROPIETARIO---------------------------------------------*/
    /*-----------------------------------------------------------------------------------------*/ 
    /*-----------------------------------------------------------------------------------------*/ 
    /*-----------------------------------------------------------------------------------------*/ 
    /*-----------------------------------------------------------------------------------------*/ 
    /*-----------------------------------------------------------------------------------------*/ 
    /*---------------------------------FUNCIONES POR DIEGO-------------------------------------*/


    //Agrego Eliminar usuario para admin. (diego)
    @Transactional
    public List<Usuario> listarPorRol(String rol) {
        return usuarioRepositorio.findByRol(Rol.valueOf(rol));
    }

    @Transactional
    public void eliminarUsuario(String id) throws MiException {
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        if (usuario == null) {
            throw new MiException("El usuario no existe");
        }

        // Eliminar el usuario asociado al rol correspondiente
        if (usuario.getRol() == Rol.PROPIETARIO) {
            propietarioRepositorio.deleteById(id);
        } else if (usuario.getRol() == Rol.INQUILINO) {
            inquilinoRepositorio.deleteById(id);
        }

        usuarioRepositorio.deleteById(id);
    }

    

}
