package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String nombre;
    private String userName;
    private String email;
    private String direccion;
    private String telefono;
    //tipo es el rol de usuario
    private String tipo;
    private String password;

    /**
     * Este campo se relaciona con la tabla de productos
     * Para este caso es una relación uno a muchos
     * Lo mapeamos a usuario, porque en la tabla producto, tenemos un campo que se llama usuario
     */
    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;
    /**
     * Este campo se relaciona con la tabla pedido
     */
    @OneToMany(mappedBy =  "usuario")
    private List<Pedido> pedidos;

    public Usuario(Integer idUsuario, String nombre, String userName, String email, String direccion, String telefono, String tipo, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.userName = userName;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipo='" + tipo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
