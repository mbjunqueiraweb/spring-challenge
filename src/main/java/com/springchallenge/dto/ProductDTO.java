package com.springchallenge.dto;

import com.springchallenge.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDTO {

    private Long productId;
    private String name;
    private Integer quantity;

    public static ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
    }


    public static List<ProductDTO> convertToDTO(List<Product> products){
        return products.stream().map(p -> convertToDTO(p)).collect(Collectors.toList());
    }
}
