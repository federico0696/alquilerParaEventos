package com.quinchos.proyecto;

import com.quinchos.proyecto.entidades.Usuario;
import com.quinchos.proyecto.repositorios.UsuarioRepositorio;
import com.quinchos.proyecto.entidades.Rol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")  // Activa el perfil "test", cargando application-test.properties
@DataJpaTest
public class UsuarioRepositorioTest {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Test
    public void testBuscarPorEmail() {
        // Crear un usuario de prueba
        Usuario usuario = new Usuario();
        usuario.setNombre("Test User");
        usuario.setEmail("test@example.com");
        usuario.setPassword("password123");
        usuario.setRol(Rol.PROPIETARIO);

        // Guardar el usuario en la base de datos (usuarioId se auto-generará)
        usuarioRepositorio.save(usuario);

        // Buscar el usuario por email
        Optional<Usuario> encontrado = usuarioRepositorio.buscarPorEmail("test@example.com");

        // Verificar el resultado
        assertTrue(encontrado.isPresent());
        assertEquals("Test User", encontrado.get().getNombre());
    }

    @Test
    public void testEmailNoEncontrado() {
        // Intentar buscar un email inexistente
        Optional<Usuario> encontrado = usuarioRepositorio.buscarPorEmail("noexiste@example.com");

        // Verificar que no se encuentra
        assertFalse(encontrado.isPresent());
    }
}

