package com.deloitte.nextgen.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part3Details {
    private Boolean displayFlagForTaxFiler;
    private String taxFiler;
    private Boolean displayFlagForSpouseOnReturn;
    private String spouseOnReturn;
    private Boolean displayFlagForDependantOnReturn;
    private String dependantOnReturn;
}
