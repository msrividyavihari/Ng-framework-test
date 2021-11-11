package com.deloitte.nextgen.demo.client.mapper;

import com.deloitte.nextgen.demo.client.entities.DcPhone;
import com.deloitte.nextgen.demo.client.request.DcPhoneRequest;
import com.deloitte.nextgen.demo.client.response.DcPhoneResponse;
import com.deloitte.nextgen.framework.commons.mapper.RequestResponseMapper;
import org.mapstruct.Mapper;

/**
 * @author nishmehta on 14/06/2021 2:33 PM
 * @project ng-demo
 */

@Mapper
public interface DcPhoneReqResMapper extends RequestResponseMapper<DcPhoneRequest, DcPhoneResponse, DcPhone> {
}
