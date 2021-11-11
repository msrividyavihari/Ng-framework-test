package com.deloitte.nextgen.demo.client.mapper;

import com.deloitte.nextgen.demo.client.entities.DcEmail;
import com.deloitte.nextgen.demo.client.request.DcEmailRequest;
import com.deloitte.nextgen.demo.client.response.DcEmailResponse;
import com.deloitte.nextgen.framework.commons.mapper.RequestResponseMapper;
import org.mapstruct.Mapper;

/**
 * @author nishmehta on 14/06/2021 2:33 PM
 * @project ng-demo
 */

@Mapper
public interface DcEmailReqResMapper extends RequestResponseMapper<DcEmailRequest, DcEmailResponse, DcEmail> {
}
