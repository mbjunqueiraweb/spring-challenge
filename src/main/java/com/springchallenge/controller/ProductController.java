package com.springchallenge.controller;

import com.springchallenge.dto.ProductDTO;
import com.springchallenge.entity.Product;
import com.springchallenge.entity.Ticket;
import com.springchallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Classe para controle dos endpoints de Products
 *
 * @author Meli - Wave 4 - Grupo 11
 *
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/ping")
    public String ping() {
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
                                @RequestParam(defaultValue = "") String order) {

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
    public ResponseEntity<List<ProductDTO>> create(@RequestBody List<Product> products){
        productService.newProducts(products);
        List<ProductDTO> productsListDTO = ProductDTO.convertToDTO(products);
        return ResponseEntity.ok().body(productsListDTO);
    }

    @PostMapping("/purchaseRequest")
    public ResponseEntity<Ticket> purchaseRequest(@RequestBody List<Product> products){
        Ticket ticket = productService.newPurchase(products);
        return ResponseEntity.ok(ticket);
    }
}
