package com.deloitte.nextgen.demo.client.automate;

import com.deloitte.nextgen.demo.client.DemoConstants;
import com.deloitte.nextgen.demo.client.entities.DcAddress;
import com.deloitte.nextgen.demo.client.entities.DcEmail;
import com.deloitte.nextgen.demo.client.entities.DcIndividual;
import com.deloitte.nextgen.demo.client.entities.DcPhone;
import com.deloitte.nextgen.demo.client.mapper.DcAddressReqResMapper;
import com.deloitte.nextgen.demo.client.mapper.DcEmailReqResMapper;
import com.deloitte.nextgen.demo.client.mapper.DcIndividualReqResMapper;
import com.deloitte.nextgen.demo.client.mapper.DcPhoneReqResMapper;
import com.deloitte.nextgen.demo.client.request.DcAddressRequest;
import com.deloitte.nextgen.demo.client.request.DcEmailRequest;
import com.deloitte.nextgen.demo.client.request.DcIndividualRequest;
import com.deloitte.nextgen.demo.client.request.DcPhoneRequest;
import com.deloitte.nextgen.demo.client.response.DcAddressResponse;
import com.deloitte.nextgen.demo.client.response.DcEmailResponse;
import com.deloitte.nextgen.demo.client.response.DcIndividualResponse;
import com.deloitte.nextgen.demo.client.response.DcPhoneResponse;
import com.deloitte.nextgen.framework.automate.annotations.Endpoint;

/**
 * @author nishmehta
 * @since 29/06/2021 11:34 AM
 */

@Endpoint(
        name = DemoConstants.EndpointNames.DC_INDIVIDUAL,
        entity = DcIndividual.class,
        request = DcIndividualRequest.class,
        response = DcIndividualResponse.class,
        mapper = DcIndividualReqResMapper.class
)
@Endpoint(
        name = DemoConstants.EndpointNames.DC_ADDRESS,
        entity = DcAddress.class,
        request = DcAddressRequest.class,
        response = DcAddressResponse.class,
        mapper = DcAddressReqResMapper.class
)
@Endpoint(
        name = DemoConstants.EndpointNames.DC_PHONE,
        entity = DcPhone.class,
        request = DcPhoneRequest.class,
        response = DcPhoneResponse.class,
        mapper = DcPhoneReqResMapper.class
)
@Endpoint(
        name = DemoConstants.EndpointNames.DC_EMAIL,
        entity = DcEmail.class,
        request = DcEmailRequest.class,
        response = DcEmailResponse.class,
        mapper = DcEmailReqResMapper.class
)
public interface DemoAutoGenerate {
}
