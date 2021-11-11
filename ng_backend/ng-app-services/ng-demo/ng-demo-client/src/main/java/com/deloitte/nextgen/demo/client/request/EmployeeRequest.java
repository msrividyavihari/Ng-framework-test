package com.deloitte.nextgen.demo.client.request;

import lombok.Data;

/**
 * @author nishmehta
 * @since 07/07/2021 6:41 PM
 */

@Data
public class EmployeeRequest {

    private Long id;

    private Long employeeId;

    private String employeeName;

    private String employeeTwoId;

}
