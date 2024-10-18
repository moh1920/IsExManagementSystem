package org.sayari.isexmanagementsystem.service.stats;

import lombok.RequiredArgsConstructor;
import org.sayari.isexmanagementsystem.dto.GraphDTo;
import org.sayari.isexmanagementsystem.dto.StatsDto;
import org.sayari.isexmanagementsystem.entity.Expense;
import org.sayari.isexmanagementsystem.entity.Income;
import org.sayari.isexmanagementsystem.reposetry.ExpenseRepo;
import org.sayari.isexmanagementsystem.reposetry.IncomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImp implements StatsService{
    @Autowired
    private IncomeRepo incomeRepo;
    @Autowired
    private ExpenseRepo expenseRepo;


    public GraphDTo getChartData(){
        LocalDate endDate= LocalDate.now();
        LocalDate startDate=endDate.minusDays(27);

        GraphDTo graphDTo = new GraphDTo();

        graphDTo.setExpenseList(expenseRepo.findByDateBetween(startDate,endDate));
        graphDTo.setIncomeList(incomeRepo.findByDateBetween(startDate,endDate));

        return graphDTo ;

    }
    public StatsDto getStats() {
        Double totalIncome = incomeRepo.SumAllAmounts();
        Double totalExpense = expenseRepo.SumAllAmounts();

        Optional<Income> optionalIncome= incomeRepo.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense= expenseRepo.findFirstByOrderByDateDesc();


        StatsDto statsDto =new StatsDto();
        statsDto.setExpense(totalExpense);
        statsDto.setIncome(totalIncome);
        optionalExpense.ifPresent(statsDto::setLastExpense);
        optionalIncome.ifPresent(statsDto::setLastIncome);


        statsDto.setBalance(totalIncome-totalExpense);


        List<Income> incomeList = incomeRepo.findAll();
        List<Expense> expenseList = expenseRepo.findAll();


        OptionalDouble minIncome = incomeList.stream()
                        .mapToDouble(Income::getAmount)
                                .min();
        OptionalDouble maxIncome = incomeList.stream()
                .mapToDouble(Income::getAmount)
                .max();
        OptionalDouble minExpense = expenseList.stream()
                .mapToDouble(Expense::getAmount)
                .min();
        OptionalDouble maxExpense = expenseList.stream()
                .mapToDouble(Expense::getAmount)
                .max();

        statsDto.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
        statsDto.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);

        statsDto.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
        statsDto.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);

        return statsDto;


    }

}
