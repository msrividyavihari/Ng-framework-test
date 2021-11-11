package com.deloitte.nextgen.demo.client.mapper;

import com.deloitte.nextgen.demo.client.entities.Employee;
import com.deloitte.nextgen.demo.client.request.EmployeeRequest;
import com.deloitte.nextgen.demo.client.response.EmployeeResponse;
import com.deloitte.nextgen.framework.commons.mapper.RequestResponseMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

/**
 * @author nishmehta on 14/06/2021 2:33 PM
 * @project ng-demo
 */

@Mapper(uses = DcAddressReqResMapper.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface EmployeeReqResMapper extends RequestResponseMapper<EmployeeRequest, EmployeeResponse, Employee> {
}
