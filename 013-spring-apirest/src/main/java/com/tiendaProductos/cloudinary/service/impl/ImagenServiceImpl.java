package com.tiendaProductos.cloudinary.service.impl;

import com.tiendaProductos.cloudinary.entity.Imagen;
import com.tiendaProductos.cloudinary.repository.ImagenRepository;
import com.tiendaProductos.cloudinary.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenServiceImpl implements ImagenService {
    @Autowired
    ImagenRepository imagenRepository;
    @Override
    public List<Imagen> listarImagenes() {
        return imagenRepository.findAll();
    }

    @Override
    public void saveImagen(Imagen imagen) {
        imagenRepository.save(imagen);
    }

    @Override
    public void deleteImagen(int idImagen) {
        imagenRepository.deleteById(idImagen);
    }

    @Override
    public Optional<Imagen> buscarImgenById(int idImagen) {
        return imagenRepository.findById(idImagen);
    }

    @Override
    public Optional<Imagen> buscarImagenPorNombre(String nombreImagen) {
        return imagenRepository.findByNombreImagen(nombreImagen);
    }

    @Override
    public boolean exists(int idImagen) {
        return imagenRepository.existsById(idImagen);
    }


}
