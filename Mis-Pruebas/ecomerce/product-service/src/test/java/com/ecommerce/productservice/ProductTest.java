package com.ecommerce.productservice;
import com.ecommerce.productservice.entity.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {
    @Test
    public void testProduct() {
        Product product = new Product();
        product.setId(2L);
        product.setName("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(100.0);

        assertEquals(2L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals("This is a test product", product.getDescription());
        assertEquals(100.0, product.getPrice(), 0.001);
    }
}
