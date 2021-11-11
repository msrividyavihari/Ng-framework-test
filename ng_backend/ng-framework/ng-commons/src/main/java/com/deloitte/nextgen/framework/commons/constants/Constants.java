package com.deloitte.nextgen.framework.commons.constants;


/**
 * @author nishmehta on 10/02/2021 2:17 PM
 * @project ng-framework
 */
public class Constants {

    public static final String UTILITY_CLASS = "Utility class";

    public static final String NOT_SUPPORTED = "Not Supported";

    public static final Integer DEFAULT_SEQ_CACHE_SIZE = 50;

    private Constants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static class Headers {
        public static final String CORRELATION_ID = "x-correlation-id";

        public static final String KAIROS_DATE = "x-kairos";

        private Headers() {
            throw new IllegalStateException(UTILITY_CLASS);
        }
    }


    public static class DateTimeFormat {

        /**
         * Date format yyyy-MM-dd
         * eg: 2021-01-31
         */
        public static String yyyyMMdd = "yyyy-MM-dd";
        /**
         * Date format MM-dd-yyyy
         * eg: 01-31-2021
         */
        public static String MMddyyyy = "MM-dd-yyyy";
        /**
         * Date format dd-MM-yyyy
         * eg: 01-31-2021
         */
        public static String ddMMyyyy = "dd-MM-yyyy";

        private DateTimeFormat() {
            throw new IllegalStateException(UTILITY_CLASS);
        }
    }

    public static class Identifier {
        private Identifier() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

        public static final int CASE_NUMBER = 10;
    }

    public static class Mask {

        public static final char CHARACTER = '*';

        private Mask() {
            throw new IllegalStateException(UTILITY_CLASS);
        }
    }

    public static class ContextType {

        public static final Character REQUEST = 'T';
        public static final Character RESPONSE = 'E';
        private ContextType() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

    }
}
