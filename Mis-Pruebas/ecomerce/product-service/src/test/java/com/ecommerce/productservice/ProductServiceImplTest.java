package com.ecommerce.productservice;

import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
@SpringBootTest
public class ProductServiceImplTest {
    /* Estas anotaciones se usan para cuando no tenemos implementados los métodos de la clase que vamos a probar
    // Anotación que se usa para inyectar instancias simuladas
    @Mock
    private ProductRepository productRepository;

    // Anotación se utiliza para inyectar los objetos simulados en la instancia bajo prueba
    @InjectMocks
    private ProductServiceImpl productService;

    // Esta anotación define que el método init() debe ejecutarse antes de cada prueba en la clase. En este método, se inicializan los objetos simulados.
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    */
    /* Una vez implemantados los métodos de clase, inyectamos de manera normal (con @Autowired) y probamos los tests */
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductServiceImpl productService;
    /* Test para usar con mocks
    @Test
    public void testBuscarProductoPorId() {
        Product product = new Product();
        product.setId(2L);
        product.setName("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(100.0);

       // solo se usa cuando usamos mocks when(productRepository.findById(2L)).thenReturn(Optional.of(product));

        Optional<Product> returnedProduct = productService.buscarProductoPorId(2L);

        assertEquals(Optional.of(product), returnedProduct);
    }
    */
    @Test
    public void testBuscarProductoPorId() {
        // Busca el producto por ID
        Optional<Product> returnedProduct = productService.buscarProductoPorId(1L);

        // Comprueba que el producto devuelto tiene los valores esperados
        assertTrue(returnedProduct.isPresent());
        assertEquals(1L, returnedProduct.get().getId());
        assertEquals("Ratón", returnedProduct.get().getName());
        assertEquals("Ratón Logitech", returnedProduct.get().getDescription());
        assertEquals(10, returnedProduct.get().getPrice());
        assertEquals(10, returnedProduct.get().getQuantity());
    }
}
