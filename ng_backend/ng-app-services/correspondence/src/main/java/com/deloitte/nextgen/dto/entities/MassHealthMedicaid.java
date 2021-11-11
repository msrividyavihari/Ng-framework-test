package com.deloitte.nextgen.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MassHealthMedicaid {
    private List<Individual> Individual;
    private TafdcBenefits tafdcBenefits;
    private SnapBenefits snapBenefits;
    private LegalServices legalServices;
    private String regulations;
    private List<SnapAdditionalInfo> snapAdditionalInfo;
    private List<Income> Income;
    private List<Expense> Expense;
    private List<Budget> budget;
    private List<SnapCalculation> snapCalculation;
    private String Householdsize;
    private String HHFPLLimit;
    private StandardRenewalLetter standardRenewalLetter;
}
