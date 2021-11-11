package com.deloitte.nextgen.framework.autoconfigure.messaging.jms;

import com.deloitte.nextgen.framework.commons.payload.request.error.ErrorContextRequest;
import com.deloitte.nextgen.framework.commons.spi.ExceptionWriter;
import com.deloitte.nextgen.framework.logging.LogMarker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
public class JmsExceptionWriter implements ExceptionWriter {

    private final JmsTemplate jmsTemplate;

    private final Queue errorQueue;

    public JmsExceptionWriter(JmsTemplate jmsTemplate, Queue errorQueue) {
        this.jmsTemplate = jmsTemplate;
        this.errorQueue = errorQueue;
    }

    @Override
    public void write(ErrorContextRequest errorContextDTO) {
        try {
            log.info(LogMarker.EXCEPTION, "Posting error log to queue");
            jmsTemplate.convertAndSend(errorQueue, errorContextDTO);
        } catch (JmsException ce) {
            log.error("Exception occurred while sending to exception to JMS error queue : ", ce);
        }
    }
}
