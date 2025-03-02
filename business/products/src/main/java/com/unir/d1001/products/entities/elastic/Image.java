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
public class Image {

    @Field(type = FieldType.Text)
    private String fotoUrl;

    @Field(type = FieldType.Keyword)
    private String fotoPublicId;
}