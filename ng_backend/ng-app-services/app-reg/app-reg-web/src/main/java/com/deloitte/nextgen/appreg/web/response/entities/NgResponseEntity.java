package com.deloitte.nextgen.appreg.web.response.entities;

import com.deloitte.nextgen.appreg.web.response.entities.common.MessageResponse;
import com.deloitte.nextgen.appreg.web.response.entities.common.WorkflowResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
/**
 * NgResponseEntity to wrap the response
 */
public class NgResponseEntity<T> {
    T data;
    List<WorkflowResponse> workflow;
    MessageResponse messages;
}
