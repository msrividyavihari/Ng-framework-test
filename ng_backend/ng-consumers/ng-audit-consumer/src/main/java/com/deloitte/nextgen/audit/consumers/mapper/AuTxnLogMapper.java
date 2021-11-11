package com.deloitte.nextgen.audit.consumers.mapper;

import com.deloitte.nextgen.audit.consumers.entities.AuTxnLog;
import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.framework.commons.payload.request.audit.AuTxnLogDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;


@Mapper(componentModel = "spring", uses = {AuTxnLogMapper.class})
public interface AuTxnLogMapper extends EntityMapper<AuTxnLogDto, AuTxnLog> {
}
