package com.deloitte.nextgen.appreg.web.repositories.impl;

import com.deloitte.nextgen.appreg.client.request.ArApplicationForAidReqAndResp;
import com.deloitte.nextgen.appreg.web.entities.QDcAuthRep;
import com.deloitte.nextgen.appreg.web.repositories.DcAuthRepCustomRepository;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Slf4j
@Repository
public class DcAuthRepCustomRepositoryImpl implements DcAuthRepCustomRepository {

    @Autowired
    JPQLQueryFactory queryFactory;

    @Override
    public ArApplicationForAidReqAndResp findDcAuthRepForCase(Long caseNum) {
        QDcAuthRep dcAuthRep = QDcAuthRep.dcAuthRep;


        List<ArApplicationForAidReqAndResp> arApplicationForAidReqAndResp = queryFactory.select(dcAuthRep.authrepFirstName,dcAuthRep.authrepMidName,dcAuthRep.authrepLastName,
                dcAuthRep.authrepSufxName).from(dcAuthRep).where(dcAuthRep.effEndDt.isNull().and(dcAuthRep.caseNum.eq(caseNum)).and(dcAuthRep.histNavInd.in('S','P'))).fetch().stream().map(
                        tuple -> ArApplicationForAidReqAndResp.builder().authrepFirstName(tuple.get(dcAuthRep.authrepFirstName)).authrepMidName(tuple.get(dcAuthRep.authrepMidName)).
                                authrepLastName(tuple.get(dcAuthRep.authrepLastName)).authrepSufxName(tuple.get(dcAuthRep.authrepSufxName)).relCd(null).build()
        ).collect(Collectors.toList());

       return (null != arApplicationForAidReqAndResp && !arApplicationForAidReqAndResp.isEmpty()) ? arApplicationForAidReqAndResp.get(0) : null ;
    }
}
