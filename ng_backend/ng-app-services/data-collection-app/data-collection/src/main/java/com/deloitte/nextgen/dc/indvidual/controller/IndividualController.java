package com.deloitte.nextgen.dc.indvidual.controller;

import com.deloitte.nextgen.dc.common.dto.NameSearchListDto;
import com.deloitte.nextgen.dc.individual.dto.IndividualAssociatedCasesResponseDto;
import com.deloitte.nextgen.dc.individual.dto.IndividualDto;
import com.deloitte.nextgen.dc.indvidual.service.IndividualService;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nonnull;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/v1/individuals")
@Slf4j
@RequiredArgsConstructor
public class IndividualController {

    private final IndividualService individualsService;

    @Value("${data-collection.get-multi-individuals.max-request-limit:1000}")
    private final int maximumAllowedIndividualsInRequest;

    @GetMapping("/{individualId}/cases")
    public ResponseEntity<ApiResponse<IndividualAssociatedCasesResponseDto>> getIndividualAssociatedCases(@NonNull @PathVariable("individualId") Long individualId) {
        IndividualAssociatedCasesResponseDto responseDto = individualsService.findAssociatedCases(individualId);
        if (responseDto == null) {
            return ApiResponse.with(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.value()).build();
        }
        return ApiResponse
                .success(100)
                .data(responseDto);
    }

    @GetMapping(value = "/{individualId}")
    public ResponseEntity<ApiResponse<IndividualDto>> getIndividual(@NonNull @PathVariable("individualId") Long individualId) {
        IndividualDto individual = individualsService.getIndividual(individualId);
        return ApiResponse.success(100).data(individual);
    }

    @GetMapping(value = "/")
    @Valid
    public ResponseEntity<ApiResponse<Map<Long, IndividualDto>>> getIndividuals(@NonNull @NotEmpty @RequestParam(value = "ids") Set<Long> individualIds) {

        if (individualIds.size() > maximumAllowedIndividualsInRequest) {
            String msg = String.format("Individuals size exceeded. Maximum of %s Individual Ids allowed, but received %s"
                    , maximumAllowedIndividualsInRequest, individualIds.size());
            return ApiResponse
                    .badRequest(404)
                    .description(msg)
                    .build();
        }

        Map<Long, IndividualDto> individuals = individualsService.getIndividuals(individualIds);
        if (individuals == null || individuals.isEmpty()) {
            return ApiResponse.noContent(100);
        }
        HttpStatus status = individualIds.size() == individuals.size() ? HttpStatus.OK : HttpStatus.PARTIAL_CONTENT;
        return ApiResponse
                .with(status, status.value())
                .data(individuals)
                ;
    }

    @GetMapping(value = "/search/names")
    @Valid
    public ResponseEntity<ApiResponse<List<IndividualDto>>> getIndividualNames(@Nonnull NameSearchListDto nameSearchListDto) {
        List<IndividualDto> individuals = individualsService.getIndividualsByNames(nameSearchListDto);
        HttpStatus status = individuals.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return ApiResponse
                .with(status, status.value())
                .data(individuals);
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse<Long>> createIndividual(@Valid @RequestBody IndividualDto individualDto) {
        Long individualId = individualsService.createNewIndividual(individualDto);
        return ApiResponse
                .with(HttpStatus.CREATED, 100)
                .data(individualId);
    }
}