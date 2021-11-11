package com.deloitte.nextgen.dc.entities;

import com.deloitte.nextgen.dc.repository.DcAuthRepRepository;
import com.deloitte.nextgen.dc.repository.DcPhnDetailsRepository;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data // getter and setters
@Entity
@Table(name = "DC_PHN_DETAILS")
@EntityType(type= TypeEnum.ONE, customRepository = DcPhnDetailsRepository.class)
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DcPhnDetails extends TypeOneBaseEntity<Long> {
    @Id
    @Column(name="PHN_SEQ_NUM")
    private Long phnSeqNum;
    private String phnTypeCd;
    private String phnNum;
    private String phnComments;
    private Integer phoneExtn;
    private String phoneSrcTyp;
    private Long authrepSeqNum;


}
