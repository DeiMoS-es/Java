package com.apiRest.producto.repository;

import com.apiRest.producto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombreProducto(String nombreProducto);
    List<Producto> findByNombreProductoContaining(String nombreProducto);
}
