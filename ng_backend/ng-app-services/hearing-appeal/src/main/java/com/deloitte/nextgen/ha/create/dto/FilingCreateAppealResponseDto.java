package com.deloitte.nextgen.ha.create.dto;

import com.deloitte.nextgen.ha.appeals.dto.AppealInfoDto;
import com.deloitte.nextgen.ha.appeals.dto.AppellantInfoDto;
import com.deloitte.nextgen.ha.appeals.dto.AuthRepInfoDto;
import com.deloitte.nextgen.ha.appeals.dto.ContactInfoDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class FilingCreateAppealResponseDto {

   private AppellantInfoDto appellantDetails;
   private AppealInfoDto appealDetails;
   private ContactInfoDto contactDetails;
   private AuthRepInfoDto authRepDetails;

}