package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.MoEmployeesId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.MoEmployeesRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="MO_EMPLOYEES")
@EntityType(type= TypeEnum.ONE, customRepository = MoEmployeesRepository.class)
@Data
@NoArgsConstructor
@IdClass(MoEmployeesId.class)
public class MoEmployees  extends TypeOneBaseEntity<Long> implements Serializable {

    @Id
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "EMP_NUM")
    private String empNum;
    @Column(name = "GENDER_CD")
    private Character genderCd;
    @Column(name = "PREFIX_NAME")
    private String prefixName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "MID_NAME")
    private String midName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "SUFX_NAME")
    private String sufxName;
    @Column(name = "JOB_TITLE_CD")
    private String jobTitleCd;
    @Column(name = "PRI_OFFICE_NUM")
    private Long priOfficeNum;
    @Column(name = "EXP_LVL_CD")
    private String expLvlCd;
    @Column(name = "SSN")
    private Long ssn;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PH_NUM")
    private String phNum;
    @Column(name = "FAX_NUM")
    private String faxNum;
    @Column(name = "UNIT_NUM")
    private Long unitNum;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "USER_ACTIVE_SW")
    private Character userActiveSw;
    @Column(name = "PHONE_NUM_EXT")
    private Long phoneNumExt;
    @Column(name = "EMPLOYEE_TYPE_CD")
    private String employeeTypeCd;
    @Column(name = "CASE_INCLUSION_SW")
    private Character caseInclusionSw;
    @Column(name = "LEGACY_WORKLOAD_NUM")
    private Long legacyWorkloadNum;
    @Column(name = "SPECIAL_ACMDTN_ASSIGN_ONLY_SW")
    private Character specialAcmdtnAssignOnlySw;
    @Column(name = "CASELOAD_PERCENTAGE_CD")
    private String caseloadPercentageCd;
    @Column(name = "SECOND_PARTY_REVIEW_CD")
    private String secondPartyReviewCd;
    @Column(name = "SECOND_PARTY_REVIEW_COUNTER")
    private Long secondPartyReviewCounter;
    @Column(name = "ACTIVE_STATUS_CD")
    private String activeStatusCd;
    @Column(name = "ENABLE_ALT_APPT_CO_LOC_SW")
    private Character enableAltApptCoLocSw;
    @Column(name = "PREFERRED_NAME")
    private String preferredName;
    @Column(name = "HOMEBASED_WORKER")
    private Character homebasedWorker;
    @Column(name = "EMP_TASK_ELIG")
    private Character empTaskElig;
    @Column(name = "EMP_CASE_ELIG")
    private Character empCaseElig;
    @Column(name = "USER_GUID")
    private String userGuid;
    @Column(name = "CC_ABILITY")
    private String ccAbility;
    @Column(name = "MAXS_WORKER_SW")
    private String maxsWorkerSw;
    @Column(name = "LOAD_ID")
    private String loadId;
    @Column(name = "RECURRANCE_TYPE")
    private String recurranceType;
    @Column(name = "WEEK_DAY")
    private String weekDay;
    @Column(name = "SECOND_PARTY_REVIEW_USER")
    private String secondPartyReviewUser;
    @Column(name = "USER_INC_REASON_CD")
    private String userIncReasonCd;
}
