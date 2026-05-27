package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "dish_recipes")
public class DishRecipe extends BaseEntity {

    private Long dishId;
    private Long ingredientId;
    private BigDecimal quantity;
    private String unit;
}
