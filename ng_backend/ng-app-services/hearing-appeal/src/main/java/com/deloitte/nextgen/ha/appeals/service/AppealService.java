package com.deloitte.nextgen.ha.appeals.service;

import com.deloitte.nextgen.ha.create.AmAplGrpDto;
import com.deloitte.nextgen.ha.entity.AmAppealInfo;
import com.deloitte.nextgen.ha.search.dto.AppealDetailDto;
import com.deloitte.nextgen.ha.search.mapper.AppealDetailDtoMapper;
import com.deloitte.nextgen.ha.search.repository.AppealDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppealService {

    private final AppealDetailRepository appealDetailRepository;
    private final AppealDetailDtoMapper appealDetailDtoMapper;

    public List<AppealDetailDto> findAppeals(Long caseNum) {
        List<Object[]>  amAppealsList= appealDetailRepository.findByCaseNum(caseNum);
        List<AmAppealInfo> amAppealInfos = amAppealsList.stream().map(i -> {
            AmAppealInfo dto = new AmAppealInfo();
            dto.setAplNum(new BigInteger(String.valueOf(i[0])));
            dto.setLastStatusEffDt(((Timestamp)i[1]));
            dto.setLastStatus((String.valueOf(i[2])));
            return dto;
        }).collect(Collectors.toList());

        return appealDetailDtoMapper.map(amAppealInfos);
    }

    //TODO: Fix N+1 problem
    public Map<Long, List<AppealDetailDto>> findAppealsByCaseNums(Collection<Long> caseNums) {
        if(caseNums == null) return Collections.emptyMap();
        return caseNums
                .stream()
                .distinct()
                .collect(Collectors.toMap(Function.identity(), this::findAppeals))
                ;
    }
}
