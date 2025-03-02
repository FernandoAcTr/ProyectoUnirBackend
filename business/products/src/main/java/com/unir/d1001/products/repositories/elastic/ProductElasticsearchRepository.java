package com.unir.d1001.products.repositories.elastic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
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

  default List<Product> searchProducts(Optional<String> categoria,
      Optional<String> marca,
      Optional<String> forma,
      Optional<String> tipoArmazon,
      ElasticsearchOperations elasticsearchOperations) {

    Criteria criteria = new Criteria();

    categoria.ifPresent(value -> criteria.and("categoria.keyword").is(value));
    marca.ifPresent(value -> criteria.and("marca.keyword").is(value));
    forma.ifPresent(value -> criteria.and("forma.keyword").is(value));
    tipoArmazon.ifPresent(value -> criteria.and("tipoArmazon.keyword").is(value));

    CriteriaQuery query = new CriteriaQuery(criteria);

    return elasticsearchOperations.search(query, Product.class)
        .stream()
        .map(SearchHit::getContent)
        .toList();
  }

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
