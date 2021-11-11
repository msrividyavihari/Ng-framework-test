package com.deloitte.nextgen.framework.persistence.Implementations;

import com.deloitte.nextgen.framework.commons.exceptions.NgUserInfoNotFoundException;
import com.deloitte.nextgen.framework.persistence.annotations.Auditor;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.AuditorInfo;

import java.util.Optional;

/**
 * @author rarathore on 30/09/2021 12:52 PM
 * @project ng-persistence
 */

@Auditor
public class AuditorInfoImpl implements AuditorInfo {

    @Override
    public Optional<String> getAuditor() throws NgUserInfoNotFoundException {

        Optional<String> auditorInfo;

        String userName = "rarathore";

        auditorInfo = Optional.of(userName);

        return auditorInfo;
    }
}
