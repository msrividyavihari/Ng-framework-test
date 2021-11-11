package com.deloitte.nextgen.dto.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Income {
    private String incomeOf;
    private String incomeType;
    private String incomeFrequency;
    private Date incomeDate;
    private Long incomeAmount;
    private Long monthlyAverage;
}
