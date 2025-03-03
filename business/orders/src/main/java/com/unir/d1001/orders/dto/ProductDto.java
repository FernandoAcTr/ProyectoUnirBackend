package com.unir.d1001.orders.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductDto {
    public int id;
    public double precio;
    public String descripcion;
    public String tipoArmazon;
    public String marca;
    public String categoria;
    public String forma;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
    public Details details;
}

@Data
class Details {
    public String color;
    public String talla;
    public String longitudVarilla;
    public String anchoPuente;
    public String anchoTotal;
    public String sku;
    public Image image;
}

@Data
class Image {
    public String fotoURL;
    public String fotoPublicID;
}
