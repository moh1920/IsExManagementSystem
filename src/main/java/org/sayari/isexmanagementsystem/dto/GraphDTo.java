package org.sayari.isexmanagementsystem.dto;

import lombok.Data;
import org.sayari.isexmanagementsystem.entity.Expense;
import org.sayari.isexmanagementsystem.entity.Income;

import java.util.List;

@Data
public class GraphDTo {

    private List<Expense> expenseList;
    private List<Income> incomeList;


}
