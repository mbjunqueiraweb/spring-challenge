package com.springchallenge.controller;

import com.springchallenge.dto.ProductDTO;
import com.springchallenge.entity.Product;
import com.springchallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
  ProductService productService;


    @PostMapping
    public ResponseEntity<List<ProductDTO>> create(@RequestBody List<Product> produtos){
<<<<<<< HEAD
        List<ProductDTO> res =   ProductDTO.convertToDTO(produtos);
        return ResponseEntity.ok().body(res);
    }

=======
        productService.newProduct(produtos);
        List<ProductDTO> res = ProductDTO.convertToDTO(produtos);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/purchaseRequest")
    public ResponseEntity<Ticket> purchaseRequest(@RequestBody List<Product> produtos){
        Ticket t = productService.newPurchase(produtos);
        return ResponseEntity.ok(t);

    }
>>>>>>> b36745207a16dd59403637f27cf049b55f50ea06

}
