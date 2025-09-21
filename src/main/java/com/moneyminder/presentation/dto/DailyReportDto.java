package com.moneyminder.presentation.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DailyReportDto(
    LocalDate date,
    BigDecimal totalIncome,
    BigDecimal totalExpense,
    BigDecimal netAmount,
    int incomeCount,
    int expenseCount
) {}
