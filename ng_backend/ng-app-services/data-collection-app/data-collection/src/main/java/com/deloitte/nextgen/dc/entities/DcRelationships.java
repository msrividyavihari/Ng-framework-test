package com.deloitte.nextgen.dc.entities;


import com.deloitte.nextgen.dc.entities.composite.DcRelationshipsCargoId;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.two.TypeTwoBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.dc.repository.DcRelationshipsRepository;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;

@Data // getter and setters
@Entity
@Table(name = "DC_RELATIONSHIPS")
@EntityType(type= TypeEnum.TWO, customRepository = DcRelationshipsRepository.class)
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@IdClass(DcRelationshipsCargoId.class)
public class DcRelationships extends TypeTwoBaseEntity<String> {
    @Id
    private Long indvId;
    @Id
    private Long refIndvId;
    private Date effBeginDt;
    private Date effEndDt;
    private String relationshipTypeCd;
    private Character preparesAndPurchasesSw;
    private Character taxDependentSw;
    private String relationshipVrfCd;
    private Character physAbleToPurchPrepSw;
    private Date reportDt;
    private Date discoveryDt;
    private Character timelySw;
    private Character providesSupportForSw;
    private Date verfReceivedDt;
    private Character caretakerSw;
    private String preparePurchaseVrfCd;
    private String ableToPurchPrepVrfCd;
    private Character indvUnderParentalControlSw;
    private String taxDependentVrfCd;
    private Character jointlyFilingSw;
    private String maternalPaternalCd;

}



