package com.deloitte.nextgen.ssp.services;
import com.deloitte.nextgen.ssp.entities.entities.T1001_AppRqst;
import com.deloitte.nextgen.ssp.entities.T1001AppRqstDto;

import java.util.List;

public interface AppRqstSSPService {

    public T1001_AppRqst findByAppNum(String appNum);

    public String  saveArProgram(T1001AppRqstDto ap);

    public List<T1001_AppRqst> findAll();
}
