package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.CoRequestRecipientsId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.CoRequestRecipientsRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@Entity
@Table(name ="CO_REQUEST_RECIPIENTS")
@EntityType(type= TypeEnum.ZERO, customRepository = CoRequestRecipientsRepository.class)
@Data
@NoArgsConstructor
@IdClass(CoRequestRecipientsId.class)
public class CoRequestRecipients extends TypeZeroBaseEntity<Long> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "CO_REQUEST_RECIPIENTS_1SQ")
    @Column(name = "CO_RPT_SEQ")
    private Long coRptSeq;

    @Id
    @Column(name = "CO_REQ_SEQ")
    private Long coReqSeq;
    @Column(name = "RECIPIENT_TYPE")
    private String recipientType;
    @Column(name = "RECIPIENT_DATA")
    private String recipientData;
    @Column(name = "RECIPIENT_COMMENTS")
    private String recipientComments;
    @Column(name = "PRINT_TYPE")
    private Character printType;
    @Column(name = "PRINT_SW")
    private Character printSw;
    @Lob
    @Column(name = "RPT_PRINT_STRING")
    private Blob rptPrintString;
    @Column(name = "RECIPIENT_TYPE_ID")
    private Character recipientTypeId;
    @Column(name = "LOCATION_PATH")
    private String locationPath;
}
