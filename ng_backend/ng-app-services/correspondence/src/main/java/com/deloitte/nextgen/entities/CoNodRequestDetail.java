package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.CoNodRequestDetailId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.CoNodRequestDetailRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="CO_MANUAL_DATA")
@EntityType(type= TypeEnum.ZERO, customRepository = CoNodRequestDetailRepository.class)
@Data
@NoArgsConstructor
@IdClass(CoNodRequestDetailId.class)
public class CoNodRequestDetail extends TypeZeroBaseEntity<Long> implements Serializable {

    @Id
    @Column(name = "CASE_NUM")
    private Long caseNum;
    @Id
    @Column(name = "CO_NOD_SEQ")
    private Long coNodSeq;
    @Id
    @Column(name = "CO_REQ_SEQ")
    private Long coReqSeq;
    @Column(name = "DOC_ID")
    private String docId;
    @Column(name = "EDG_NUM")
    private Long edgNum;
    @Column(name = "EDG_TRACE_ID")
    private Long edgTraceId;
    @Column(name = "PAYMONTH")
    private String paymonth;
    @Column(name = "PROCESS_SW")
    private Character processSw;
    @Column(name = "SAS_AUTHORIZED_SW")
    private Character sasAuthorizedSw;
    @Column(name = "CANCELED_EDG_TRACE_ID")
    private Long canceledEdgTraceId;
}
