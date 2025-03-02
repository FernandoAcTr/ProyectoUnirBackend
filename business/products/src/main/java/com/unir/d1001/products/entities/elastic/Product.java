package com.unir.d1001.products.entities.elastic;

import java.time.LocalDateTime;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private Integer id;

    private Double precio;

    @Field(type = FieldType.Text)
    private String descripcion;

    @Field(type = FieldType.Keyword)
    private String tipoArmazon;

    @Field(type = FieldType.Keyword)
    private String marca;

    @Field(type = FieldType.Keyword)
    private String categoria;

    @Field(type = FieldType.Keyword)
    private String forma;

    @Field(type = FieldType.Date)
    private LocalDateTime createdAt;

    @Field(type = FieldType.Date)
    private LocalDateTime updatedAt;

    @Field(type = FieldType.Object)
    private ProductDetail details;
}
