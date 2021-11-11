package com.deloitte.nextgen.framework.logging.utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * @author nishmehta on 01/02/2021 2:32 PM
 * @project ng-logging
 */

public class MaskingUtils {

    private MaskingUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final char MASK_CHARACTER = '*';
    private static Logger log;

    public static String maskCharsInBetween(String str, char maskingChar) {

        return maskString(str, 1, str.length() - 4, maskingChar);
    }

    public static String maskCharsInBetween(String str) {

        return maskString(str, 1, str.length() - 4, MASK_CHARACTER);
    }

    public static String maskString(String str, char maskingChar) {

        return maskString(str, 0, str.length(), maskingChar);
    }

    public static String maskString(String str) {

        return maskString(str, 0, str.length(), MASK_CHARACTER);
    }

    public static String maskEverythingExceptLastFourChars(String str, char maskingChar) {

        return maskString(str, 0, str.length() - 4, maskingChar);
    }

    public static String maskEverythingExceptLastFourChars(String str) {

        return maskString(str, 0, str.length() - 4, MASK_CHARACTER);
    }

    public static String maskFirstFourChars(String str, char maskingChar) {

        return maskString(str, 0, 4, maskingChar);
    }

    public static String maskFirstFourChars(String str) {

        return maskString(str, 0, 4, MASK_CHARACTER);
    }

    public static String maskStringWithExtra(String str, String extra) {

        return maskStringWithExtra(str, 0, str.length(), MASK_CHARACTER, extra);
    }

    private static String maskString(String strText, int start, int end, char maskChar) {

        if (strText == null || strText.equals(""))
            return "";

        if (start < 0)
            start = 0;

        if (end > strText.length())
            end = strText.length();

        if (start > end) {
            log.error("End index cannot be greater than start index. Cannot mask string");
        }

        int maskLength = end - start;

        if (maskLength == 0)
            return strText;

        String strMaskString = StringUtils.repeat(maskChar, maskLength);

        return StringUtils.overlay(strText, strMaskString, start, end);
    }

    private static String maskStringWithExtra(String strText, int start, int end, char maskChar, String extra) {

        return extra + " : " + maskString(strText, start, end, maskChar);
    }
}
