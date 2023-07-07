package com.example.tienda.service.impl;

import com.example.tienda.exception.ImagenException;
import com.example.tienda.exception.ImagenNotFoundException;
import com.example.tienda.service.ImagenService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImagenServiceImpl implements ImagenService {

    private static final String UPLOADS_DIRECTORY = "src/main/resources/static/uploads/";
    @Override
    public String guardarImagen(MultipartFile imagen) {
        try {
            String nombreImagen = imagen.getOriginalFilename();
            String rutaImagen = UPLOADS_DIRECTORY + nombreImagen;
            Path directorioDestino = Paths.get(UPLOADS_DIRECTORY);
            Files.createDirectories(directorioDestino);
            Path archivoDestino = directorioDestino.resolve(nombreImagen);
            Files.copy(imagen.getInputStream(), archivoDestino, StandardCopyOption.REPLACE_EXISTING);
            return rutaImagen;
        } catch (IOException e) {
            throw new ImagenException("Error al guardar la imagen", e);
        }
    }

    @Override
    public void eliminarImagen(String nombreImagen) {
        try {
            Path rutaImagen = Paths.get(UPLOADS_DIRECTORY).resolve(nombreImagen);
            if (Files.exists(rutaImagen)) {
                Files.delete(rutaImagen);
            } else {
                throw new ImagenNotFoundException("La imagen no existe");
            }
        } catch (IOException e) {
            throw new ImagenException("Error al eliminar la imagen", e);
        }
    }
}
