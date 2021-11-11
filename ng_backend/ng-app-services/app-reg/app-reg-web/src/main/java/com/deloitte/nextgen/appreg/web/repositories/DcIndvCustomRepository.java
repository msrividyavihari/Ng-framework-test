package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.client.request.DcIndvFileClearReqAndResp;
import com.deloitte.nextgen.appreg.client.request.FileClearResponse;
import com.deloitte.nextgen.appreg.client.response.AppIndvListResponse;
import com.deloitte.nextgen.appreg.web.entities.DcIndv;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface DcIndvCustomRepository {

    List<AppIndvListResponse> findAppIndvByAppNum(@Param("appNum") String appNum);

    List<FileClearResponse> selectForFileClearSSN(@Param("ssn") String ssn);

    List<FileClearResponse> selectForFileClearSSN( String firstName, String lastName,  Timestamp dobDt, Character gender);
}
