package com.unir.d1001.products.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonBackReference
    private Product producto;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String talla;

    @Column(nullable = false)
    private String longitudVarilla;

    @Column(nullable = false)
    private String anchoPuente;

    @Column(nullable = false)
    private String anchoTotal;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(nullable = false)
    private String sku;

}
