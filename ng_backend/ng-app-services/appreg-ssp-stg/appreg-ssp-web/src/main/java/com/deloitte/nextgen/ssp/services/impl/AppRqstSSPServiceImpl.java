package com.deloitte.nextgen.ssp.services.impl;

import com.deloitte.nextgen.ssp.entities.entities.T1001_AppRqst;
import com.deloitte.nextgen.ssp.entities.T1001AppRqstDto;
import com.deloitte.nextgen.ssp.mappings.T1001AppRqstMapping;
import com.deloitte.nextgen.ssp.repo.AppRqustSSPInterface;
import com.deloitte.nextgen.ssp.services.AppRqstSSPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class AppRqstSSPServiceImpl implements AppRqstSSPService {

    private final T1001AppRqstMapping t1002AppRqstMapping;
    @Autowired
    private AppRqustSSPInterface repo;

    @Override
    public T1001_AppRqst findByAppNum(String appNum) {
        return repo.findByAppNum(appNum);
    }

    @Override
    public String saveArProgram(T1001AppRqstDto ap) {
        return repo.save(t1002AppRqstMapping.toEntity(ap)).getAppNum();
    }

    @Override
    public List<T1001_AppRqst> findAll() {
        return repo.findAll();
    }
}
