package com.unir.d1001.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductFilters {
    public Integer categoriaId;
    public Integer marcaId;
    public Integer formaId;
    public Integer tipoArmazonId;
}
