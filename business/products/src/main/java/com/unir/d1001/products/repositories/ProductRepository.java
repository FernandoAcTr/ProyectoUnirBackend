package com.unir.d1001.products.repositories;

import com.unir.d1001.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p " +
           "JOIN FETCH p.tipoArmazon " +
           "JOIN FETCH p.marca " +
           "JOIN FETCH p.categoria " +
           "JOIN FETCH p.forma " +
           "WHERE p.id = :id")
    Optional<Product> findByIdWithRelations(@Param("id") Integer id);
}
