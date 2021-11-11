package com.deloitte.nextgen.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Individual {
    private String Name;
    private String MemberId;
    @JsonFormat(pattern="MM-dd-yyyy")
    private Timestamp IndividualDOB;
    private String InHousehold;
    private Long Age;
    private String noticeSalutation;
    private String noticeContent;
    private String policyReference;
    private String Program;
    @JsonFormat(pattern="MM-dd-yyyy")
    private Timestamp EffectiveDate;
    @JsonFormat(pattern="MM-dd-yyyy")
    private Timestamp ClosureDate;
    private Timestamp RenewalMonth;
    private String EligibilityStatus;
    private String ProgramClosureReason;

}
