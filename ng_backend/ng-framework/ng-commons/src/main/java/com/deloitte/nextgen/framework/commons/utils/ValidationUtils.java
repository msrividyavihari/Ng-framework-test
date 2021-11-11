package com.deloitte.nextgen.framework.commons.utils;

/**
 * @author nishmehta on 22/06/2021 12:44 PM
 * @project ng-commons
 */
public class ValidationUtils {

    private ValidationUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isNull(Long number) {
        return number == null;
    }

    public static boolean isPositive(Long number) {
        return number > 0;
    }

    /**
     * This method will check whether given number is null and is positive but not zero.
     *
     * @param number
     * @return true | false
     */
    public static boolean isIdValid(Long number) {
        return isNull(number) && isPositive(number);
    }
}
