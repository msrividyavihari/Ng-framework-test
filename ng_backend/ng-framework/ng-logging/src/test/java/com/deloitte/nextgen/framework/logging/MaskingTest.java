package com.deloitte.nextgen.framework.logging;

import org.apache.logging.log4j.test.appender.ListAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author nishmehta on 23/10/2020 1:26 PM
 * @project ng-framework
 */
class MaskingTest {
    private static final String CONFIG = "log4j2.xml";

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityMarkersTest.class);

    private static final Long SSN = 123123L;



    ListAppender appender;

    @BeforeEach
    public void setUp() {
        //Nothing to setup
    }

    @AfterEach
    public void tearDown() {
        //Nothing to tear down
    }

    @Test
    void testRaw() {

        assertNotNull(LOGGER);

/*
        LOGGER.info(LogMarker.CONFIDENTIAL, "ssn=" + SSN);
        LogEvent failEvent = appender.getEvents().get(0);
        Message message = failEvent.getMessage();

        System.out.println("Formatted message: " + message.getFormattedMessage());
        assertTrue(message.getFormattedMessage().contains("ssn=" + SSN));
*/

    }
}
