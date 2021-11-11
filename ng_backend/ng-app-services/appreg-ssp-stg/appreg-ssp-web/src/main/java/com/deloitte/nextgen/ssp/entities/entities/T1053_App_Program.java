package com.deloitte.nextgen.ssp.entities.entities;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.ssp.entities.entities.generated.T1053_App_ProgramPK;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = T1053_App_Program.TABLE_NAME)
@EntityType(type= TypeEnum.ZERO)
@NoArgsConstructor
@IdClass(T1053_App_ProgramPK.class)
public class T1053_App_Program extends TypeZeroBaseEntity<T1053_App_ProgramPK> {

    @Transient
    public static final String TABLE_NAME = "T1053_APP_PROGRAM";

    @Id
    private String appNum;
    private Character request_status_ind;
    @Id
    private String program_cd;
}
