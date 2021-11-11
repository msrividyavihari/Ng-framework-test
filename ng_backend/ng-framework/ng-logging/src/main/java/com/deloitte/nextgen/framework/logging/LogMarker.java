package com.deloitte.nextgen.framework.logging;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * @author nishmehta on 22/10/2020 3:18 PM
 * @project ng-logging
 */
public class LogMarker {

    private LogMarker() {
        throw new IllegalStateException("Utility class");
    }

    private static final String PERSISTENCE_MARKER = "PERSISTENCE";

    private static final String WEB_MARKER = "WEB";
    private static final String EXCEPTION_MARKER = "EXCEPTION";
    private static final String AUDIT_MARKER = "AUDIT";
    private static final String COMMONS_MARKER = "COMMONS";
    private static final String AUTOCONFIGURE_MARKER = "AUTOCONFIGURE";
    private static final String VALIDATOR_MARKER = "VALIDATOR";

    private static final String SECRET_LEVEL_MARKER = "SECRET";
    private static final String SECRET_LEVEL_ONE_MARKER = "SECRET_LEVEL_ONE";
    private static final String SECRET_LEVEL_TWO_MARKER = "SECRET_LEVEL_TWO";


    public static final Marker SECRET = MarkerFactory.getDetachedMarker(SECRET_LEVEL_MARKER);
    public static final Marker SECRET_LEVEL_ONE = MarkerFactory.getDetachedMarker(SECRET_LEVEL_ONE_MARKER);
    public static final Marker SECRET_LEVEL_TWO = MarkerFactory.getDetachedMarker(SECRET_LEVEL_TWO_MARKER);

    public static final Marker WEB  = MarkerFactory.getDetachedMarker(WEB_MARKER);
    public static final Marker EXCEPTION  = MarkerFactory.getDetachedMarker(EXCEPTION_MARKER);
    public static final Marker AUDIT  = MarkerFactory.getDetachedMarker(AUDIT_MARKER);
    public static final Marker PERSISTENCE  = MarkerFactory.getDetachedMarker(PERSISTENCE_MARKER);
    public static final Marker COMMONS  = MarkerFactory.getDetachedMarker(COMMONS_MARKER);
    public static final Marker AUTOCONFIGURE = MarkerFactory.getDetachedMarker(AUTOCONFIGURE_MARKER);
    public static final Marker VALIDATOR = MarkerFactory.getDetachedMarker(VALIDATOR_MARKER);

}


