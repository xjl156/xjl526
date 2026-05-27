package com.restaurant.management.repository;

import com.restaurant.management.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    List<Dish> findByStoreIdAndCategoryIdAndStatus(Long storeId, Long categoryId, Integer status);
    List<Dish> findByStoreIdAndStatus(Long storeId, Integer status);
    List<Dish> findByStoreIdOrderByPriorityScoreDesc(Long storeId);
}
