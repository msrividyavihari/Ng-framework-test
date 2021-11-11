package com.deloitte.nextgen.util;

//
//@Component
//public class QueryUtilCoSearchHistory {
//
//    String SELECT_SQL = "SELECT A.*, B.DIS_DOC_MSTR_SEQ_NUM from V_CO_REQUEST A, IN_DIS_DOC_MASTER B";
//    String WHERE_CLAUSE = " WHERE 1=1 AND A.T2_CO_REQ_SEQ = B.CO_REQ_SEQ AND (B.FILE_PATH IS NOT NULL OR B.DOC_ID IS NOT NULL)";
//
//    String SQL_1 = " AND A.HISTORY_SW = 'Y' AND A.PRINT_DT IS NOT NULL AND A.PENDING_TRIG_SW = 'N'" +
//            " AND (A.EFF_BEGIN_DT <= A.ORIG_PRINT_DT AND (A.EFF_END_DT >= A.ORIG_PRINT_DT OR A.EFF_END_DT IS NULL))";
//
//    String REQ_DT = " AND TRUNC(A.PRINT_DT) >= ";
//
//    String PRINT_DT = " AND TRUNC(A.PRINT_DT) <= ";
//
//    String CC_PROVIDER_CERT_START_DT = " AND TRUNC(A.CC_PROVIDER_CERT_START_DT)<= ";
//
//    String CC_PROVIDER_CERT_END_DT = " AND TRUNC(A.CC_PROVIDER_CERT_END_DT)>= ";
//
//    String CC_PROVIDER_ID = " AND A.CC_PROVIDER_ID = ";
//
//    String T1_DOCiD = " AND A.T1_DOC_ID LIKE ";
//
//    String PRINT_MODE = " AND A.PRINT_MODE LIKE ";
//
//    String PROGRAM_CD = " AND (A.PROGRAM_CD LIKE ";
//
//    String REST_SQL = " OR A.PROGRAM_CD IS NULL) ";
//    String ORDER_BY = " ORDER BY PRINT_DT DESC";
//
//
//    @SuppressWarnings("unchecked")
//    public String getSQLForCaseNum(Long caseNum,
//                                   String reqDt,
//                                   String printDt,
//                                   String ccProviderCertStartDt,
//                                   String ccProviderCertEndDt,
//                                   Long ccProviderId,
//                                   String t1DocId,
//                                   String printMode,
//                                   String programCd) {
//
//        String result = "";
//        result += SELECT_SQL + WHERE_CLAUSE + "AND A.CASE_NUM = " + caseNum;
//        result += getRestSQL(reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd);
//        return result;
//    }
//
//    public String getSQLForAppNum(String appNum,
//                                  String reqDt,
//                                  String printDt,
//                                  String ccProviderCertStartDt,
//                                  String ccProviderCertEndDt,
//                                  Long ccProviderId,
//                                  String t1DocId,
//                                  String printMode,
//                                  String programCd) {
//        String result = "";
//        result += SELECT_SQL + WHERE_CLAUSE + "AND A.APP_NUM = '" + appNum + "'";
//        result += getRestSQL(reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd);
//        return result;
//    }
//
//    public String getSQLForClientId(Long clientId,
//                                    String reqDt,
//                                    String printDt,
//                                    String ccProviderCertStartDt,
//                                    String ccProviderCertEndDt,
//                                    Long ccProviderId,
//                                    String t1DocId,
//                                    String printMode,
//                                    String programCd) {
//        String result = "";
//        result += SELECT_SQL + WHERE_CLAUSE + "AND A.INDV_ID = " + clientId;
//        result += getRestSQL(reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd);
//        return  result;
//
//    }
//
//    public String getSQLForWorkerName(String workerName,
//                                      String reqDt,
//                                      String printDt,
//                                      String ccProviderCertStartDt,
//                                      String ccProviderCertEndDt,
//                                      Long ccProviderId,
//                                      String t1DocId,
//                                      String printMode,
//                                      String programCd) {
//        String result = "";
//        result += SELECT_SQL + WHERE_CLAUSE + "AND A.T2_CREATE_USER_ID = '" + workerName + "'";
//        result += getRestSQL(reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd);
//        return result;
//
//    }
//
//    public String getSQLForWorkerId(Long workerId,
//                                    String reqDt,
//                                    String printDt,
//                                    String ccProviderCertStartDt,
//                                    String ccProviderCertEndDt,
//                                    Long ccProviderId,
//                                    String t1DocId,
//                                    String printMode,
//                                    String programCd) {
//
//        String result ="";
//        result += SELECT_SQL + WHERE_CLAUSE + "AND A.EMP_ID = " + workerId;
//        result += getRestSQL(reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd);
//        return result;
//    }
//
//    public String getRestSQL(String reqDt,
//                             String printDt,
//                             String ccProviderCertStartDt,
//                             String ccProviderCertEndDt,
//                             Long ccProviderId,
//                             String t1DocId,
//                             String printMode,
//                             String programCd) {
//
//        String result = "";
//        result += SQL_1;
//        if(reqDt != null) {
//            result += REQ_DT + "'" +reqDt + "'";
//        } else {
//            result += REQ_DT + null;
//        }
//
//        if(printDt != null) {
//            result += PRINT_DT +  "'" +  printDt +  "'";
//        } else {
//            result += PRINT_DT + null;
//        }
//
//        if(ccProviderCertStartDt != null) {
//            result += CC_PROVIDER_CERT_START_DT +  "'" + ccProviderCertStartDt +  "'";
//        }
//
//        if(ccProviderCertEndDt != null) {
//            result += CC_PROVIDER_CERT_END_DT +  "'" +  ccProviderCertEndDt +  "'";
//        }
//
//        if(ccProviderId != null && ccProviderId != 0 ) {
//            result += CC_PROVIDER_ID + ccProviderId;
//        }
//
//        if(t1DocId != null) {
//            result += T1_DOCiD +  "'" + t1DocId +  "'";
//        } else {
//            result += T1_DOCiD + null;
//        }
//
//        if(printMode != null) {
//            result += PRINT_MODE +  "'" +  printMode +  "'";
//        } else {
//            result += PRINT_MODE + null;
//        }
//
//        if(programCd != null) {
//            result += PROGRAM_CD +  "'" + programCd +  "'"  ;
//        } else {
//            result += PROGRAM_CD + null;
//        }
//
//        result += REST_SQL;
//        result += ORDER_BY;
//
//        return result;
//
//    }
//}




//Modified Code, but this code does not include IN_DIS_DOC_MASTER table usage

import org.springframework.stereotype.Component;

@Component
public class QueryUtilCoViewHistorySearch {

//    String SELECT_SQL = "SELECT A.* from V_CO_REQUEST A ";
    private final String SELECT_1 = " SELECT T1_DOC_ID, HISTORY_SEQ, DOC_NAME, T1_CREATE_USER_ID, " +
        " T1_UPDATE_USER_ID, T1_CREATE_DT, T1_UPDATE_DT, T1_UNIQUE_TRANS_ID, EFF_BEGIN_DT, " +
        " EFF_END_DT, HIST_NAV_IND,  GENERATE_MANUALLY_SW, T1_DOC_TYPE_CD, PRINT_MODE_CD, " +
        " FREEFORM_SW, STUFFER_SW, MASS_ENABLED_SW, REQUEST_CD, T1_ARCHIVE_DT, T2_CO_REQ_SEQ, " +
        " T2_DOC_ID, INDV_ID, ACTION_CD, REASON_CD_LIST, DRAFT_SW, LANGUAGE_CD, EMP_ID, GENERATE_DT, " +
        " MISC_PARMS, HISTORY_SW, PENDING_TRIG_SW, HST_PRINT_STRING ,T2_DOC_TYPE_CD, REQUEST_TYPE_CD, " +
        " PROGRAM_CD, T2_ARCHIVE_DT, ORIG_PRINT_DT, APPT_ID, OFFICE_NUM, EDG_NUM, BENEFIT_NUM, MANUALLY_GENERATED_SW," +
        " MASS_GENERATED_SW, ASSISTANCE_LIST, CASE_NUM, APP_NUM, LOG_ID, EDG_TRACE_ID, T2_CREATE_USER_ID, " +
        " T2_UPDATE_USER_ID, T2_UPDATE_DT, T2_UNIQUE_TRANS_ID, T2_CREATE_DT, T3_CO_REQ_SEQ, CO_DET_SEQ, " +
        " PRINT_DT, PRINT_MODE, REPRINT_SW, T3_CREATE_USER_ID, REQ_DT, T3_ARCHIVE_DT, CO_RPT_SEQ, T3_UNIQUE_TRANS_ID, " +
        " T3_UPDATE_DT, T3_CREATE_DT, T3_UPDATE_USER_ID, " +
        " MASS_MAILING_ID, PROVIDER_ID, CHIP_APP_NUM, CO_STATUS_SW, DIS_ID, EDBC_RUN_ID, " +
        " MED_INDV_ID, SPECIAL_NOTES, CC_PROVIDER_ID, CC_PROVIDER_CERT_START_DT, CC_PROVIDER_CERT_END_DT " +
        " from V_CO_REQUEST A  ";

    private final String SELECT_2 = " SELECT A.T1_DOC_ID, A.HISTORY_SEQ, A.DOC_NAME, A.T1_CREATE_USER_ID, " +
            " A.T1_UPDATE_USER_ID, A.T1_CREATE_DT, A.T1_UPDATE_DT, A.T1_UNIQUE_TRANS_ID, A.EFF_BEGIN_DT, " +
            " A.EFF_END_DT, A.HIST_NAV_IND, A.GENERATE_MANUALLY_SW, A.T1_DOC_TYPE_CD, A.PRINT_MODE_CD, " +
            " A.FREEFORM_SW, A.STUFFER_SW, A.MASS_ENABLED_SW, A.REQUEST_CD, A.T1_ARCHIVE_DT, A.T2_CO_REQ_SEQ, " +
            " A.T2_DOC_ID, A.INDV_ID, A.ACTION_CD, A.REASON_CD_LIST, A.DRAFT_SW, A.LANGUAGE_CD, A.EMP_ID, A.GENERATE_DT, " +
            " A.MISC_PARMS, A.HISTORY_SW, A.PENDING_TRIG_SW, A.HST_PRINT_STRING , A.T2_DOC_TYPE_CD, A.REQUEST_TYPE_CD, " +
            " A.PROGRAM_CD, A.T2_ARCHIVE_DT, A.ORIG_PRINT_DT, A.APPT_ID, A.OFFICE_NUM, A.EDG_NUM, A.BENEFIT_NUM, A.MANUALLY_GENERATED_SW," +
            " A.MASS_GENERATED_SW, A.ASSISTANCE_LIST, A.CASE_NUM, A.APP_NUM, A.LOG_ID, A.EDG_TRACE_ID, A.T2_CREATE_USER_ID, " +
            " A.T2_UPDATE_USER_ID, A.T2_UPDATE_DT, A.T2_UNIQUE_TRANS_ID, A.T2_CREATE_DT, A.T3_CO_REQ_SEQ, A.CO_DET_SEQ, " +
            " A.PRINT_DT, A.PRINT_MODE, A.REPRINT_SW, A.T3_CREATE_USER_ID, A.REQ_DT, A.T3_ARCHIVE_DT, A.CO_RPT_SEQ, A.T3_UNIQUE_TRANS_ID, " +
            " A.T3_UPDATE_DT, A.T3_CREATE_DT, A.T3_UPDATE_USER_ID, " +
            " A.MASS_MAILING_ID, A.PROVIDER_ID, A.CHIP_APP_NUM, A.CO_STATUS_SW, A.DIS_ID, A.EDBC_RUN_ID, " +
            " A.MED_INDV_ID, A.SPECIAL_NOTES, A.CC_PROVIDER_ID, A.CC_PROVIDER_CERT_START_DT, A.CC_PROVIDER_CERT_END_DT, " +
            " B.DIS_DOC_MSTR_SEQ_NUM from V_CO_REQUEST A, IN_DIS_DOC_MASTER B ";

    String WHERE_CLAUSE = " WHERE 1=1 AND A.T2_CO_REQ_SEQ = B.CO_REQ_SEQ AND (B.FILE_PATH IS NOT NULL OR B.DOC_ID IS NOT NULL)";
//    String WHERE_CLAUSE = " WHERE 1=1 ";

    String SQL_1 = " AND A.HISTORY_SW = 'Y' AND A.PRINT_DT IS NOT NULL AND A.PENDING_TRIG_SW = 'N'" +
            " AND (A.EFF_BEGIN_DT <= A.ORIG_PRINT_DT AND (A.EFF_END_DT >= A.ORIG_PRINT_DT OR A.EFF_END_DT IS NULL))";

    String REQ_DT = " AND TRUNC(A.PRINT_DT) >= ";

    String PRINT_DT = " AND TRUNC(A.PRINT_DT) <= ";

    String CC_PROVIDER_CERT_START_DT = " AND TRUNC(A.CC_PROVIDER_CERT_START_DT)<= ";

    String CC_PROVIDER_CERT_END_DT = " AND TRUNC(A.CC_PROVIDER_CERT_END_DT)>= ";

    String CC_PROVIDER_ID = " AND A.CC_PROVIDER_ID = ";

    String T1_DOCiD = " AND A.T1_DOC_ID LIKE ";

    String PRINT_MODE = " AND A.PRINT_MODE LIKE ";

    String PROGRAM_CD = " AND (A.PROGRAM_CD LIKE ";

    String REST_SQL = " OR A.PROGRAM_CD IS NULL) ";
    String ORDER_BY = " ORDER BY PRINT_DT DESC";



    public String getSQLForCaseNum(Long caseNum,
                                   String reqDt,
                                   String printDt,
                                   String ccProviderCertStartDt,
                                   String ccProviderCertEndDt,
                                   Long ccProviderId,
                                   String t1DocId,
                                   Character printMode,
                                   String programCd) {

        String result = "";
        result += SELECT_2 + WHERE_CLAUSE + " AND A.CASE_NUM = " + caseNum;
        result += getRestSQL(reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd);
        return result;
    }

    public String getSQLForAppNum(String appNum,
                                  String reqDt,
                                  String printDt,
                                  String ccProviderCertStartDt,
                                  String ccProviderCertEndDt,
                                  Long ccProviderId,
                                  String t1DocId,
                                  Character printMode,
                                  String programCd) {
        String result = "";
        result += SELECT_2 + WHERE_CLAUSE + " AND A.APP_NUM = '" + appNum + "'";
        result += getRestSQL(reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd);
        return result;
    }

    public String getSQLForClientId(Long clientId,
                                    String reqDt,
                                    String printDt,
                                    String ccProviderCertStartDt,
                                    String ccProviderCertEndDt,
                                    Long ccProviderId,
                                    String t1DocId,
                                    Character printMode,
                                    String programCd) {
        String result = "";
        result += SELECT_2+ WHERE_CLAUSE + " AND A.INDV_ID = " + clientId;
        result += getRestSQL(reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd);
        return  result;

    }

    public String getSQLForWorkerName(String workerName,
                                      String reqDt,
                                      String printDt,
                                      String ccProviderCertStartDt,
                                      String ccProviderCertEndDt,
                                      Long ccProviderId,
                                      String t1DocId,
                                      Character printMode,
                                      String programCd) {
        String result = "";
        result += SELECT_2  + WHERE_CLAUSE + " AND A.T2_CREATE_USER_ID = '" + workerName + "'";
        result += getRestSQL(reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd);
        return result;

    }

    public String getSQLForWorkerId(Long workerId,
                                    String reqDt,
                                    String printDt,
                                    String ccProviderCertStartDt,
                                    String ccProviderCertEndDt,
                                    Long ccProviderId,
                                    String t1DocId,
                                    Character printMode,
                                    String programCd) {

        String result ="";
        result += SELECT_2 + WHERE_CLAUSE + " AND A.EMP_ID = " + workerId;
        result += getRestSQL(reqDt, printDt, ccProviderCertStartDt, ccProviderCertEndDt, ccProviderId, t1DocId, printMode, programCd);
        return result;
    }

    public String getRestSQL(String reqDt,
                             String printDt,
                             String ccProviderCertStartDt,
                             String ccProviderCertEndDt,
                             Long ccProviderId,
                             String t1DocId,
                             Character printMode,
                             String programCd) {

        String str;
        StringBuilder result = new StringBuilder();
        result.append(SQL_1);

        if(reqDt != null) {
            result.append(REQ_DT + "'" +reqDt + "'");
        } else {
            result.append(REQ_DT + null);
        }

        if(printDt != null) {
            result.append(PRINT_DT +  "'" +  printDt +  "'");
        } else {
            result.append(PRINT_DT + null);
        }

        if(ccProviderCertStartDt != null) {
            result.append(CC_PROVIDER_CERT_START_DT +  "'" + ccProviderCertStartDt +  "'");
        }

        if(ccProviderCertEndDt != null) {
            result.append(CC_PROVIDER_CERT_END_DT +  "'" +  ccProviderCertEndDt +  "'");
        }

        if(ccProviderId != null && ccProviderId != 0 ) {
            result.append(CC_PROVIDER_ID + ccProviderId);
        }

        if (t1DocId == null) {
            t1DocId = CoConstants.EMPTY_STRING;
        }

        if ((t1DocId != null) && t1DocId.equals(CoConstants.SPACE))
            t1DocId = CoConstants.EMPTY_STRING;

        str = t1DocId.trim() + "%";
        result.append(T1_DOCiD +  "'" + str +  "'");

        if(printMode == '0') printMode = ' ';
        str = ( printMode + "%" ).trim();
        result.append(PRINT_MODE +  "'" +  str +  "'");


        str = programCd.trim() + "%";
        result.append( PROGRAM_CD +  "'" + str +  "'");

        result.append(REST_SQL);
        result.append(ORDER_BY);

        return result.toString();

    }

    public String getSQLForReqSeqAndDetSeq(Long t2CoReqSeq, Long coDetSeq) {
        StringBuffer result = new StringBuffer();
        result.append(SELECT_1);
        result.append(" WHERE (A.T2_CO_REQ_SEQ = ").append(t2CoReqSeq).append(" AND A.CO_DET_SEQ = ").append(coDetSeq).append(" AND ");
        result.append(" (A.EFF_BEGIN_DT <= A.REQ_DT AND (A.EFF_END_DT >= A.REQ_DT ");
        result.append(" OR A.EFF_END_DT IS NULL))) ");
        return result.toString();
    }
}
