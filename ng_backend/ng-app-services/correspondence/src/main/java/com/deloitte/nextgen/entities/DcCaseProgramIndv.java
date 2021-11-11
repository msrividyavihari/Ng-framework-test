package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.DcCaseProgramIndvId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.DcCaseProgramIndvRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="DC_CASE_PROGRAM_INDV")
@EntityType(type= TypeEnum.ONE, customRepository = DcCaseProgramIndvRepository.class)
@Data
@NoArgsConstructor
@IdClass(DcCaseProgramIndvId.class)
public class DcCaseProgramIndv extends TypeOneBaseEntity<Long> implements Serializable {

    @Id
    @Column(name="CASE_NUM")
    private Long caseNum;
    @Column(name="INDV_ID")
    private Long indvId;
    @Column(name="PROG_CD")
    private String progCd;
    @Column(name="PRIOR_MEDICAID_CD")
    private String priorMedicaidCd;
    @Column(name="PRIOR_MA_SEQ_NUM")
    private Long priorMaSeqNum;
    @Column(name="AID_REQUEST_SW")
    private Character aidRequestSw;
    @Column(name="SEPARATE_FS_SW")
    private Character separateFsSw;
    @Column(name="EFF_BEGIN_DT")
    private java.sql.Timestamp effBeginDt;
    @Column(name="EFF_END_DT")
    private java.sql.Timestamp effEndDt;
    @Column(name="REQUEST_DT")
    private java.sql.Timestamp requestDt;
    @Column(name="TDH_SERVICES_IN_ELIG_MTH_SW")
    private Character tdhServicesInEligMthSw;
    @Column(name="CHIP_END_DT")
    private java.sql.Timestamp chipEndDt;
    @Column(name="PROCESS_ID")
    private String processId;
    @Column(name="INDV_SGND_REPAT_AGMT_SW")
    private Character indvSgndRepatAgmtSw;
    @Column(name="MA_DCH_APPROVAL_DT")
    private java.sql.Timestamp maDchApprovalDt;
    @Column(name="MA_DCH_APPROVAL_TYPE_CD")
    private String maDchApprovalTypeCd;
    @Column(name="DCH_APPROVED_MA_ELIG_SW")
    private Character dchApprovedMaEligSw;
    @Column(name="MA_FORM_CD")
    private String maFormCd;
    @Column(name="PCK_PROG_SW")
    private Character pckProgSw;
    @Column(name="REQUESTING_GRG_SW")
    private String requestingGrgSw;

    //please do not deprecate this custom fields which is used for Retro Medicaid functionality for DC
    private String priorMonthOne;
    private String priorMonthTwo;
    private String priorMonthThree;

    @Column(name="REQUEST_PCK_DT")
    private java.sql.Timestamp requestPckDt;
    @Column(name="QRF_FORM_RECEIVED_DT")
    private java.sql.Timestamp qrfFormReceivedDt;
    @Column(name="QRF_INCOMPLETE_RSN_CD")
    private String qrfIncompleteRsnCd;
    @Column(name="QRF_DUE_DT")
    private java.sql.Timestamp qrfDueDt;
    @Column(name="BENEFIT_MISMATCH_SW")
    private Character benefitMismatchSw;

}
