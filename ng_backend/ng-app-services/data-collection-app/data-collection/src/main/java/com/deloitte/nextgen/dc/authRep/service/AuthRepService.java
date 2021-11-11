package com.deloitte.nextgen.dc.authrep.service;

import com.deloitte.nextgen.dc.authrep.dto.AuthRepDto;
import com.deloitte.nextgen.dc.authrep.mapper.AuthRepSearchResponseMapper;
import com.deloitte.nextgen.dc.entities.*;
import com.deloitte.nextgen.dc.repository.*;
import com.deloitte.nextgen.framework.commons.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthRepService {

    private final DcCasesRepository dcCasesRepository;
    private final DcCaseAddressesRepository dcCaseAddressesRepository;
    private final AuthRepSearchResponseMapper authRepSearchResponseMapper;
    private final DcAuthRepRepository dcAuthRepRepository;
    private final DcPhnDetailsRepository dcPhnDetailsRepository;
    private final DcEmailDetailsRepository dcEmailDetailsRepository;

    public AuthRepDto getAuthRepByCaseNum(Long caseNum) {
        Optional<AuthRepDto> authRepDto = findAuthRepByCaseNum(caseNum);
        return authRepDto.orElseThrow(() -> {
            return new ResourceNotFoundException(404, "Auth Rep not exists for Case # " + caseNum);
        });
    }

    public Optional<AuthRepDto> findAuthRepByCaseNum(Long caseNum) {
        log.info("Fetching AuthRep Details for CaseNum {}  ", caseNum);
        Objects.requireNonNull(caseNum, "Case Num should not be null");
        final DcCases dcCase = dcCasesRepository.findByCaseNum(caseNum);
        if (dcCase == null) throw new ResourceNotFoundException(404, "Case Number", String.valueOf(caseNum), null);

        final Optional<DcAuthRep> authRep = dcAuthRepRepository.findByCaseNum(caseNum);
        if(!authRep.isPresent()) return Optional.empty();

        final DcCaseAddresses authRepAddress = dcCaseAddressesRepository.findAuthRepAddressByCaseNum(caseNum).orElse(null);
        final DcPhnDetails authRepPhnDetails = dcPhnDetailsRepository.findAuthRepPhoneDetailsByCaseNum(caseNum).orElse(null);
        final DcEmailDetails authRepEmailDetails = dcEmailDetailsRepository.findAuthRepEmailDetailsByCaseNum(caseNum).orElse(null);
        return Optional.of(authRepSearchResponseMapper.map(authRep.get(), authRepAddress, authRepPhnDetails, authRepEmailDetails));
    }
}
