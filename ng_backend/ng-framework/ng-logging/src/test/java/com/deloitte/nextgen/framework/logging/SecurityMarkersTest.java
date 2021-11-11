package com.deloitte.nextgen.framework.logging;

import org.apache.logging.log4j.test.appender.ListAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author nishmehta on 22/10/2020 3:21 PM
 * @project ng-framework
 */
class SecurityMarkersTest {
    private static final String CONFIG = "log4j2.xml";

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityMarkersTest.class);

    ListAppender appender;

    @BeforeEach
    public void setUp() {
        // Nothing to setup
    }

    @AfterEach
    public void tearDown() {
        // Nothing to tead down
    }

    @Test
    void testRaw() {
        // create a new marker filter
        assertNotNull(LOGGER);
        RestrictedDataMarkerFilter mkt = RestrictedDataMarkerFilter.createFilter(false);
        mkt.start();

        assertTrue(mkt.isStarted());

        /*LOGGER.info("This statement has no markers");
        LogEvent nulEvent = appender.getEvents().get(0);
        assertEquals(Filter.Result.DENY, mkt.filter(nulEvent));


        LOGGER.info(LogMarker.CONFIDENTIAL, "This statement is confidential");
        LogEvent successEvent = appender.getEvents().get(1);
        assertEquals(Filter.Result.ACCEPT, mkt.filter(successEvent));

        LOGGER.info(LogMarker.SECRET, "This statement is confidential");
        successEvent = appender.getEvents().get(2);
        assertEquals(Filter.Result.DENY, mkt.filter(successEvent));*/

    }

}
