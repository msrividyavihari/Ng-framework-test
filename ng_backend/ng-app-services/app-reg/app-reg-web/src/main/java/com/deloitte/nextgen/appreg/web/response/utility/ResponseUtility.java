package com.deloitte.nextgen.appreg.web.response.utility;

import com.deloitte.nextgen.appreg.web.response.entities.common.MessageResponse;
import com.deloitte.nextgen.appreg.web.response.entities.common.WorkflowResponse;
import com.deloitte.nextgen.appreg.web.response.entities.NgExceptionResponseEntity;
import com.deloitte.nextgen.appreg.web.response.entities.NgResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;

@Component
public class ResponseUtility {
    /**
     *  If the user wanted to send  messages and data to teh suer
     * @param response the response that reaches to the screen
     * @param workflowResponse To send the workflow response to the user for manipulating workflow respnse
     * @param <T> type of data use of generics
     * @return NgResponseEntity
     */
    public  <T> NgResponseEntity  createResponseObject
            (T response, List<WorkflowResponse> workflowResponse) {
        return createFullResponseObject(response, workflowResponse, null);

    }
    /**
     *  If the user wanted to send  messages and data to teh suer
     * @param response the response that reaches to the screen
     * @param messageResponse Contains all the messages that are displayed to the user
     * @param <T> type of data use of generics
     * @return NgResponseEntity
     */
    public <T> NgResponseEntity createResponseObject
            (T response, MessageResponse messageResponse) {
        return createFullResponseObject(response, null, messageResponse);
    }

    /**
     * The method is used to create a <b>Exception Response</b> with the <b>ebRequest</b> and the <b>message</b>
     *
     * @param Message Error Message
     * @param request WebRequest
     * @return NgExceptionResponseEntity
     * @Author deloitte
     */
    public NgExceptionResponseEntity createErrorResponseObject
    (String Message, WebRequest request) {
        return NgExceptionResponseEntity.builder()
                .errorData(request)
                .messages(MessageResponse.builder()
                        .error(Arrays.asList(Message))
                        .build())
                .build();
    }

    /**
     *  If the user wanted to send workflow , messages and data to teh suer
     * @param response
     * @param workflowResponse To send the workflow response to the user for manipulating workflow respnse
     * @param messageResponse Contains all the messages that are displayed to the user
     * @param <T> type of data use of generics
     * @return NgResponseEntity
     */
    public <T> NgResponseEntity createResponseObject(T response, List<WorkflowResponse> workflowResponse, MessageResponse messageResponse) {
        return createFullResponseObject(response, workflowResponse, messageResponse);
    }

    /**
     *  If the user wanted to send only data then he can use this to send
     *  only data to the user
     * @param response Dto or json data to return to user
     * @param <T> type of data use of generics
     * @return NgResponseEntity
     */
    public <T> NgResponseEntity createResponseObject(T response) {
        return createFullResponseObject(response, null, null);
    }


    /**
     * Private function to create the NgResponseEntity object
     * @param response Dto or json data to return to user
     * @param workflowResponse To send the workflow response to the user for manipulating workflow respnse
     * @param messageResponse Contains all the messages that are displayed to the user
     * @param <T> type of data use of generics
     * @return NgResponseEntity
     */
    private <T> NgResponseEntity createFullResponseObject(T response, List<WorkflowResponse> workflowResponse, MessageResponse messageResponse) {
        return NgResponseEntity.builder()
                .data(response)
                .messages(messageResponse)
                .workflow(workflowResponse)
                .build();
    }

}
