package com.deloitte.nextgen.appreg.client.request;

import com.deloitte.nextgen.appreg.client.enums.Active;
import com.deloitte.nextgen.appreg.client.entities.converters.ActiveConverter;
import lombok.*;

import javax.persistence.Convert;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class PriorityStatusRequest {
    private List<String> priorityAppStatus;
    @NotNull
    private String appNum;
    @Convert(converter = ActiveConverter.class)
    private Active abdCheckSw;
}
