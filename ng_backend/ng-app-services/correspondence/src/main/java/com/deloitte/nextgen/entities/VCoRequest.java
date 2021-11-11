package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.VCoRequestRepository;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "V_CO_REQUEST")
@EntityType(type= TypeEnum.ONE, customRepository = VCoRequestRepository.class)
@NoArgsConstructor
public class VCoRequest extends TypeOneBaseEntity<Long> implements Serializable {
    private Character actionCd;
    private String appNum;
    private Long apptId;
    private String assistanceList;
    private String benefitNum;
    private Long caseNum;
    private String chipAppNum;
    private Long coDetSeq;
    private String coMasterRid;
    @Id
    private String coRequestHistoryDetailRid;
    private String coRequestHistoryRid;
    private Long coRptSeq;
    private String docName;
    private Character draftSw;
    private Long edgNum;
    private Long edgTraceId;
    private Long empId;
    private Character freeformSw;
    private Timestamp generateDt;
    private Character generateManuallySw;
    private Character historySw;
    private String hstPrintString;
    private Long indvId;
    private String languageCd;
    private Long logId;
    private Character manuallyGeneratedSw;
    private Character massEnabledSw;
    private Character massGeneratedSw;
    private String miscParms;
    private Long officeNum;
    private Timestamp origPrintDt;
    private Character pendingTrigSw;
    private Timestamp printDt;
    private Character printMode;
    private Character printModeCd;
    private String programCd;
    private Long providerId;
    private String reasonCdList;
    private Character reprintSw;
    private Timestamp reqDt;
    private Character requestCd;
    private Character requestTypeCd;
    private Character stufferSw;
    private Timestamp t1ArchiveDt;
    private Timestamp t1CreateDt;
    private String t1CreateUserId;
    private String t1DocId;
    private Character t1DocTypeCd;
    private Long t1UniqueTransId;
    private Timestamp t1UpdateDt;
    private String t1UpdateUserId;
    private Timestamp t2ArchiveDt;
    private Long t2CoReqSeq;
    private Timestamp t2CreateDt;
    private String t2CreateUserId;
    private String t2DocId;
    private Character t2DocTypeCd;
    private Long t2UniqueTransId;
    private Timestamp t2UpdateDt;
    private String t2UpdateUserId;
    private Timestamp t3ArchiveDt;
    private Long t3CoReqSeq;
    private Timestamp t3CreateDt;
    private String t3CreateUserId;
    private Long t3UniqueTransId;
    private Timestamp t3UpdateDt;
    private String t3UpdateUserId;
    private Long massMailingId;
    private String caseAppNum;
    private Long provider;
    private Timestamp effBeginDt;
    private Timestamp effEndDt;
    private String recipientType;
    private Character coStatusSw;
    private String disId;
    private Long disDocMstrSeqNum;
    private String edbcRunId;
    private String specialNotes;
    private Long ccProviderId;
    private Timestamp ccProviderCertStartDt;
    private Timestamp ccProviderCertEndDt;
    private Character histNavInd;
    private String medIndvId;
}
