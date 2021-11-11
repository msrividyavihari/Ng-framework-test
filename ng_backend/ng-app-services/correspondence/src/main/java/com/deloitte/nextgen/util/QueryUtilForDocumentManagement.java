package com.deloitte.nextgen.util;

import com.deloitte.nextgen.dto.entities.DocumentManagementSearchHistoryDTO;
import com.deloitte.nextgen.entities.InDisDocMaster;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
@Component
public class QueryUtilForDocumentManagement {

//    private final String SELECT = " SELECT * FROM IN_DIS_DOC_MASTER DOC";

    String SELECT_SQL = "SELECT DIS_DOC_MSTR_SEQ_NUM, TRANSACTION_ID, DOC_ID, DOC_TYPE, DOC_UPLOAD_TYPE, " +
            "ENTRY_DT, PROCESS_FLAG, CASE_NUM, INDV_ID, APP_NUM, INDV_SEQ_NUM,  TASK_NUM, CP_HISTORY_FLAG, " +
            "DELINK_IND, DIS_UPD_IND, SOURCE, UNIQUE_TRANS_ID, CREATE_USER_ID, CREATE_DT, UPDATE_USER_ID, " +
            "UPDATE_DT, HISTORY_SEQ, ARCHIVE_DT, PROGRAM, CO_REQ_SEQ, FILE_PATH, DATE_OF_RECEIPT, COMMENT_CD, " +
            "DOCUEDGE_DOCUMENT_ID FROM IN_DIS_DOC_MASTER DOC";

    private final String SELECT = " SELECT DOC.DIS_DOC_MSTR_SEQ_NUM, DOC.TRANSACTION_ID, DOC.DOC_ID, " +
            " DOC.DOC_TYPE, DOC.DOC_UPLOAD_TYPE, DOC.ENTRY_DT, DOC.PROCESS_FLAG, DOC.CASE_NUM, DOC.INDV_ID, " +
            " DOC.APP_NUM, DOC.INDV_SEQ_NUM, DOC.TASK_NUM, DOC.CP_HISTORY_FLAG, DOC.DELINK_IND, DOC.DIS_UPD_IND, " +
            " DOC.SOURCE, DOC.UNIQUE_TRANS_ID, DOC.CREATE_USER_ID, DOC.CREATE_DT, DOC.UPDATE_USER_ID, " +
            " DOC.UPDATE_DT, DOC.HISTORY_SEQ, DOC.ARCHIVE_DT, DOC.PROGRAM, DOC.CO_REQ_SEQ, DOC.FILE_PATH, " +
            " DOC.DATE_OF_RECEIPT, DOC.COMMENT_CD, DOC.DOCUEDGE_DOCUMENT_ID FROM IN_DIS_DOC_MASTER DOC ";

    private String WHERE_SQL = " WHERE 1=1";
    private static final String NO_IES_PDF= " AND (DOC.DELINK_IND ='N' OR  DOC.DELINK_IND IS NULL) " +
            " AND (DOC.FILE_PATH IS NOT NULL OR DOC.DOC_ID IS NOT NULL) AND DOC.DOC_TYPE <> 61 " +
            " AND DOC.DOC_TYPE NOT IN ( SELECT CODE FROM RT_DISDOCTYPE_MV WHERE DOCTYPESHORTFORM  " +
            " like 'FGG%'  OR DOCTYPESHORTFORM like 'NGG%' ) ";
    private static final String PAST_NINETY_DAYS= " AND DOC.ENTRY_DT >= SYSDATE - 90 ";


    public String getSQLForSearchByCase(DocumentManagementSearchHistoryDTO documentDto,
                                        boolean isShinesCcCase,
                                        boolean isIndvSearch,
                                        String indvListStr,
                                        boolean isCcCaseApp) {
        StringBuffer sql = new StringBuffer();

        sql.append(SELECT);
//        sql.append("SELECT * FROM IN_DIS_DOC_MASTER DOC ");
        sql.append(WHERE_SQL);
        sql.append(" AND  DOCUEDGE_DOCUMENT_ID IS NOT NULL ");
        sql.append(NO_IES_PDF);
        if(isCcCaseApp) {
            sql.append(PAST_NINETY_DAYS);
        }
        sql.append(StringUtils.isEmpty(documentDto.getDocType())?"":" AND DOC.DOC_TYPE ="
                + documentDto.getDocType());
        sql.append((documentDto.getDisDocMstrSeqNum()==null || documentDto.getDisDocMstrSeqNum()==0)?"":" AND DOC.DIS_DOC_MSTR_SEQ_NUM ='"
                + documentDto.getDisDocMstrSeqNum()+"'");

        if (isIndvSearch && !StringUtils.isEmpty(indvListStr)) {
            sql.append(" AND DOC.INDV_ID IN ("+indvListStr + ")");
        }else {

            if(isShinesCcCase){
                sql.append(" AND ( CASE_NUM = "+ documentDto.getCaseNum());
                sql.append(" OR (exists (SELECT 1 FROM AR_APPLICATION_FOR_AID AID WHERE AID.CASE_NUM = "
                        + documentDto.getCaseNum()+" and DOC.APP_NUM =  AID.APP_NUM  ) " +
                        "AND DOC.SOURCE = 'IESED')) ");
            }else{
                sql.append(documentDto.getCaseNum()==null?"":" AND DOC.CASE_NUM = "
                        + documentDto.getCaseNum());
            }

            sql.append(StringUtils.isEmpty(documentDto.getAppNum())?
                    "":" AND DOC.APP_NUM ='"+ documentDto.getAppNum().toUpperCase()+"'");
            sql.append((documentDto.getIndvId()==null)
                    ?"":" AND DOC.INDV_ID ="+ documentDto.getIndvId());
        }
        sql.append(StringUtils.isEmpty(documentDto.getTransactionId())?
                "":" AND DOC.TRANSACTION_ID ='"+ documentDto.getTransactionId().toUpperCase()+"'");
        if(documentDto.getEntryDt()!=null) {
            sql.append(" AND TRUNC(DOC.ENTRY_DT) >= '" + documentDto.getEntryDt() + "'");
        }
        if(documentDto.getCreateDate()!=null) {
            sql.append(" AND TRUNC(DOC.ENTRY_DT) <= '" + documentDto.getCreateDate() + "'");
        }
        sql.append(" ORDER BY DOC.DIS_DOC_MSTR_SEQ_NUM DESC ");
        return sql.toString();
    }

    public String getSQLforSearchByOther(DocumentManagementSearchHistoryDTO documentDto)
            throws FwApplicationException {

        String ssn = documentDto.getSsn();
        String firstName = documentDto.getFirstName();
        String lastName = documentDto.getLastName();
        String midName = documentDto.getMiddleName();
        String dob = documentDto.getDateOfBirth();
        String fromDt = documentDto.getBeginDate();
        String toDt = documentDto.getToDate();

        StringBuilder sql = new StringBuilder();
        StringBuilder receivedDateSql = new StringBuilder();

        sql.append("SELECT *  FROM ( ");
        StringBuilder dcIndv = new StringBuilder();
        dcIndv.append(" select DOC.* from DC_INDV DC,IN_DIS_DOC_MASTER DOC where DC.INDV_ID = DOC.INDV_ID ");
        dcIndv.append(NO_IES_PDF);
        //dcIndv.append(PAST_NINETY_DAYS);
        dcIndv.append(" AND DC.SSN = " + ssn);


        StringBuilder t1004 = new StringBuilder();
        t1004.append(" select DOC.* from T1004_APP_INDV T1004,IN_DIS_DOC_MASTER DOC where T1004.APP_NUM = DOC.APP_NUM " +
                "AND T1004.INDV_SEQ_NUM = DOC.INDV_SEQ_NUM ");
        t1004.append(NO_IES_PDF);
        //t1004.append(PAST_NINETY_DAYS);
        t1004.append(" AND T1004.SSN_NUM = " + ssn );


        if (StringUtils.isNotEmpty(firstName)) {
            dcIndv.append(" AND UPPER(DC.FIRST_NAME) = UPPER('" + firstName + "')");
            t1004.append(" AND UPPER(T1004.FST_NAM) = UPPER('" + firstName + "')");

        }
        if (StringUtils.isNotEmpty(midName)) {
            dcIndv.append(" AND UPPER(DC.MID_NAME) = UPPER('" + midName + "')");
            t1004.append(" AND UPPER(T1004.MID_INIT) = UPPER('" + midName + "')");
        }
        if (StringUtils.isNotEmpty(lastName)) {
            dcIndv.append(" AND UPPER(DC.LAST_NAME) = UPPER('" + lastName + "') ");
            t1004.append(" AND UPPER(T1004.LAST_NAM) = UPPER('" + lastName + "') ");
        }
        Timestamp date = getTimestamp(dob);
        if ( date != null) {
            dob = CoUtil.getDateForWhereClauseANSI(date);
            dcIndv.append(" AND trunc(DC.DOB_DT) = CAST('" + dob + "'  AS DATE) ");
            t1004.append(" AND trunc(T1004.BRTH_DT) = CAST('" + dob + "'  AS DATE) ");
        }
        date = getTimestamp(fromDt);
        if ( date != null) {
            fromDt = CoUtil.getDateForWhereClauseANSI(date);
            receivedDateSql
                    .append("  AND trunc(DOC.ENTRY_DT) >= CAST('" + fromDt + "'  AS DATE) ");
        }

        date = getTimestamp(toDt);
        if (date != null) {
            toDt = CoUtil.getDateForWhereClauseANSI(date);
            receivedDateSql
                    .append(" AND trunc(DOC.ENTRY_DT) <= CAST('" + toDt + "'  AS DATE) ");
        }

        sql.append(dcIndv.toString());
        sql.append(receivedDateSql);
        sql.append(" UNION ");
        sql.append(t1004.toString());
        sql.append(receivedDateSql);
        sql.append(" ) ");
        sql.append(" ORDER BY DIS_DOC_MSTR_SEQ_NUM DESC ");
        return sql.toString();
    }

    public String getSQLForFindDuplicate(InDisDocMaster inDisDocMaster) {
        StringBuffer sql = new StringBuffer();

        sql.append(SELECT);
        sql.append(" WHERE DOC.DOC_ID =  " + inDisDocMaster.getDocId());
        sql.append(" AND DOC.DOC_TYPE = '" + inDisDocMaster.getDocType() + "' ");
        sql.append(" AND (DOC.DELINK_IND ='N' OR  DOC.DELINK_IND IS NULL) ");
        sql.append(" AND DOC.DOC_UPLOAD_TYPE =  '" + inDisDocMaster.getDocUploadType() + "' ");


        if (CoConstants.APP_TYPE_CHAR == inDisDocMaster
                .getDocUploadType()) {
            sql.append(" AND DOC.INDV_SEQ_NUM = " + inDisDocMaster.getIndvId());
            sql.append(" AND DOC.APP_NUM = '" + inDisDocMaster.getAppNum() + "' ");

        } else {
            sql.append(" AND DOC.INDV_ID = " + inDisDocMaster.getIndvId());
            sql.append(" AND DOC.CASE_NUM = " + inDisDocMaster.getCaseNum());
        }

        return sql.toString();
    }

    private Timestamp getTimestamp(String dateString)  {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        if (!"".equals(dateString) && dateString != null) {
            try{
                final java.util.Date date = dateFormat.parse(dateString);
                final java.sql.Timestamp timestamp = new java.sql.Timestamp(
                        date.getTime());
                return timestamp;
            } catch(ParseException p) {
                log.error(p.getMessage());
            }

        }
        return null;
    }
}
