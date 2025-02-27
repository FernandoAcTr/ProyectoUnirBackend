package com.unir.d1001.products.controllers;

import com.unir.d1001.products.dto.ProductFilters;
import com.unir.d1001.products.entities.Product;
import com.unir.d1001.products.repositories.ProductRepository;
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
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(required = false) Integer categoriaId,
            @RequestParam(required = false) Integer marcaId,
            @RequestParam(required = false) Integer formaId,
            @RequestParam(required = false) Integer tipoArmazonId) {

        ProductFilters filters = new ProductFilters(categoriaId, marcaId, formaId, tipoArmazonId);
        List<Product> products = productService.getAllProducts(filters);

        return products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productRepository.findByIdWithRelations(id);

        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.of(product);
    }
}
