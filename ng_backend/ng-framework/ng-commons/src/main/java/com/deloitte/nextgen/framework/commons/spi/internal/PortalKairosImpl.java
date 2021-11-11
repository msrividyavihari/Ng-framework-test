package com.deloitte.nextgen.framework.commons.spi.internal;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
public class PortalKairosImpl extends AbstractKairosClock {

    private final HttpServletRequest request;

    public PortalKairosImpl(HttpServletRequest request) {
        super(request);
        this.request = request;
    }
}
