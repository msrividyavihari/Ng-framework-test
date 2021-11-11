package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.MailingAddressRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="MAILING_ADDRESS")
@EntityType(type= TypeEnum.ZERO, customRepository = MailingAddressRepository.class)
@NoArgsConstructor
@Data
public class MailingAddress extends TypeZeroBaseEntity<Long> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "MAILING_ADDRESS_1SQ")
    private Long mailingAddrId;
    private String origStreet1;
    private String origStreet2;
    private String origCity;
    private String origState;
    private Long origZip5;
    private Long origZip4;
    private String updStreet1;
    private String updStreet2;
    private String updCity;
    private String updState;
    private Long updZip4;
    private Long updZip5;
    private Long noticeRequestId;
}
