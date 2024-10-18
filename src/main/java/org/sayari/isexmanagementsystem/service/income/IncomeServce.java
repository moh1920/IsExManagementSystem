package org.sayari.isexmanagementsystem.service.income;

import org.sayari.isexmanagementsystem.dto.IncomeDto;
import org.sayari.isexmanagementsystem.entity.Income;

import java.util.List;

public interface IncomeServce {
    Income saveOrUpdateIncome(Income income, IncomeDto incomeDto);
    List<IncomeDto> getAllIncome();
    void deleteIncomeById(Long id);
    Income updateIncome(Long id,IncomeDto  incomeDto);
    Income getIncomeById(Long id);
    Income postIncome(IncomeDto incomeDto);
}
