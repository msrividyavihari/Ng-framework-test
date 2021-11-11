package com.deloitte.nextgen.framework.commons;

/**
 * @author nishmehta on 15/03/2021 5:16 PM
 * @project ng-commons
 */
public class CorrelationIdHolder {

    private CorrelationIdHolder() {
        throw new IllegalStateException("Utility class");
    }

    private static final ThreadLocal<String> id = new ThreadLocal<>();

    public static void setId(String correlationId) {
        id.set(correlationId);
    }

    public static String getId() {
        return id.get();
    }

    public static void removeId() {
        id.remove();
    }
}
