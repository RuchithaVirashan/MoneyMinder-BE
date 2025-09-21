package com.moneyminder.service;

import com.moneyminder.presentation.dto.DailyReportDto;
import com.moneyminder.presentation.dto.MonthlyReportDto;
import com.moneyminder.repository.ExpenseRepository;
import com.moneyminder.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class ReportService {

    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;

    public ReportService(ExpenseRepository expenseRepository, IncomeRepository incomeRepository) {
        this.expenseRepository = expenseRepository;
        this.incomeRepository = incomeRepository;
    }

    public DailyReportDto getDailyReport(LocalDate date) {
        // Get income data for the date
        BigDecimal totalIncome = incomeRepository.sumAmountByDate(date);
        Long incomeCount = incomeRepository.countByDate(date);
        
        // Get expense data for the date
        BigDecimal totalExpense = expenseRepository.sumAmountByDate(date);
        Long expenseCount = expenseRepository.countByDate(date);
        
        // Handle null values
        totalIncome = totalIncome != null ? totalIncome : BigDecimal.ZERO;
        totalExpense = totalExpense != null ? totalExpense : BigDecimal.ZERO;
        incomeCount = incomeCount != null ? incomeCount : 0L;
        expenseCount = expenseCount != null ? expenseCount : 0L;
        
        // Calculate net amount (income - expense)
        BigDecimal netAmount = totalIncome.subtract(totalExpense);
        
        return new DailyReportDto(
            date,
            totalIncome,
            totalExpense,
            netAmount,
            incomeCount.intValue(),
            expenseCount.intValue()
        );
    }

    public MonthlyReportDto getMonthlyReport(int year, int month) {
        // Get income data for the month
        BigDecimal totalIncome = incomeRepository.sumAmountByYearAndMonth(year, month);
        Long incomeCount = incomeRepository.countByYearAndMonth(year, month);
        
        // Get expense data for the month
        BigDecimal totalExpense = expenseRepository.sumAmountByYearAndMonth(year, month);
        Long expenseCount = expenseRepository.countByYearAndMonth(year, month);
        
        // Handle null values
        totalIncome = totalIncome != null ? totalIncome : BigDecimal.ZERO;
        totalExpense = totalExpense != null ? totalExpense : BigDecimal.ZERO;
        incomeCount = incomeCount != null ? incomeCount : 0L;
        expenseCount = expenseCount != null ? expenseCount : 0L;
        
        // Calculate net amount (income - expense)
        BigDecimal netAmount = totalIncome.subtract(totalExpense);
        
        // Get the number of days in the month
        YearMonth yearMonth = YearMonth.of(year, month);
        int totalDays = yearMonth.lengthOfMonth();
        
        return new MonthlyReportDto(
            yearMonth,
            totalIncome,
            totalExpense,
            netAmount,
            incomeCount.intValue(),
            expenseCount.intValue(),
            totalDays
        );
    }

    public MonthlyReportDto getCurrentMonthReport() {
        LocalDate now = LocalDate.now();
        return getMonthlyReport(now.getYear(), now.getMonthValue());
    }

    public DailyReportDto getTodayReport() {
        return getDailyReport(LocalDate.now());
    }
}
