package com.deloitte.nextgen.ha.search;

import com.deloitte.nextgen.dc.cases.dto.CaseSearchResponseDto;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.ha.appeals.service.AppealService;
import com.deloitte.nextgen.ha.common.WebClientApiResponseTypeConsumer;
import com.deloitte.nextgen.ha.search.dto.AppealDetailDto;
import com.deloitte.nextgen.ha.search.mapper.CaseSearchResponseDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "hearing-appeal.data-collection")
public class CaseSearchService {

    private static final ParameterizedTypeReference<ApiResponse<CaseSearchResponseDto>> DC_FIND_CASE_API_RESPONSE_TYPE
            = new ParameterizedTypeReference<ApiResponse<CaseSearchResponseDto>>() {
    };

    private final AppealService appealService;
    private final CaseSearchResponseDtoMapper caseSearchResponseDtoMapper;

    @Value(value = "${get-case.end-point:${url:http://localhost:10100/data-collection/api}/v1/cases/{caseNum}}")
    private final String datCollectionAppFindByCaseNumEndPoint;
    private final WebClient webClient;

    public com.deloitte.nextgen.ha.search.dto.CaseSearchResponseDto getCaseAndAppealDetails(@NonNull final Long caseNum) {
        Mono<ApiResponse<CaseSearchResponseDto>> dcFindByCaseMono = invokeDCGetCaseEndPoint(caseNum);
        List<AppealDetailDto> amAppeals = appealService.findAppeals(caseNum);
        return caseSearchResponseDtoMapper.map(Objects.requireNonNull(dcFindByCaseMono.block()).getData(), amAppeals);
    }

    public CaseSearchResponseDto getCaseDetails(@NonNull final Long caseNum) {
        return Objects.requireNonNull(invokeDCGetCaseEndPoint(caseNum).block()).getData();
    }

    private Mono<ApiResponse<CaseSearchResponseDto>> invokeDCGetCaseEndPoint(final Long caseNum) {
        return webClient
                .get()
                .uri(datCollectionAppFindByCaseNumEndPoint, caseNum)
                .retrieve()
                .bodyToMono(DC_FIND_CASE_API_RESPONSE_TYPE)
                .doOnSuccess(WebClientApiResponseTypeConsumer.endPoint(datCollectionAppFindByCaseNumEndPoint));
    }
}
