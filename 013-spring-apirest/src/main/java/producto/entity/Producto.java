package producto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;
    @NotBlank(message = "El nombre del producto no puede estar vacío")
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
}
