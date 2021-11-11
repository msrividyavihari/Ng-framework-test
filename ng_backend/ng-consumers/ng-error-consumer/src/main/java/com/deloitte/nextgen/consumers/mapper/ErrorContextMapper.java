package com.deloitte.nextgen.consumers.mapper;

import com.deloitte.nextgen.consumers.entities.ErrorContext;
import com.deloitte.nextgen.framework.commons.mapper.EntityMapper;
import com.deloitte.nextgen.framework.commons.payload.request.error.ErrorContextRequest;
import org.mapstruct.Mapper;

/**
 * @author nishmehta on 15/03/2021 2:40 PM
 * @project ng-consumers
 */

@Mapper(componentModel = "spring", uses = {ErrorLogMapper.class})
public interface ErrorContextMapper extends EntityMapper<ErrorContextRequest, ErrorContext> {
}
