package org.sayari.isexmanagementsystem.dto;

import lombok.Data;
import org.sayari.isexmanagementsystem.entity.Expense;
import org.sayari.isexmanagementsystem.entity.Income;

@Data
public class StatsDto {
    private Double income;
    private Double expense;
    private Income LastIncome;
    private Expense LastExpense;
    private Double balance;
    private Double maxIncome;
    private Double maxExpense;
    private Double minExpense;
    private Double minIncome;
}
