package com.deloitte.nextgen.demo.client.mapper;

import com.deloitte.nextgen.demo.client.entities.DcIndividual;
import com.deloitte.nextgen.demo.client.request.DcIndividualRequest;
import com.deloitte.nextgen.demo.client.response.DcIndividualResponse;
import com.deloitte.nextgen.framework.commons.mapper.RequestResponseMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

/**
 * @author nishmehta on 14/06/2021 2:33 PM
 * @project ng-demo
 */

@Mapper(uses = {DcAddressReqResMapper.class, DcEmailReqResMapper.class, DcPhoneReqResMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface DcIndividualReqResMapper extends RequestResponseMapper<DcIndividualRequest, DcIndividualResponse, DcIndividual> {
}
