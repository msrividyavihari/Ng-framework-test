package com.deloitte.nextgen.framework.web.auditor;


import com.deloitte.nextgen.framework.commons.exceptions.NgUserInfoNotFoundException;
import com.deloitte.nextgen.framework.persistence.annotations.Auditor;
import com.deloitte.nextgen.framework.persistence.internal.service.spi.AuditorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
@Auditor
public class AuditorInfoImpl implements AuditorInfo {

    @Override
    public Optional<String> getAuditor() throws NgUserInfoNotFoundException {

        Optional<String> auditorInfo;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userName = auth == null ? "system" : auth.getName();
        if (userName != null && !userName.isEmpty()) {
            auditorInfo = Optional.of(userName);
        } else {
            throw new NgUserInfoNotFoundException(404);
        }
        return auditorInfo;
    }
}
