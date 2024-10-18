package org.sayari.isexmanagementsystem.service.expense;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sayari.isexmanagementsystem.dto.ExpenseDto;
import org.sayari.isexmanagementsystem.entity.Expense;
import org.sayari.isexmanagementsystem.reposetry.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService implements ExpendeServiceImp{

    @Autowired
    private ExpenseRepo expenseRepo;


    public Expense postExpense(ExpenseDto expenseDto){
        return saveOrUpdateExpense(new Expense(), expenseDto);
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDto expenseDto){
        expense.setTitle(expenseDto.getTitle());
        expense.setDate(expenseDto.getDate());
        expense.setAmount(expenseDto.getAmount());
        expense.setDescription(expenseDto.getDescription());
        expense.setCategory(expenseDto.getCategory());
        return expenseRepo.save(expense);
    }
    public List<Expense> getAllExpenses(){
        return expenseRepo.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }
    public Expense getExpenseById(Long id){
        Optional<Expense> expenseOptional= expenseRepo.findById(id);
        if(expenseOptional.isPresent()){
            return expenseOptional.get();
        }else {
            throw new EntityNotFoundException("the id not found"+id);
        }
    }
    public Expense updateExpense(Long id,ExpenseDto expenseDto){
        Optional<Expense> expenseOptional  = expenseRepo.findById(id);
        if (expenseOptional.isPresent()){
            return saveOrUpdateExpense(expenseOptional.get(),expenseDto);
        }else {
            throw new EntityNotFoundException("the id not found"+id);
        }
    }
    public void deleteExpenseById(Long id){
        Optional<Expense> expenseOptional =expenseRepo.findById(id);
        if(expenseOptional.isPresent()){
            expenseRepo.deleteById(id);
        }else {
            throw new EntityNotFoundException("id not found"+id);
        }
    }



}
