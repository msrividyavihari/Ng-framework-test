package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.CoRequestHistoryDetailId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.CoRequestHistoryDetailRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="CO_REQUEST_HISTORY_DETAIL")
@EntityType(type= TypeEnum.ZERO, customRepository = CoRequestHistoryDetailRepository.class)
@Data
@NoArgsConstructor
@IdClass(CoRequestHistoryDetailId.class)
public class CoRequestHistoryDetail extends TypeZeroBaseEntity<Long> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "CO_REQUEST_HISTORY_DETAIL_1SQ")
    @Column(name = "CO_DET_SEQ")
    private Long coDetSeq;

    @Id
    @Column(name = "CO_REQ_SEQ")
    private Long coReqSeq;

    @Column(name = "PRINT_DT")
    private Timestamp printDt;
    @Column(name = "PRINT_MODE")
    private Character printMode;
    @Column(name = "REPRINT_SW")
    private Character reprintSw;
    @Column(name = "REQ_DT")
    private Timestamp reqDt;
    @Column(name = "CO_RPT_SEQ")
    private Long coRptSeq;
    @Column(name = "ENVELOPE_DT")
    private Timestamp envelopeDt;
    @Column(name = "ENVELOPE_SW")
    private String envelopeSw;
}
