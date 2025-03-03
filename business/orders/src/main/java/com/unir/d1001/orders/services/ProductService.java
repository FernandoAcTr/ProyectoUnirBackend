package com.unir.d1001.orders.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.d1001.orders.dto.ProductDto;
import com.unir.d1001.orders.utils.AppHTTPClient;

@Service
public class ProductService {

    @Autowired
    AppHTTPClient appHTTPClient;

    public Optional<ProductDto> getProductById(int id) {
        try {
            ProductDto product = appHTTPClient.getProductsClient().get()
                    .uri("/products/" + id)
                    .retrieve()
                    .bodyToMono(ProductDto.class)
                    .block();

            return Optional.of(product);
        } catch (Exception e) {
            System.out.println(e);
            return Optional.empty();
        }
    }
}
