package com.deloitte.nextgen.ssp.services;

import com.deloitte.nextgen.ssp.entities.entities.T1053_App_Program;
import com.deloitte.nextgen.ssp.entities.vo.SSPDataRequest;
import com.deloitte.nextgen.ssp.entities.T1053AppProgramDto;
import com.deloitte.nextgen.ssp.responses.SnapExpeditedResponse;

import java.util.List;
import java.util.Map;

public interface AppregSSPservice {

    public List<T1053_App_Program> findByAppNum(String appNum);

    public String  saveArProgram(T1053AppProgramDto ap);

    public List<T1053_App_Program> findAll();

    List<Map<String, Map<String, Map<String, Object>>>> insertSSPData(List<SSPDataRequest> sspDataRequest);

    public SnapExpeditedResponse getSnapExpDetails(String appNum);
}
