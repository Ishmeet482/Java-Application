package com.Backend.item_api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemResponse {

    private Long id;

    private String name;

    private String description;

    private String category;

    private BigDecimal price;
}
