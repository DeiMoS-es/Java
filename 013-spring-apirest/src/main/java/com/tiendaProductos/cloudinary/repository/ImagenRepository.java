package com.tiendaProductos.cloudinary.repository;

import com.tiendaProductos.cloudinary.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer> {
    List<Imagen> findByOrderByIdImagen();
    Optional<Imagen> findByNombreImagen(String nombreImagen);
}
