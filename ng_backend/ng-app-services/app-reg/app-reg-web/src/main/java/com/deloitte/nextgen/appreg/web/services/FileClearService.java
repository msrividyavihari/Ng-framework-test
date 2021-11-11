package com.deloitte.nextgen.appreg.web.services;


import com.deloitte.nextgen.appreg.client.request.DcIndvFileClearReqAndResp;

import java.util.List;

public interface FileClearService {
    public List<DcIndvFileClearReqAndResp> getFileClearRecords(String ssn);
    public List<DcIndvFileClearReqAndResp> getFileClearRecords(DcIndvFileClearReqAndResp dcIndvFileClearReqAndResp);
    List<DcIndvFileClearReqAndResp> getFileClearFromMpi(DcIndvFileClearReqAndResp fileClear);
    List<DcIndvFileClearReqAndResp> getFileClearFromMpi(String ssn);
}
