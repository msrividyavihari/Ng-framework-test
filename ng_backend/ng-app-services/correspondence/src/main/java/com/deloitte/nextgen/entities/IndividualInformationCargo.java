package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.util.PrintDate;
import lombok.Data;

import java.io.Serializable;

@Data
public class IndividualInformationCargo implements Serializable {
    private String name = null;
    private String dateOfBirth = null;
    private String raceCode = null;
    private String ethnicityCode = null;
    private Long ssn = 0L;
    private String sscn;
    private Character sexCode = 0;
    private String sex = null;
    private String phoneNumber = null;
    private String citizenCode = null;
    private String educationCode = null;
    private String spouseName = null;
    private Long individualID = 0L;
    private String registrationNumber = null;
    private String nationality = null;
    private PrintDate dobPrintDate = null;
    private String nameReverse = null;
    private String nameFirstLastSuffix = null;
}
