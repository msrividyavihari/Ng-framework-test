package com.deloitte.nextgen.dc.common.mapstruct.mappers;

import com.deloitte.nextgen.dc.common.mapstruct.qualifiers.CaseIndividuals;
import com.deloitte.nextgen.dc.common.mapstruct.qualifiers.HeadOfHouseHold;
import com.deloitte.nextgen.dc.common.mapstruct.qualifiers.HouseHoldMembers;
import com.deloitte.nextgen.dc.entities.DcCaseIndividual;
import com.deloitte.nextgen.dc.entities.DcIndv;
import com.deloitte.nextgen.dc.entities.DcRelationships;
import com.deloitte.nextgen.dc.individual.dto.IndividualDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {IndividualNameDtoMapper.class}, unmappedTargetPolicy = ReportingPolicy.ERROR)
@CaseIndividuals
public abstract class IndividualDtoMapper {

    @Autowired
    private IndividualNameDtoMapper individualNameDtoMapper;

    @Mapping(source = "individual.dobDt", target = "dob")
    @Mapping(source = "individual.indvId", target = "individualId")
    @Mapping(source = "individualRelationship.relationshipTypeCd", target = "relationShipCd")
    @Mapping(target = "name", source = "individual")
    public abstract IndividualDto map(DcIndv individual, @Nullable DcRelationships individualRelationship);


    /**
     * There were many attributes in DcIndv which can't be mapped .. rather than changing
     * the reporting policy to WARN/IGNORE.. simply manually populating the DcIndv
     * from IndividualDto
     */
    public DcIndv map(IndividualDto individual) {
        DcIndv dcIndv = new DcIndv();
        individualNameDtoMapper.map(individual.getName(), dcIndv);
        dcIndv.setDobDt(individual.getDob());
        dcIndv.setSsn(individual.getSsn());
        if (individual.getGenderCd() != null) dcIndv.setGenderCd(individual.getGenderCd());
        return dcIndv;
    }

    public IndividualDto map(DcIndv individual) {
        if (individual == null) return null;
        return map(individual, null);
    }

    public List<IndividualDto> mapToList(List<DcIndv> individuals) {
        if (individuals == null) return null;
        return individuals
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public Map<Long, IndividualDto> transformToMap(List<DcIndv> individuals) {
        List<IndividualDto> individualDtos = mapToList(individuals);
        if (individualDtos == null) return null;
        return individualDtos
                .stream()
                .collect(Collectors.toMap(IndividualDto::getIndividualId, Function.identity()));
    }

    @HeadOfHouseHold
    public IndividualDto mapToheadOfHouseHold(List<DcIndv> individuals, @Context List<DcCaseIndividual> caseIndividuals, @Context List<DcRelationships> individualRelationships) {
        if (individuals == null || caseIndividuals == null) return null;

        final Optional<DcCaseIndividual> hohCaseInd = caseIndividuals
                .stream()
                .filter(e -> "Y".equalsIgnoreCase(String.valueOf(e.getHeadOfHouseholdSw())))
                .findFirst();

        return mapToheadOfHouseHold(individuals, hohCaseInd.orElse(null), individualRelationships);
    }

    @HeadOfHouseHold
    public IndividualDto mapToheadOfHouseHold(List<DcIndv> individuals, @Context DcCaseIndividual hohCaseInd, @Context List<DcRelationships> individualRelationships) {
        if (individuals == null) return null;
        if (hohCaseInd == null) return null;

        Optional<DcIndv> headOfHouseIndividual = individuals
                .stream()
                .filter(e -> e.getIndvId() != null && e.getIndvId().equals(hohCaseInd.getIndvId()))
                .findFirst();
        if (!headOfHouseIndividual.isPresent()) return null;

        List<IndividualDto> individualDtos = mapTo(Collections.singletonList(headOfHouseIndividual.get()), individualRelationships);
        return individualDtos.get(0);
    }

    @HouseHoldMembers
    public List<IndividualDto> mapToHouseHoldMembers(List<DcIndv> individuals, @Context DcCaseIndividual hohCaseInd, @Context List<DcRelationships> individualRelationships) {
        if (individuals == null) return null;

        // head of household is not available .. so return all individuals
        // as house hold individuals.
        if (hohCaseInd == null)
            return mapTo(individuals, individualRelationships);

        // filter the Head of household from the list.
        List<DcIndv> houseHoldInds = individuals
                .stream()
                .filter(e -> e.getIndvId() != null && !e.getIndvId().equals(hohCaseInd.getIndvId()))
                .collect(Collectors.toList());

        return mapTo(houseHoldInds, individualRelationships);
    }

    @HouseHoldMembers
    public List<IndividualDto> mapToHouseHoldMembers(List<DcIndv> individuals, @Context List<DcCaseIndividual> caseIndividuals, @Context List<DcRelationships> individualRelationships) {
        if (individuals == null || caseIndividuals == null) return null;

        final Optional<DcCaseIndividual> hohCaseInd = caseIndividuals
                .stream()
                .filter(e -> "Y".equalsIgnoreCase(String.valueOf(e.getHeadOfHouseholdSw())))
                .findFirst();
        return mapToHouseHoldMembers(individuals, hohCaseInd.orElse(null), individualRelationships);
    }

    private List<IndividualDto> mapTo(List<DcIndv> individuals, List<DcRelationships> individualRelationships) {
        if (individuals == null || individuals.size() == 0) return null;
        if (individualRelationships == null) individualRelationships = Collections.emptyList();
        final Map<Long, DcRelationships> dcRelationShipMap = individualRelationships
                .stream()
                .collect(Collectors.toMap(DcRelationships::getIndvId, e -> e));
        return individuals
                .stream()
                .map(e -> this.map(e, dcRelationShipMap.get(e.getIndvId())))
                .collect(Collectors.toList());
    }
}