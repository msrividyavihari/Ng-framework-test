package com.deloitte.nextgen.appreg.web.repositories.impl;

import com.deloitte.nextgen.appreg.client.entities.ArEmailDetailsDto;
import com.deloitte.nextgen.appreg.web.entities.QDcEmailDetails;
import com.deloitte.nextgen.appreg.web.entities.QDcEmailXref;
import com.deloitte.nextgen.appreg.web.repositories.ArEmailDetailsCustomRepository;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArEmailDetailsCustomRepositoryImpl implements ArEmailDetailsCustomRepository {

    @Autowired
    JPQLQueryFactory queryFactory;

    public List<ArEmailDetailsDto> findEmailDetailsForCaseAndEmailScrTyp(Long caseNum, String emailSrcTyp) {

        QDcEmailDetails dcEmailDetails = QDcEmailDetails.dcEmailDetails;
        QDcEmailXref dcEmailXref = QDcEmailXref.dcEmailXref;

        List<ArEmailDetailsDto> emailDetailsDtoList = queryFactory.select(dcEmailDetails.emailTypeCd,
                dcEmailDetails.email, dcEmailDetails.emailComments, dcEmailDetails.emailSrcTyp).
                from(dcEmailDetails).join(dcEmailXref).on(dcEmailDetails.emailSeqNum.eq(dcEmailXref.emailSeqNum)).where(
                dcEmailXref.emailSrcTypeCd.eq("CS").and(dcEmailXref.emailSrcId.eq(caseNum)).and(
                        dcEmailDetails.emailSrcTyp.eq(emailSrcTyp))).fetch().
                        stream().map(tuple -> ArEmailDetailsDto.builder().emailTypeCd(tuple.get(dcEmailDetails.emailTypeCd)).
                        email(tuple.get(dcEmailDetails.email)).emailComments(tuple.get(dcEmailDetails.emailComments)).emailSrcTyp(
                        tuple.get(dcEmailDetails.emailSrcTyp))
                        .build()).collect(Collectors.toList());

        return emailDetailsDtoList;


    }

}
