package org.sayari.isexmanagementsystem.service.income;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sayari.isexmanagementsystem.dto.IncomeDto;
import org.sayari.isexmanagementsystem.entity.Income;
import org.sayari.isexmanagementsystem.reposetry.IncomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServieImp implements IncomeServce{

    @Autowired
    private IncomeRepo incomeRepo;
    public Income postIncome(IncomeDto incomeDto){
        return saveOrUpdateIncome(new Income(), incomeDto);
    }

    public Income saveOrUpdateIncome(Income income, IncomeDto incomeDto){
        income.setTitle(incomeDto.getTitle());
        income.setDate(incomeDto.getDate());
        income.setAmount(incomeDto.getAmount());
        income.setDescription(incomeDto.getDescription());
        income.setCategory(incomeDto.getCategory());
        return incomeRepo.save(income);
    }
    public List<IncomeDto> getAllIncome(){
        return incomeRepo.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }
    public Income getIncomeById(Long id){
        Optional<Income> IncomrOptional= incomeRepo.findById(id);
        if(IncomrOptional.isPresent()){
            return IncomrOptional.get();
        }else {
            throw new EntityNotFoundException("the id not found"+id);
        }
    }
    public Income updateIncome(Long id,IncomeDto  incomeDto){
        Optional<Income> incomeOptional  = incomeRepo.findById(id);
        if (incomeOptional.isPresent()){
            return saveOrUpdateIncome(incomeOptional.get(),incomeDto);
        }else {
            throw new EntityNotFoundException("the id not found"+id);
        }
    }
    public void deleteIncomeById(Long id){
        Optional<Income> incomeOptional =incomeRepo.findById(id);
        if(incomeOptional.isPresent()){
            incomeRepo.deleteById(id);
        }else {
            throw new EntityNotFoundException("id not found"+id);
        }
    }




}
