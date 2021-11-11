package com.deloitte.nextgen.framework.persistence.constants;

/**
 * @author nishmehta on 10/04/2021 11:11 AM
 * @project ng-framework
 */
public final class PersistenceConstants {

    public static final String UTILITY_CLASS = "Persistence Constant class";

    private PersistenceConstants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public final class Number {

        private Number() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

        public static final int ONE = 1;
        public static final int TWO = 2;
        public static final int THREE = 3;
        public static final int FOUR = 4;
        public static final int FIVE = 5;
        public static final int SIX = 6;
        public static final int SEVEN = 7;
        public static final int EIGHT = 8;
        public static final int NINE = 9;
        public static final int TEN = 10;
        public static final int FIFTEEN = 15;
        public static final int TWENTY = 20;
        public static final int TWENTY_FIVE = 25;
        public static final int THIRTY = 30;
        public static final int FIFTY = 50;
        public static final int HUNDRED = 100;
        public static final int FIVE_HUNDRED = 500;
        public static final int THOUSAND = 1000;
        public static final int MAX = 8000;
    }

    public final class Field {

        private Field() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

        public static final int USER_ID_LENGTH = Number.THIRTY;
        public static final int ADDRESS_LINE_ONE_LENGTH = Number.FIFTY;
        public static final int ADDRESS_LINE_TWO_LENGTH = Number.FIFTY;
    }

    public final class Sequence {

        private Sequence() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

        public static final int ALLOCATION_SIZE = Number.THIRTY;

    }

}
