package com.deloitte.nextgen.dc.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data // getter and setters
@Entity
@Table(name = "DC_EMAIL_XREF")
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DcEmailXref {

    @Id
    @Column(name = "EMAIL_SEQ_NUM")
    private Long emailSeqNum;
    @Column(name = "EMAIL_SRC_ID")
    private Long emailSrcId;
    @Column(name = "EMAIL_SRC_TYPE_CD")
    private String emailSrcTypeCd;

}
