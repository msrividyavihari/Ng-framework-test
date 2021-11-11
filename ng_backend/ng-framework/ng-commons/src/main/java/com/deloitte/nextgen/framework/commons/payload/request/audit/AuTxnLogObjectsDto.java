package com.deloitte.nextgen.framework.commons.payload.request.audit;

import lombok.Data;

import java.util.Date;

@Data
public class AuTxnLogObjectsDto {


    private Long txnLogObjectId;

    private Date archiveDt;

    private String pageId;

    private Character viewMode;

    private Character updateMode;

    private Character persistence;

    private String createUserId;

    private String updatedUserId;

    private Date createDt;

    private Date updateDt;

    private String updateUserId;

    private Long uniqueTransId;
}
