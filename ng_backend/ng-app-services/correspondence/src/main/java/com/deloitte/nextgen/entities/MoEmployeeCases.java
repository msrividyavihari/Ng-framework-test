package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.entities.composite.MoEmployeeCasesId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.MoEmployeesCasesRepository;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="MO_EMPLOYEE_CASES")
@EntityType(type= TypeEnum.ONE, customRepository = MoEmployeesCasesRepository.class)
@Data
@NoArgsConstructor
@IdClass(MoEmployeeCasesId.class)
public class MoEmployeeCases extends TypeOneBaseEntity<Long> implements Serializable {

    @Id
    @Column(name = "CASE_NUM")
    private Long caseNum;
    @Column(name = "EDG_NUM")
    private Long edgNum;
    @Id
    @Column(name = "PROGRAM_CD")
    private String programCd;
    @Id
    @Column(name = "ASSIGN_BEGIN_DT")
    private Timestamp assignBeginDt;
    @Column(name = "EMP_ID")
    private Long empId;
    @Column(name = "EMP_CAT_CD")
    private String empCatCd;
    @Column(name = "ASSIGN_END_DT")
    private Timestamp assignEndDt;
    @Column(name = "TYPE_OF_ASSISTANCE_CD")
    private String typeOfAssistanceCd;
    @Id
    @Column(name = "SEQUENCE_NUM")
    private Long sequenceNum;
    @Column(name = "PROCESS_ID")
    private String processId;
    @Column(name = "OFFICE_NUM")
    private Long officeNum;
    @Column(name = "SOFT_LOCKING_SW")
    private String softLockingSw;
}
