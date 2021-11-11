package com.deloitte.nextgen.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part1Details {
    private String primaryContactName;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp primaryContactDOB;
    private String residenceAddressLine1;
    private String residenceAddressLine2;
    private String residenceAddressCity;
    private String residenceAddressState;
    private Long residenceAddressZipCode;
    private Boolean displayFlagForMailingAddress;
    private String mailingAddressLine1;
    private String mailingAddressLine2;
    private String mailingAddressCity;
    private String mailingAddressState;
    private Long mailingAddressZipCode;
    private Boolean displayFlagForCellNumber;
    private Long cellNumber;
    private Boolean displayFlagForLandlineNumber;
    private Long landlineNumber;
    private Boolean displayFlagForWorkNumber;
    private Long workNumber;
    private Boolean displayFlagForOtherNumber;
    private Long otherNumber;
    private Boolean displayFlagForPersonalEmail;
    private String personalEmail;
    private Boolean displayFlagForWorkEmail;
    private String workEmail;
}
