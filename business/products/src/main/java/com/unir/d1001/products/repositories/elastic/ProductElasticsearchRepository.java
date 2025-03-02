package com.unir.d1001.products.repositories.elastic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  default List<Product> searchProducts(
      Optional<String> categoria,
      Optional<String> marca,
      Optional<String> forma,
      Optional<String> tipoArmazon,
      ElasticsearchOperations elasticsearchOperations) {

    List<Criteria> criteriaList = new ArrayList<>();

    categoria.ifPresent(value -> criteriaList.add(new Criteria("categoria.keyword").is("value")));
    marca.ifPresent(value -> criteriaList.add(new Criteria("marca.keyword").is(value)));
    forma.ifPresent(value -> criteriaList.add(new Criteria("forma.keyword").is(value)));
    tipoArmazon.ifPresent(value -> criteriaList.add(new Criteria("tipoArmazon.keyword").is(value)));

    if (criteriaList.isEmpty()) {
      List<Product> allProducts = new ArrayList<>();
      findAll().forEach(allProducts::add);
      return allProducts;
    }

    Criteria combinedCriteria = criteriaList.get(0);
    for (int i = 1; i < criteriaList.size(); i++) {
      combinedCriteria = combinedCriteria.and(criteriaList.get(i));
    }

    CriteriaQuery query = new CriteriaQuery(combinedCriteria);
    return elasticsearchOperations.search(query, Product.class)
        .stream()
        .map(SearchHit::getContent)
        .collect(Collectors.toList());
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
