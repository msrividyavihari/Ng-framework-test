package com.deloitte.nextgen.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardRenewalLetterDetails {
    private Boolean displayFlagForWaysToRenewCoverage;
    private String renewCoverageRenewalIndividual;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private java.sql.Timestamp renewCoverageDueDt;
    private Boolean displayFlagForAppendixC;
    private Part1Details part1Details;
    private List<Part2Details> part2Details;
    private Part3Details part3Details;
    private Part6Details part6Details;
    private Part7Details part7Details;
    private Part9Details part9Details;

}
