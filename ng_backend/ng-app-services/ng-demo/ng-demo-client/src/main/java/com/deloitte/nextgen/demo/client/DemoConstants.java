package com.deloitte.nextgen.demo.client;

import com.deloitte.nextgen.framework.persistence.constants.PersistenceConstants;

/**
 * @author nishmehta
 * @since 26/07/2021 4:41 PM
 */

public class DemoConstants {

    private DemoConstants(){
        throw new IllegalStateException("Utility class");
    }

    public static class EndpointNames {

        private EndpointNames(){
            throw new IllegalStateException("Utility class");
        }

        public static final String DC_ADDRESS = "dcAddress";
        public static final String DC_INDIVIDUAL = "dcIndividual";
        public static final String DC_PHONE = "dcPhone";
        public static final String DC_EMAIL = "dcEmail";
        public static final String EMPLOYEE = "employee";

    }

    public static class ColumnLength {

        private ColumnLength(){
            throw new IllegalStateException("Utility class");
        }

        public static final int AR_APP_NUM = PersistenceConstants.Number.FIFTEEN;

        public static final int FIRST_NAME = PersistenceConstants.Number.THIRTY;
        public static final int LAST_NAME = PersistenceConstants.Number.THIRTY;
        public static final int MIDDLE_NAME = PersistenceConstants.Number.THIRTY;

        public static final int ADDRESS_LINE_ONE = PersistenceConstants.Number.FIFTY;
        public static final int ADDRESS_LINE_TWO = PersistenceConstants.Number.FIFTY;
        public static final int ZIPCODE = PersistenceConstants.Number.TEN;
        public static final int ADDRESS_TYPE_CD = PersistenceConstants.Number.TWO;
        public static final int CONTACT_TYPE_CD = PersistenceConstants.Number.ONE;
        public static final int EMAIL = PersistenceConstants.Number.HUNDRED;

        public static final int PHONE = PersistenceConstants.Number.FIFTEEN;
        public static final int SSN = PersistenceConstants.Number.FIFTEEN;

    }

}
