package com.deloitte.nextgen.ssp.services;

import com.deloitte.nextgen.ssp.entities.entities.T1004_App_Indv;
import com.deloitte.nextgen.ssp.entities.T1004AppIndvDto;

import java.util.List;

public interface AppIndvSSPService {

    public List<T1004_App_Indv> findByAppNum(String appNum);

    public String  saveArProgram(T1004AppIndvDto ap);

    public List<T1004_App_Indv> findAll();
}
