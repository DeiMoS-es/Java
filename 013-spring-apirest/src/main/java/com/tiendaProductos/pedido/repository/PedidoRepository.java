package com.tiendaProductos.pedido.repository;

import com.tiendaProductos.pedido.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
