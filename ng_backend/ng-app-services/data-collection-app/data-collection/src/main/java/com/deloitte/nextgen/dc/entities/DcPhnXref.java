package com.deloitte.nextgen.dc.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data // getter and setters
@Entity
@Table(name = "DC_PHN_XREF")
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DcPhnXref {

    @Id
    @Column(name = "PHN_SEQ_NUM")
    private Long phnSeqNum;
    @Column(name = "PHN_SRC_ID")
    private Long phnSrcId;
    @Column(name = "PHN_SRC_TYPE_CD")
    private String phnSrcTypeCd;

}
