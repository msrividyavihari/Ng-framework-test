package com.deloitte.nextgen.dc.authrep.mapper;

import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.common.mapstruct.mappers.AddressDtoMapper;
import com.deloitte.nextgen.dc.common.mapstruct.mappers.IndividualNameDtoMapper;
import com.deloitte.nextgen.dc.entities.DcAuthRep;
import com.deloitte.nextgen.dc.entities.DcCaseAddresses;
import com.deloitte.nextgen.dc.entities.DcEmailDetails;
import com.deloitte.nextgen.dc.entities.DcPhnDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {IndividualNameDtoMapper.class, AddressDtoMapper.class}, unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class AuthRepSearchResponseMapper {


    @Mapping(source = "authRep.authrepSeqNum", target = "authRepId")
    @Mapping(source = "authRep", target = "name")
    @Mapping(source = "authRepAddress", target = "address")
    @Mapping(source = "authRepPhnDetails.phnNum", target = "phoneNum1")
    @Mapping(source = "authRepPhnDetails.phoneExtn", target = "phoneExt")
    @Mapping(source = "authRepEmailDetails.email", target = "email")
    public abstract AuthRepDto map(DcAuthRep authRep, DcCaseAddresses authRepAddress, DcPhnDetails authRepPhnDetails, DcEmailDetails authRepEmailDetails);

}
