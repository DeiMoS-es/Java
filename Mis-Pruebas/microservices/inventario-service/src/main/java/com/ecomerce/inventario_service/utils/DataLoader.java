package com.ecomerce.inventario_service.utils;

import com.ecomerce.inventario_service.model.entities.Inventory;
import com.ecomerce.inventario_service.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

/**
 * Clase de utilidad para insertar varios registros en la tabla y poder hacer consultas
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final InventoryRepository inventoryRepository;
    @Override
    public void run(String... args) throws Exception {
        log.info("Cargando datos de inventario");
        if(inventoryRepository.findAll().size() == 0){
            inventoryRepository.saveAll(List.of(
                    Inventory.builder().sku("00001").quantity(10L).build(),
                    Inventory.builder().sku("00002").quantity(5L).build(),
                    Inventory.builder().sku("00003").quantity(15L).build(),
                    Inventory.builder().sku("00004").quantity(0L).build()
            ));
        }
        log.info("Datos de inventario cargados");
    }
}
