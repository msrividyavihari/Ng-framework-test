package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.CoRequestRecipients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoRequestRecipientsRepository extends JpaRepository<CoRequestRecipients,Long> {

    String SELECT_SQL = "SELECT CO_RPT_SEQ , \n" +
            "RECIPIENT_TYPE , \n" +
            "RECIPIENT_DATA , \n" +
            "RECIPIENT_COMMENTS , \n" +
            "PRINT_TYPE , \n" +
            "PRINT_SW , \n" +
            "RPT_PRINT_STRING , \n" +
            "CREATE_USER_ID , \n" +
            "CREATE_DT , \n" +
            "UNIQUE_TRANS_ID , \n" +
            "UPDATE_DT , \n" +
            "ARCHIVE_DT , \n" +
            "UPDATE_USER_ID , \n" +
            "RECIPIENT_TYPE_ID , \n" +
            "LOCATION_PATH FROM CO_REQUEST_RECIPIENTS";


    List<CoRequestRecipients> findByCoReqSeqOrderByCoRptSeq(Long coReqSeq);
    List<CoRequestRecipients> findByCoReqSeqAndCoRptSeq(Long coReqSeq, Long coRptSeq);

    @Query(
            value= "FROM  com.deloitte.nextgen.entities.CoRequestRecipients WHERE " +
                    " coReqSeq = (:coReqSeq) ORDER BY coRptSeq ASC ")
    List<CoRequestRecipients> findByAllRecipients(long coReqSeq);


}
