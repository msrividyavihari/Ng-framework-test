package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.InDisDocMasterRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "IN_DIS_DOC_MASTER")
@EntityType(type= TypeEnum.ONE, customRepository = InDisDocMasterRepository.class)
@NoArgsConstructor
public class InDisDocMaster  extends TypeOneBaseEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "IN_DIS_DOC_MASTER_1SQ")
    @Column(name = "DIS_DOC_MSTR_SEQ_NUM")
    private Long disDocMstrSeqNum;
    @Column(name = "DOCUEDGE_DOCUMENT_ID")
    private String docuedgeDocumentId;
    @Column(name = "TRANSACTION_ID")
    private String transactionId;
    @Column(name = "DOC_ID")
    private Long docId;
    @Column(name = "DOC_TYPE")
    private String docType;
    @Column(name = "DOC_UPLOAD_TYPE")
    private Character docUploadType;
    @Column(name = "ENTRY_DT")
    @JsonFormat(pattern = "MM/dd/YY")
    private java.sql.Timestamp entryDt;
    @Column(name = "PROCESS_FLAG")
    private Character processFlag;
    @Column(name = "CASE_NUM")
    private Long caseNum;
    @Column(name = "INDV_ID")
    private Long indvId;
    @Column(name = "APP_NUM")
    private String appNum;
    @Column(name = "INDV_SEQ_NUM")
    private Long indvSeqNum;
    @Column(name = "TASK_NUM")
    private Long taskNum;
    @Column(name = "CP_HISTORY_FLAG")
    private Character cpHistoryFlag;
    @Column(name = "DELINK_IND")
    private Character delinkInd;
    @Column(name = "DIS_UPD_IND")
    private Character disUpdInd;
    @Column(name = "SOURCE")
    private String source;
    @Column(name = "PROGRAM")
    private String program;
    @Column(name = "CO_REQ_SEQ")
    private Long coReqSeq;
    @Column(name = "FILE_PATH")
    private String filePath;
    @Column(name = "COMMENT_CD")
    private String commentCd;
    @Column(name = "DATE_OF_RECEIPT")
    @JsonFormat(pattern = "MM/dd/YY")
    private java.sql.Timestamp dateOfReceipt;
}
