package com.deloitte.nextgen.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    /**
     * DateFormatter constructor.
     */
    public DateFormatter() {
        super();
    }
    /**
     *Converts date to string.
     * @param aDate Date
     * @return String
     */
    public static String dateToString(Date aDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        if(null != aDate){
            return (sdf.format(aDate));
        }
        return CoConstants.EMPTY_STRING;
    }

    /**
     *Converts string with hyphens to date with format dd-MMM-yyyy .
     * @param aDateString String
     * @return String
     * @throws ParseException Exception
     */
    public static String stringHyphenToDateAnsi(String aDateString)
            throws ParseException {

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MMM-yyyy");

        Date newDate;
        newDate = sdf2.parse(aDateString);
        return sdf3.format(newDate);
    }

    /**
     * @deprecated - use getDateForWhereClauseANSI method.
     * Generates ANSI standard function for dates
     * @param obj Object
     * @return String
     * @throws ParseException Exception
     */
    @Deprecated
    public static String getDateForWhereClauseAnsi(Object obj) throws ParseException{
        if(obj!=null){
            if(obj instanceof Timestamp){
                String sqlDate = String.valueOf(obj).substring(0,10);
                return "CAST("+"'"+stringHyphenToDateAnsi(sqlDate)+"'"+" AS DATE)";
            }else{
                return "CAST("+"'"+ stringHyphenToDateAnsi(obj.toString()) +"'"+" AS DATE)";
            }
        }else{
            return null;
        }
    }
    public static Date getDateFromString(String aDateString)
            throws ParseException {
        Date newDate = null;
        if (aDateString.indexOf('/') != -1) {
            newDate = stringToDate(aDateString);
        } else if (aDateString.indexOf('-') != -1) {
            newDate = stringHyphenToDate(aDateString);
        } else {
            newDate = stringNoSlashToDate(aDateString);
        }
        return newDate;
    }

    public static Date stringToDate(String aDateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date newDate = null;
        newDate = sdf.parse(aDateString);
        return newDate;
    }

    private static Date stringHyphenToDate(String aDateString)
            throws ParseException {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        newDate = sdf2.parse(aDateString);
        return newDate;
    }

    private static Date stringNoSlashToDate(String aDateString)
            throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMddyyyy");
        Date newDate = null;
        newDate = sdf1.parse(aDateString);
        return newDate;
    }
}
