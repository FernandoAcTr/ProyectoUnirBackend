package com.unir.d1001.products.repositories.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.unir.d1001.products.entities.elastic.Product;

@Repository
public interface ProductElasticsearchRepository extends ElasticsearchRepository<Product, Integer> {

  List<Product> findByDescripcionContaining(String descripcion);

  @Query("""
      {
        "bool": {
          "must": [
            { "match": { "categoria.keyword": "?0" }},
            { "match": { "marca.keyword": "?1" }},
            { "match": { "forma.keyword": "?2" }},
            { "match": { "tipoArmazon.keyword": "?3" }}
          ]
        }
      }
      """)
  List<Product> searchProducts(String categoria, String marca, String forma, String tipoArmazon);

  @Query("""
      {
        "bool": {
          "must": [
            { "match": { "descripcion": { "query": "?0", "fuzziness": "AUTO" }}}
          ]
        }
      }
      """)
  List<Product> searchByDescripcion(String descripcion);

}
