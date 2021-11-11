package com.deloitte.nextgen.entities;


import com.deloitte.nextgen.entities.composite.DcCaseIndividualId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="DC_CASE_INDIVIDUAL")
@Data
@NoArgsConstructor
@IdClass(DcCaseIndividualId.class)
public class DcCaseIndividual  implements Serializable {

    @Id
    private Long indvId;
    @Id
    private Long caseNum;
    @Id
    @Column(name = "EFF_BEGIN_DT")
    private Timestamp effBeginDt;
    private Long historySeq;
    private char activeInCaseSw;
    private String createUserId;
    private String updateUserId;
    private Timestamp createDt;
    private Timestamp updateDt;
    private Long uniqueTransId;
    private Timestamp effEndDt;
    private char histNavInd;
    private char headOfHouseholdSw;
    private char employeeSw;
    private Timestamp archiveDt;

}
