package com.moneyminder.presentation.mapper;

import com.moneyminder.domain.Expense;
import com.moneyminder.presentation.dto.ExpenseDto;

public final class ExpenseMapper {

    private ExpenseMapper() {}

    public static Expense toEntity(ExpenseDto dto) {
        if (dto == null) return null;
        Expense expense = new Expense();
        expense.setAmount(dto.amount());
        expense.setDescription(dto.description());
        expense.setExpenseDate(dto.expenseDate());
        return expense;
    }

    public static ExpenseDto toDto(Expense entity) {
        if (entity == null) return null;
        return new ExpenseDto(entity.getAmount(), entity.getDescription(), entity.getExpenseDate());
    }
}


