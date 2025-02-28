package com.unir.d1001.orders.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @GetMapping("/orders/check")
    public String check() {
        return "OK";
    }
}
