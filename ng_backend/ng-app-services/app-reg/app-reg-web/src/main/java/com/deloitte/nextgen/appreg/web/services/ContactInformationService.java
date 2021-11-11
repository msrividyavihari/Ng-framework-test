package com.deloitte.nextgen.appreg.web.services;

import com.deloitte.nextgen.appreg.client.entities.ArAppAddrDto;
import com.deloitte.nextgen.appreg.client.request.ArApplicationForAidReqAndResp;
import com.deloitte.nextgen.appreg.client.entities.ArEmailDetailsDto;
import com.deloitte.nextgen.appreg.client.entities.ArPhnDetailsDto;
import com.deloitte.nextgen.appreg.client.response.AppContactDetailsResponse;
import com.deloitte.nextgen.appreg.web.entities.ArAppAddr;
import com.deloitte.nextgen.appreg.web.entities.ArEmailDetails;
import com.deloitte.nextgen.appreg.web.entities.ArPhnDetails;

import java.util.List;

public interface ContactInformationService {

    public ArApplicationForAidReqAndResp findAppDetails(String appNum);

    public List<ArPhnDetails> findPhoneByAppNumAndPhoneSrcTyp(String appNum, String phoneSrcTyp);
    public List<ArEmailDetails> findEmailByAppNumAndEmailSrcTyp(String appNum, String emailSrcTyp);
    public ArAppAddr findAppAddrDetailsAndAddrTypeCd(String appNum, String addrTypeCd);

    public List<ArPhnDetailsDto> findPhoneByCaseNumAndPhoneSrcTyp(String appNum, Long caseNum, String phoneSrcTyp);
    public List<ArEmailDetailsDto> findEmailByCaseNumAndEmailSrcTyp(String appNum, Long caseNum, String emailSrcTyp);
    public ArAppAddrDto findDcAddrByCaseNumAndAddrTypeCd(String appNum,Long caseNum, String addrTypeCd);

    public AppContactDetailsResponse insertContactDetails(AppContactDetailsResponse appContactDetailsResponse);

    public void deleteArData(String appNum);


    }
