package com.deloitte.nextgen.demo.client.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

/**
 * @author nishmehta on 14/06/2021 6:49 PM
 * @project ng-demo
 */

@Data
@Builder
public class DcIndividualResponse {

    private Long personId;

    private String firstName;

    private String lastName;

    private String middleName;

    private Date effBeginDt;

    private Date effEndDt;

    private Long uniqueTransId;

    private Set<DcAddressResponse> addresses;

    private Set<DcEmailResponse> emailIds;

    private Set<DcPhoneResponse> phoneNumbers;
}
