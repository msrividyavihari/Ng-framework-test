package com.deloitte.nextgen.framework.commons.constants;

/**
 * @author nishmehta
 * @since 16/08/2021 2:52 PM
 */
public class PatternConstants {

    public static final String UTILITY_CLASS = "Utility class";

    private PatternConstants() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static class US {

        private US() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

        public static final String POSTAL_CODE = "^[0-9]{5}(?:-[0-9]{4})?$";
        /**
         * Rules of SSN
         * index 1-3 should not have 000 or 666
         * index 4-5 should not have 00
         * index 6-9 should not have 0000
         *
         * Pattern matches SSN with of below format.
         * 663 01 0003
         * 663-01-0003
         * 663010003
         * 663.01.0003
         *
         */
        public static final String SSN = "^((?!000|666)[0-8][0-9]{2})?[-.\\s]?((?!00)[0-9]{2})[-.\\s]?((?!0000)[0-9]{4})$";

        /**
         * Pattern matches phone number of below format.
         * 123-456-7890
         * 123.456.7890
         * (123)-456-7890
         * (123).456.7890
         * (123) 456 7890
         * (123)4567890
         * 1234567890
         */
        public static final String PHONE_NUMBER = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
    }

    public static class Identifier {
        private Identifier() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

        public static final String CASE_NUMBER = "^[\\d]{10}$";

        public static final String APPLICATION_NUMBER = "^T[\\d]{9}$";
    }

    public static class Phone {

        private Phone() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

        public static final String INTL_NUM = "^\\+(?:[0-9] ?){6,14}[0-9]$";
    }

    public static class Text {
        private Text() {
            throw new IllegalStateException(UTILITY_CLASS);
        }

        /**
         * Matches alphabets with upper and lower case
         */
        public static final String ALPHA = "^[A-Za-z]+$";

        /**
         * Pattern to match only uppercase alphabets
         */
        public static final String ALPHA_UPPER = "^[A-Z]+$";

        /**
         * Pattern to match only lower case alphabets
         */
        public static final String ALPHA_LOWER = "^[a-z]+$";

        /**
         * Pattern to match with alphabets having space
         */
        public static final String ALPHA_SPACE = "^[\\sA-Za-z]+$";

        /**
         * Pattern to match only numbers
         */
        public static final String NUMERIC = "^[0-9]+$";

        /**
         * Pattern to match with numbers having space
         */
        public static final String NUMERIC_SPACE = "^[\\s0-9]+$";

        /**
         * Pattern to match with alphabets and numbers
         */
        public static final String ALPHA_NUMERIC = "^[A-Za-z0-9]+$";

        /**
         * Pattern to match with alphabets, numbers having space
         */
        public static final String ALPHA_NUMERIC_SPACE = "^[\\sA-Za-z0-9]+$";

        /**
         * Pattern to match if string has any special characters
         */
        public static final String SPECIAL = "^[^\\sA-Za-z0-9]+$";

        /**
         * Pattern to match if string has alphabets, numbers, space and all special characters
         */
        public static final String ALPHA_NUMERIC_SPACE_SPECIAL = "^[\\sA-Za-z0-9[^A-Za-z0-9]]+$";

        /**
         * Pattern to match if string has alphabets, numbers, space and below mentioned special characters
         * NOTE: Special Chars accepted are '_','-','&', '#', ',', '.'
         */
        public static final String ALPHA_NUMERIC_SPACE_SPECIFIC_SPECIAL = "^[\\sA-Za-z0-9-&_#,.]+$";

    }

}
