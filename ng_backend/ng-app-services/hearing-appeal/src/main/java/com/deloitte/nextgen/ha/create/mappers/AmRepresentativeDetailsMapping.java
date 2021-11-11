package com.deloitte.nextgen.ha.create.mappers;

import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.common.dto.IndividualNameDto;
import com.deloitte.nextgen.ha.entity.AmRepresentativeDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AmRepresentativeDetailsMapping {

    @Mapping(target="companyName", constant="HearningAndAppealchange")
    @Mapping(target="relToAppellantCd", constant="A")
    @Mapping(target="repTypeCd", constant="A")
    @Mapping(target="emailId", source="authRepDto.email")
    @Mapping(target="workPhNum", source="authRepDto.phoneNum1")
    @Mapping(target="phExt", source="authRepDto.phoneExt")
    @Mapping(target="homePhNum", source="authRepDto.phoneNum1") //TODO check thse number
    @Mapping(target="otherNum", source="authRepDto.phoneNum1") //TODO
    @Mapping(target="firstName", source="individualNameDto.firstName")
    @Mapping(target="lastName", source="individualNameDto.lastName")
    @Mapping(target="midName", source="individualNameDto.midName") //TODO
    @Mapping(target="suffixName", source="individualNameDto.suffixName") //TODO check
  //  @Mapping(target="prefixName", constant="individualNameDto.suffixName") //TODO check
    AmRepresentativeDetails getEntityFromDto(AuthRepDto authRepDto, IndividualNameDto individualNameDto);


    @Mapping(target="amRepresentativeDetails.emailId", source="authRepDto.email")
    @Mapping(target="amRepresentativeDetails.workPhNum", source="authRepDto.phoneNum1")
    @Mapping(target="amRepresentativeDetails.phExt", source="authRepDto.phoneExt")
    @Mapping(target="amRepresentativeDetails.homePhNum", source="authRepDto.phoneNum1") //TODO check thse number
    @Mapping(target="amRepresentativeDetails.otherNum", source="authRepDto.phoneNum1") //TODO
    @Mapping(target="amRepresentativeDetails.firstName", source="individualNameDto.firstName")
    @Mapping(target="amRepresentativeDetails.lastName", source="individualNameDto.lastName")
    @Mapping(target="amRepresentativeDetails.midName", source="individualNameDto.midName") //TODO
    @Mapping(target="amRepresentativeDetails.suffixName", source="individualNameDto.suffixName") //TODO check
   // @Mapping(target="amRepresentativeDetails.prefixName", constant="individualNameDto.suffixName") //TODO check
    void mergeEntity(AuthRepDto authRepDto, IndividualNameDto individualNameDto,  @MappingTarget AmRepresentativeDetails amRepresentativeDetails);



}
