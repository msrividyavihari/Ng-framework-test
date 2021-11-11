package com.deloitte.nextgen.dto.entities;

import lombok.Data;

@Data
public class Expense {
    private String expenseOf;
    private String expenseType;
    private String expenseFrequency;
    private Long expenseAmount;
    private Long monthlyAverage;
}
