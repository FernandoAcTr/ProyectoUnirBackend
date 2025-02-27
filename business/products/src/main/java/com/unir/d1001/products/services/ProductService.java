package com.unir.d1001.products.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.d1001.products.dto.ProductFilters;
import com.unir.d1001.products.entities.Product;
import com.unir.d1001.products.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(ProductFilters filters) {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .filter(product -> matchesCategory(product, filters.categoriaId))
                .filter(product -> matchesMarca(product, filters.marcaId))
                .filter(product -> matchesForma(product, filters.formaId))
                .filter(product -> matchesTipoArmazon(product, filters.tipoArmazonId))
                .collect(Collectors.toList());
    }

    private boolean matchesCategory(Product product, Integer categoriaId) {
        return categoriaId == null
                || (product.getCategoria() != null && product.getCategoria().getId().equals(categoriaId));
    }

    private boolean matchesMarca(Product product, Integer marcaId) {
        return marcaId == null || (product.getMarca() != null && product.getMarca().getId().equals(marcaId));
    }

    private boolean matchesForma(Product product, Integer formaId) {
        return formaId == null || (product.getForma() != null && product.getForma().getId().equals(formaId));
    }

    private boolean matchesTipoArmazon(Product product, Integer tipoArmazonId) {
        return tipoArmazonId == null
                || (product.getTipoArmazon() != null && product.getTipoArmazon().getId().equals(tipoArmazonId));
    }
}
