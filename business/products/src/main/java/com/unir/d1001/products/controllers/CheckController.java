package com.unir.d1001.products.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @GetMapping("/products/check")
    public String check() {
        return "OK";
    }
}
