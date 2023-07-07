package com.example.tienda.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImagenService {

    String guardarImagen(MultipartFile imagen);

    void eliminarImagen(String nombreImagen);
}
