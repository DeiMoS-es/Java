package com.tiendaProductos.user.service;

import com.tiendaProductos.user.entity.User;

public interface UserService {
    User buscarPorNombre(String username);
}
