package com.deloitte.nextgen.ha.appeals.service;

import com.deloitte.nextgen.dc.individual.dto.IndividualDto;
import com.deloitte.nextgen.ha.appeals.repository.AppealSearchRepository;
import com.deloitte.nextgen.ha.dashboard.dto.AppealSearchCriteria;
import com.deloitte.nextgen.ha.dashboard.dto.AppealSearchResponseDto;
import com.deloitte.nextgen.ha.individuals.service.IndividualService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppealSearchService {

    private final AppealSearchRepository appealSearchRepository;
    private final IndividualService individualService;

    @Value("${hearing-appeal.appeal-search.filter-expression-splitter-pattern:[\\s,;]+}")
    private final String filterExpressionSplitterPattern;

    public Page<AppealSearchResponseDto> findAppeals(AppealSearchCriteria criteria
            , String filterExpression
            , @PageableDefault Pageable pageable) {
        Page<AppealSearchResponseDto> appealSearchResponseDtos = appealSearchRepository.findAppeals(criteria, pageable);

        // lets group the appeal search response whose name details are NULL..
        final Map<Long, List<AppealSearchResponseDto>> individualAppeals = appealSearchResponseDtos.get()
                .filter(e -> e.getPrimaryAppellant() == null)
                .collect(Collectors.groupingBy(AppealSearchResponseDto::getIndividualId));
        // if no elements founds without names then simply return...
        if (individualAppeals.keySet().isEmpty()) return appealSearchResponseDtos;
        // invoke the service to get individual details by individual id..
        final Map<Long, IndividualDto> individualDtoMap = individualService.getIndividuals(individualAppeals.keySet());

        // set the individual name from the service response.
        individualAppeals.values().stream()
                .flatMap(Collection::stream)
                .filter(e -> individualDtoMap.containsKey(e.getIndividualId()))
                .forEach(e -> e.setPrimaryAppellant(individualDtoMap.get(e.getIndividualId()).getName()))
        ;
        return appealSearchResponseDtos;
    }
}
