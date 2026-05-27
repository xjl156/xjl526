package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ingredient_categories")
public class IngredientCategory extends BaseEntity {

    private Long storeId;
    private String categoryCode;
    private String categoryName;
    private Long parentId;
    private Integer sortOrder;
}
