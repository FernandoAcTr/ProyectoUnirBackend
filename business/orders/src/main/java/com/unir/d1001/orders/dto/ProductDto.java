package com.unir.d1001.orders.dto;

import java.time.LocalDateTime;

import lombok.Data;

@lombok.Data
class ProductProp {
    public int id;
    public String descripcion;
}

@Data
public class ProductDto {
    public int id;
    public double precio;
    public String descripcion;
    public ProductProp tipoArmazon;
    public ProductProp marca;
    public ProductProp categoria;
    public ProductProp forma;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}
