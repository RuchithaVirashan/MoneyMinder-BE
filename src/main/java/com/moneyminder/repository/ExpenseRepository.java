package com.moneyminder.repository;

import com.moneyminder.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.expenseDate = :date")
    BigDecimal sumAmountByDate(@Param("date") LocalDate date);
    
    @Query("SELECT COUNT(e) FROM Expense e WHERE e.expenseDate = :date")
    Long countByDate(@Param("date") LocalDate date);
    
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE YEAR(e.expenseDate) = :year AND MONTH(e.expenseDate) = :month")
    BigDecimal sumAmountByYearAndMonth(@Param("year") int year, @Param("month") int month);
    
    @Query("SELECT COUNT(e) FROM Expense e WHERE YEAR(e.expenseDate) = :year AND MONTH(e.expenseDate) = :month")
    Long countByYearAndMonth(@Param("year") int year, @Param("month") int month);
    
    @Query("SELECT e FROM Expense e WHERE e.expenseDate BETWEEN :startDate AND :endDate ORDER BY e.expenseDate DESC")
    List<Expense> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}


