package com.deloitte.nextgen.demo.client.entities;

import com.deloitte.nextgen.demo.client.entities.generated.EmployeePK;
import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * @author nishmehta
 * @since 07/07/2021 6:41 PM
 */

@Data
@Entity
@Table(name = Employee.TABLE_NAME)
@IdClass(EmployeePK.class)
@EntityType(type = TypeEnum.ZERO)
public class Employee extends TypeZeroBaseEntity<EmployeePK> {

    @Transient
    public static final String TABLE_NAME = "EMPLOYEE";

    @Id
    private Long id;

    @Id
    private Long employeeId;

}
