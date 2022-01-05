package com.springchallenge.service;

import com.springchallenge.entity.Product;
<<<<<<< HEAD
=======
import com.springchallenge.entity.Ticket;
import com.springchallenge.exceptions.RepositoryExceptions;
>>>>>>> b36745207a16dd59403637f27cf049b55f50ea06
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

    public List<Product> listProducts(){
        // TODO: definir exceções recebidas e envolver no try catch
        List<Product> products = productRepository.getProducts();

        try {
            ProductRepository.;
            logger.debug("product sa");


            }catch (IOException e){
            logger.error(e.getMessage());
            logger.debug("passando no catch");
            throw new RepositoryException("MSG Customizada: Erro ao gravar o usuario")
    }catch (){
        throw new RuntimeException("usuario deve ser maior de idade");
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

        }catch (IOException e) {
            throw new RuntimeException("Produto nao encontrado:");

        }
        return products;
    }

    public List<Product> listProducts(Product query, int orderBy) throws IOException {
        // TODO: definir exceções recebidas e envolver no try catch
        List<Product> products = ProductRepository.getProducts();

        List<Product> filteredProducts = products.stream()
                .filter(p -> resolveQuery(p, query)).collect(Collectors.toList());

        // TODO: ordenar

        return filteredProducts;
    }

    public BigDecimal newPurchase (List<Product> purchase){
        // TODO: definir exceções recebidas e envolver no try catch
        // TODO implementar

        BigDecimal total = new BigDecimal(0); // provisório
        return total;
    }

    public void newProduct (List<Product> product){
        // TODO: definir exceções recebidas e envolver no try catch
        // TODO implementar
    }

    private Boolean resolveQuery(Product p, Product query) {
        return (query.getName() == null || p.getName() == query.getName()) &&
                (query.getCategory() == null || query.getCategory() == p.getCategory()) &&
                (query.getBrand() == null || query.getBrand() == p.getBrand()) &&
                (query.getQuantity() == null || query.getQuantity() == p.getQuantity()) &&
                (query.getFreeShipping() == null || query.getFreeShipping() == p.getFreeShipping()) &&
                (query.getPrestige() == null || query.getPrestige() == p.getPrestige());
    }
}
