package com.deloitte.nextgen.demo.client.entities;

import com.deloitte.nextgen.demo.client.DemoConstants;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.framework.persistence.generators.ApplicationSequenceGenerator;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Data
@Entity
@Table(name = ArApp.TABLE_NAME)
@EntityType(type = TypeEnum.ONE)
public class ArApp extends TypeOneBaseEntity<String> {

    @Transient
    public static final String TABLE_NAME = "AR_APP";

    @Transient
    public static final String ArAppFKName = "arAppNum";

    @Transient
    private static final String ArAppMappedByName = "arApp";

    @Transient
    private static final String PK_SEQUENCE_GEN = TABLE_NAME + "_1SQ";

    @Id
    @Column(length = DemoConstants.ColumnLength.AR_APP_NUM)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PK_SEQUENCE_GEN)
    @GenericGenerator(
            name = PK_SEQUENCE_GEN,
            strategy = "com.deloitte.nextgen.framework.persistence.generators.ApplicationSequenceGenerator",
            parameters = {
                    @Parameter(name = ApplicationSequenceGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = ApplicationSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "T_"),
                    @Parameter(name = ApplicationSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
    private String arAppNum;

    @Valid
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = ArAppMappedByName, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ArAppIndividual> appIndividuals;

    public void addIndividual(ArAppIndividual arAppIndividual) {
        if (CollectionUtils.isEmpty(appIndividuals)) {
            appIndividuals = new HashSet<>();
        }
        arAppIndividual.setArApp(this);
        appIndividuals.add(arAppIndividual);
    }

    public void removeIndividual(ArAppIndividual arAppIndividual) {
        arAppIndividual.setArApp(null);
        appIndividuals.remove(arAppIndividual);
    }

}
