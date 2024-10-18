package org.sayari.isexmanagementsystem.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sayari.isexmanagementsystem.dto.IncomeDto;
import org.sayari.isexmanagementsystem.entity.Income;
import org.sayari.isexmanagementsystem.service.income.IncomeServieImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/income")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class IncomeController {

    @Autowired
    private IncomeServieImp incomeServce ;

    @PostMapping("/post")
    public ResponseEntity<?> postIncome(@RequestBody IncomeDto dto){
        Income createIncome = incomeServce.postIncome(dto);
        if (createIncome!= null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createIncome);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/getAllIncome")
    public ResponseEntity<List<?>> getAllIncome(){
        return ResponseEntity.ok(incomeServce.getAllIncome());
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(incomeServce.getIncomeById(id));

        }catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateIncome(@PathVariable Long id,@RequestBody IncomeDto incomeDto){
        try{
            return ResponseEntity.ok(incomeServce.updateIncome(id,incomeDto));
        }catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Long id){
        try{
            incomeServce.deleteIncomeById(id);
            return ResponseEntity.ok(null);
        }catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }









}
