package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.CoManualFieldsId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.CoManualFieldsRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="CO_MANUAL_FIELDS")
@EntityType(type= TypeEnum.ZERO, customRepository = CoManualFieldsRepository.class)
@Data
@NoArgsConstructor
@IdClass(CoManualFieldsId.class)
@Embeddable
public class CoManualFields extends TypeZeroBaseEntity<String> implements Serializable {

    @Column(name = "DE_ELEMENT_ID")
    private Long deElementId;
    @Column(name = "DE_ELEMENT_TYPE")
    private String deElementType;
    @Id
    @Column(name = "DOC_ID")
    private String docId;
    @Column(name = "FIELD_ORDER_NUM")
    private Long fieldOrderNum;
    @Column(name = "REF_TABLE_NAME")
    private String refTableName;
    @Column(name = "REQUIRED_SW")
    private Character requiredSw;
    @Id
    @Column(name = "SEQ_NUM")
    private Long seqNum;
    @Id
    @Column(name = "TEMPLATE_ID")
    private String templateId;
    @Column(name = "X2_COORD")
    private String x2Coord;
    @Column(name = "X_COORD")
    private String xCoord;
    @Column(name = "Y2_COORD")
    private String y2Coord;
    @Column(name = "Y_COORD")
    private String yCoord;
}
