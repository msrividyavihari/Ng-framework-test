package com.deloitte.nextgen.dc.cases;

import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.authrep.service.AuthRepService;
import com.deloitte.nextgen.dc.cases.dto.CaseSearchResponseDto;
import com.deloitte.nextgen.dc.entities.*;
import com.deloitte.nextgen.dc.repository.*;
import com.deloitte.nextgen.framework.commons.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CaseSearchService {

    private final DcCasesRepository dcCasesRepository;
    private final DcCaseProgramRepository dcCaseProgramRepository;
    private final DcCaseIndividualRepository dcCaseIndividualRepository;
    private final DcIndividualRepository individualRepository;
    private final DcRelationshipsRepository dcRelationshipsRepository;
    private final DcCaseAddressesRepository dcCaseAddressesRepository;
    private final CaseSearchResponseMapper caseSearchResponseMapper;
    private final AuthRepService authRepService;

    public CaseSearchResponseDto findByCaseNum(Long caseNum) {

        log.info("Fetching Details for CaseNum {}  ", caseNum);
        Objects.requireNonNull(caseNum, "Case Num should not be null");
        final DcCases dcCase = dcCasesRepository.findByCaseNum(caseNum);
        if (dcCase == null) throw new ResourceNotFoundException(404, "Case Number", String.valueOf(caseNum), null);
        final List<DcCaseIndividual> dcCaseIndividualList = dcCaseIndividualRepository.findActiveIndividuals(caseNum);
        final DcCaseIndividual headOfHouseHold = getHeadOfHouseHold(dcCaseIndividualList, caseNum);
        final List<Long> individualIdList = dcCaseIndividualList
                .stream()
                .map(DcCaseIndividual::getIndvId)
                .collect(Collectors.toList());
        final List<DcRelationships> dcRelationshipsList = dcRelationshipsRepository.findByRefIndvId(headOfHouseHold.getIndvId());
        final List<DcIndv> dcIndividuals = individualRepository.findByIndvIdIn(individualIdList);
        //TODO: Fix fetching the Address .. Should get only one address either residence or mailing and active address only
        final DcCaseAddresses caseAddress = dcCaseAddressesRepository.findCaseAddressByCaseNum(caseNum).orElse(null);
        final List<DcCaseProgram> dcCasePrograms = dcCaseProgramRepository.findByCaseNum(caseNum);
        final Optional<AuthRepDto> authRepDto = authRepService.findAuthRepByCaseNum(caseNum);
        return caseSearchResponseMapper.map(dcCase, caseAddress, dcCasePrograms, dcIndividuals, dcRelationshipsList, headOfHouseHold, authRepDto);
    }

    private DcCaseIndividual getHeadOfHouseHold(List<DcCaseIndividual> dcCaseIndividualList, Long caseNum) {
        Optional<DcCaseIndividual> headOfHouseHold = dcCaseIndividualList
                .stream()
                .filter(e -> "Y".equalsIgnoreCase(String.valueOf(e.getHeadOfHouseholdSw())))
                .findFirst();
        return headOfHouseHold.orElseThrow(() -> new IllegalStateException("Head of Household not found for case num" + caseNum));
    }

}
