package org.sayari.isexmanagementsystem.service.expense;

import org.sayari.isexmanagementsystem.dto.ExpenseDto;
import org.sayari.isexmanagementsystem.entity.Expense;

import java.util.List;

public interface ExpendeServiceImp {

    Expense postExpense(ExpenseDto expenseDto);
    List<Expense> getAllExpenses();
    Expense getExpenseById(Long id);
    Expense updateExpense(Long id,ExpenseDto expenseDto);
    void deleteExpenseById(Long id);
}
