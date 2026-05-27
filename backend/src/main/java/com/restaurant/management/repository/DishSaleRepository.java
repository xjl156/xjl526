package com.restaurant.management.repository;

import com.restaurant.management.entity.DishSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface DishSaleRepository extends JpaRepository<DishSale, Long> {

    List<DishSale> findByBusinessDateBetween(LocalDate start, LocalDate end);
    List<DishSale> findByDishIdAndBusinessDateBetween(Long dishId, LocalDate start, LocalDate end);
}
