package com.springchallenge.controller;

import com.springchallenge.dto.ProductDTO;
import com.springchallenge.entity.Product;
import com.springchallenge.entity.Ticket;
import com.springchallenge.repository.ProductRepository;
import com.springchallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/ping")
    public String meuMetodo() {
        return "pong";
    }


    @GetMapping()
    public List<Product> getProduct() {
        return productService.listProducts();
    }

    @GetMapping("/articles")
    public ResponseEntity query(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String category,
                                @RequestParam(required = false) String brand,
                                @RequestParam(required = false) Integer quantity,
                                @RequestParam(required = false) BigDecimal price,
                                @RequestParam(required = false) Boolean freeShipping,
                                @RequestParam(required = false) String prestige,
                                @RequestParam(required = false) int order) {

        Product product = Product.builder()
                .name(name)
                .category(category)
                .brand(brand)
                .quantity(quantity)
                .price(price)
                .freeShipping(freeShipping)
                .prestige(prestige)
                .build();

        List<Product> list = productService.listProducts(product, order);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<List<ProductDTO>> create(@RequestBody List<Product> produtos){
        productService.newProducts(produtos);
        List<ProductDTO> res = ProductDTO.convertToDTO(produtos);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/purchaseRequest")
    public ResponseEntity<Ticket> purchaseRequest(@RequestBody List<Product> produtos){
        Ticket t = productService.newPurchase(produtos);
        return ResponseEntity.ok(t);

    }

}
