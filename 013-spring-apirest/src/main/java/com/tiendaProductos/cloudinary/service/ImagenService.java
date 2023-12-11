package com.tiendaProductos.cloudinary.service;

import com.tiendaProductos.cloudinary.entity.Imagen;

import java.util.List;
import java.util.Optional;

public interface ImagenService {

    List<Imagen> listarImagenes();
    void saveImagen(Imagen imagen);
    void deleteImagen(int idImagen);
    Optional<Imagen> buscarImgenById(int idImagen);
    Optional<Imagen> buscarImagenPorNombre(String nombreImagen);
    boolean exists(int idImagen);

}
