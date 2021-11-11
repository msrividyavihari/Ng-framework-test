package com.deloitte.nextgen.appreg.web.response.entities;

import com.deloitte.nextgen.appreg.web.response.entities.common.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
/**
 * NgExceptionResponseEntity to wrap the error Response
 */
public class NgExceptionResponseEntity  {
    Object errorData;
    MessageResponse messages;
}
