package com.deloitte.nextgen.consumers.listener;


import com.deloitte.nextgen.consumers.entities.ErrorContext;
import com.deloitte.nextgen.consumers.mapper.ErrorContextMapper;
import com.deloitte.nextgen.consumers.service.ErrorMessageService;
import com.deloitte.nextgen.framework.commons.payload.request.error.ErrorContextRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author nishmehta on 09/03/2021 11:10 AM
 * @project ng-consumers
 */

@Slf4j
@Component
public class ErrorMessageListener {

    @Autowired
    private ErrorMessageService errorMessageService;

    @Autowired
    private ErrorContextMapper errorContextMapper;

    @JmsListener(destination = "error.log")
    public void onMessage(ErrorContextRequest errorContextDTO) {
        try {


            ErrorContext ec = errorContextMapper.toEntity(errorContextDTO);
            ec.getErrorLog().setExceptionDetail(ec.getErrorLog().getExceptionDetail().substring(0, 7999));
            errorMessageService.insert(ec);
            log.info("Received Message -> " + ec.getCorrelationId());
        } catch (Exception e) {
            log.error("Received Exception : ", e);
        }

    }
}
