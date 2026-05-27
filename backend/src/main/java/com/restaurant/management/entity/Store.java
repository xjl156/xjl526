package com.restaurant.management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stores")
public class Store extends BaseEntity {

    private Long storeId;
    private String storeCode;
    private String storeName;
    private String address;
    private String contactPhone;
    private BigDecimal dailyProfitTarget;
    private BigDecimal monthlyProfitTarget;
    private Integer status;
}
