package com.unir.d1001.products.entities.elastic;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
    @Field(type = FieldType.Keyword)
    private String color;

    @Field(type = FieldType.Keyword)
    private String talla;

    @Field(type = FieldType.Keyword)
    private String longitudVarilla;

    @Field(type = FieldType.Keyword)
    private String anchoPuente;

    @Field(type = FieldType.Keyword)
    private String anchoTotal;

    @Field(type = FieldType.Keyword)
    private String sku;

    @Field(type = FieldType.Object)
    private Image image;
}