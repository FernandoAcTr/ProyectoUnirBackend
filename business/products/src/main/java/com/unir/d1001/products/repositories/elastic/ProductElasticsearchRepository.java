package com.unir.d1001.products.repositories.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.unir.d1001.products.entities.elastic.Product;

public interface ProductElasticsearchRepository extends ElasticsearchRepository<Product, Integer> {

    List<Product> findByDescripcionContaining(String descripcion);

}
