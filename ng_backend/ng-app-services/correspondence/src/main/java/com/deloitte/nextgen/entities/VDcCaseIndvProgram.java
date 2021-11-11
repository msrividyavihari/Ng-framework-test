package com.deloitte.nextgen.entities;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="V_DC_CASE_INDV_PROGRAM")
@NoArgsConstructor
@Data
public class VDcCaseIndvProgram implements Serializable {

    @Id
    @Column(name = "T1_INDV_ID")
    private long t1IndvId;
    @Column(name = "T1_CASE_NUM")
    private long t1caseNum;
    @Column(name = "DOB_DT")
    private java.sql.Timestamp dobDt;
    @Column(name = "GENDER_CD")
    private char genderCd;
    @Column(name = "LAST_NAME")
    private java.lang.String lastName;
    @Column(name = "FIRST_NAME")
    private java.lang.String firstName;

}
