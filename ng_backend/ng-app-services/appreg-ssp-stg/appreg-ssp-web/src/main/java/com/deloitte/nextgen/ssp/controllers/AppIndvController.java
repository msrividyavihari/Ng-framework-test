package com.deloitte.nextgen.ssp.controllers;

import com.deloitte.nextgen.ssp.entities.entities.T1004_App_Indv;
import com.deloitte.nextgen.ssp.entities.T1004AppIndvDto;
import com.deloitte.nextgen.ssp.enums.Active;
import com.deloitte.nextgen.ssp.mappings.T1004AppIndvMapping;
import com.deloitte.nextgen.ssp.request.AppRequest;
import com.deloitte.nextgen.ssp.services.AppIndvSSPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ssp-staging-individual")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class AppIndvController
{
    @Autowired
    private AppIndvSSPService service;

    RestTemplate restTemplate=new RestTemplate();

    private final T1004AppIndvMapping t1004AppIndvMapping;

    @PostMapping("/v1/indviduals/appNum")
    public ResponseEntity<List<T1004AppIndvDto>> getSSPdetails(@Valid @RequestBody AppRequest appRequest){
        List<T1004_App_Indv> appIndv=service.findByAppNum(appRequest.getAppNum());
        appIndv.forEach(temp->temp.setClientId(
                (long) Period.between(
                        LocalDate.of(
                                temp.getBrthDt().toLocalDateTime().getYear(),
                                temp.getBrthDt().toLocalDateTime().getMonth(),
                                temp.getBrthDt().toLocalDateTime().getDayOfMonth()
                        ),
                        LocalDate.now()).getYears()

        ));
        appIndv.forEach(f -> f.setIndvSeqNum(null));
        appIndv.forEach(f -> f.setDlValidSw("Yes"));
        appIndv.forEach(i->i.setPrimPrsnSw(i.getPrimPrsnSw()==null? Active.NO: i.getPrimPrsnSw()));
        appIndv=appIndv.stream().sorted((i1,i2)-> - i2.getPrimPrsnSw().compareTo(i1.getPrimPrsnSw())).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(t1004AppIndvMapping.toDto(appIndv));
    }
}
