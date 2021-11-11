package com.deloitte.nextgen.ha.create.mappers;


import com.deloitte.nextgen.dc.common.dto.AddressDto;
import com.deloitte.nextgen.ha.appeals.dto.ContactInfoDto;

import com.deloitte.nextgen.ha.create.dto.FilingCreateAppealDto;
import com.deloitte.nextgen.ha.entity.AmAddress;
import com.deloitte.nextgen.ha.entity.AmRequestDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AmRequestDetailsMapping  {

    @Mapping(target="preferredCntctCd", source="contactInfo.preferredContactTimeCode")
    @Mapping(target="preferredLangCd", source="contactInfo.preferredLanguageCode")
    @Mapping(target="perfectedDt", source="filingCreateAppealDto.filedDate")
    @Mapping(target="filingMethodCd", source="filingCreateAppealDto.filingMethod")
    @Mapping(target="nonIesCaseNum", source="filingCreateAppealDto.caseNum")
    @Mapping(target="homePhNum", source="contactInfo.phone1")
    @Mapping(target="workPhNum", source="contactInfo.phone2")
    AmRequestDetails mapDtoToEntity(FilingCreateAppealDto filingCreateAppealDto,ContactInfoDto contactInfo);



    @Mapping(target="preferredCntctCd", source="contactInfo.preferredContactTimeCode")
    @Mapping(target="preferredLangCd", source="contactInfo.preferredLanguageCode")
    @Mapping(target="perfectedDt", source="filingCreateAppealDto.filedDate")
    @Mapping(target="filingMethodCd", source="filingCreateAppealDto.filingMethod")
    @Mapping(target="nonIesCaseNum", source="filingCreateAppealDto.caseNum")
    @Mapping(target="homePhNum", source="contactInfo.phone1")
    @Mapping(target="workPhNum", source="contactInfo.phone2")
    void mergeEntity(FilingCreateAppealDto filingCreateAppealDto,ContactInfoDto contactInfo, @MappingTarget AmRequestDetails amRequestDetails);




}


