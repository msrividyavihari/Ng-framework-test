package com.deloitte.nextgen.appreg.web.controllers;



import com.deloitte.nextgen.framework.persistence.annotations.Auditor;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.AuditorInfo;

import java.util.Optional;

/**
 * @author nishmehta on 06/11/2020 08:38 PM
 * @project ng-demo-app
 */
@Auditor
public class AuditorInfoImpl implements AuditorInfo {

    @Override
    public Optional<String> getAuditor() {

        return Optional.of("system");
    }
}
