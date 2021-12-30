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

/**
 *
 * Classe para tratamento de regras de negócio dos Products
 *
 * @author Meli - Wave 4 - Grupo 11
 *
 * @version 0.0.1
 *
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     *
     * Lista todos os produtos
     *
     * @return List
     *
     * @throws RepositoryExceptions em função de depender de acesso IO.
     * Neste caso não há tratamento  e a response envia o status code 500
     *
     */
    public List<Product> listProducts() {
        try {
            List<Product> products;
            products = productRepository.getProducts();
            return products;
        } catch (IOException e) {
            throw new RepositoryExceptions("Erro ao acessar base de dados");
        }
    }

    /**
     *
     * Lista produtos filtrados e ordenados
     *
     * @param query um objeto Product com os atributos setados para dar match no filtro
     * @param orderBy 0 alfabética crescente,
     *                1 alfabética decrescente,
     *                2 Maior para menor preço,
     *                3 menor para maior preço
     * @return List
     *
     * @throws RepositoryExceptions em função de depender de acesso IO.
     * O tratamento é feito em advice enviando status code 500
     *
     * @see com.springchallenge.controller.advice.PersistenceExceptionsAdvice
     *
     */
    public List<Product> listProducts(Product query, String orderBy) {

        try {
            List<Product> products = productRepository.getProducts();
            List<Product> filteredProducts = products.stream()
                    .filter(p -> resolveQuery(p, query)).collect(Collectors.toList());

            switch (orderBy){

                case "0":
                    filteredProducts = filteredProducts.stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList());
                    break;

                case "1":
                    filteredProducts = filteredProducts.stream().sorted((p1, p2) -> p2.getName().compareTo(p1.getName())).collect(Collectors.toList());
                    break;

                case "2":
                    filteredProducts = filteredProducts.stream().sorted((p1, p2) -> p2.getPrice().compareTo(p1.getPrice())).collect(Collectors.toList());
                    break;

                case "3":
                    filteredProducts = filteredProducts.stream().sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice())).collect(Collectors.toList());
                    break;
            }
            return filteredProducts;
        } catch (IOException e) {
            throw new RepositoryExceptions("Erro ao acessar base de dados");
        }
    }

    /**
     *
     * Registra uma nova compra
     *
     * @param purchase Uma lista de Products a ser comprados.
     * @return Um Ticket com a lista de produtos e o valor total
     *
     * @throws RepositoryExceptions em função de depender de acesso IO.
     * O tratamento é feito em advice enviando status code 500
     *
     * @throws NullPointerException se receber id de produto inválido. A excessão é tratada no pacote advice
     * retornando status 400
     *
     * @see com.springchallenge.controller.advice.PersistenceExceptionsAdvice
     *
     */
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

            ticket.setId(1l);
            ticket.setArticles(list);
            ticket.setTotal(total);

            return ticket;

        }catch (NullPointerException e) {
            throw new NullPointerException("Id de produto inválido");
        } catch (IOException e) {
            throw new RepositoryExceptions("Erro ao acessar base de dados");
        }
    }

    /**
     *
     * Adiciona um novo produto
     *
     * @param products O produto a ser adicionado
     *
     * @throws RepositoryExceptions em função de depender de acesso IO.
     * O tratamento é feito em advice enviando status code 500
     *
     * @see com.springchallenge.controller.advice.PersistenceExceptionsAdvice
     */
    public void newProduct(List<Product> products) {

        try {
            for (Product product : products) {
                productRepository.newProduct(product);
            }

        } catch (IOException e) {
            throw new RepositoryExceptions("Erro ao acessar base de dados");
        }
    }

    /**
     *
     * Callback para tratar a query recebida em #listProducts em cada iteração do filter
     * @param p o product da iteração corrente a ser verificado
     * @param query o product recebido controller com os valores para serem filtrados
     * @return Boolean
     *
     */
    private Boolean resolveQuery(Product p, Product query) {
            return (query.getName() == null || query.getName().equals(p.getName())) &&
                (query.getCategory() == null || query.getCategory().equals(p.getCategory())) &&
                (query.getBrand() == null || query.getBrand().equals(p.getBrand())) &&
                (query.getQuantity() == null || query.getQuantity().equals(p.getQuantity())) &&
                (query.getFreeShipping() == null || query.getFreeShipping().equals(p.getFreeShipping())) &&
                (query.getPrestige() == null || query.getPrestige().equals(p.getPrestige()));
    }
}
