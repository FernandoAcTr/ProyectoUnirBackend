package com.unir.d1001.products;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.unir.d1001.products.services.ProductMigrationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataMigrationRunner implements CommandLineRunner {

    private final ProductMigrationService migrationService;

    @Override
    public void run(String... args) {
        migrationService.migrateProductsToElasticsearch();
        log.info("Migración a Elasticsearch completada.");
    }
}