package com.tiendaProductos.cloudinary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idImagen;
    private String nombreImagen;
    private String imagenUrl;
    private String idCloudinary;
    public Imagen(String nombreImagen, String imagenUrl, String idCloudinary) {
        this.nombreImagen = nombreImagen;
        this.imagenUrl = imagenUrl;
        this.idCloudinary = idCloudinary;
    }
}
