package com.deloitte.nextgen.ha.create.mappers;


import com.deloitte.nextgen.ha.appeals.dto.AppealInfoDto;
import com.deloitte.nextgen.ha.entity.AmRequestDetails;
import com.deloitte.nextgen.ha.entity.AmRequestProgDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AppealInfoDtoMapper {


    @Mapping(source = "amRequestDetailsInfo.perfectedDt", target = "filedDate")
    @Mapping(source = "amRequestDetailsInfo.filingMethodCd", target = "filingMethod")
    @Mapping(source = "amRequestDetailsInfo.nonIesCaseNum", target = "caseNum")
    @Mapping(source = "amRequestProgDetails.programCd", target = "programCd")
    @Mapping(source = "amRequestProgDetails.aplType", target = "typeCd")
    @Mapping(source = "amRequestProgDetails.fromCoeCd", target = "reasonCd")
    public AppealInfoDto mapToAppealInfo(AmRequestDetails amRequestDetailsInfo, AmRequestProgDetails amRequestProgDetails);

}
