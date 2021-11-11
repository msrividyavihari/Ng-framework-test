package com.deloitte.nextgen.ha.create.mappers;


import com.deloitte.nextgen.dc.common.dto.AddressDto;
import com.deloitte.nextgen.dc.individual.dto.IndividualDto;
import com.deloitte.nextgen.ha.appeals.dto.AppellantInfoDto;
import com.deloitte.nextgen.ha.appeals.dto.ContactInfoDto;
import com.deloitte.nextgen.ha.entity.AmRequestDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AppelllantDtoMapper {

    @Mapping(source = "primaryInfo", target = "primaryAppellant")
    @Mapping(source = "additionalInfo", target = "additionalAppellants")
    @Mapping(source = "nonAppellantsInfo", target = "nonAppellants")
    public AppellantInfoDto convertTo(IndividualDto primaryInfo, List<IndividualDto> additionalInfo, List<IndividualDto> nonAppellantsInfo);
}
