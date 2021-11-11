package com.deloitte.nextgen.dto.entities;

import lombok.Data;

import java.util.Date;

@Data
public class SnapBenefits {
   private Long snapBenefitAmount;
   private Date snapBenefitStartDate;
   private Date snapBenefitEndDate;
   private String abawdIndividualName;
   private Date snapSemiAnualRenewalDate;
   private String snapHhfplLimit;

}
