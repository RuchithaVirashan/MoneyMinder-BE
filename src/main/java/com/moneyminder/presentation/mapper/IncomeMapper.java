package com.moneyminder.presentation.mapper;

import com.moneyminder.domain.Income;
import com.moneyminder.presentation.dto.IncomeDto;

public final class IncomeMapper {

    private IncomeMapper() {}

    public static Income toEntity(IncomeDto dto) {
        if (dto == null) return null;
        Income income = new Income();
        income.setAmount(dto.amount());
        income.setDescription(dto.description());
        income.setIncomeDate(dto.incomeDate());
        return income;
    }

    public static IncomeDto toDto(Income entity) {
        if (entity == null) return null;
        return new IncomeDto(entity.getAmount(), entity.getDescription(), entity.getIncomeDate());
    }
}


