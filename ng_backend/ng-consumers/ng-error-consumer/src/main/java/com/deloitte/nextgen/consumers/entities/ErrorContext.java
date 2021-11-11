package com.deloitte.nextgen.consumers.entities;


import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@EntityType
public class ErrorContext extends TypeZeroBaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long referenceId;

	private String resourceUrl;

	private String serverName;

	private String serviceName;

	private String correlationId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "error_id")
	private ErrorLog errorLog;
}