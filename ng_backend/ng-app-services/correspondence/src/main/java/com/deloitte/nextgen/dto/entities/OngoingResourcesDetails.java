package com.deloitte.nextgen.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OngoingResourcesDetails {
    private String resourceIndividual;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp resourceIndividualDOB;
    private String resourceType;
}
