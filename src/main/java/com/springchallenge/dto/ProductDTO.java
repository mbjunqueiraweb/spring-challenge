package com.springchallenge.dto;

import com.springchallenge.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object para a entity Product
 *
 * @author Meli - Wave 4 - Grupo 11
 *
 * @version 0.0.1
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDTO {

    private Long productId;
    private String name;
    private Integer quantity;

    /**
     * Converte um objeto Product em ProductDTO
     *
     * @param product
     * @return
     */
    public static ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
    }

    /**
     * Converte uma lista de Products em uma Lista de ProductsDTO
     * @param products
     * @return
     */
    public static List<ProductDTO> convertToDTO(List<Product> products){
        return products.stream().map(p -> convertToDTO(p)).collect(Collectors.toList());
    }
}
