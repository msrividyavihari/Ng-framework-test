package com.deloitte.nextgen.repository.Impl;

import com.deloitte.nextgen.dto.entities.NoticeStatusRequest;
import com.deloitte.nextgen.repository.NoticeRequestStatusRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class NoticeRequestStatusRepositoryImpl implements NoticeRequestStatusRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private TransactionTemplate transactionTemplate;

    private static final String BASE_QUERY =
            "select NOTICE_REQUEST_ID,LOG_REQUEST_ID,HOH_ID,TEMPLATE_ID,NOTICE_TYPE,MAIL_DATE," +
                    "WATER_MARK,SECURITY_FLAG,FORM_TITLE,CASE_NUM,CREATE_DT,UPDATE_DT,CREATE_USER_ID," +
                    "UPDATE_USER_ID,GO_GREEN,LANGUAGE,STATUS,AGENCY_NAME,AGENCY_ID,REQUEST_DATE," +
                    "ADDRESS_UPDATED,ARCHIVE_DT,UNIQUE_TRANS_ID,REQUEST_JSON,CLIENT_NAME," +
                    "AGENCY_CODE,SENT_TO_EDMS,EDELIVERY_STATUS,RETRY_PROCESS,EMAIL_DELIVERY_STATUS," +
                    "TEXT_NOTIFICATION_STATUS,REASON_FOR_FAILURE,EMAIL_ID,PHONE_NUMBER from notice_request_status where 1=1";



    @Override
    public List<Object[]> findByLogRequestId(NoticeStatusRequest dto) throws ParseException {
        StringBuilder appSearchQuery = new StringBuilder(BASE_QUERY);

        if(StringUtils.isNotBlank(dto.getAgency()) && dto.getAgency().equalsIgnoreCase("DTA")){
            appSearchQuery.append(" AND  AGENCY_ID = 'DTA' ");
        }

        if(StringUtils.isNotBlank(dto.getAgency()) && dto.getAgency().equalsIgnoreCase("MASSHEALTH")){
            appSearchQuery.append(" AND  AGENCY_ID = 'MASSHEALTH' ");
        }

        if(ObjectUtils.isNotEmpty(dto.getFromDate())){
            appSearchQuery.append(" AND  TRUNC (CREATE_DT) >= (:fromDate)");
        }

        if(ObjectUtils.isNotEmpty(dto.getToDate())){
            appSearchQuery.append(" AND   TRUNC (CREATE_DT) <= (:toDate)");
        }

        if(StringUtils.isNotBlank(dto.getStatus()) && dto.getStatus().equalsIgnoreCase("ALL")){
            appSearchQuery.append(" AND  STATUS in ('PR','FL') ");
        }

        if(StringUtils.isNotBlank(dto.getStatus()) && dto.getStatus().equalsIgnoreCase("SUCCESS")){
            appSearchQuery.append(" AND  STATUS in ('PR') ");
        }

        if(StringUtils.isNotBlank(dto.getStatus()) && dto.getStatus().equalsIgnoreCase("FAILURE")){
            appSearchQuery.append(" AND  STATUS in ('FL') ");
        }

        if(ObjectUtils.isNotEmpty(dto.getChannel())){
            if(dto.getChannel().contains("Mail") && dto.getChannel().size()==1){
                appSearchQuery.append(" AND GO_GREEN = 'N' ");
            }

            if(dto.getChannel().contains("Email") && dto.getChannel().size()==1){
                appSearchQuery.append(" AND (GO_GREEN = 'Y' AND EMAIL_DELIVERY_STATUS = 'Y') ");
            }
            if(dto.getChannel().contains("Text") && dto.getChannel().size()==1) {
                appSearchQuery.append(" AND (GO_GREEN = 'Y' AND TEXT_NOTIFICATION_STATUS = 'Y') ");
            }

            if(dto.getChannel().size()==3) {
                appSearchQuery.append(" AND (GO_GREEN = 'N' OR (GO_GREEN = 'Y' AND EMAIL_DELIVERY_STATUS = 'Y') OR (GO_GREEN = 'Y' AND TEXT_NOTIFICATION_STATUS = 'Y')) ");
            }

            if(dto.getChannel().size()==2 && dto.getChannel().contains("Mail") && dto.getChannel().contains("Email")) {
                appSearchQuery.append(" AND (GO_GREEN = 'N' OR (GO_GREEN = 'Y' AND EMAIL_DELIVERY_STATUS = 'Y')) ");
            }

            if(dto.getChannel().size()==2 && dto.getChannel().contains("Text") && dto.getChannel().contains("Email")) {
                appSearchQuery.append(" AND ((GO_GREEN = 'Y' AND EMAIL_DELIVERY_STATUS = 'Y') OR (GO_GREEN = 'Y' AND TEXT_NOTIFICATION_STATUS = 'Y')) ");
            }

            if(dto.getChannel().size()==2 && dto.getChannel().contains("Mail") && dto.getChannel().contains("Text")) {
                appSearchQuery.append(" AND (GO_GREEN = 'N' OR (GO_GREEN = 'Y' AND TEXT_NOTIFICATION_STATUS = 'Y')) ");
            }

        }

        Query query = entityManager.createNativeQuery(appSearchQuery.toString());
        if(ObjectUtils.isNotEmpty(dto.getFromDate())) {
            String date =new SimpleDateFormat("dd-MMM-yy").format(dto.getFromDate());
            //System.out.println("------------date--------"+date);
            query.setParameter("fromDate",date.toUpperCase());
        }

        if(ObjectUtils.isNotEmpty(dto.getToDate())) {
            String date =new SimpleDateFormat("dd-MMM-yy").format(dto.getToDate());
            query.setParameter("toDate",date.toUpperCase());
        }

        return query.getResultList();
    }
}
