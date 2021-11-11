package com.deloitte.nextgen.audit.consumers.services.impl;

import com.deloitte.nextgen.audit.consumers.entities.AuTxnLogContext;
import com.deloitte.nextgen.audit.consumers.repository.AuTxnLogContextRepository;
import com.deloitte.nextgen.audit.consumers.services.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    private AuTxnLogContextRepository auTxnLogContextRepository;

    @Override
    public AuTxnLogContext insert(AuTxnLogContext auTxnLogContext) {

        List<AuTxnLogContext> contextList = auTxnLogContextRepository.findByCorrealtionId(auTxnLogContext.getCorrelationId());

        if (contextList != null && !contextList.isEmpty()) {
            Optional<AuTxnLogContext> first = contextList.stream().findFirst();

            AuTxnLogContext logContext = first.get();

            logContext.getAuTxnLogList().add(auTxnLogContext.getAuTxnLogList().get(0));
            return auTxnLogContextRepository.save(logContext);
        } else{
            return auTxnLogContextRepository.save(auTxnLogContext);
        }

    }

}
