package com.deloitte.nextgen.consumers.service;

import com.deloitte.nextgen.consumers.entities.ErrorContext;

/**
 * @author nishmehta on 13/03/2021 12:09 AM
 * @project ng-consumers
 */

public interface ErrorMessageService {

    void insert(ErrorContext ec);
}
