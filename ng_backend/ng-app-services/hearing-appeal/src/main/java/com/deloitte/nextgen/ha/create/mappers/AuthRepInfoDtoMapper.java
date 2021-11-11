package com.deloitte.nextgen.ha.create.mappers;


import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.common.dto.AddressDto;
import com.deloitte.nextgen.dc.common.dto.IndividualNameDto;
import com.deloitte.nextgen.ha.appeals.dto.AuthRepInfoDto;
import com.deloitte.nextgen.ha.entity.AmAddress;
import com.deloitte.nextgen.ha.create.dto.AmRepresentativeDetailsDto;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class AuthRepInfoDtoMapper {


    @Autowired
    private AmAddressMapping amAddressMapping;

    @Mapping(source = "caseAuthRepFlag", target = "caseAuthRepIncludedInAppeal")
    @Mapping(source = "authRepDetails", target = "caseLevelAuthRep") //Optional authroization
    @Mapping(ignore = true, target = "associatedAuthReps")
    public abstract AuthRepInfoDto convertToAuthRepDto(Boolean caseAuthRepFlag,
                                              AuthRepDto authRepDetails,List<AmRepresentativeDetailsDto> amRepresentativeInfo,
                                              List<AmAddress> amAuthRepAddressDetails);




    @Mapping(source = "amAuthRepAddressDetails", target = "address")
    @Mapping(source = "amRepresentativeInfo.phoneNum1", target = "phoneNum1")
    @Mapping(source = "amRepresentativeInfo.phoneExt", target = "phoneExt")
    @Mapping(source = "amRepresentativeInfo.email", target = "email")
    public abstract AuthRepDto mapAssociatesRepresentative(AmRepresentativeDetailsDto amRepresentativeInfo,AddressDto amAuthRepAddressDetails);




    @Mapping(source = "amRepresentativeInfo.lastName", target = "lastName")
    @Mapping(source = "amRepresentativeInfo.firstName", target = "firstName")
    @Mapping(source = "amRepresentativeInfo.suffixName", target = "suffixName")
    @Mapping(source = "amRepresentativeInfo.midName", target = "midName")
    public abstract IndividualNameDto mapIndividualName(AmRepresentativeDetailsDto amRepresentativeInfo);


    @AfterMapping
    public void afterMapping(@MappingTarget AuthRepInfoDto responseDto, List<AmRepresentativeDetailsDto> amRepresentativeInfo,List<AmAddress> amAuthRepAddressDetails) {

        if(amRepresentativeInfo == null || amRepresentativeInfo.size() == 0) return ;
        if(amAuthRepAddressDetails == null) amAuthRepAddressDetails = Collections.emptyList();
        final Map<BigInteger, AmAddress> amAddressMap = amAuthRepAddressDetails
                .stream()
                .collect(Collectors.toMap(AmAddress::getAddressId, e -> e));

        for ( AmRepresentativeDetailsDto inputDto :amRepresentativeInfo) {

            AmAddress amAddress= amAddressMap.get(inputDto.getAddressId());
            AddressDto addressDto =amAddressMapping.entityToDto(amAddress);
            AuthRepDto authrepDetail = this.mapAssociatesRepresentative(inputDto,addressDto);
            IndividualNameDto individualNameDto = this.mapIndividualName(inputDto);
            authrepDetail.setName(individualNameDto);
            responseDto.getAssociatedAuthReps().add(authrepDetail);
        }

    }

}
