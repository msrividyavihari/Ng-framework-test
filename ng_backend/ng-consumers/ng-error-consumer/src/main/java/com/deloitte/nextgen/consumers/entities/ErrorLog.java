package com.deloitte.nextgen.consumers.entities;


import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author nishmehta on 09/03/2021 4:57 PM
 * @project ng-consumer
 */

@Data
@Entity
@EntityType
//@IdClass(ErrorLogPK.class)
public class ErrorLog extends TypeZeroBaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long errorId;

	@Column(length = 8000)
	private String exceptionDetail;

	private Timestamp exceptionDt;

	private char exceptionSeverity;

	private String exceptionSummary;

	@OneToOne(mappedBy = "errorLog")
	private ErrorContext errorContext;

}