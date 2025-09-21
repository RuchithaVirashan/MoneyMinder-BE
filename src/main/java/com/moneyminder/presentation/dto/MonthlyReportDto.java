package com.moneyminder.presentation.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

public record MonthlyReportDto(
    YearMonth month,
    BigDecimal totalIncome,
    BigDecimal totalExpense,
    BigDecimal netAmount,
    int incomeCount,
    int expenseCount,
    int totalDays
) {}
