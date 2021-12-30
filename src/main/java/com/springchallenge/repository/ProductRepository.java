package com.springchallenge.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.springchallenge.entity.Product;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository {
    public List<Product> products = new ArrayList<Product>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "products.json";

    public List<Product> getProducts() throws IOException{
        File file = new File(PATH);
        FileInputStream is = new FileInputStream(file);
        products = Arrays.asList(objectMapper.readValue(is, Product[].class));
        return products;
    }

    public Product getProductsById(Long id) throws IOException {

        Product product = getProducts().stream().filter(p -> p.getProductId() == id).findFirst().orElse(new Product());

        return product;
    }


    public void newProduct(Product product) throws IOException {
        product.setProductId((long) products.size()+1);
        products.add(product);
        objectMapper.writeValue(new File(PATH), products);
    }
}
