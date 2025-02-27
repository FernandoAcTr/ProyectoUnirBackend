package com.unir.d1001.products.controllers;

import com.unir.d1001.products.entities.Product;
import com.unir.d1001.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(required = false) Integer categoriaId,
            @RequestParam(required = false) Integer marcaId,
            @RequestParam(required = false) Integer formaId,
            @RequestParam(required = false) Integer tipoArmazonId) {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .filter(product -> (categoriaId == null
                        || (product.getCategoria() != null && product.getCategoria().getId().equals(categoriaId))) &&
                        (marcaId == null || (product.getMarca() != null && product.getMarca().getId().equals(marcaId)))
                        &&
                        (formaId == null || (product.getForma() != null && product.getForma().getId().equals(formaId)))
                        &&
                        (tipoArmazonId == null || (product.getTipoArmazon() != null
                                && product.getTipoArmazon().getId().equals(tipoArmazonId))))
                .collect(Collectors.toList());
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
