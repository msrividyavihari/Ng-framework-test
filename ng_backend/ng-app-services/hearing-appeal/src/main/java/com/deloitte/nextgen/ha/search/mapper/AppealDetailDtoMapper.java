package com.deloitte.nextgen.ha.search.mapper;

import com.deloitte.nextgen.ha.entity.AmAppealInfo;
import com.deloitte.nextgen.ha.search.dto.AppealDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AppealDetailDtoMapper {

    @Mapping(source = "appealInfo.lastStatus", target = "appealStatusCd")
    @Mapping(source = "appealInfo.lastStatusEffDt", target = "appealLastUpdatedDt")
    @Mapping(source = "appealInfo.aplNum", target = "appealNum")
    public AppealDetailDto map(AmAppealInfo appealInfo);

    public List<AppealDetailDto> map(List<AmAppealInfo> appealInfo);
}
