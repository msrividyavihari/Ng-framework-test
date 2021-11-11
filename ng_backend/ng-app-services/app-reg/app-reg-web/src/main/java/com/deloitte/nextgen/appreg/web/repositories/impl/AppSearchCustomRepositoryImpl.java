package com.deloitte.nextgen.appreg.web.repositories.impl;

import com.deloitte.nextgen.appreg.client.request.AppSearchRequest;
import com.deloitte.nextgen.appreg.web.repositories.AppSearchCustomRepository;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Repository
public class AppSearchCustomRepositoryImpl implements AppSearchCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private TransactionTemplate transactionTemplate;

    private static final String BASE_QUERY =
            "SELECT DISTINCT dcIndvt.first_name,dcIndvt.last_name,aid.app_recvd_dt,aid.app_mode_cd,aid.app_num,aid.application_status_cd \n" +
            "FROM AR_APPLICATION_FOR_AID aid \n" +
            "LEFT JOIN AR_APP_INDV appIndv ON aid.APP_NUM=appIndv.APP_NUM   \n" +
                    "LEFT JOIN AR_APP_INDV appIndvt ON appIndvt.APP_NUM=appIndv.APP_NUM and appindvt.head_of_household_sw='Y' \n"+
            "LEFT JOIN DC_INDV dcIndv ON dcIndv.INDV_ID=appIndv.INDV_ID LEFT JOIN DC_INDV dcIndvt ON dcIndvt.INDV_ID=appIndvt.INDV_ID  WHERE 1=1 ";

    private static final String BASE_QUERY_USING_VIEW =
            "SELECT DISTINCT var.first_name,var.last_name,var.app_recvd_dt,var.t1_app_num as app_num,var.application_status_cd \n" +
            "FROM V_AR_APPLICATION_INDV var WHERE 1=1 \n";

    @Override
    public List<Object[]> findApplications(@NonNull AppSearchRequest appSearchRequest) {
        StringBuilder appSearchQuery = new StringBuilder(BASE_QUERY);
        String appNum = appSearchRequest.getAppNum();
        Long ssn = appSearchRequest.getApplicantSSN();
        String firstName = appSearchRequest.getApplicantFirstName();
        String lastName = appSearchRequest.getApplicantLastName();

        if(StringUtils.isNotBlank(appNum)) {
            appSearchQuery.append(" AND aid.app_num = (:appNum) ");
        }
        if(0 != defaultIfNull(ssn, 0L)){
            if(String.valueOf(ssn).length()==9){
                appSearchQuery.append(" AND dcIndv.SSN = (:ssn) ");
            } else {
                appSearchQuery.append(" AND dcIndv.SSN LIKE :ssn ");
            }
        }
        if(StringUtils.isNotBlank(firstName)){
            appSearchQuery.append(" AND UPPER(dcIndv.FIRST_NAME) LIKE UPPER(:firstName) ");
        }
        if(StringUtils.isNotBlank(lastName)){
            appSearchQuery.append(" AND UPPER(dcIndv.LAST_NAME) LIKE UPPER(:lastName) ");
        }

        Query query = entityManager.createNativeQuery(appSearchQuery.toString());
        if(StringUtils.isNotBlank(appNum)) {
            query.setParameter("appNum",appNum);
        }
        if(0 != defaultIfNull(ssn, 0L)){
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
        if( StringUtils.isNotBlank(lastName)){
            lastName=lastName.replace("'","''");
            query.setParameter("lastName",lastName+"%");
        }
        return query.getResultList();
    }

}
