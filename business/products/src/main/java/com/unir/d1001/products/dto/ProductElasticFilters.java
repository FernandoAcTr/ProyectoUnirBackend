package com.unir.d1001.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductElasticFilters {
    public String categoria;
    public String marca;
    public String forma;
    public String tipoArmazon;
}
