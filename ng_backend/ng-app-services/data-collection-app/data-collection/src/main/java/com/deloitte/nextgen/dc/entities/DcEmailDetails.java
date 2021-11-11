package com.deloitte.nextgen.dc.entities;

import com.deloitte.nextgen.dc.repository.DcEmailDetailsRepository;
import com.deloitte.nextgen.dc.repository.DcPhnDetailsRepository;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data // getter and setters
@Entity
@Table(name = "DC_EMAIL_DETAILS")
@EntityType(type= TypeEnum.ONE, customRepository = DcEmailDetailsRepository.class)
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DcEmailDetails extends TypeOneBaseEntity<Long> {
    @Id
    @Column(name="EMAIL_SEQ_NUM")
    private Long emailSeqNum;
    private Long authrepSeqNum;
    private String emailTypeCd;
    private String email;
    private String emailComments;
    private String emailSrcTyp;

}
