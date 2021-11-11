package com.deloitte.nextgen.demo.client.dto;

import lombok.Data;

import java.sql.Date;

/**
 * @author nishmehta on 14/06/2021 2:36 PM
 * @project ng-demo
 */

@Data
public class PersonDTO {

    private Long personId;

    private String firstName;

    private String lastName;

    private String middleName;

    private Date effBeginDt;

    private Date effEndDt;
}
