package com.unir.d1001.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unir.d1001.products.entities.Image;
import com.unir.d1001.products.entities.Product;
import com.unir.d1001.products.entities.ProductDetail;
import com.unir.d1001.products.repositories.ProductRepository;
import com.unir.d1001.products.repositories.elastic.ProductElasticsearchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductMigrationService {

    private final ProductRepository productRepository;
    private final ProductElasticsearchRepository productElasticsearchRepository;

    public void migrateProductsToElasticsearch() {
        List<Product> products = productRepository.findAll();

        List<com.unir.d1001.products.entities.elastic.Product> productDocuments = products.stream()
                .map(this::convertToElasticsearch).toList();

        productElasticsearchRepository.saveAll(productDocuments);
    }

    private com.unir.d1001.products.entities.elastic.Product convertToElasticsearch(Product product) {
        return com.unir.d1001.products.entities.elastic.Product.builder()
                .id(product.getId())
                .precio(product.getPrecio())
                .descripcion(product.getDescripcion())
                .tipoArmazon(product.getTipoArmazon().getDescripcion())
                .marca(product.getMarca().getDescripcion())
                .categoria(product.getCategoria().getDescripcion())
                .forma(product.getForma().getDescripcion())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .details(convertDetails(product.getDetails()))
                .build();
    }

    private com.unir.d1001.products.entities.elastic.ProductDetail convertDetails(ProductDetail details) {
        if (details == null)
            return null;

        return com.unir.d1001.products.entities.elastic.ProductDetail.builder()
                .color(details.getColor())
                .talla(details.getTalla())
                .longitudVarilla(details.getLongitudVarilla())
                .anchoPuente(details.getAnchoPuente())
                .anchoTotal(details.getAnchoTotal())
                .sku(details.getSku())
                .image(convertImage(details.getImage()))
                .build();
    }

    private com.unir.d1001.products.entities.elastic.Image convertImage(Image image) {
        if (image == null)
            return null;

        return com.unir.d1001.products.entities.elastic.Image.builder()
                .fotoUrl(image.getFotoUrl())
                .fotoPublicId(image.getFotoPublicId())
                .build();
    }
}