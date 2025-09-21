package com.moneyminder.repository;

import com.moneyminder.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    
    @Query("SELECT SUM(i.amount) FROM Income i WHERE i.incomeDate = :date")
    BigDecimal sumAmountByDate(@Param("date") LocalDate date);
    
    @Query("SELECT COUNT(i) FROM Income i WHERE i.incomeDate = :date")
    Long countByDate(@Param("date") LocalDate date);
    
    @Query("SELECT SUM(i.amount) FROM Income i WHERE YEAR(i.incomeDate) = :year AND MONTH(i.incomeDate) = :month")
    BigDecimal sumAmountByYearAndMonth(@Param("year") int year, @Param("month") int month);
    
    @Query("SELECT COUNT(i) FROM Income i WHERE YEAR(i.incomeDate) = :year AND MONTH(i.incomeDate) = :month")
    Long countByYearAndMonth(@Param("year") int year, @Param("month") int month);
    
    @Query("SELECT i FROM Income i WHERE i.incomeDate BETWEEN :startDate AND :endDate ORDER BY i.incomeDate DESC")
    List<Income> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}


