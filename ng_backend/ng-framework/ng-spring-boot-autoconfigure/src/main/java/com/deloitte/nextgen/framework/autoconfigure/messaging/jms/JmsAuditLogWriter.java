package com.deloitte.nextgen.framework.autoconfigure.messaging.jms;

import com.deloitte.nextgen.framework.commons.payload.request.audit.AuTxnLogContextDto;
import com.deloitte.nextgen.framework.commons.spi.AuditLogWriter;
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
public class JmsAuditLogWriter implements AuditLogWriter {


    private final JmsTemplate jmsTemplate;

    private final Queue auditQueue;

    public JmsAuditLogWriter(JmsTemplate jmsTemplate, Queue auditQueue) {
        this.jmsTemplate = jmsTemplate;
        this.auditQueue = auditQueue;
    }


    @Override
    public void writeLog(AuTxnLogContextDto contextDto) {
        try {
            log.info(LogMarker.AUDIT, "Posting audit log to queue");
            jmsTemplate.convertAndSend(auditQueue, contextDto);
        } catch (JmsException ce) {
            log.error("Exception occurred while sending to exception to JMS audit queue : ", ce);
        }

    }
}
