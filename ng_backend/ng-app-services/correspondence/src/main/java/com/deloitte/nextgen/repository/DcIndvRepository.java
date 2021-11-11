package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.DcIndv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface DcIndvRepository extends JpaRepository<DcIndv,Long> {

    String SELECT_SQL = "SELECT INDV_ID, HISTORY_SEQ, CREATE_USER_ID, CREATE_DT, UNIQUE_TRANS_ID, " +
            " SSN, DOB_DT, LAST_NAME, FIRST_NAME, SUFX_NAME, MID_NAME, DOB_VRF_CD, RACE_CD, SSA_VALIDATION_SW, " +
            " SSN_VRF_CD, ETHNICITY_CD, GENDER_CD, INACTIVE_IND, FILE_CLEARANCE_SW, MERGE_SEPARATE_IND, " +
            " DEATH_DT, MHMR_CASE_NUM, DECEASED_DT_VRF_CD, ARCHIVE_DT, DELETE_SW, CLAIMED_SSN, SSA_VRF_CD, " +
            " ESTIMATED_DOB_SW, AUTH_REP_SW, CITIZENSHIP_VRF, IDENTITY_VRF, CITIZENSHIP_DT, IDENTITY_DT, " +
            " FILE_CLEARANCE_DT, EBT_SW, PREFIX_NAME, VOTE_REG_WISH_SW, RES_ADDR_SW, ADDR_FORMAT, " +
            " ADDR_LINE1, ADDR_LINE2, ADDR_LINE3, ADDR_COUNTY_CD, ADDR_CITY, ADDR_STATE_CD, ADDR_MILITARY, APO_FPO_ADDR, " +
            " ADDR_ZIP5, ADDR_ZIP4, ADDR_CARE_OF_LINE, TAX_STATUS, TAX_STATUS_VERF, INTERPRETER_SW, COMMUNICATION_ASST, " +
            " PRIMARY_LANG, OTHER_LANGUAGE, DISABILITY_ACCOM, OTHER_ACCOMODATION, REPRESENTATIVE_SW, EBT_CARD_SW, " +
            " VERF_RECEIVED_DT, ORGANIZATION_NAME, ORGANIZATION_ID, GMWD_FMONTH_CNT, UPDATE_USER_ID, UPDATE_DT ";

    List<DcIndv> findByIndvId(Long caseNum);

//    @Query(value= SELECT_SQL + " FROM DC_INDV WHERE INDV_ID = (:indvId) " +
//            " UNION " +
//            SELECT_SQL + " FROM DC_INDV_STG WHERE INDV_ID = (:indvId) ", nativeQuery = true)
//    List<Object[]> findByIndividualID(Long indvId);

    @Query(value = " FROM com.deloitte.nextgen.entities.DcIndv WHERE INDV_ID = (:indvId)" +
            " AND FILE_CLEARANCE_SW = 'Y' ORDER BY INDV_ID ")
    List<DcIndv> findByIndividualID(Long indvId);

    @Query(
            value = "SELECT * FROM DC_INDV WHERE INDV_ID IN(SELECT INDV_ID FROM AR_APP_INDV WHERE APP_NUM =(:appNum) " +
                    "AND HEAD_OF_HOUSEHOLD_SW = 'Y')",nativeQuery = true)
    List<DcIndv> findIndvByAppNumHohSw(String appNum);

    @Query(value=  "FROM com.deloitte.nextgen.entities.DcIndv "+
            "WHERE INDV_ID =(:indvId) " )
    DcIndv findByIndividualId(Long indvId);

    @Query(value = "Select A.*  from  DC_INDV A, DC_CASE_INDIVIDUAL B " +
            "Where B.CASE_NUM = (:caseAppNum) AND  A.INDV_ID = B.INDV_ID AND B.ACTIVE_IN_CASE_SW ='Y' AND " +
            "B.EFF_END_DT IS NULL AND B.HEAD_OF_HOUSEHOLD_SW ='Y' AND A.FILE_CLEARANCE_SW='Y' "
            , nativeQuery = true)
    List<DcIndv> findHohByCaseNum(String caseAppNum);

    @Query(value = "from  DcIndv A, DcCaseIndividual B " +
        "Where B.caseNum = (:caseNum) AND  A.indvId = B.indvId " +
        "AND B.activeInCaseSw ='Y' AND B.effEndDt IS NULL ")
    List<DcIndv> findByCaseNum(Long caseNum);





    default List<DcIndv> getDcIndvCargo(List<Object[]> dcIndvList) {
        List<DcIndv> resultList = new ArrayList<>();

        for(Object[] tempArray : dcIndvList) {
            DcIndv dcIndv = new DcIndv();
            if(null != tempArray[0])
                dcIndv.setIndvId(new BigInteger(String.valueOf(tempArray[0])).longValue());
            if(null != tempArray[1])
                dcIndv.setHistorySeq(new BigInteger(String.valueOf(tempArray[1])).longValue());
            dcIndv.setCreateUserId((String) tempArray[2]);
//            dcIndv.setCreateDt((Timestamp)tempArray[3]);
            if(null != tempArray[4])
                dcIndv.setUniqueTransId(new BigInteger(String.valueOf(tempArray[4])).longValue());
            if(null != tempArray[5])
                dcIndv.setSsn(new BigInteger(String.valueOf(tempArray[5])).longValue());
            dcIndv.setDobDt((Timestamp) tempArray[6]);
            dcIndv.setLastName((String) tempArray[7]);
            dcIndv.setFirstName((String) tempArray[8]);
            dcIndv.setSufxName((String) tempArray[9]);
            dcIndv.setMidName((String) tempArray[10]);
            dcIndv.setDobVrfCd((String) tempArray[11]);
            dcIndv.setRaceCd((String) tempArray[12]);
            dcIndv.setSsaValidationSw((Character) tempArray[13]);
            dcIndv.setSsnVrfCd((String) tempArray[14]);
            dcIndv.setEthnicityCd((String) tempArray[15]);
            dcIndv.setGenderCd((Character) tempArray[16]);
            dcIndv.setInactiveInd((Character) tempArray[17]);
            dcIndv.setFileClearanceSw((Character) tempArray[18]);
            dcIndv.setMergeSeparateInd((String) tempArray[19]);
            dcIndv.setDeathDt((Timestamp) tempArray[20]);
            dcIndv.setMhmrCaseNum((String) tempArray[21]);
            dcIndv.setDeceasedDtVrfCd((String) tempArray[22]);
            dcIndv.setArchiveDt((Timestamp) tempArray[23]);
            dcIndv.setDeleteSw((Character) tempArray[24]);
            if(null != tempArray[25])
                dcIndv.setClaimedSsn(new BigInteger(String.valueOf(tempArray[25])).longValue());
            dcIndv.setSsaVrfCd((String) tempArray[26]);
            dcIndv.setEstimatedDobSw((Character) tempArray[27]);
            dcIndv.setAuthRepSw((Character) tempArray[28]);
            dcIndv.setCitizenshipVrf((String) tempArray[29]);
            dcIndv.setIdentityVrf((String) tempArray[30]);
            dcIndv.setCitizenshipDt((Timestamp) tempArray[31]);
            dcIndv.setIdentityDt((Timestamp) tempArray[32]);
            dcIndv.setFileClearanceDt((Timestamp) tempArray[33]);
            dcIndv.setEbtSw((Character) tempArray[34]);
            dcIndv.setPrefixName((String) tempArray[35]);
            dcIndv.setVoteRegWishSw((Character) tempArray[36]);
            dcIndv.setResAddrSw((Character) tempArray[37]);
            dcIndv.setAddrFormat((String) tempArray[38]);
            dcIndv.setAddrLine1((String) tempArray[39]);
            dcIndv.setAddrLine2((String) tempArray[40]);
            dcIndv.setAddrLine3((String) tempArray[41]);
            dcIndv.setAddrCountyCd((String) tempArray[42]);
            dcIndv.setAddrCity((String) tempArray[43]);
            dcIndv.setAddrStateCd((String) tempArray[44]);
            dcIndv.setAddrMilitary((String) tempArray[45]);
            dcIndv.setApoFpoAddr((String) tempArray[46]);
            dcIndv.setAddrZip5((String) tempArray[47]);
            dcIndv.setAddrZip4((String) tempArray[48]);
            dcIndv.setAddrCareOfLine((String) tempArray[49]);
            dcIndv.setTaxStatus((String) tempArray[50]);
            dcIndv.setTaxStatusVerf((String) tempArray[51]);
            dcIndv.setInterpreterSw((String) tempArray[52]);
            dcIndv.setCommunicationAsst((String) tempArray[53]);
            dcIndv.setPrimaryLang((String) tempArray[54]);
            dcIndv.setOtherLanguage((String) tempArray[55]);
            dcIndv.setDisabilityAccom((String) tempArray[56]);
            dcIndv.setOtherAccomodation((String) tempArray[57]);
            dcIndv.setRepresentativeSw((String) tempArray[58]);
            dcIndv.setEbtCardSw((String) tempArray[59]);
            dcIndv.setVerfReceivedDt((Timestamp) tempArray[60]);
            dcIndv.setOrganizationName((String) tempArray[61]);
            dcIndv.setOrganizationId((String) tempArray[62]);
            if(null != tempArray[63])
                dcIndv.setGmwdFmonthCnt(new BigInteger(String.valueOf(tempArray[63])).longValue());
//            dcIndv.setUpdateUserId((String) tempArray[64]);
//            dcIndv.setUpdateDt((Timestamp) tempArray[65]);

            resultList.add(dcIndv);
        }
        return resultList;
    }

}
