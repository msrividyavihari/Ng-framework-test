package com.deloitte.nextgen.ha.individuals.service;

import com.deloitte.nextgen.dc.common.dto.NameSearchListDto;
import com.deloitte.nextgen.dc.individual.dto.IndividualAssociatedCasesDto;
import com.deloitte.nextgen.dc.individual.dto.IndividualAssociatedCasesResponseDto;
import com.deloitte.nextgen.dc.individual.dto.IndividualDto;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.ha.appeals.service.AppealService;
import com.deloitte.nextgen.ha.common.WebClientApiResponseTypeConsumer;
import com.deloitte.nextgen.ha.individuals.mapper.IndividualAssociatedCasesResponseMapper;
import com.deloitte.nextgen.ha.search.dto.AppealDetailDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Iterables;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "hearing-appeal.data-collection")
public class IndividualService {

    private static final ParameterizedTypeReference<ApiResponse<IndividualDto>> DC_GET_INDIVIDUAL_API_RESPONSE_TYPE
            = new ParameterizedTypeReference<ApiResponse<IndividualDto>>() {
    };

    private static final ParameterizedTypeReference<ApiResponse<Set<IndividualDto>>> DC_GET_SEARCH_INDIVIDUAL_BY_NAME_API_RESPONSE_TYPE
            = new ParameterizedTypeReference<ApiResponse<Set<IndividualDto>>>() {
    };

    private static final TypeReference<Map<String, String>> QUERY_PARAMETER_TYPE = new TypeReference<Map<String, String>>() {
    };

    private static final ParameterizedTypeReference<ApiResponse<IndividualAssociatedCasesResponseDto>> DC_GET_INDIVIDUAL_ASSOCIATED_CASES_API_RESPONSE_TYPE
            = new ParameterizedTypeReference<ApiResponse<IndividualAssociatedCasesResponseDto>>() {
    };

    private static final ParameterizedTypeReference<ApiResponse<Map<Long, IndividualDto>>> DC_GET_INDIVIDUAL_LIST_API_RESPONSE_TYPE
            = new ParameterizedTypeReference<ApiResponse<Map<Long, IndividualDto>>>() {
    };

    @Value("${get-individuals.end-point:${url:http://localhost:10100/data-collection/api}/v1/individuals/{individualId}}")
    private final String getIndividualEndPoint;

    @Value("${get-individuals.-search-by-names.end-point:${url:http://localhost:10100/data-collection/api}/v1/individuals/search/names}")
    private final String searchIndividualByNamesEndPoint;

    @Value("${get-individuals-associated-cases.end-point:${url:http://localhost:10100/data-collection/api}/v1/individuals/{individualId}/cases}")
    private final String getIndividualAssociatedCasesEndPoint;

    @Value("${get-multi-individuals.end-point:${url:http://localhost:10100/data-collection/api}/v1/individuals/?ids={individualIds}}")
    private final String getIndividualListEndPoint;

    @Value("${get-multi-individuals.max-request-limit:1000}")
    private final int maximumAllowedIndividualsInRequest;

    private final Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

    @NonNull
    private final AppealService appealService;
    @NonNull
    private final WebClient webClient;
    @NonNull
    private final IndividualAssociatedCasesResponseMapper individualAssociatedCasesResponseMapper;

    public IndividualDto getIndividual(final Long individualId) {
        return Objects.requireNonNull(webClient
                .get()
                .uri(getIndividualEndPoint, individualId)
                .retrieve()
                .bodyToMono(DC_GET_INDIVIDUAL_API_RESPONSE_TYPE)
                .doOnSuccess(WebClientApiResponseTypeConsumer.endPoint(getIndividualEndPoint))
                .block())
                .getData();
    }

    public Set<IndividualDto> getIndividualsByNames(@NonNull NameSearchListDto nameSearchListDto) {
        // Converting
        Map<String, String> queryParameters = jackson2ObjectMapperBuilder.build().convertValue(nameSearchListDto, QUERY_PARAMETER_TYPE);
        MultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<>();
        linkedMultiValueMap.setAll(queryParameters);

        return Objects.requireNonNull(webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(searchIndividualByNamesEndPoint).queryParams(linkedMultiValueMap).build())
                .retrieve()
                .bodyToMono(DC_GET_SEARCH_INDIVIDUAL_BY_NAME_API_RESPONSE_TYPE)
                .doOnSuccess(WebClientApiResponseTypeConsumer.endPoint(getIndividualEndPoint))
                .block())
                .getData();
    }

    @Valid
    public Map<Long, IndividualDto> getIndividuals(@NonNull @NotEmpty final Set<Long> individualIds) {
        if (individualIds.isEmpty())
            throw new IllegalArgumentException("Expected Individual Ids, but received null or empty ");
        if (individualIds.size() <= maximumAllowedIndividualsInRequest) {
            ApiResponse<Map<Long, IndividualDto>> response = invokeGetIndividualsListEndPoint(individualIds).block();
            if (response != null && response.getData() != null) return response.getData();
            return Collections.emptyMap();
        }

        List<Mono<ApiResponse<Map<Long, IndividualDto>>>> nonBlockingResponses = new ArrayList<>();
        for (List<Long> ids : Iterables.partition(individualIds, maximumAllowedIndividualsInRequest)) {
            nonBlockingResponses.add(invokeGetIndividualsListEndPoint(ids));
        }

        return Objects.requireNonNull(Flux
                .concat(nonBlockingResponses)
                .map(ApiResponse::getData)
                .collectList()
                .block())
                .stream()
                .filter(Objects::nonNull)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public com.deloitte.nextgen.ha.individuals.dto.IndividualAssociatedCasesResponseDto findAssociatedCases(final Long individualId) {

        IndividualAssociatedCasesResponseDto individualAssociatedCasesDto = Objects.requireNonNull(webClient
                .get()
                .uri(getIndividualAssociatedCasesEndPoint, individualId)
                .retrieve()
                .bodyToMono(DC_GET_INDIVIDUAL_ASSOCIATED_CASES_API_RESPONSE_TYPE)
                .doOnSuccess(WebClientApiResponseTypeConsumer.endPoint(getIndividualAssociatedCasesEndPoint))
                .block())
                .getData();

        Collection<Long> caseNums = getAssociatedCaseNums(individualAssociatedCasesDto);
        Map<Long, List<AppealDetailDto>> caseNumAppealsMap = this.appealService.findAppealsByCaseNums(caseNums);
        return individualAssociatedCasesResponseMapper.map(individualAssociatedCasesDto, caseNumAppealsMap);
    }

    private Collection<Long> getAssociatedCaseNums(IndividualAssociatedCasesResponseDto responseDto) {
        if (responseDto.getAssociatedCases() == null) return Collections.emptyList();
        return responseDto
                .getAssociatedCases()
                .stream()
                .map(IndividualAssociatedCasesDto::getCaseNum)
                .collect(Collectors.toSet());
    }

    private Mono<ApiResponse<Map<Long, IndividualDto>>> invokeGetIndividualsListEndPoint(final Collection<Long> individualIds) {
        String ids = individualIds
                .stream()
                .distinct()
                .map(e -> Long.toString(e))
                .collect(Collectors.joining(","));
        return webClient
                .get()
                .uri(getIndividualListEndPoint, ids)
                .retrieve()
                .bodyToMono(DC_GET_INDIVIDUAL_LIST_API_RESPONSE_TYPE)
                ;
    }
}