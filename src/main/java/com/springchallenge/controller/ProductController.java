package com.springchallenge.controller;

import com.springchallenge.dto.ProductDTO;
import com.springchallenge.entity.Product;
import com.springchallenge.entity.Ticket;
import com.springchallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private List<List<Product>> products = new ArrayList<List<Product>>();

    private List<Product> filterList = new ArrayList<Product>();

    @Autowired
    ProductService productService;

    @GetMapping("/ping")
    public String meuMetodo() {
        return "pong";
    }

    @PostMapping
    public ResponseEntity<List<ProductDTO>> create(@RequestBody List<Product> produtos){
        //products.add(produtos);
        productService.newProduct(produtos);
        List<ProductDTO> res = ProductDTO.convertToDTO(produtos);
        return ResponseEntity.ok().body(res);
    }

    public Product getProduct(Long productId){
        for(int i=0; i<products.size(); i++){
            for(int j=0; j<products.get(i).size(); j++){
                if(products.get(i).get(j).getProductId().equals(productId)){
                    return products.get(i).get(j);
                }
                System.out.println(products.get(i).get(j));
            }
        }
        return new Product();
    }

    @PostMapping("/purchaseRequest")
    public ResponseEntity<Ticket> purchaseRequest(@RequestBody List<Product> produtos){
        BigDecimal total = new BigDecimal(0.0);
        for(int i=0; i<produtos.size(); i++){
            total = total.add(produtos.get(i).getPrice().multiply(BigDecimal.valueOf(produtos.get(i).getQuantity())));
        }
        List<Long> lista = produtos.stream().map(p -> p.getProductId()).collect(Collectors.toList());

        for(int j=0; j< lista.size(); j++){
            Product prod = getProduct(lista.get(j));
            filterList.add(prod);
        }
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setArticles(filterList);
        ticket.setTotal(total);

        return ResponseEntity.ok(ticket);
    }

}
