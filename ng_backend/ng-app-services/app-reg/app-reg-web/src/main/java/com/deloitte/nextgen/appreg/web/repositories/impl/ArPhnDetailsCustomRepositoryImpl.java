package com.deloitte.nextgen.appreg.web.repositories.impl;

import com.deloitte.nextgen.appreg.client.entities.ArPhnDetailsDto;
import com.deloitte.nextgen.appreg.web.entities.QDcPhnDetails;
import com.deloitte.nextgen.appreg.web.entities.QDcPhnXref;
import com.deloitte.nextgen.appreg.web.repositories.ArPhnDetailsCustomRepository;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArPhnDetailsCustomRepositoryImpl implements ArPhnDetailsCustomRepository {

    @Autowired
    JPQLQueryFactory queryFactory;

    public List<ArPhnDetailsDto> findPhnDetailsForCaseAndPhoneSrcTyp(Long caseNum, String phoneSrcTyp){

        QDcPhnDetails dcPhnDetails = QDcPhnDetails.dcPhnDetails;
        QDcPhnXref dcPhnXref = QDcPhnXref.dcPhnXref;

        List<ArPhnDetailsDto> arPhnDetailsDtoList = queryFactory.select(dcPhnDetails.phnTypeCd,dcPhnDetails.phnNum,
                dcPhnDetails.phnComments,dcPhnDetails.phoneExtn,dcPhnDetails.phoneSrcTyp).
                from(dcPhnXref).join(dcPhnDetails).on(dcPhnDetails.phnSeqNum.eq(dcPhnXref.phnSeqNum)).
                where(dcPhnXref.phnSrcTypeCd.eq("CS").and(dcPhnXref.phnSrcId.eq(caseNum)).
                        and(dcPhnDetails.phoneSrcTyp.eq(phoneSrcTyp))).fetch().stream().map(
                         tuple->
                             ArPhnDetailsDto.builder().phnTypeCd(tuple.get(dcPhnDetails.phnTypeCd)).phnNum(
                                     tuple.get(dcPhnDetails.phnNum)).phnNum(tuple.get(dcPhnDetails.phnNum)).phnComments(
                                             tuple.get(dcPhnDetails.phnComments)).phoneExtn(tuple.get(dcPhnDetails.phoneExtn).toString()).
                                     phoneSrcTyp(tuple.get(dcPhnDetails.phoneSrcTyp)).build()).collect(Collectors.toList());

        return arPhnDetailsDtoList;


    }
}
