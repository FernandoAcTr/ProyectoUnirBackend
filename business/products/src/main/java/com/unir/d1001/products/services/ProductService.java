package com.unir.d1001.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.d1001.products.dto.ProductElasticFilters;
import com.unir.d1001.products.entities.elastic.Product;
import com.unir.d1001.products.repositories.elastic.ProductElasticsearchRepository;

@Service
public class ProductService {

    @Autowired
    private ProductElasticsearchRepository productElasticsearchRepository;

    public List<Product> getAllProducts(ProductElasticFilters filters) {
        List<Product> products = productElasticsearchRepository.searchProducts(
                filters.categoria,
                filters.marca,
                filters.forma,
                filters.tipoArmazon);

        return products;
    }

    public Optional<Product> getProductById(Integer id) {
        return productElasticsearchRepository.findById(id);
    }

    public List<Product> searchProductsByDescripcion(String query) {
        return productElasticsearchRepository.searchByDescripcion(query);
    }
}
