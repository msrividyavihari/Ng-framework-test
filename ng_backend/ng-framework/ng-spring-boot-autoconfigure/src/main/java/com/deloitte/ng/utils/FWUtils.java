/*
 * Created on Jul 8, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.deloitte.ng.utils;

import com.deloitte.nextgen.framework.commons.exceptions.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * @author nishmehta on 01/02/2021 2:32 PM
 * @project ng-spring-boot-autoconfigure
 * @deprecated
 */
@Deprecated
public class FWUtils {

    private FWUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static final String MMU_DDL_YYYYL_WITH_SLASH_SEPARATOR = "MM/dd/yyyy";
    public static final String YYYYL_MMU_DDL_WITH_HYPHEN_SEPARATOR = "yyyy-MM-dd";
    public static final String YYYYL_MML_DDL_WITH_HYPHEN_SEPARATOR = "yyyy/mm/dd";
    public static final String MMU_DDL_YYYYL_WITH_HYPHEN_SEPARATOR = "MM-dd-yyyy";
    public static final String MML_DDL_YYYYL_WITH_NO_SEPARATOR = "mmddyyyy";
    public static final String YYYYL_MML_DDL_WITH_NO_SEPARATOR = "yyyymmdd";
    public static final String YYL_MML_DDL_WITH_NO_SEPARATOR = "yymmdd";
    public static final String MML_YYYYL_WITH_NO_SEPARATOR = "mmyyyy";
    public static final String DDL_MMU_YYYYL_WITH_HYPHEN_SEPARATOR = "dd-MM-yyyy";
    public static final String YYYYL_MMU_DDL_SPACE_HHL_MML_SSL_WITH_HYPHEN_SEPARATOR = "yyyy-MM-dd hh:mm:ss";

    /**
     * Field yyyyMMddSDFormat.
     */
    //private static SimpleDateFormat yyyyMMddSDFormat = null;

    //private static FwCalendar calendar = FwCalendar.getInstance();

    public static String getDate(Timestamp date, String format) {
        String strDate = null;
        String tempDate = null;
        if (format.equalsIgnoreCase(MMU_DDL_YYYYL_WITH_SLASH_SEPARATOR)) {
            strDate = new SimpleDateFormat(MMU_DDL_YYYYL_WITH_SLASH_SEPARATOR).format(date);
        } else if (format.equalsIgnoreCase(YYYYL_MMU_DDL_WITH_HYPHEN_SEPARATOR)) {
            strDate = new SimpleDateFormat(YYYYL_MMU_DDL_WITH_HYPHEN_SEPARATOR).format(date);
        } else if (format.equalsIgnoreCase(MMU_DDL_YYYYL_WITH_HYPHEN_SEPARATOR)) {
            strDate = new SimpleDateFormat(MMU_DDL_YYYYL_WITH_HYPHEN_SEPARATOR).format(date);
        } else if (format.equalsIgnoreCase(MML_DDL_YYYYL_WITH_NO_SEPARATOR)) {
            tempDate = new SimpleDateFormat(MMU_DDL_YYYYL_WITH_HYPHEN_SEPARATOR).format(date);
            strDate = trimValue(tempDate.substring(0, 10), "-");
        } else if (format.equalsIgnoreCase(YYYYL_MML_DDL_WITH_NO_SEPARATOR)) {
            strDate = trimValue(date.toString().substring(0, 10), "-");
        } else if (format.equalsIgnoreCase(YYL_MML_DDL_WITH_NO_SEPARATOR)) {
            strDate = trimValue(date.toString().substring(2, 10), "-");
        } else if (format.equalsIgnoreCase(MML_YYYYL_WITH_NO_SEPARATOR)) {
            tempDate = new SimpleDateFormat(DDL_MMU_YYYYL_WITH_HYPHEN_SEPARATOR).format(date);
            strDate = trimValue(tempDate.substring(3, 10), "-");
        } else {
            strDate = new SimpleDateFormat(YYYYL_MMU_DDL_SPACE_HHL_MML_SSL_WITH_HYPHEN_SEPARATOR).format(date);
        }
        return strDate;
    }

    public static String trimValue(String src, String delimiter) {
        StringTokenizer stk = new StringTokenizer(src, delimiter);
        StringBuilder sbf = new StringBuilder();
        while (stk.hasMoreTokens()) {
            sbf.append(stk.nextToken());
        }
        return sbf.toString();
    }


    public static Timestamp strDateToTimestamp(String strDate, String format) throws ParseException {
        SimpleDateFormat dateFormatter = null;
        Timestamp timeStampDate = null;
        String temp = null;
        if (MMU_DDL_YYYYL_WITH_HYPHEN_SEPARATOR.equalsIgnoreCase(format)) {
            dateFormatter = new SimpleDateFormat(MMU_DDL_YYYYL_WITH_HYPHEN_SEPARATOR);
            temp = strDate;
        } else if (YYYYL_MMU_DDL_WITH_HYPHEN_SEPARATOR.equalsIgnoreCase(format)) {
            dateFormatter = new SimpleDateFormat(YYYYL_MMU_DDL_WITH_HYPHEN_SEPARATOR);
            temp = strDate;
        } else if (YYYYL_MML_DDL_WITH_HYPHEN_SEPARATOR.equalsIgnoreCase(format)) {
            dateFormatter = new SimpleDateFormat(YYYYL_MML_DDL_WITH_HYPHEN_SEPARATOR);
            temp = strDate;
        } else if (YYYYL_MML_DDL_WITH_NO_SEPARATOR.equalsIgnoreCase(format)) {
            dateFormatter = new SimpleDateFormat(YYYYL_MMU_DDL_WITH_HYPHEN_SEPARATOR);
            temp = strDate.substring(0, 4) + "-" + strDate.substring(4, 6)
                    + "-" + strDate.substring(6, strDate.length());
        } else if (MML_DDL_YYYYL_WITH_NO_SEPARATOR.equalsIgnoreCase(format)) {
            dateFormatter = new SimpleDateFormat(MMU_DDL_YYYYL_WITH_SLASH_SEPARATOR);
            temp = strDate.substring(0, 2) + "/" + strDate.substring(2, 4)
                    + "/" + strDate.substring(4, strDate.length());
        } else {
            dateFormatter = new SimpleDateFormat(MMU_DDL_YYYYL_WITH_SLASH_SEPARATOR);
            temp = strDate;
        }
        timeStampDate = new Timestamp(dateFormatter.parse(temp).getTime());

        return timeStampDate;
    }

    /**
     * Method formatStringToTimestamp coverts a date in String Format 'MM/DD/YYYY' or MM-DD-YYYY to Timestamp
     *
     * @param aInDate - Date String
     * @return Timestamp - Date String converted to Timestamp
     * @throws Exception
     */
    public static Timestamp formatStringToTimestamp(String aInDate) throws ApplicationException {                    /*Sonar Fix by Nivedita*/
        if (aInDate == null || aInDate.trim().equals("")) {
            throw new ApplicationException(
                    "formatStringToTimestamp : Input Date supplied is Null");
        }
        StringTokenizer strT = new StringTokenizer(aInDate, "/- ");
        if (strT.countTokens() < 3) {
            throw new ApplicationException(
                    "Invalid Input Date supplied (expects MM/DD/YYYY or MM-DD-YYYY) ");
        }
        int month = Integer.parseInt(strT.nextToken()) - 1;
        int day = Integer.parseInt(strT.nextToken());
        int year = Integer.parseInt(strT.nextToken());
        Calendar cal = new GregorianCalendar(year, month, day);
//		Timestamp tst = new Timestamp(cal.getTime().getTime());
        return (new Timestamp(cal.getTime().getTime()));
    }

    /**
     * Method getNextDayTimeStamp creates a Time Stamp Object for the Next Day
     * of input timestamp.
     *
     * @param aTimestamp -
     *                   Timestamp whose next day timestamp needs to be determined
     * @return Timestamp - Next Day Timestamp
     * @throws Exception
     */
    public static Timestamp getNextDayTimeStamp(Timestamp aTimestamp) throws ApplicationException {                    /*Sonar Fix by Nivedita*/
        if (aTimestamp == null) {
            throw new ApplicationException("Null Cannot be converted to Next Day");
        }
        GregorianCalendar cal = timeStampToGregorian(aTimestamp);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return getDateAsTimeStamp(formatGregCalToDateString(cal), YYYYL_MML_DDL_WITH_NO_SEPARATOR);

    }

    /**
     * Method timeStampToGregorian returns the GregorianCalendar for a given
     * Timestamp Object.
     *
     * @param ts -
     *           Timestamp
     * @return GregorianCalendar - Gregorian calender format
     */
    public static GregorianCalendar timeStampToGregorian(Timestamp ts) {
        return new GregorianCalendar(ts.getYear() + 1900, ts
                .getMonth(), ts.getDate());
    }

    /**
     * Method formatGregCalToDateString converts a GregorianCalendar object to Date String "CCYYMMDD".
     *
     * @param aInDate - Date to be converted
     * @return String - Date String in yyyyMMdd format
     * @throws Exception
     */
    public static String formatGregCalToDateString(GregorianCalendar aInDate) {                                                                    /*Sonar Fix by Nivedita*/

        Date date = aInDate.getTime();
        return new SimpleDateFormat(YYYYL_MML_DDL_WITH_NO_SEPARATOR).format(date);
    }

    /**
     * Method getDateAsTimeStamp converts the Date string in given format to Timestamp.
     *
     * @param dateString - Date in String format
     * @param format     - Timestamp format required
     * @return Timestamp - Date converted into Timestamp in desired format
     */
    public static Timestamp getDateAsTimeStamp(String dateString, String format) {
        try {
            if (dateString != null && !dateString.trim().isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setLenient(false);
                return new Timestamp(sdf.parse(dateString)
                        .getTime());
            } else {
                return null;
            }

        } catch (Exception ex) {
            //logger.log(FwConstants.LOGGING_DEFAULT_CATEGORY ,ILog.ERROR, "Exception: " + ex.getMessage(),ex);
            return null;
        }
    }

    /**
     * This method is used to get the current date as time stamp object
     *
     * @return current date as time stamp
     */
    public static Timestamp getTimestampNow() {
        GregorianCalendar calendar = new GregorianCalendar();
//		Timestamp currTime = new Timestamp(calendar.getTime().getTime());
        return (new Timestamp(calendar.getTime().getTime()));
    }

    /*
     * Simply assigning the array as is for now.
     */
	/*
	//NOSONAR
	public static void copyArray(Object[] sourceArray, Object[] destinationArray){
		//System.arraycopy(sourceArray, 0, destinationArray, 0, sourceArray.length);
		destinationArray = sourceArray;
	}
	
	//NOSONAR
	public static void copyArray(Object[][] sourceArray, Object[][] destinationArray){
		//destinationArray = (Object[][])sourceArray.clone();
		destinationArray = sourceArray;		
	}
	*/

    //NOSONAR
    public static <T> T[] arrayCopy(T[] sourceArray) {
        return sourceArray;
    }

    //NOSONAR
    public static <T> T[][] arrayCopy(T[][] sourceArray) {
        return sourceArray;
    }

    /*
     * NG-4597: Remove all CRITICAL sonar issues
     * Code Start
     */
    public static byte[] arrayCopy(byte[] sourceArray) {
        return sourceArray;
    }

    public static byte[][] arrayCopy(byte[][] sourceArray) {
        return sourceArray;
    }

    public static char[] arrayCopy(char[] sourceArray) {
        return sourceArray;
    }

    public static char[][] arrayCopy(char[][] sourceArray) {
        return sourceArray;
    }

    public static long[] arrayCopy(long[] sourceArray) {
        return sourceArray;
    }

    public static long[][] arrayCopy(long[][] sourceArray) {
        return sourceArray;
    }
    /*
     * NG-4597: Remove all CRITICAL sonar issues
     * Code End
     */

    /**
     * Method to check if the given String is empty.
     *
     * @param field instance of {@link String}
     * @return {@link boolean} true if not empty and false if empty
     */
    public static boolean isNotEmpty(String field) {
        return (field != null && !field.equals("") && !field.equals(" "));
    }

    /**
     * Method to check if the given String is empty.If it is empty return and empty string
     *
     * @param field instance of {@link String}
     * @return {@link String} true if not empty and false if empty
     */
    public static String isEmpty(String field) {
        String returnValue = null;
        if (field != null && !field.equals("")) {
            returnValue = field;
        }
        return returnValue;
    }

    /***
     * this function is for getting the host IP addersss changes done as part of EE to IE migration chnages
     * @param request
     * @return
     */
    public static String getHostIPAddress(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");
        String headerClientIp = request.getHeader("Client-IP");
        String remoteAddress = request.getRemoteAddr();
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown"))
            ip = headerClientIp;

        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown"))
            ip = remoteAddress;

        return ip;

    }

    /**
     * Round the Decimal digits to 2 digits.
     * <p>
     * Creation date: (10/10/2010 12:00:00 AM)
     *
     * @param value
     * @return boolean
     */

    public static double roundTwoDigits(double value) {
        if (value > 0) {
            BigDecimal bd = BigDecimal.valueOf(value);                    /* Sonar Fix by Nivedita*/
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            value = bd.doubleValue();
        }
        return value;
    }

    public static Timestamp getDateAsTimeStampDefLenient(String dateString, String format) {
        try {
            if (dateString != null && !dateString.trim().isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return new Timestamp(sdf.parse(dateString)
                        .getTime());
            } else {
                return null;
            }

        } catch (Exception ex) {
            //logger.log(FwConstants.LOGGING_DEFAULT_CATEGORY ,ILog.ERROR, "Exception: " + ex.getMessage(),ex);
            return null;
        }
    }

}