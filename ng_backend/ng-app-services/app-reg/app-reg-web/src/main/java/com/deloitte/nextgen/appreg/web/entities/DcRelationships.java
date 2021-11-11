package com.deloitte.nextgen.appreg.web.entities;

import com.deloitte.nextgen.appreg.web.entities.generated.DcRelationshipsPK;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // getter and setters
@Entity
@Table(name = DcRelationships.TABLE_NAME)
@EntityType(type= TypeEnum.ONE)
@NoArgsConstructor
@IdClass(DcRelationshipsPK.class)
public class DcRelationships extends TypeOneBaseEntity<DcRelationshipsPK> {
    @Transient
    public static final String TABLE_NAME = "DC_RELATIONSHIPS";

    @Id
    private Long indvId;
    @Id
    private Long refIndvId;
    @Id
    private java.sql.Timestamp effBeginDt;
    private java.sql.Timestamp effEndDt;
    private String relationshipTypeCd;
    private Character preparesAndPurchasesSw;
    private Character taxDependentSw;
    private String relationshipVrfCd;
    private Character physAbleToPurchPrepSw;
    private java.sql.Timestamp reportDt;
    private java.sql.Timestamp discoveryDt;
    private Character timelySw;
    private Character providesSupportForSw;
    private java.sql.Timestamp verfReceivedDt;
    private Character completedSw;//custom field
    private Character caretakerSw;
    private String preparePurchaseVrfCd;
    private String ableToPurchPrepVrfCd;
    private Character indvUnderParentalControlSw;
    private String taxDependentVrfCd;
    private Character jointlyFilingSw;
    private String maternalPaternalCd;
}
