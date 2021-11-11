package com.deloitte.nextgen.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoRecipients implements Serializable {

    private static final Long serialVersionUID = 1L;
    /**
     * recipient type to identify type of recipient
     * CT --- client
     * AR --- authorized representative
     * NH --- nursing home provider
     */
    private String recipientType = null;
    private Character recipientTypeId;
    private String recipientId = null;

    /**
     * recipient data to have
     * indv_id for Client
     * name of Authorized Rep/ Nursing Home
     */
    private String recipientData = null;
    private Long coRptSeq;
    private Character printSw = 'N';
    private String hohName = null;
}
