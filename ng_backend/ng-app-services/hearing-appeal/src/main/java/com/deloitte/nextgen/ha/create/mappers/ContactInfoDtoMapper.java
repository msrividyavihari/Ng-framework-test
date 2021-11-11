package com.deloitte.nextgen.ha.create.mappers;

import com.deloitte.nextgen.dc.common.dto.AddressDto;
import com.deloitte.nextgen.ha.appeals.dto.ContactInfoDto;
import com.deloitte.nextgen.ha.entity.AmRequestDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigInteger;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ContactInfoDtoMapper {

    @Mapping(source = "amRequestDetailsInfo.preferredLangCd", target = "preferredLanguageCode")
    @Mapping(source = "amRequestDetailsInfo.preferredCntctCd", target = "preferredContactTimeCode")
    @Mapping(source = "amRequestDetailsInfo.homePhNum", target = "phone1")
    @Mapping(source = "amRequestDetailsInfo.workPhNum", target = "phone2")
    @Mapping(source = "addressDto", target = "address")
    public ContactInfoDto addressandRequestToContactInfo(AmRequestDetails amRequestDetailsInfo, AddressDto addressDto);

}
