package com.deloitte.ng.constants;

/**
 * The class <code> IReferenceConstants </code> is used for Constants
 * to provided the details of reference table as begindate,
 * active status,end date, verison,
 * row and column.
 *
 * @author mukepatel on 20/06/2020 2:36 PM
 * @project ng-spring-boot-autoconfigure
 * @deprecated
 *
 */
@Deprecated
public final class IReferenceConstants {

    private IReferenceConstants() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Field BEGIN_DATE.
     * (value is ""effbegdate"")
     */
    public static final String BEGIN_DATE = "EFFBEGDATE";

    /**
     * Field END_DATE.
     * (value is ""effenddate"")
     */
    public static final String END_DATE = "EFFENDDATE";


    /**
     * Field DATA.
     * (value is ""data"")
     */
    public static final String DATA = "data";

    /**
     * Field S_DATA.
     * (value is ""sorteddata"")
     */
    public static final String S_DATA = "sorteddata";

    /**
     * Field US_DATA.
     * (value is ""unsorteddata"")
     */
    public static final String US_DATA = "unsorteddata";

    /**
     * Field CODE.
     * (value is ""CODE"")
     */
    public static final String CODE = "CODE";

    /**
     * Field DESC.
     * (value is ""DESCRIPTION"")
     */
    public static final String DESC = "DESCRIPTION";


}