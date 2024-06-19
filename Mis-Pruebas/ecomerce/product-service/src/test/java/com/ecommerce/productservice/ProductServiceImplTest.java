package com.ecommerce.productservice;

import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import com.ecommerce.productservice.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
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

    @Test
    public void testBuscarProductoPorId() {
        Product product = new Product();
        product.setId(2L);
        product.setName("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(100.0);

        when(productRepository.findById(2L)).thenReturn(Optional.of(product));

        Optional<Product> returnedProduct = productService.buscarProductoPorId(2L);

        assertEquals(Optional.of(product), returnedProduct);
    }
}
