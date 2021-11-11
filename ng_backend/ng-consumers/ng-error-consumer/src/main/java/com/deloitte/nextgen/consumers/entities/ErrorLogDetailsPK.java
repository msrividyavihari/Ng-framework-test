package com.deloitte.nextgen.consumers.entities;

import lombok.Data;

import java.io.Serializable;

/**
 * @author nishmehta on 13/03/2021 12:27 PM
 * @project ng-consumers
 */

@Data
public class ErrorLogDetailsPK implements Serializable {

    private String contextId;

    private long referenceId;

    private long contextNum;
}
