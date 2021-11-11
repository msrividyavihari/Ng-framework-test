package com.deloitte.nextgen.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "DUAL")
@NoArgsConstructor
public class TransactionIdEntity implements Serializable {
    @Id
    private String coDisDocUploadTxnSeq;
}
