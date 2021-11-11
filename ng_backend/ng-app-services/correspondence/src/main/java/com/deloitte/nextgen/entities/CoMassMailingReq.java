package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.CoMassMailingRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="CO_MASS_MAILING_REQ")
@EntityType(type= TypeEnum.ONE, customRepository = CoMassMailingRepository.class)
@Data
@NoArgsConstructor
public class CoMassMailingReq extends TypeOneBaseEntity<Long> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CO_MASS_MAILING_REQ_1SQ")
    @Column(name = "MASS_MAILING_SEQ_NUM")
    private Long massMailingSeqNum;
    @Column(name = "NOTICE_TITLE")
    private String noticeTitle;
    @Column(name = "NOTICE_TXT")
    private String noticeTxt;
    @Column(name = "PROGRAM_LST")
    private String programLst;
    @Column(name = "SCHD_DT")
    @JsonFormat(pattern = "dd-MMM-yy")
    private java.sql.Timestamp schdDt;
    @Column(name = "JOB_PROCESSED_SW")
    private Character jobProcessedSw;
    @Column(name = "STD_TEXT_LST")
    private String stdTextLst;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "COUNTY_CD")
    private String countyCd;
    @Column(name = "LOGO_CD")
    private String logoCd;
    @Column(name = "LEGAL_CITES")
    private String legalCites;
}