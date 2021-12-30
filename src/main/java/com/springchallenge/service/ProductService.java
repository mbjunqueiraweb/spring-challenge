package com.springchallenge.service;

import com.springchallenge.entity.Product;
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
