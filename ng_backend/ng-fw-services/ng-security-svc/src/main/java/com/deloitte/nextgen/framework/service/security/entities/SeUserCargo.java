package com.deloitte.nextgen.framework.service.security.entities;

//import com.deloitte.nextgen.framework.persistence.entity.type.zero.TypeZeroBaseEntity;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import lombok.*;
import org.hibernate.annotations.RowId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data // getter and setters
@Entity
@Table(name = "SE_USER")
@RowId("ROWID")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@EntityType(type= TypeEnum.ONE)
public class SeUserCargo extends TypeOneBaseEntity {
	@Id
	private String userId;

	private String activeStatusCd;
	private String allowMultipleLoginSw;
	private String contractorSw;
	private Long defaultLocId;
	private Date effBeginDt;
	private Date effEndDt;
	private String email;
	private String firstName;
	private String globalSecAdmin;
	private Long historySeq;
	private String histNavInd;
	private String idCode;
	private Date lastLoginTime;
	private String lastName;
	private String midName;
	private String phNum;
	private String preferredName;
	private String prefixName;
	private String sufxName;
	private Long unsuccLoginAttempts;
	private String userGuid;

	private String userTypeCd;
	private String workerId;

}
