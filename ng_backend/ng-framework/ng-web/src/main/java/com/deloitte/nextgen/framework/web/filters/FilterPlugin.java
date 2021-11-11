package com.deloitte.nextgen.framework.web.filters;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;

/**
 * @author nishmehta
 * @since 1.0.0
 */

public interface FilterPlugin {

    default void init(FilterConfig config) {}

    void addToDigest(HttpServletRequest request);

}
