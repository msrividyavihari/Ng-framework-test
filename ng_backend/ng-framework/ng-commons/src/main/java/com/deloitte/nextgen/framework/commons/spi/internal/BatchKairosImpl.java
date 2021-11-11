package com.deloitte.nextgen.framework.commons.spi.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BatchKairosImpl extends AbstractKairosClock {

    private final HttpServletRequest request;

    public BatchKairosImpl(HttpServletRequest request) {
        super(request);
        this.request = request;
    }
}
