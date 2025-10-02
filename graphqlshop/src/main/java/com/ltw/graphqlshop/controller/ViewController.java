package com.ltw.graphqlshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/products")
    public String productsPage() {
        // Trả về file /webapp/WEB-INF/views/products.jsp
        return "products";
    }
}
