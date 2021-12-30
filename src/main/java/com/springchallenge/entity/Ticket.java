package com.springchallenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

/**
 * Entity Ticket
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
public class Ticket {
    private Long id;
    private List<Product> articles;
    private BigDecimal total;

}
