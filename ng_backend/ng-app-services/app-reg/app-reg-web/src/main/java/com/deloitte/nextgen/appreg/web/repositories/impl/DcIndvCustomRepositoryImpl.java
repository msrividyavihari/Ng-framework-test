package com.deloitte.nextgen.appreg.web.repositories.impl;

import com.deloitte.nextgen.appreg.client.request.FileClearResponse;
import com.deloitte.nextgen.appreg.client.response.AppIndvListResponse;
import com.deloitte.nextgen.appreg.web.entities.QArAppIndv;
import com.deloitte.nextgen.appreg.web.entities.QDcAlias;
import com.deloitte.nextgen.appreg.web.entities.QDcIndv;
import com.deloitte.nextgen.appreg.web.entities.QDcRelationships;
import com.deloitte.nextgen.appreg.web.repositories.DcIndvCustomRepository;
import com.deloitte.nextgen.appreg.web.utils.CommonUtils;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DcIndvCustomRepositoryImpl implements DcIndvCustomRepository {

    @Autowired
    JPQLQueryFactory queryFactory;

    @Override
    public List<AppIndvListResponse> findAppIndvByAppNum(String appNum) {

        QDcIndv dcIndv = QDcIndv.dcIndv;
        QArAppIndv arAppIndv = QArAppIndv.arAppIndv;

        List<AppIndvListResponse> appIndvListResponse = queryFactory.select(dcIndv.firstName, dcIndv.lastName, arAppIndv.headOfHouseholdSw, dcIndv.indvId, dcIndv.dobDt).
                from(dcIndv).join(arAppIndv).on(dcIndv.indvId.eq(arAppIndv.indvId)).where(arAppIndv.appNum.eq(appNum)).fetch().
                stream().map(tuple -> AppIndvListResponse.builder().indvName(tuple.get(dcIndv.firstName).concat(" ").concat(tuple.get(dcIndv.lastName))).
                headOfHousehold(tuple.get(arAppIndv.headOfHouseholdSw)).indvId(tuple.get(dcIndv.indvId)).age(CommonUtils.calculateAge(tuple.get(dcIndv.dobDt))).build()).collect(Collectors.toList());


        return appIndvListResponse;
    }

    @Override
    public List<FileClearResponse> selectForFileClearSSN(String ssn) {
        QDcIndv dcIndv = QDcIndv.dcIndv;
        QDcAlias dcAlias = QDcAlias.dcAlias;
        QDcRelationships dcRelationships = QDcRelationships.dcRelationships;
        QDcIndv relationIndv = QDcIndv.dcIndv;

        List<FileClearResponse> dcIndvFileClearReqAndRespList =
                queryFactory.select(dcIndv.indvId, dcIndv.firstName, dcIndv.midName, dcIndv.lastName, dcIndv.sufxName, dcIndv.raceCd, dcIndv.ethnicityCd, dcIndv.dobDt, dcIndv.genderCd, dcAlias.firstName, dcAlias.midName, dcAlias.lastName, relationIndv.firstName, relationIndv.midName, relationIndv.lastName, dcRelationships.relationshipTypeCd, dcIndv.ssn).from(dcIndv)
                        .leftJoin(dcAlias).on(dcIndv.indvId.eq(dcAlias.indvId)).leftJoin(dcRelationships).on(dcIndv.indvId.eq(dcRelationships.indvId)).leftJoin(relationIndv).on(relationIndv.indvId.eq(dcRelationships.indvId).and(relationIndv.fileClearanceSw.eq('Y'))).
                        where(dcIndv.ssn.eq(Long.valueOf(ssn)).and(dcIndv.fileClearanceSw.eq('Y'))).fetch().stream().map(
                        tuple -> FileClearResponse.builder().indvId(tuple.get(dcIndv.indvId)).firstName(tuple.get(dcIndv.firstName)).midName(tuple.get(dcIndv.midName)).lastName(tuple.get(dcIndv.lastName)).
                                sufxName(tuple.get(dcIndv.sufxName)).raceCd(tuple.get(dcIndv.raceCd)).ethnicityCd(tuple.get(dcIndv.ethnicityCd)).dobDt(tuple.get(dcIndv.dobDt)).gender(tuple.get(dcIndv.genderCd)).aliasFirstName(tuple.get(dcAlias.firstName)).aliasMidName(
                                tuple.get(dcAlias.midName)).aliasLastName(tuple.get(dcAlias.lastName)).relFirstName(tuple.get(relationIndv.firstName)).relMidName(tuple.get(relationIndv.midName)).relLastName(tuple.get(relationIndv.lastName)).relationshipTypeCd(tuple.get(dcRelationships.relationshipTypeCd)).ssn(tuple.get(dcIndv.ssn))
                                .build()
                ).collect(Collectors.toList());

        return dcIndvFileClearReqAndRespList;
    }

    @Override
    public List<FileClearResponse> selectForFileClearSSN(String firstName, String lastName, Timestamp dobDt, Character gender) {
        QDcIndv dcIndv = QDcIndv.dcIndv;
        QDcAlias dcAlias = QDcAlias.dcAlias;
        QDcRelationships dcRelationships = QDcRelationships.dcRelationships;
        QDcIndv relationIndv = QDcIndv.dcIndv;

        List<FileClearResponse> dcIndvFileClearReqAndRespList =
                queryFactory.select(dcIndv.indvId, dcIndv.firstName, dcIndv.midName, dcIndv.lastName, dcIndv.sufxName, dcIndv.raceCd, dcIndv.ethnicityCd, dcIndv.dobDt, dcIndv.genderCd, dcAlias.firstName, dcAlias.midName, dcAlias.lastName, relationIndv.firstName, relationIndv.midName, relationIndv.lastName, dcRelationships.relationshipTypeCd, dcIndv.ssn).from(dcIndv)
                        .leftJoin(dcAlias).on(dcIndv.indvId.eq(dcAlias.indvId)).leftJoin(dcRelationships).on(dcIndv.indvId.eq(dcRelationships.indvId)).leftJoin(relationIndv).on(relationIndv.indvId.eq(dcRelationships.indvId).and(relationIndv.fileClearanceSw.eq('Y'))).
                        where(((dcIndv.firstName.like(firstName).or(dcIndv.lastName.like(lastName))).and(dcIndv.genderCd.eq(gender))).or(dcIndv.dobDt.eq(dobDt))).fetch().stream().map(
                        tuple -> FileClearResponse.builder().indvId(tuple.get(dcIndv.indvId)).firstName(tuple.get(dcIndv.firstName)).midName(tuple.get(dcIndv.midName)).lastName(tuple.get(dcIndv.lastName)).
                                sufxName(tuple.get(dcIndv.sufxName)).raceCd(tuple.get(dcIndv.raceCd)).ethnicityCd(tuple.get(dcIndv.ethnicityCd)).dobDt(tuple.get(dcIndv.dobDt)).gender(tuple.get(dcIndv.genderCd)).aliasFirstName(tuple.get(dcAlias.firstName)).aliasMidName(
                                tuple.get(dcAlias.midName)).aliasLastName(tuple.get(dcAlias.lastName)).relFirstName(tuple.get(relationIndv.firstName)).relMidName(tuple.get(relationIndv.midName)).relLastName(tuple.get(relationIndv.lastName)).relationshipTypeCd(tuple.get(dcRelationships.relationshipTypeCd)).ssn(tuple.get(dcIndv.ssn))
                                .build()
                ).collect(Collectors.toList());

        return dcIndvFileClearReqAndRespList;
    }
}
