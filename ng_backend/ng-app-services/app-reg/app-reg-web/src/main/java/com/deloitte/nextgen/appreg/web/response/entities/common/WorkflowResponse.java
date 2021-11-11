package com.deloitte.nextgen.appreg.web.response.entities.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
/**
 * Workflow object which will manipulate the workflow of the screen
 * which has workFlowName the following workflow name or is defaulted to the current workflow
 */
public class WorkflowResponse {
    String pageId;
    Mode mode; //add or Delete
    Position position; // After or Before
    String workFlowName; // currentWOrkflow name
    String refrencePageId; //
}
