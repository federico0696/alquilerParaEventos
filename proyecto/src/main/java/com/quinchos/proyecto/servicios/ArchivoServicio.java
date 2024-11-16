package com.quinchos.proyecto.servicios;

//ESTE ARCHIVO NOS PERMITIRIA MANEJAR EN UN SOLO LUGAR EL ALMACENAMIENTO DE IMAGENES Y OTROS; CENTRALIZANDO LA GESTION
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.quinchos.proyecto.excepciones.MiException;


@Service
public class ArchivoServicio {

    // Directorio de almacenamiento dentro de static
    private static final String DIRECTORIO_IMAGENES = "src/main/resources/static/img";

    public String guardarArchivo(MultipartFile archivo) throws MiException {
        // Verifica que el archivo no sea nulo ni vacío
        if (archivo == null || archivo.isEmpty()) {
            throw new MiException("El archivo está vacío.");
        }

        try {
            // Crea el directorio si no existe
            Path directorio = Paths.get(DIRECTORIO_IMAGENES);
            if (!Files.exists(directorio)) {
                Files.createDirectories(directorio);  // Crear directorio si no existe
            }

            // Obtiene el nombre del archivo y lo convierte a bytes
            byte[] bytesArchivo = archivo.getBytes();
            // Crea la ruta completa del archivo
            Path rutaCompleta = Paths.get(directorio.toString(), archivo.getOriginalFilename());
            // Escribe el archivo en el directorio
            Files.write(rutaCompleta, bytesArchivo);

            // Devuelve la URL pública del archivo (desde el directorio static)
            return "/img/" + archivo.getOriginalFilename();
        } catch (IOException e) {
            // Lanza una excepción personalizada si ocurre un error
            throw new MiException("Error al guardar el archivo.", e);
        }
    }
}