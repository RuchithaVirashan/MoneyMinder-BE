package com.moneyminder.presentation.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseDto(
    @NotNull(message = "amount is required") @DecimalMin(value = "0.00", message = "amount must be >= 0.00") BigDecimal amount,
    @Size(max = 255, message = "description must be at most 255 characters") String description,
    @NotNull(message = "expenseDate is required") LocalDate expenseDate
) {}


