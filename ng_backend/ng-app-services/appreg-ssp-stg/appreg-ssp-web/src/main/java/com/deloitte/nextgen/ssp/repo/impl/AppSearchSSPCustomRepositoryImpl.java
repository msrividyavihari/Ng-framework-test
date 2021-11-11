package com.deloitte.nextgen.ssp.repo.impl;

import com.deloitte.nextgen.ssp.request.AppSearchSSPRequest;
import com.deloitte.nextgen.ssp.repo.AppSearchSSPCustomRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AppSearchSSPCustomRepositoryImpl implements AppSearchSSPCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private TransactionTemplate transactionTemplate;

    private static final String APP_NUM = "appNum";

    private static final String BASE_QUERY =
    "select DISTINCT a.FST_NAM,a.LAST_NAM,b.APP_RCV_DT,b.APP_MODE,b.APP_NUM,b.APP_STAT_CD from T1004_APP_INDV a,T1001_APP_RQST b\n" +
            "WHERE a.app_num=b.app_num and a.PRIM_PRSN_SW='Y' and a.app_num NOT IN(select distinct app_num from wp_ar_app.ar_application_for_aid)";

    private static final String BASE_INDV_QUERY =
            "select \n" +
                    "CASE \n" +
                    "WHEN a.APP_NUM IN(select distinct app_num from wp_ar_app.ar_application_for_aid WHERE APP_NUM=(:appNum)) AND\n" +
                    "a.APP_NUM NOT IN (select distinct app_num from WP_AR_APP.ar_app_indv WHERE APP_NUM=(:appNum)) THEN 'NEW_RECORD'\n" +
                    "WHEN \n" +
                    "a.APP_NUM IN(select distinct app_num from wp_ar_app.ar_application_for_aid WHERE APP_NUM=(:appNum)) AND\n" +
                    "a.APP_NUM IN (select distinct app_num from WP_AR_APP.ar_app_indv WHERE APP_NUM=(:appNum)) THEN 'EXISTING_RECORD'\n" +
                    "WHEN \n" +
                    "a.APP_NUM NOT IN(select distinct app_num from wp_ar_app.ar_application_for_aid WHERE APP_NUM=(:appNum)) AND\n" +
                    "a.APP_NUM NOT IN (select distinct app_num from WP_AR_APP.ar_app_indv WHERE APP_NUM=(:appNum)) THEN 'NO_RECORD'\n" +
                    "ELSE 'SKIP'\n" +
                    "END RESULT\n" +
                    "from T1004_APP_INDV a,T1001_APP_RQST b WHERE a.APP_NUM=(:appNum) AND a.APP_NUM=b.APP_NUM ";

    private static final String CONFLICT_QUERY =
            "SELECT DISTINCT\n" +
                    "INDV_SEQ_NUM INDV_ID,PRIM_PRSN_SW PRIMARYAPPLICANTSW,INDV_ESTB_STAT_IND INCLUDEAPPLICANTSW,\n" +
                    "FST_NAM FRST_NAME,MID_INIT MID_NAME,LAST_NAM  LAST_NAME,SFX_NAM  SUFFIX,SEX_IND  SEX,\n" +
                    "NVL(ROUND((months_between(trunc(sysdate),BRTH_DT ))/12),0) AGE,BRTH_DT DOB,SSN_NUM SSN,null RACE,\n" +
                    "NULL ETHNICITY,ALIAS_IND ALIASSW,ALIAS_FST_NAM ALIASFIRST,ALIAS_MID_INIT ALIASMID,\n" +
                    "ALIAS_LAST_NAM ALIASLAST, ALIAS_SUFFIX_NAME ALIASSUFFIX, SEX_IND ALIASSEX, REQ_INTERP_IND INTERPRETORSW,\n" +
                    "LANG_OTH_DSC PRIMARYLANG,null  SPECPRIMARYLANG,null ACCOMADATIONSW, null ACCOMODATIONTYPE,\n" +
                    "null AUTHREPSW,null EBTCARDSW,null VOTESW,INDV_ESTB_STAT_IND INDVSTATSSW\n" +
                    "FROM T1004_APP_INDV WHERE app_num=(:appNum)";

    private static final String WEEK_DAY_QUERY =
            "select DISTINCT PREF_CONT_METHOD_CD,PREF_CONT_TIME_CD from T1002_APP_DTL WHERE app_num=(:appNum)";

    private static final String FIND_ADDR_QUERY =
            " SELECT DISTINCT APP_NUM, NVL(HSHL_ADDR_FORMAT,'US') AS RES_ADDR_FORMAT_CD,HSHL_L1_ADR  RES_ADDR_LINE,HSHL_L2_ADR  RES_ADDR_LINE1,'' AS RES_ADDR_COUNTY_CD,HSHL_CITY_ADR  RES_ADDR_CITY,HSHL_ST_ADR RES_ADDR_STATE_CD,HSHL_ZIP_ADR RES_ADDR_ZIP5,'PA' AS RES_ADDR_TYPE_CD, " +
                    " NVL(HSHL_ADDR_FORMAT,'US')AS MAIL_ADDR_FORMAT_CD,HSHL_MAIL_STREET_ADR  MAIL_ADDR_LINE,HSHL_ADDRLINE_2  MAIL_ADDR_LINE1,'' AS MAIL_ADDR_COUNTY_CD,HSHL_MAIL_CITY_ADR  MAIL_ADDR_CITY,HSHL_MAIL_ST_ADR MAIL_ADDR_STATE_CD,HSHL_MAIL_ZIP5_ADR MAIL_ADDR_ZIP5 ,'MA' AS MAIL_ADDR_TYPE_CD,IP_RES_SW RES_ADDR_SW,HSHL_EMAIL_ADR AS EMAIL_ID " +
                    "FROM T1002_APP_DTL WHERE APP_NUM=(:appNum)";

    private static final String FIND_AUTH_REP_ADDR_QUERY =
            "select DISTINCT APP_NUM,HAS_AUTH_REP_SW,AUTH_REP_FIRST_NAME, AUTH_REP_LAST_NAME,L1_ADR,L2_ADR,CITY_ADR,STA_ADR,ZIP_ADR,PHN_NUM,EMAIL_ADR,AUTH_REP_MID_NAME, AUTH_REP_SUFFIX_NAM \n" +
                    "from T1065_APP_AUTH_REP where app_num=(:appNum)";

    private static final String PHN_DTL_QUERY =
            "SELECT DISTINCT APP_NUM,\n" +
                    "'PRP' AS PRI_PHN_TYPE_CD,HSHL_PHN_NUM AS PRI_PHN_NUM,NULL AS PRI_PHN_COMMENTS,NULL AS PRI_PHONE_EXTN,'PA' AS PRI_PHONE_SRC_TYP,\n" +
                    "'WOP' AS WRK_PHN_TYPE_CD,HSHL_WORK_PHN_NUM AS WRK_PHN_NUM,CMT_TXT AS WRK_PHN_COMMENTS,WORK_PHN_EXTN_NUM AS WRK_PHONE_EXTN,'PA' AS WRK_PHONE_SRC_TYP,\n" +
                    "'ALP' AS ALT_PHN_TYPE_CD,HSHL_CELL_PHN_NUM AS ALT_PHN_NUM,NULL AS ALT_PHN_COMMENTS,NULL AS ALT_PHONE_EXTN,'PA' AS ALT_PHONE_SRC_TYP \n" +
                    "FROM T1002_APP_DTL WHERE APP_NUM=(:appNum)";

    private static final String EMAIL_DTL_QUERY =
            "SELECT DISTINCT APP_NUM,HSHL_EMAIL_ADR FROM T1002_APP_DTL WHERE APP_NUM=(:appNum)";
    @Override
    public List<Object[]> findApplications(AppSearchSSPRequest appSearchVO) {
        StringBuilder appSearchQuery = new StringBuilder(BASE_QUERY);
        String appNum = appSearchVO.getAppNum();
        Long ssn = appSearchVO.getApplicantSSN();
        String firstName = appSearchVO.getApplicantFirstName();
        String lastName = appSearchVO.getApplicantLastName();

        if(StringUtils.isNotBlank(appNum)) {
            appSearchQuery.append(" AND a.app_num = (:appNum) ");
        }
        if(null!=ssn && ssn!=0L){
            String ssnQuery = "";
            ssnQuery=   String.valueOf(ssn).length() == 9 ?
                 " AND a.SSN_NUM = (:ssn) " :  " AND a.SSN_NUM LIKE :ssn ";
            appSearchQuery.append(ssnQuery);
        }
        if(StringUtils.isNotBlank(firstName)){
            appSearchQuery.append(" AND UPPER(a.FST_NAM) LIKE UPPER(:firstName) ");
        }
        if(StringUtils.isNotBlank(lastName)){
            appSearchQuery.append(" AND UPPER(a.LAST_NAM) LIKE UPPER(:lastName) ");
        }

        Query query = entityManager.createNativeQuery(appSearchQuery.toString());
        if(StringUtils.isNotBlank(appNum)) {
            query.setParameter(APP_NUM, appNum);
        }
        if(null!=ssn && ssn!=0L){
            if(String.valueOf(ssn).length()==9) {
                query.setParameter("ssn", ssn);
            } else {
                query.setParameter("ssn", "%"+ssn+"%");
            }
        }
        if(StringUtils.isNotBlank(firstName)){
            firstName=firstName.replace("'","''");
            query.setParameter("firstName",firstName+"%");
        }
        if(StringUtils.isNotBlank(lastName)){
            lastName=lastName.replace("'","''");
            query.setParameter("lastName",lastName+"%");
        }
        query.getResultList();
        return query.getResultList();
    }

    @Override
    public List<Object[]> findConflictApps(String appNum) {
        StringBuilder appSearchQuery = new StringBuilder(CONFLICT_QUERY);
        Query query = entityManager.createNativeQuery(appSearchQuery.toString());
        if(null!=appNum && !appNum.isEmpty()) {
            query.setParameter(APP_NUM, appNum);
        }
        return query.getResultList();
    }

    @Override
    public List<String> findByAppNum(String appNum) {
        StringBuilder appSearchQuery = new StringBuilder(BASE_INDV_QUERY);
        Query query = entityManager.createNativeQuery(appSearchQuery.toString());
        if(null!=appNum && !appNum.isEmpty()) {
            query.setParameter(APP_NUM, appNum);
        }
        return query.getResultList();
    }

    @Override
    public List<Object[]> findAddrDtl(String appNum) {
        StringBuilder appSearchQuery = new StringBuilder(FIND_ADDR_QUERY);
        Query query = entityManager.createNativeQuery(appSearchQuery.toString());
        if(null!=appNum && !appNum.isEmpty()) {
            query.setParameter(APP_NUM, appNum);
        }
        return query.getResultList();
    }

    @Override
    public List<Object[]> findAuthRepAddrDtl(String appNum) {
        StringBuilder appSearchQuery = new StringBuilder(FIND_AUTH_REP_ADDR_QUERY);
        Query query = entityManager.createNativeQuery(appSearchQuery.toString());
        if(null!=appNum && !appNum.isEmpty()) {
            query.setParameter(APP_NUM, appNum);
        }
        return query.getResultList();
    }

    @Override
    public List<Object[]> findWeekDayContactDtl(String appNum) {
        StringBuilder appSearchQuery = new StringBuilder(WEEK_DAY_QUERY);
        Query query = entityManager.createNativeQuery(appSearchQuery.toString());
        if(null!=appNum && !appNum.isEmpty()) {
            query.setParameter(APP_NUM, appNum);
        }
        return query.getResultList();
    }

    @Override
    public List<Object[]> findPhnDtl(String appNum) {
        Query query = entityManager.createNativeQuery(PHN_DTL_QUERY);
        if(null!=appNum && !appNum.isEmpty()) {
            query.setParameter(APP_NUM, appNum);
        }
        return query.getResultList();
    }

    @Override
    public List<Object[]> findEmailDtl(String appNum) {
        Query query = entityManager.createNativeQuery(EMAIL_DTL_QUERY);
        if(null!=appNum && !appNum.isEmpty()) {
            query.setParameter(APP_NUM, appNum);
        }
        return query.getResultList();
    }
}
