package org.sayari.isexmanagementsystem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;

}
