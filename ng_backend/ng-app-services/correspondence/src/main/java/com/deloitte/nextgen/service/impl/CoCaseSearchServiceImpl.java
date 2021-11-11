package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.vo.CoCaseSearchVO;
import com.deloitte.nextgen.dto.vo.CoMasterVO;
import com.deloitte.nextgen.entities.CoMaster;
import com.deloitte.nextgen.entities.VDcCaseIndvProgram;
import com.deloitte.nextgen.repository.CoMasterRepository;
import com.deloitte.nextgen.repository.VDcCaseIndvProgramRepository;
import com.deloitte.nextgen.service.CoCaseSearchService;
import com.deloitte.nextgen.util.CoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CoCaseSearchServiceImpl implements CoCaseSearchService {


    @Autowired
    VDcCaseIndvProgramRepository repo;

    @Autowired
    CoMasterRepository coMasterRepo;

    public List<CoCaseSearchVO> fetchByCaseNum(Long caseNum, String curDt) {
        List<VDcCaseIndvProgram> obj = repo.findByCaseNum(caseNum, curDt);
        return getCaseSearchVO(obj);


    }

    @Override
    public List<CoMasterVO> fetchCOMaster() {
        List<CoMaster> coMasterObj = coMasterRepo.findAll();
        List<CoMasterVO> coMasterList  = new ArrayList<>();
        for (CoMaster coMaster : coMasterObj){
            CoMasterVO newCoMaster = new CoMasterVO();
            newCoMaster.setDocId(coMaster.getDocId());
            newCoMaster.setDocName(coMaster.getDocName());
            coMasterList.add(newCoMaster);
        }
        return coMasterList;
    }


    public Map<String, String> fetchCOMasterMap() {
        List<CoMaster> coMasterObj = coMasterRepo.findAll();
        Map<String, String> coMasterList  = new HashMap<>();
        for (CoMaster coMaster : coMasterObj){
            coMasterList.put(coMaster.getDocId(), coMaster.getDocName());
//            CoMasterVO newCoMaster = new CoMasterVO();
//            newCoMaster.setDocId(coMaster.getDocId());
//            newCoMaster.setDocName(coMaster.getDocName());
//            coMasterList.add(newCoMaster);
        }
        return coMasterList;
    }

    public List<CoCaseSearchVO> getCaseSearchVO(List<VDcCaseIndvProgram> obj) {
        List<CoCaseSearchVO> caseList = new ArrayList<>();
        String fullName = "";
        for(VDcCaseIndvProgram indv: obj) {
            CoCaseSearchVO dcIndvObj = new CoCaseSearchVO();
            dcIndvObj.setClientId(indv.getT1IndvId());
            fullName += indv.getFirstName() + " ";
            fullName += indv.getLastName();
            dcIndvObj.setFullName(fullName);
            dcIndvObj.setAge(CoUtil.calculateDOB(indv.getDobDt()));
            dcIndvObj.setGender(indv.getGenderCd());
            caseList.add(dcIndvObj);
        }
        return caseList;
    }
}
