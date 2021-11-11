package com.deloitte.nextgen.demo.client.api;

import com.deloitte.nextgen.demo.client.DemoConstants;
import com.deloitte.nextgen.framework.automate.annotations.OperationURI;

/**
 * @author nishmehta
 * @since 09/07/2021 11:15 AM
 */

@OperationURI(DemoConstants.EndpointNames.DC_ADDRESS)
@OperationURI(DemoConstants.EndpointNames.EMPLOYEE)
@OperationURI(DemoConstants.EndpointNames.DC_INDIVIDUAL)
@OperationURI(DemoConstants.EndpointNames.DC_PHONE)
@OperationURI(DemoConstants.EndpointNames.DC_EMAIL)
public interface DemoApi {
}
