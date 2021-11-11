package com.deloitte.nextgen.ssp.services.impl;

import com.deloitte.nextgen.ssp.entities.entities.T1004_App_Indv;
import com.deloitte.nextgen.ssp.entities.T1004AppIndvDto;
import com.deloitte.nextgen.ssp.mappings.T1004AppIndvMapping;
import com.deloitte.nextgen.ssp.repo.AppIndvSSPInterface;
import com.deloitte.nextgen.ssp.services.AppIndvSSPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppIndvSSPServiceImpl implements AppIndvSSPService
{
    private final T1004AppIndvMapping t1004AppIndvMapping;

    @Autowired
    private AppIndvSSPInterface repo;

    @Override
    public List<T1004_App_Indv> findByAppNum(String appNum) {
        return repo.findByAppNum(appNum);
    }

    @Override
    public String saveArProgram(T1004AppIndvDto ap) {
        return repo.save(t1004AppIndvMapping.toEntity(ap)).getAppNum();
    }

    @Override
    public List<T1004_App_Indv> findAll() {
        return repo.findAll();
    }
}
