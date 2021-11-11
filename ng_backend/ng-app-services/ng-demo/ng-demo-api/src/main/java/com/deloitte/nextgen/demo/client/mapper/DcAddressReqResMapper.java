package com.deloitte.nextgen.demo.client.mapper;

import com.deloitte.nextgen.demo.client.entities.DcAddress;
import com.deloitte.nextgen.demo.client.request.DcAddressRequest;
import com.deloitte.nextgen.demo.client.response.DcAddressResponse;
import com.deloitte.nextgen.framework.commons.mapper.RequestResponseMapper;
import org.mapstruct.Mapper;

/**
 * @author nishmehta on 14/06/2021 2:33 PM
 * @project ng-demo
 */

@Mapper
public interface DcAddressReqResMapper extends RequestResponseMapper<DcAddressRequest, DcAddressResponse, DcAddress> {
}
