package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.CoMasterId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.CoMasterRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="CO_MASTER")
@EntityType(type= TypeEnum.ONE, customRepository = CoMasterRepository.class)
@Data
@NoArgsConstructor
@IdClass(CoMasterId.class)
public class CoMaster extends TypeOneBaseEntity<String> implements Serializable{
    @Id
    @Column(name = "DOC_ID")
    private String docId;
    @Column(name = "DOC_NAME")
    private String docName;
    @Column(name = "DOC_TYPE_CD")
    private Character docTypeCd;
    @Column(name = "FREEFORM_SW")
    private Character freeformSw;
    @Column(name = "GENERATE_MANUALLY_SW")
    private Character generateManuallySw;
    @Column(name = "MASS_ENABLED_SW")
    private Character massEnabledSw;
    @Column(name = "PRINT_MODE_CD")
    private Character printModeCd;
    @Column(name = "REQUEST_CD")
    private Character requestCd;
    @Column(name = "STUFFER_SW")
    private Character stufferSw;
    @Id
    @Column(name = "EFF_BEGIN_DT")
    private Timestamp effBeginDt;
    @Column(name = "EFF_END_DT")
    private Timestamp effEndDt;
    @Column(name = "ENVELOPE_ORDER")
    private Long envelopeOrder;
}
