package com.unir.d1001.products.controllers;

import com.unir.d1001.products.dto.ProductElasticFilters;
import com.unir.d1001.products.entities.elastic.Product;
import com.unir.d1001.products.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String forma,
            @RequestParam(required = false) String tipoArmazon) {

        ProductElasticFilters filters = new ProductElasticFilters(categoria, marca, forma, tipoArmazon);
        List<Product> products = productService.getAllProducts(filters);

        return products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productService.getProductById(id);

        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.of(product);
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String query) {
        return productService.searchProductsByDescripcion(query);
    }
}
