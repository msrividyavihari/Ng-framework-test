package com.deloitte.nextgen.ha.create.mappers;


import com.deloitte.nextgen.ha.appeals.dto.ContactInfoDto;
import com.deloitte.nextgen.ha.create.dto.FilingCreateAppealDto;
import com.deloitte.nextgen.ha.create.dto.FilingUpdateAppealDto;
import com.deloitte.nextgen.ha.entity.AmRequestDetails;
import com.deloitte.nextgen.ha.entity.AmRequestProgDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.math.BigInteger;

@Mapper(componentModel = "spring" , unmappedTargetPolicy = ReportingPolicy.WARN)

public interface AmRequestProgDetailsMapping {


    @Mapping(target="aplNum", source="filingUpdateAppealDto.appealNum")
    @Mapping(target="programCd", source="filingUpdateAppealDto.programCd")
    @Mapping(target="aplType", source="filingUpdateAppealDto.typeCd")
    @Mapping(target="fromCoeCd", source="filingUpdateAppealDto.reasonCd")
    @Mapping(target="iesCaseNum", source="filingUpdateAppealDto.caseNum")
    /*@Mapping(target = "pcktMailSw", constant = "N")
    @Mapping(target = "cmpltPcktSentSw", constant = "N" )
    @Mapping(target = "otIndvPcktSw", constant = "N" )
    @Mapping(target = "pcktMthdCd", constant = "N" )
    @Mapping(target = "rnAiRcvdSw", constant = "N" )
    @Mapping(target = "rnAiRtnSw", constant = "N" )
    @Mapping(target = "rnAiRtnMthdCd", constant = "N" )*/
    AmRequestProgDetails getEntityFromDto(FilingUpdateAppealDto filingUpdateAppealDto);

    @Mapping(target="amRequestProgDetails.aplNum", source="filingUpdateAppealDto.appealNum")
    @Mapping(target="amRequestProgDetails.programCd", source="filingUpdateAppealDto.programCd")
    @Mapping(target="amRequestProgDetails.aplType", source="filingUpdateAppealDto.typeCd")
    @Mapping(target="amRequestProgDetails.fromCoeCd", source="filingUpdateAppealDto.reasonCd")
    @Mapping(target="amRequestProgDetails.iesCaseNum", source="filingUpdateAppealDto.caseNum")
    void mergeEntity(FilingUpdateAppealDto filingUpdateAppealDto,  @MappingTarget AmRequestProgDetails amRequestProgDetails);



}
