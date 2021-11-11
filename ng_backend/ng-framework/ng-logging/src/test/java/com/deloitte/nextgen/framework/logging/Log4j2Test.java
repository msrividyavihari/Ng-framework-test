package com.deloitte.nextgen.framework.logging;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author nishmehta on 22/10/2020 1:11 PM
 * @project ng-framework
 */

class Log4j2Test {

    private static final String CONFIG = "log4j2.xml";

    private Logger LOGGER = LoggerFactory.getLogger(SecurityMarkersTest.class);

    @BeforeEach
    public void setUp() {
        System.setProperty("log4j.configurationFile", CONFIG);
//        LOGGER = LoggerFactory.getLogger(Log4j2Test.class);
    }

    @AfterEach
    public void tearDown() {
        // Nothing to tear down
    }

    @Test
    void test() {

        assertNotNull(LOGGER);
        LOGGER.trace("This is a log statement");
        LOGGER.debug("There is a monster at the end of this block");
        LOGGER.debug("Hi {}!", "Deloitte");
        LOGGER.info("Monster activity detected");
        LOGGER.warn("This is your last warning");
        LOGGER.error("Monster!");
    }
}
