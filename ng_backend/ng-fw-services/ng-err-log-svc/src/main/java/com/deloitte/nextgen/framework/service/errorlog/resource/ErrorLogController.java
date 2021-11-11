package com.deloitte.nextgen.framework.service.errorlog.resource;


import com.deloitte.nextgen.framework.commons.payload.request.error.ErrorContextRequest;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@Slf4j
@RestController
public class ErrorLogController {


    @Autowired
    @Qualifier("errorJmsTemplate")
    private JmsTemplate jmsTemplate;

    @Autowired
    @Qualifier("errorQueue")
    private Queue queue;

    @PostMapping("/log")
    public ResponseEntity<ApiResponse<String>> sendErrorMessage(@RequestBody final ErrorContextRequest errorContext) {
        log.info(errorContext.getCorrelationId());

        jmsTemplate.convertAndSend(queue, errorContext);
        //sendMessage(logMessage);
        return ApiResponse.success(100).build();
    }

/*    @SendTo("error.log.queue")
    public ErrorMessageRequest sendMessage(final ErrorMessageRequest jsonMessage) {
        return jsonMessage;
    }*/
}
