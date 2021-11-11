package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.CoManualDataId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.CoManualDataRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="CO_MANUAL_DATA")
@EntityType(type= TypeEnum.ZERO, customRepository = CoManualDataRepository.class)
@Data
@NoArgsConstructor
@IdClass(CoManualDataId.class)
public class CoManualData extends TypeZeroBaseEntity<Long> implements Serializable {
    @Id
    @Column(name = "CO_REQ_SEQ")
    private Long coReqSeq;
    @Column(name = "FIELD_CONTENT")
    private String fieldContent;
    @Column(name = "FIELD_ORDER_NUM")
    private Long fieldOrderNum;
    @Id
    @Column(name = "SEQ_NUM")
    private Long seqNum;
}
