package com.tiendaProductos.producto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiendaProductos.cloudinary.entity.Imagen;
import com.tiendaProductos.pedido.entity.DetallePedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Column(unique = true)
    private String nombreProducto;
    @NotBlank(message = "La descripción del producto no puede estar vacía")
    private String descripcionProducto;
    @NotNull(message = "El precio del producto no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    private Double precioProducto;
    @NotNull(message = "El stock del producto no puede ser nulo")
    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    private Integer stockProducto;
    @NotBlank(message = "El tipo del producto no puede estar vacío")
    private String tipoProducto;
    //Este campo se asignará automaticamente al dar de alta un producto
    private LocalDateTime fechaAltaProducto;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "imagen_id", referencedColumnName = "idImagen")
    // name = "imagen_id": Especifica el nombre de la columna en la tabla de la entidad propietaria (Producto)
    // que actuará como clave externa. En la tabla Producto, habrá una columna llamada imagen_id que contendrá el ID de la imagen asociada a ese producto.
    private Imagen imagen;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evitar que se serialice esta propiedad
    private List<DetallePedido> detallesPedido;

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", descripcionProducto='" + descripcionProducto + '\'' +
                ", precioProducto=" + precioProducto +
                ", stockProducto=" + stockProducto +
                ", tipoProducto='" + tipoProducto + '\'' +
                ", fechaAltaProducto=" + fechaAltaProducto +
                // Evita la referencia circular a DetallePedido aquí
                '}';
    }

}
