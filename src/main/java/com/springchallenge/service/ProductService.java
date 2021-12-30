package com.springchallenge.service;

import com.springchallenge.entity.Product;
import com.springchallenge.exceptions.RepositoryExceptions;
import com.springchallenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProducts() {
        // TODO: importar e instanciar o logger
        // TODO: Tratar as exceções no RepositoryException

        try {

            List<Product> products;
            products = productRepository.getProducts();
            //logger.debug("product sa");
            return products;

        } catch (IOException e) {
            // logger.error(e.getMessage());
            // logger.debug("passando no catch");
            throw new RepositoryExceptions("Erro"); //RepositoryException("MSG Customizada: Erro ao gravar o usuario")
        }
    }

    public List<Product> listProducts(Product query, int orderBy) {
        // TODO: Tratar as exceções no RepositoryException
        // TODO: ordenar
        try {
            List<Product> products = productRepository.getProducts();
            List<Product> filteredProducts = products.stream()
                    .filter(p -> resolveQuery(p, query)).collect(Collectors.toList());


            return filteredProducts;
        } catch (IOException e) {
            throw new RuntimeException("mm");

            return filteredProducts;
        } catch (IOException e) {
            throw new  RuntimeException("erro ao listar produtos");
        }
    }

    public BigDecimal newPurchase (List<Product> purchase){
        try {
            BigDecimal total = new BigDecimal(0); // provisório
            for (Product prod :purchase) {
                Product p = productRepository.getProductsById(prod.getProductId());
                BigDecimal value = new BigDecimal(String.valueOf(p.getPrice().multiply(new BigDecimal(p.getQuantity()))));
                total = total.add(value);
            }
            return total;
        }catch (IOException e){
            throw new RuntimeException("Produto nao encontrado:");
        }
            return filteredProducts;
        } catch (IOException e) {
            throw new RuntimeException("mm");
        }
    }



    public void newProduct(List<Product> products) {

        try {
            for (Product product : products) {
                productRepository.newProduct(product);
            }

        } catch (IOException e) {
            throw new RuntimeException("erro no io");

        }

    }

    private Boolean resolveQuery(Product p, Product query) {

        System.out.println(query.getName());
        System.out.println(p.getName());
        Boolean q = (query.getName() == null || query.getName().equals(p.getName())) && // true
                (query.getCategory() == null || query.getCategory().equals(p.getCategory())) && // true
                (query.getBrand() == null || query.getBrand().equals(p.getBrand())) &&
                (query.getQuantity() == null || query.getQuantity().equals(p.getQuantity())) &&
                (query.getFreeShipping() == null || query.getFreeShipping().equals(p.getFreeShipping())) &&
                (query.getPrestige() == null || query.getPrestige().equals(p.getPrestige()));
        return q;
    }

}
