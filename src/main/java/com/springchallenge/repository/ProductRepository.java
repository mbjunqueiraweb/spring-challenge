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

/**
 * Classe para persistência de dados de Products
 *
 * @author Meli - Wave 4 - Grupo 11
 *
 * @version 0.0.1
 */
@Component
public class ProductRepository {
    public List<Product> products = new ArrayList<Product>();
    private ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final String PATH = "products.json";

    /**
     * Lista todos os produtos da base de dados
     *
     * @return List
     * @throws IOException
     */
    public List<Product> getProducts() throws IOException{
        File file = new File(PATH);
        FileInputStream is = new FileInputStream(file);
        products = Arrays.asList(objectMapper.readValue(is, Product[].class));
        return products;
    }

    /**
     * Retorna o produto por ID
     *
     * @param id o Id do Product
     *
     * @return Product
     * @throws IOException
     */
    public Product getProductsById(Long id) throws IOException {
        Product product = getProducts().stream().filter(p -> p.getProductId() == id).findFirst().orElse(new Product());
        return product;
    }

    /**
     * Adiciona novo Produto na Base de dados
     *
     * @param product o Product a ser adicionado
     * @throws IOException
     */
    public void newProduct(Product product) throws IOException {
        product.setProductId((long) products.size()+1);
        products.add(product);
        objectMapper.writeValue(new File(PATH), products);
    }
}
