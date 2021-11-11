package com.deloitte.nextgen.appreg.client.response;

import com.deloitte.nextgen.appreg.client.entities.ArExpScreenRespDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class ArExpResponse {
    List<ArExpScreenRespDto> arExpScreenRespDtoList;
    @NotNull
    String appNum;
}
