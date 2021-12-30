package com.springchallenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 *
 * Entity Product
 *
 * @author Meli - Wave 4 - Grupo 11
 *
 * @version 0.0.1
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product {
    private Long productId;
    private String name;
    private String category;
    private String brand;
    private Integer quantity;
    private BigDecimal price;
    private Boolean freeShipping;
    private String prestige;
}
