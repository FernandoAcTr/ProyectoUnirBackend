package com.unir.d1001.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import com.unir.d1001.products.dto.ProductElasticFilters;
import com.unir.d1001.products.entities.elastic.Product;
import com.unir.d1001.products.repositories.elastic.ProductElasticsearchRepository;

@Service
public class ProductService {

    @Autowired
    private ProductElasticsearchRepository productElasticsearchRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public List<Product> getAllProducts(ProductElasticFilters filters) {
        Optional<String> categoria = Optional.ofNullable(filters.categoria);
        Optional<String> marca = Optional.ofNullable(filters.marca);
        Optional<String> forma = Optional.ofNullable(filters.forma);
        Optional<String> tipoArmazon = Optional.ofNullable(filters.tipoArmazon);

        List<Product> products = productElasticsearchRepository.searchProducts(categoria, marca, forma, tipoArmazon,
                elasticsearchOperations);

        return products;
    }

    public Optional<Product> getProductById(Integer id) {
        return productElasticsearchRepository.findById(id);
    }

    public List<Product> searchProductsByDescripcion(String query) {
        return productElasticsearchRepository.searchByDescripcion(query);
    }
}
