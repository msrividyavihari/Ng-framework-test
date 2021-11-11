package com.deloitte.nextgen.dc.cases;

import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.common.mapstruct.mappers.AddressDtoMapper;
import com.deloitte.nextgen.dc.common.mapstruct.mappers.IndividualDtoMapper;
import com.deloitte.nextgen.dc.common.mapstruct.mappers.ProgramDtoMapper;
import com.deloitte.nextgen.dc.common.mapstruct.qualifiers.CaseIndividuals;
import com.deloitte.nextgen.dc.common.mapstruct.qualifiers.HeadOfHouseHold;
import com.deloitte.nextgen.dc.common.mapstruct.qualifiers.HouseHoldMembers;
import com.deloitte.nextgen.dc.cases.dto.CaseSearchResponseDto;
import com.deloitte.nextgen.dc.entities.*;
import com.deloitte.nextgen.framework.commons.mapper.OptionalMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {AddressDtoMapper.class
                , ProgramDtoMapper.class
                , CaseIndividuals.class
                , IndividualDtoMapper.class
                , OptionalMapper.class
                }
        )
public abstract class CaseSearchResponseMapper {

    @Mapping(source = "dcCase.caseNum", target = "caseNum")
    @Mapping(source = "dcCase.actionDt", target = "lastUpdatedDt")
    @Mapping(source = "programs", target = "programs")
    @Mapping(source = "caseAddress", target = "address")
    @Mapping(source = "authRepDto", target = "authRep")
    @Mapping(target = "headOfHouseHold", source = "individuals", qualifiedBy = {CaseIndividuals.class, HeadOfHouseHold.class})
    @Mapping(target = "members", source = "individuals", qualifiedBy = {CaseIndividuals.class, HouseHoldMembers.class})
    public abstract CaseSearchResponseDto map(DcCases dcCase, DcCaseAddresses caseAddress, List<DcCaseProgram> programs, List<DcIndv> individuals, @Context List<DcRelationships> individualRelationships, @Context DcCaseIndividual headOfHouseHold, Optional<AuthRepDto> authRepDto);

}
