package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "dish_categories")
public class DishCategory extends BaseEntity {

    private Long storeId;
    private String categoryCode;
    private String categoryName;
    private Integer sortOrder;
}
