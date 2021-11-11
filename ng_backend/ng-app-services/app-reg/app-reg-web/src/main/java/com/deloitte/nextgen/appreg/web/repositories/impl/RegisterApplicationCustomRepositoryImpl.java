package com.deloitte.nextgen.appreg.web.repositories.impl;

import com.deloitte.nextgen.appreg.client.enums.Active;
import com.deloitte.nextgen.appreg.client.response.ApplicantsResponse;
import com.deloitte.nextgen.appreg.client.response.ArFetchProgressResponse;
import com.deloitte.nextgen.appreg.web.QDcCaseIndividual;
import com.deloitte.nextgen.appreg.web.entities.*;
import com.deloitte.nextgen.appreg.web.repositories.RegisterApplicationCustomRepository;
import com.deloitte.nextgen.appreg.web.utils.CommonUtils;
import com.deloitte.nextgen.appreg.web.utils.Constants;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class RegisterApplicationCustomRepositoryImpl implements RegisterApplicationCustomRepository {

    @Autowired
    JPQLQueryFactory queryFactory;

    @Override
    public List<ArFetchProgressResponse> fetchProgressForApp(String appNum) {

        QArApplicationForAid arApplicationForAid = QArApplicationForAid.arApplicationForAid;
        QArAppProgram arAppProgram = QArAppProgram.arAppProgram;
        QArAppAddr arAppAddr = QArAppAddr.arAppAddr;
        QArAppIndv arAppIndv = QArAppIndv.arAppIndv;

        List<ArFetchProgressResponse> arFetchProgressResponseList = queryFactory.select(arApplicationForAid.applicationStatusCd, arAppProgram.programCd, arAppAddr.addrLine, arApplicationForAid.caseNum, arAppIndv.indvId).from(arApplicationForAid).
                leftJoin(arAppProgram).on(arApplicationForAid.appNum.eq(arAppProgram.appNum)).leftJoin(arAppAddr).on(arAppAddr.appNum.eq(arApplicationForAid.appNum).and(arAppAddr.addrTypeCd.eq("PA"))).leftJoin(arAppIndv).
                on(arAppIndv.appNum.eq(arApplicationForAid.appNum).and(arAppIndv.headOfHouseholdSw.eq(Active.YES))).where(arApplicationForAid.appNum.eq(appNum)).fetch().stream().
                map(tuple -> ArFetchProgressResponse.builder().applicationStatusCd(tuple.get(arApplicationForAid.applicationStatusCd)).program_cd(tuple.get(arAppProgram.programCd)).addrLine(tuple.get(arAppAddr.addrLine)).caseNum(tuple.get(arApplicationForAid.caseNum)).
                        indvId(tuple.get(arAppIndv.indvId)).build()).collect(Collectors.toList());

        return arFetchProgressResponseList;
    }

    @Override
    public List<ApplicantsResponse> fetchApplInfoPanelDetails(String appNum) {

        QDcIndv dcIndv = QDcIndv.dcIndv;
        QArAppIndv arAppIndv = QArAppIndv.arAppIndv;
        QDcAlias dcAlias = QDcAlias.dcAlias;
        QArApplicationForAid arApplicationForAid = QArApplicationForAid.arApplicationForAid;

        List<ApplicantsResponse> applicantsResponsesList = queryFactory.select(arAppIndv.indvId, arAppIndv.headOfHouseholdSw, dcIndv.firstName, dcIndv.midName,
                dcIndv.lastName, dcIndv.sufxName, dcIndv.genderCd, dcIndv.dobDt, dcIndv.ssn, dcIndv.raceCd, dcIndv.ethnicityCd, dcAlias.firstName,
                dcAlias.midName, dcAlias.lastName, dcAlias.sufxName, dcAlias.genderCd, dcIndv.interpreterSw, dcIndv.primaryLang, dcIndv.otherLanguage,
                dcIndv.disabilityAccom, dcIndv.communicationAsst, arApplicationForAid.authRepSw, dcIndv.ebtCardSw, dcIndv.voteRegWishSw, arAppIndv.indvStatusSw,
                dcAlias.aliasIndSeqNum).from(dcIndv).join(arAppIndv).on(dcIndv.indvId.eq(arAppIndv.indvId)).leftJoin(dcAlias).on(dcAlias.indvId.eq(arAppIndv.indvId)).
                join(arApplicationForAid).on(arApplicationForAid.appNum.eq(arAppIndv.appNum)).where(arAppIndv.appNum.eq(appNum)).orderBy(arAppIndv.headOfHouseholdSw.desc()).
                fetch().stream().map(tuple -> ApplicantsResponse.builder()
                        .indvId(tuple.get(arAppIndv.indvId))
                        .primaryApplicantSw(tuple.get(arAppIndv.headOfHouseholdSw).equals(Active.YES) ? Constants.YES_CAPITALIZED : Constants.NO_CAPITALIZED)
                        .includeApplicantSw(Constants.YES_CAPITALIZED)
                        .firstName(tuple.get(dcIndv.firstName))
                        .middleName(tuple.get(dcIndv.midName))
                        .lastName(tuple.get(dcIndv.lastName))
                        .sufxName(tuple.get(dcIndv.sufxName))
                        .gender(tuple.get(dcIndv.genderCd))
                        .age(CommonUtils.calculateAge(Objects.requireNonNull(tuple.get(dcIndv.dobDt))))
                        .dob(tuple.get(dcIndv.dobDt))
                        .ssn(tuple.get(dcIndv.ssn))
                        .race(tuple.get(dcIndv.raceCd))
                        .ethnicity(tuple.get(dcIndv.ethnicityCd))
                        .aliasFirstName(tuple.get(dcAlias.firstName))
                        .aliasMiddleName(tuple.get(dcAlias.midName))
                        .aliasLastName(tuple.get(dcAlias.lastName))
                        .aliasSuffix(tuple.get(dcAlias.sufxName))
                        .aliasGender(tuple.get(dcAlias.genderCd))
                        .interpreterSw(tuple.get(dcIndv.interpreterSw))
                        .primaryLanguage(tuple.get(dcIndv.primaryLang))
                        .specificPrimaryLanguage(tuple.get(dcIndv.otherLanguage))
                        .accommodationSw(( null != tuple.get(dcIndv.disabilityAccom) && tuple.get(dcIndv.disabilityAccom).equals(Active.YES)) ? Active.YES : Active.NO)
                        .typeAccommodation(tuple.get(dcIndv.communicationAsst))
                        .authRepresentativeSw(tuple.get(arApplicationForAid.authRepSw))
                        .ebtcardSw(tuple.get(dcIndv.ebtCardSw))
                        .registervoteSw(tuple.get(dcIndv.voteRegWishSw))
                        .indvStatusSw(tuple.get(arAppIndv.indvStatusSw))
                        .aliasIndSeqNum(tuple.get(dcAlias.aliasIndSeqNum))
                        .aliasSw(tuple.get(dcAlias.aliasIndSeqNum) != null ? Active.YES : Active.NO)
                        .build()
                ).collect(Collectors.toList());


        return applicantsResponsesList;
    }

    @Override
    public List<ApplicantsResponse> fetchApplicantsInfoForAddApp(Long indvId) {

        QDcIndv dcIndv = QDcIndv.dcIndv;
        QDcCaseIndividual dcCaseIndividual = QDcCaseIndividual.dcCaseIndividual;
        QDcAlias dcAlias = QDcAlias.dcAlias;
        QDcCases dcCases = QDcCases.dcCases;

        return queryFactory.select(dcIndv.indvId,dcIndv.firstName,dcIndv.midName,
                dcIndv.lastName,dcIndv.sufxName,dcIndv.genderCd,dcIndv.dobDt,dcIndv.ssn,dcIndv.raceCd,dcIndv.ethnicityCd,dcAlias.firstName,
                dcAlias.midName,dcAlias.lastName,dcAlias.sufxName,dcAlias.genderCd,dcIndv.interpreterSw,dcIndv.primaryLang,dcIndv.disabilityAccom,
                dcIndv.communicationAsst,dcCases.authRepSw,dcIndv.ebtCardSw,dcIndv.voteRegWishSw,dcAlias.aliasIndSeqNum).from(dcIndv).
                leftJoin(dcCaseIndividual).on(dcIndv.indvId.eq(dcCaseIndividual.indvId).and(dcCaseIndividual.headOfHouseholdSw.eq('Y')))
                .leftJoin(dcAlias).on(dcAlias.indvId.eq(dcIndv.indvId)).leftJoin(dcCases).on(dcCases.caseNum.eq(dcCaseIndividual.caseNum)).
                where(dcIndv.indvId.eq(indvId)).fetch().stream().map(tuple -> ApplicantsResponse.builder()
                        .indvId(tuple.get(dcIndv.indvId))
                        .primaryApplicantSw(Constants.NO_CAPITALIZED)
                        .firstName(tuple.get(dcIndv.firstName))
                        .middleName(tuple.get(dcIndv.midName))
                        .lastName(tuple.get(dcIndv.lastName))
                        .sufxName(tuple.get(dcIndv.sufxName))
                        .gender(tuple.get(dcIndv.genderCd))
                        .age(CommonUtils.calculateAge(tuple.get(dcIndv.dobDt)))
                        .dob(tuple.get(dcIndv.dobDt))
                        .ssn(tuple.get(dcIndv.ssn))
                        .race(tuple.get(dcIndv.raceCd))
                        .ethnicity(tuple.get(dcIndv.ethnicityCd))
                        .aliasFirstName(tuple.get(dcAlias.firstName))
                        .aliasMiddleName(tuple.get(dcAlias.midName))
                        .aliasLastName(tuple.get(dcAlias.lastName))
                        .aliasSuffix(tuple.get(dcAlias.sufxName))
                        .aliasGender(tuple.get(dcAlias.genderCd))
                        .interpreterSw(tuple.get(dcIndv.interpreterSw))
                        .primaryLanguage(tuple.get(dcIndv.primaryLang))
                        .accommodationSw((null !=tuple.get(dcIndv.disabilityAccom) && tuple.get(dcIndv.disabilityAccom).equals(Active.YES))? Active.YES : Active.NO)
                        .typeAccommodation(tuple.get(dcIndv.communicationAsst))
                        .authRepresentativeSw((null !=tuple.get(dcCases.authRepSw) && tuple.get(dcCases.authRepSw).equals(Active.YES)) ? Active.YES : Active.NO)
                        .ebtcardSw(tuple.get(dcIndv.ebtCardSw))
                        .registervoteSw(tuple.get(dcIndv.voteRegWishSw))
                        .aliasSw(tuple.get(dcAlias.aliasIndSeqNum) != null ? Active.YES : Active.NO)
                        .includeApplicantSw(Constants.YES_CAPITALIZED)
                        .build()
                        ).collect(Collectors.toList());


    }

    @Override
    public List<ApplicantsResponse> fetchApplicantsInfoForPrimApp(Long indvId) {

        QDcIndv dcIndv = QDcIndv.dcIndv;
        QDcCaseIndividual dcCaseIndividual = QDcCaseIndividual.dcCaseIndividual;
        QDcAlias dcAlias = QDcAlias.dcAlias;
        QDcCases dcCases = QDcCases.dcCases;
        QDcIndv dcIndvSub = QDcIndv.dcIndv;
        QDcCaseIndividual dcCaseIndividualSub1 = QDcCaseIndividual.dcCaseIndividual;
        QDcCaseIndividual dcCaseIndividualSub2 = QDcCaseIndividual.dcCaseIndividual;

        return  queryFactory.selectDistinct(dcIndv.indvId,dcIndv.firstName,dcIndv.midName,
                dcIndv.lastName,dcIndv.sufxName,dcIndv.genderCd,dcIndv.dobDt,dcIndv.ssn,dcIndv.raceCd,dcIndv.ethnicityCd,dcAlias.firstName,
                dcAlias.midName,dcAlias.lastName,dcAlias.sufxName,dcAlias.genderCd,dcIndv.interpreterSw,dcIndv.primaryLang,dcIndv.disabilityAccom,
                dcIndv.communicationAsst,dcCases.authRepSw,dcIndv.ebtCardSw,dcIndv.voteRegWishSw,dcAlias.aliasIndSeqNum,dcCaseIndividual.indvId).from(dcIndv).
                join(dcCaseIndividual).on(dcIndv.indvId.eq(dcCaseIndividual.indvId).and(dcCaseIndividual.headOfHouseholdSw.eq('Y')))
                .leftJoin(dcAlias).on(dcAlias.indvId.eq(dcIndv.indvId)).join(dcCases).on(dcCases.caseNum.eq(dcCaseIndividual.caseNum)).
                        where(
                                dcIndv.indvId.in(
                                queryFactory.select(dcIndvSub.indvId).from(dcIndvSub).
                                        where(dcIndv.indvId.
                                            in(
                                        queryFactory.select(dcCaseIndividualSub1.indvId).from(dcCaseIndividualSub1).
                                                where(dcCaseIndividualSub1.
                                                        caseNum.in(
                                                            queryFactory.select(dcCaseIndividualSub2.caseNum).from(dcCaseIndividualSub2).
                                                                    where(dcCaseIndividualSub2.indvId.eq(indvId))
                                                                 )
                                                         )
                                                )
                                          )
                                )).fetch().stream().map(tuple -> ApplicantsResponse.builder()
                        .indvId(tuple.get(dcIndv.indvId))
                        .primaryApplicantSw((null != tuple.get(dcCaseIndividual.indvId) && tuple.get(dcCaseIndividual.indvId).equals(indvId) ) ? Constants.YES_CAPITALIZED : Constants.NO_CAPITALIZED)
                        .firstName(tuple.get(dcIndv.firstName))
                        .middleName(tuple.get(dcIndv.midName))
                        .lastName(tuple.get(dcIndv.lastName))
                        .sufxName(tuple.get(dcIndv.sufxName))
                        .gender(tuple.get(dcIndv.genderCd))
                        .age(CommonUtils.calculateAge(tuple.get(dcIndv.dobDt)))
                        .dob(tuple.get(dcIndv.dobDt))
                        .ssn(tuple.get(dcIndv.ssn))
                        .race(tuple.get(dcIndv.raceCd))
                        .ethnicity(tuple.get(dcIndv.ethnicityCd))
                        .aliasFirstName(tuple.get(dcAlias.firstName))
                        .aliasMiddleName(tuple.get(dcAlias.midName))
                        .aliasLastName(tuple.get(dcAlias.lastName))
                        .aliasSuffix(tuple.get(dcAlias.sufxName))
                        .aliasGender(tuple.get(dcAlias.genderCd))
                        .interpreterSw(tuple.get(dcIndv.interpreterSw))
                        .primaryLanguage(tuple.get(dcIndv.primaryLang))
                        .accommodationSw((null !=tuple.get(dcIndv.disabilityAccom) && tuple.get(dcIndv.disabilityAccom).equals(Active.YES))? Active.YES : Active.NO)
                        .typeAccommodation(tuple.get(dcIndv.communicationAsst))
                        .authRepresentativeSw((null !=tuple.get(dcCases.authRepSw) && tuple.get(dcCases.authRepSw).equals(Active.YES)) ? Active.YES : Active.NO)
                        .ebtcardSw(tuple.get(dcIndv.ebtCardSw))
                        .registervoteSw(tuple.get(dcIndv.voteRegWishSw))
                        .aliasSw(tuple.get(dcAlias.aliasIndSeqNum) != null ? Active.YES : Active.NO)
                        .includeApplicantSw(Constants.YES_CAPITALIZED)
                        .build()
                ).collect(Collectors.toList());

    }

}
