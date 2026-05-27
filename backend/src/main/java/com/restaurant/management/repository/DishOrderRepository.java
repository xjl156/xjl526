package com.restaurant.management.repository;

import com.restaurant.management.entity.DishOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface DishOrderRepository extends JpaRepository<DishOrder, Long> {

    List<DishOrder> findByStoreIdOrderByCreatedAtDesc(Long storeId);
    Optional<DishOrder> findByOrderNo(String orderNo);
    List<DishOrder> findByStoreIdAndStatus(Long storeId, String status);
}
