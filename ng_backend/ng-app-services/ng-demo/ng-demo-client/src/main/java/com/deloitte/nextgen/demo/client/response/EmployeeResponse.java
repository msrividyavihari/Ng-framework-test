package com.deloitte.nextgen.demo.client.response;

import lombok.Data;

/**
 * @author nishmehta
 * @since 07/07/2021 6:41 PM
 */

@Data
public class EmployeeResponse {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private String employeeTwoId;

    private Long uniqueTransId;

}
