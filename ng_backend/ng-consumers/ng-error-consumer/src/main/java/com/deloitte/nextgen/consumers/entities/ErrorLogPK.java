package com.deloitte.nextgen.consumers.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * @author nishmehta on 13/03/2021 12:26 PM
 * @project ng-consumers
 */

@Data
public class ErrorLogPK implements Serializable {

    private long referenceId;

    private long exceptionNum;
}
