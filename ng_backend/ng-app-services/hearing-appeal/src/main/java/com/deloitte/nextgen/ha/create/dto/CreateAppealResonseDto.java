package com.deloitte.nextgen.ha.create.dto;

import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@ToString
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class CreateAppealResonseDto {

    @NotNull
    BigInteger primaryAppealNum;
    AuthRepDto authRepInfo;

}
