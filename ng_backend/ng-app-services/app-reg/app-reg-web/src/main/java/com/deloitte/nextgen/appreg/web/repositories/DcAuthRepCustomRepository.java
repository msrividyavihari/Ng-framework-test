package com.deloitte.nextgen.appreg.web.repositories;


import com.deloitte.nextgen.appreg.client.request.ArApplicationForAidReqAndResp;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DcAuthRepCustomRepository {

    ArApplicationForAidReqAndResp findDcAuthRepForCase(@Param("caseNum") Long caseNum);
}
