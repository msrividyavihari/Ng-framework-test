package com.deloitte.nextgen.audit.consumers.listener;


import com.deloitte.nextgen.audit.consumers.entities.AuTxnLogContext;
import com.deloitte.nextgen.audit.consumers.mapper.AuTxnContextMapper;
import com.deloitte.nextgen.audit.consumers.services.AuditLogService;
import com.deloitte.nextgen.framework.commons.payload.request.audit.AuTxnLogContextDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MessageListener {


    @Autowired
    public AuTxnContextMapper auContextMapper;

    @Autowired
    private AuditLogService auditLogService;

    @JmsListener(destination = "audit.log")
    public void onMessage(AuTxnLogContextDto auTxnLogContextDto) {
        try {
            System.err.println(auTxnLogContextDto);
            AuTxnLogContextDto ec = auContextMapper.toEntity(auTxnLogContextDto);
            auditLogService.insert(ec);

        } catch (Exception e) {
            log.error("Error while inserting audit data", e);
        }

    }
}
