package com.deloitte.nextgen.framework.persistence.internal.service.spi;

import com.deloitte.nextgen.framework.commons.exceptions.NgUserInfoNotFoundException;

import java.util.Optional;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
public interface AuditorInfo {

    Optional<String> getAuditor() throws NgUserInfoNotFoundException;
}
