package com.deloitte.nextgen.dc.indvidual.service;

import com.deloitte.nextgen.dc.common.dto.NameSearchListDto;
import com.deloitte.nextgen.dc.common.mapstruct.mappers.IndividualDtoMapper;
import com.deloitte.nextgen.dc.entities.DcCaseIndividual;
import com.deloitte.nextgen.dc.entities.DcCaseProgram;
import com.deloitte.nextgen.dc.entities.DcIndv;
import com.deloitte.nextgen.dc.individual.dto.IndividualAssociatedCasesResponseDto;
import com.deloitte.nextgen.dc.individual.dto.IndividualDto;
import com.deloitte.nextgen.dc.indvidual.mapper.IndividualAssociatedCasesResponseDtoMapper;
import com.deloitte.nextgen.dc.repository.DcCaseIndividualRepository;
import com.deloitte.nextgen.dc.repository.DcCaseProgramRepository;
import com.deloitte.nextgen.dc.repository.DcIndividualRepository;
import com.deloitte.nextgen.framework.commons.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class IndividualService {

    private final DcCaseProgramRepository dcCaseProgramRepository;
    private final DcCaseIndividualRepository dcCaseIndividualRepository;
    private final DcIndividualRepository individualRepository;
    private final IndividualAssociatedCasesResponseDtoMapper responseDtoMapper;
    private final IndividualDtoMapper individualDtoMapper;

    @Transactional
    public Long createNewIndividual(@Valid IndividualDto individual) {
        DcIndv dcIndv = individualDtoMapper.map(individual);
        dcIndv = individualRepository.save(dcIndv);
        return dcIndv.getIndvId();
    }

    @Valid
    public Map<Long, IndividualDto> getIndividuals(@NonNull @NotEmpty Set<Long> individualIds) {
        List<DcIndv> individuals = individualRepository.findAllById(individualIds);
        return individualDtoMapper.transformToMap(individuals);
    }

    public IndividualDto getIndividual(@NonNull Long individualId) {
        final DcIndv dcIndividual = findIndividual(individualId);
        return individualDtoMapper.map(dcIndividual);
    }

    @Valid
    public List<IndividualDto> getIndividualsByNames(@NonNull NameSearchListDto nameSearchListDto) {
        final List<DcIndv> indvList = individualRepository.findByNames(nameSearchListDto.getNames());
        return individualDtoMapper.mapToList(indvList);
    }

    public IndividualAssociatedCasesResponseDto findAssociatedCases(@NonNull Long individualId) {
        log.info("Fetching Individual Associated Details for Individual Id {}  ", individualId);
        final DcIndv dcIndividual = findIndividual(individualId);
        final List<DcCaseIndividual> dcCaseIndividualList = dcCaseIndividualRepository.findAssociatedCases(individualId);
        if (dcCaseIndividualList == null || dcCaseIndividualList.isEmpty()) {
            log.warn("No associated cases found for individual id {} ", individualId);
            return null;
        }

        final List<Long> caseNumList = dcCaseIndividualList
                .stream()
                .map(DcCaseIndividual::getCaseNum)
                .collect(Collectors.toList());

        final List<DcCaseProgram> dcCasePrograms = dcCaseProgramRepository.findByCaseNumIn(caseNumList);

        return responseDtoMapper.map(dcIndividual, dcCasePrograms);
    }

    private DcIndv findIndividual(@NonNull Long individualId) {
        final Optional<DcIndv> dcIndividual = individualRepository.findById(individualId);
        if (dcIndividual.isPresent()) return dcIndividual.get();
        throw new ResourceNotFoundException(404, "Individual", individualId.toString(), null);
    }

}