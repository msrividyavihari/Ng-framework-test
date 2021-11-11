package com.deloitte.nextgen.appreg.web.services.impl;

import com.deloitte.nextgen.appreg.client.request.FileClearResponse;
import com.deloitte.nextgen.appreg.web.exceptionhandlers.AppRegCustomException;
import com.deloitte.nextgen.appreg.client.request.DcIndvFileClearReqAndResp;
import com.deloitte.nextgen.appreg.client.entities.PcRelationShipDto;
import com.deloitte.nextgen.appreg.web.repositories.DcIndvCustomRepository;
import com.deloitte.nextgen.appreg.web.repositories.DcIndvRepository;
import com.deloitte.nextgen.appreg.web.services.FileClearService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileClearServiceImpl implements FileClearService {

    private final DcIndvRepository dcIndvRepository;
    private final DcIndvCustomRepository dcIndvCustomRepository;

    private List<DcIndvFileClearReqAndResp> getbySSNObjects(List<FileClearResponse> fileClearObjects) {
        Map<BigDecimal, DcIndvFileClearReqAndResp> indvIdDcIndivPcClearMap = new HashMap<>();
        try
        {
            fileClearObjects.forEach(fileClear -> {
                BigDecimal indvId = new BigDecimal(fileClear.getIndvId().toString());
                DcIndvFileClearReqAndResp dcIndvFileClearReqAndResp = null;
                if (!indvIdDcIndivPcClearMap.containsKey(indvId)) {
                    dcIndvFileClearReqAndResp = this.newDcIndvFileClearDtofromObjects(fileClear, indvId.longValue());
                } else {
                    dcIndvFileClearReqAndResp = indvIdDcIndivPcClearMap.get(indvId);
                }

                dcIndvFileClearReqAndResp.getAlisName()
                        .add(this.nameJoiner(fileClear.getAliasFirstName(), fileClear.getAliasMidName(), fileClear.getAliasLastName(), null));
                dcIndvFileClearReqAndResp.getRelationShips()
                        .add(PcRelationShipDto.builder()
                                .name(this.nameJoiner(fileClear.getRelFirstName(), fileClear.getRelMidName(), fileClear.getRelLastName(), null))
                                .relation((String) fileClear.getRelationshipTypeCd())
                                .build());
                indvIdDcIndivPcClearMap.put(indvId, dcIndvFileClearReqAndResp);
            });
        }catch (Exception e) {
            log.error("Error inside getbySSNObjects ", e);
            throw new AppRegCustomException(e.toString());
        }
        return indvIdDcIndivPcClearMap.values().stream().collect(Collectors.toList());
    }

    private <O> String nameJoiner(O firstName, O middleName, O lastName, O suffix) {
        StringBuilder name = new StringBuilder(StringUtils.isEmpty(firstName) ? "" : firstName + " ");
        name.append(StringUtils.isEmpty(middleName) ? "" : middleName + " ");
        name.append(StringUtils.isEmpty(lastName) ? "" : lastName);
        name.append(StringUtils.isEmpty(suffix) ? "" : " " + suffix);
        return StringUtils.hasLength(name) ? name.toString() : null;
    }

    private DcIndvFileClearReqAndResp newDcIndvFileClearDtofromObjects(FileClearResponse fileClear, Long indvId) {
        DcIndvFileClearReqAndResp dcIndvFileClearReqAndResp = new DcIndvFileClearReqAndResp();
        dcIndvFileClearReqAndResp.setIndvId(indvId);
        dcIndvFileClearReqAndResp.setFirstName(fileClear.getFirstName());
        dcIndvFileClearReqAndResp.setLastName(fileClear.getLastName());
        dcIndvFileClearReqAndResp.setMidName(fileClear.getMidName());
        dcIndvFileClearReqAndResp.setSufxName(fileClear.getSufxName());
        dcIndvFileClearReqAndResp.setRaceCd(fileClear.getRaceCd());
        dcIndvFileClearReqAndResp.setEthnicityCd(fileClear.getEthnicityCd());
        dcIndvFileClearReqAndResp.setDobDt(fileClear.getDobDt());
        dcIndvFileClearReqAndResp.setAge(Period.between(((Timestamp) fileClear.getDobDt()).toLocalDateTime().toLocalDate(), LocalDate.now()).getYears());
        dcIndvFileClearReqAndResp.setGender(fileClear.getGender());
        dcIndvFileClearReqAndResp.setSsn(null != fileClear.getSsn() ? new BigDecimal(fileClear.getSsn().toString()).longValue() : 0);
        return dcIndvFileClearReqAndResp;
    }

    @Override
    public List<DcIndvFileClearReqAndResp> getFileClearRecords(String ssn) {
        try
        {
            List<FileClearResponse> fileClearObjects = dcIndvCustomRepository.selectForFileClearSSN(ssn);
            return this.getbySSNObjects(fileClearObjects).stream().map(dto -> {
                dto.setScore(100l);
                return dto;
            }).collect(Collectors.toList());
        }catch (Exception e) {
            log.error("Error inside getFileClearRecords ", e);
            throw new AppRegCustomException(e.toString());
        }
    }

    @Override
    public List<DcIndvFileClearReqAndResp> getFileClearRecords(DcIndvFileClearReqAndResp dcIndvFileClearReqAndResp) {
        List<FileClearResponse> fileClearObjects =new ArrayList<>();
        try
        {
            fileClearObjects= dcIndvCustomRepository.selectForFileClearSSN
                    (dcIndvFileClearReqAndResp.getFirstName(), dcIndvFileClearReqAndResp.getLastName(), dcIndvFileClearReqAndResp.getDobDt(), dcIndvFileClearReqAndResp.getGender());

        }catch (Exception e) {
            log.error("Error inside getFileClearRecords ", e);
            throw new AppRegCustomException(e.toString());
        }

        return this.getbySSNObjects(fileClearObjects).stream()
                .map(fileClearData -> {
                    if (fileClearData.getFirstName().equals(dcIndvFileClearReqAndResp.getFirstName())
                            && fileClearData.getLastName().equals(dcIndvFileClearReqAndResp.getLastName())

                    ) {
                        if (fileClearData.getDobDt().equals(fileClearData.getDobDt()))
                            fileClearData.setScore(85l);
                        else
                            fileClearData.setScore(70l);
                    } else if (!StringUtils.isEmpty(dcIndvFileClearReqAndResp.getFirstName()) && fileClearData.getFirstName().length() >=3
                            && !StringUtils.isEmpty(fileClearData.getFirstName()) && dcIndvFileClearReqAndResp.getFirstName().length() >=3
                            && fileClearData.getFirstName().substring(0,3).equals(dcIndvFileClearReqAndResp.getFirstName().substring(0,3))) {
                        if (fileClearData.getDobDt().equals(dcIndvFileClearReqAndResp.getDobDt()))
                            fileClearData.setScore(75l);
                        else
                            fileClearData.setScore(60l);
                    }else{
                        fileClearData.setScore(0l);
                    }
                    return fileClearData;
                })
                .sorted(Comparator.comparingLong(DcIndvFileClearReqAndResp:: getScore).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<DcIndvFileClearReqAndResp> getFileClearFromMpi(DcIndvFileClearReqAndResp fileClear) {
        return new ArrayList<>();
    }

    @Override
    public List<DcIndvFileClearReqAndResp> getFileClearFromMpi(String ssn) {
        return new ArrayList<>();
    }
}
