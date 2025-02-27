package com.unir.d1001.products.controllers;

import com.unir.d1001.products.entities.Category;
import com.unir.d1001.products.entities.Forma;
import com.unir.d1001.products.entities.Marca;
import com.unir.d1001.products.entities.TipoArmazon;
import com.unir.d1001.products.repositories.CategoryRepository;
import com.unir.d1001.products.repositories.FormaRepository;
import com.unir.d1001.products.repositories.MarcaRepository;
import com.unir.d1001.products.repositories.TipoArmazonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lookups")
@RequiredArgsConstructor
public class LookupController {

    private final TipoArmazonRepository tipoArmazonRepository;
    private final MarcaRepository marcaRepository;
    private final CategoryRepository categoryRepository;
    private final FormaRepository formaRepository;

    @GetMapping("/tipo-armazones")
    public ResponseEntity<List<TipoArmazon>> getTipoArmazones() {
        List<TipoArmazon> tipoArmazones = tipoArmazonRepository.findAll();
        return ResponseEntity.ok(tipoArmazones);
    }

    @GetMapping("/marcas")
    public ResponseEntity<List<Marca>> getMarcas() {
        List<Marca> marcas = marcaRepository.findAll();
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/formas")
    public ResponseEntity<List<Forma>> getFormas() {
        List<Forma> formas = formaRepository.findAll();
        return ResponseEntity.ok(formas);
    }
}
