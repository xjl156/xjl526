package com.restaurant.management.repository;

import com.restaurant.management.entity.SalesRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface SalesRecordRepository extends JpaRepository<SalesRecord, Long> {

    Optional<SalesRecord> findByStoreIdAndBusinessDate(Long storeId, LocalDate businessDate);
    List<SalesRecord> findByStoreIdAndBusinessDateBetween(Long storeId, LocalDate start, LocalDate end);
}
