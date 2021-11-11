package com.deloitte.nextgen.audit.consumers.mapper;

import com.deloitte.nextgen.audit.consumers.entities.AuTxnLogContext;
import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.framework.commons.payload.request.audit.AuTxnLogContextDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Mapper(componentModel = "spring" , uses = {AuTxnContextMapper.class})
public interface AuTxnContextMapper extends EntityMapper<AuTxnLogContextDto, AuTxnLogContext> {

}
