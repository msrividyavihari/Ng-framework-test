package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.CoManualData;
import com.deloitte.nextgen.entities.FwDataElementList;
import com.deloitte.nextgen.entities.composite.CoManualDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoManualDataRepository extends JpaRepository<CoManualData, CoManualDataId> {

    String SELECT_SQL = "SELECT CO_REQ_SEQ ,SEQ_NUM ,FIELD_ORDER_NUM ,FIELD_CONTENT ,CREATE_USER_ID ,ARCHIVE_DT ,UNIQUE_TRANS_ID ,UPDATE_DT ,CREATE_DT ,UPDATE_USER_ID FROM CO_MANUAL_DATA";

    @Query(
            value= SELECT_SQL +" WHERE CO_REQ_SEQ = (:coReqSeq) AND SEQ_NUM = (:seqNum) AND FIELD_ORDER_NUM = (:fieldOrderNum)",nativeQuery = true)
    List<CoManualData>  findBySeqNumReqSeqAndFieldOrder(Long coReqSeq, Long seqNum , Long fieldOrderNum);

    List<CoManualData> findByCoReqSeqAndFieldOrderNum(Long coReqSeq, Long fieldOrderNum);
    List<CoManualData> findByCoReqSeq(Long coReqSeq);

}
