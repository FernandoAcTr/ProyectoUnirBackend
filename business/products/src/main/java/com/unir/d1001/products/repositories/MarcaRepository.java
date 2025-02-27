package com.unir.d1001.products.repositories;

import com.unir.d1001.products.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
}
