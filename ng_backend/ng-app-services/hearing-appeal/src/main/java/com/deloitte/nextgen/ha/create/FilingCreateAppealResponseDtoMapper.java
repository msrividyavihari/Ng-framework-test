package com.deloitte.nextgen.ha.create;


import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.common.dto.AddressDto;
import com.deloitte.nextgen.dc.individual.dto.IndividualDto;

import com.deloitte.nextgen.ha.create.dto.AmRepresentativeDetailsDto;
import com.deloitte.nextgen.ha.create.dto.FilingCreateAppealResponseDto;
import com.deloitte.nextgen.ha.create.mappers.AppealInfoDtoMapper;
import com.deloitte.nextgen.ha.create.mappers.AppelllantDtoMapper;
import com.deloitte.nextgen.ha.create.mappers.AuthRepInfoDtoMapper;
import com.deloitte.nextgen.ha.create.mappers.ContactInfoDtoMapper;
import com.deloitte.nextgen.ha.entity.*;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                ContactInfoDtoMapper.class,
                AppealInfoDtoMapper.class,
                AppelllantDtoMapper.class,
                AuthRepInfoDtoMapper.class
        },
        unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class FilingCreateAppealResponseDtoMapper {

    @Autowired
    private ContactInfoDtoMapper contactInfoDtoMapper;

    @Autowired
    private AuthRepInfoDtoMapper authRepInfoDtoMapper;

    @Autowired
    private AppelllantDtoMapper appelllantDtoMapper;

    @Autowired
    private AppealInfoDtoMapper appealInfoDtoMapper;

    public abstract FilingCreateAppealResponseDto map(AmRequestDetails amRequestDetailsInfo, AddressDto addressDto,
                                                          IndividualDto primaryAppellantInfo, List<IndividualDto> secondaryAppellants, List<IndividualDto> nonAppellants);

    public abstract FilingCreateAppealResponseDto mapAuthRep(AmRequestProgDetails amRequestProgDetails , Boolean caseAuthRepFlag, AuthRepDto authRepDetails ,
                                                             List<AmRepresentativeDetailsDto> amRepresentativeDetails, List<AmAddress> amAuthRepAddressDetails);

    @AfterMapping
    public void afterMapping(@MappingTarget FilingCreateAppealResponseDto responseDto, AmRequestDetails amRequestDetailsInfo, AddressDto addressDto,
                                             IndividualDto primaryAppellantInfo,
                                            List<IndividualDto> secondaryAppellants, List<IndividualDto> nonAppellants){

        responseDto.setContactDetails(this.contactInfoDtoMapper.addressandRequestToContactInfo(amRequestDetailsInfo,addressDto));
        responseDto.setAppealDetails(this.appealInfoDtoMapper.mapToAppealInfo(amRequestDetailsInfo,null));
        responseDto.setAppellantDetails(this.appelllantDtoMapper.convertTo(primaryAppellantInfo,secondaryAppellants,nonAppellants));

    }

    @AfterMapping
    public void afterMapping(@MappingTarget FilingCreateAppealResponseDto responseDto,AmRequestProgDetails amRequestProgDetails,Boolean caseAuthRepFlag,
                             AuthRepDto authRepDetails,List<AmRepresentativeDetailsDto> amRepresentativeInfo,List<AmAddress> amAuthRepAddressDetails){

        responseDto.setAppealDetails(this.appealInfoDtoMapper.mapToAppealInfo(null,amRequestProgDetails));
        responseDto.setAuthRepDetails(this.authRepInfoDtoMapper.convertToAuthRepDto(caseAuthRepFlag,authRepDetails,amRepresentativeInfo,amAuthRepAddressDetails));

    }

}
