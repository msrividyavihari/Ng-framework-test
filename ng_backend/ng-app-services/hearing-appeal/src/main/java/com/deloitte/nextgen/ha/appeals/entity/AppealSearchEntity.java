package com.deloitte.nextgen.ha.appeals.entity;

import com.deloitte.nextgen.ha.common.dto.IndividualNameDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;
import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@XmlRootElement
@Data
@AllArgsConstructor
public class AppealSearchEntity {

    @EqualsAndHashCode.Include
    @NonNull
    @Id
    private BigInteger appealId;
    private Long ageInDays;
    private IndividualNameDto primaryAppellant;
    private BigInteger individualId;
    private BigInteger caseNum;
    private BigInteger docketId;
    @NonNull
    private String appealTypeCd;
    private String appealStatusCd;
    private LocalDate lastUpdatedDt;

}
