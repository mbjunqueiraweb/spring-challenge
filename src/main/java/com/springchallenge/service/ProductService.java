package com.springchallenge.service;

import com.springchallenge.entity.Product;
import com.springchallenge.entity.Ticket;
import com.springchallenge.exceptions.RepositoryExceptions;
import com.springchallenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProducts() {

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

        try {
            List<Product> products = productRepository.getProducts();
            List<Product> filteredProducts = products.stream()
                    .filter(p -> resolveQuery(p, query)).collect(Collectors.toList());

            switch (orderBy){

                case 0:
                    filteredProducts = filteredProducts.stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList());
                    break;

                case 1:
                    filteredProducts = filteredProducts.stream().sorted((p1, p2) -> p2.getName().compareTo(p1.getName())).collect(Collectors.toList());
                    break;

                case 2:
                    filteredProducts = filteredProducts.stream().sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice())).collect(Collectors.toList());
                    break;

                case 3:
                    filteredProducts = filteredProducts.stream().sorted((p1, p2) -> p2.getPrice().compareTo(p1.getPrice())).collect(Collectors.toList());
                    break;
            }

            return filteredProducts;
        } catch (IOException e) {
            throw new RuntimeException("mm");
        }
    }

    public Ticket newPurchase (List<Product> purchase){
        try {
            BigDecimal total = new BigDecimal(0);
            Ticket ticket = new Ticket();
            List<Product> list = new ArrayList<Product>();
            for (Product prod :purchase) {
                Product p = productRepository.getProductsById(prod.getProductId());
                p.setQuantity(prod.getQuantity());
                BigDecimal value = new BigDecimal(String.valueOf(p.getPrice().multiply(new BigDecimal(p.getQuantity()))));
                total = total.add(value);
                list.add(p);
            }

            ticket.setArticles(list);
            ticket.setTotal(total);

            return ticket;

        }catch (NullPointerException e) {
            throw new NullPointerException("Id de produto inválido");
        }catch (IOException e){
            throw new RuntimeException(e);
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

        Boolean q = (query.getName() == null || query.getName().equals(p.getName())) && // true
                (query.getCategory() == null || query.getCategory().equals(p.getCategory())) && // true
                (query.getBrand() == null || query.getBrand().equals(p.getBrand())) &&
                (query.getQuantity() == null || query.getQuantity().equals(p.getQuantity())) &&
                (query.getFreeShipping() == null || query.getFreeShipping().equals(p.getFreeShipping())) &&
                (query.getPrestige() == null || query.getPrestige().equals(p.getPrestige()));
        return q;
    }
}
