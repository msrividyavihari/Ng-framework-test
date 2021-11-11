package com.deloitte.nextgen.dto.entities;

import lombok.Data;

import java.sql.Date;

@Data
public class TafdcBenefits {
    private Date tanfProgramEndDate;
    private Date tanfReapplicationDate;
    private String tanfClosureReasons;
    private String tanfHhfplLimit;
}
