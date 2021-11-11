package com.deloitte.nextgen.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class PrintDate extends Calendar implements Serializable, IPrintDate {

    public PrintDate(java.util.Date utilDate) {
        Calendar cal = getInstance();
        cal.setTime(utilDate);
        this.set(DATE, cal.get(DATE));
        this.set(DAY_OF_WEEK, cal.get(DAY_OF_WEEK));
        this.set(MONTH, cal.get(MONTH) + 1);
        this.set(YEAR, cal.get(YEAR));
        this.set(HOUR, cal.get(HOUR));
        this.set(MINUTE, cal.get(MINUTE));
        this.set(AM_PM, cal.get(AM_PM));
        this.setTime(utilDate);
    }

    /**
     * Constructor to get a PrintDate based on timestamp passed as an argument.
     *
     * @param timestamp java.sql.Timestamp
     */
    public PrintDate(java.sql.Timestamp timestamp) {
        this(new java.util.Date(timestamp.getTime()));
    }

    /**
     * Constructor to get PrintDate based on java.sql.Date passed as an argument
     *
     * @param sqlDate java.sql.Date
     */
    public PrintDate(java.sql.Date sqlDate) {
        this(new java.util.Date(sqlDate.getTime()));
    }

    /**
     * Method to retrieve Day of this PrintDate
     *
     * @return String
     */
    public String getDay() {

        String day = null;
        if (this.isSet(DAY_OF_WEEK)) {
            switch (this.get(DAY_OF_WEEK)) {
                case SUNDAY:
                    day = "Sunday";
                    break;
                case MONDAY:
                    day = "Monday";
                    break;
                case TUESDAY:
                    day = "Tuesday";
                    break;
                case WEDNESDAY:
                    day = "Wednesday";
                    break;
                case THURSDAY:
                    day = "Thursday";
                    break;
                case FRIDAY:
                    day = "Friday";
                    break;
                case SATURDAY:
                    day = "Saturday";
                    break;
            }
        }
        return day;

    }

    /**
     * Method to retrieve Month of this PrintDate
     *
     * @return String
     */
    public String getMonth() {

        String month = null;
        if (this.isSet(MONTH)) {
            switch (this.get(MONTH) - 1) {
                case JANUARY:
                    month = "JANUARY";
                    break;
                case FEBRUARY:
                    month = "FEBRUARY";
                    break;
                case MARCH:
                    month = "MARCH";
                    break;
                case APRIL:
                    month = "APRIL";
                    break;
                case MAY:
                    month = "MAY";
                    break;
                case JUNE:
                    month = "JUNE";
                    break;
                case JULY:
                    month = "JULY";
                    break;
                case AUGUST:
                    month = "AUGUST";
                    break;
                case SEPTEMBER:
                    month = "SEPTEMBER";
                    break;
                case OCTOBER:
                    month = "OCTOBER";
                    break;
                case NOVEMBER:
                    month = "NOVEMBER";
                    break;
                case DECEMBER:
                    month = "DECEMBER";
                    break;

            }
        }
        return month;

    }

    /**
     * Used to add any number of days to this date
     *
     * @param noOfDays int
     */

    public void addDays(int noOfDays) {
        long timeInMillis = this.getTimeInMillis();
        GregorianCalendar gcldr = new GregorianCalendar();
        gcldr.setTime(new java.util.Date(timeInMillis));
        gcldr.add(DATE, noOfDays);

        Calendar cal = getInstance();
        cal.setTime(gcldr.getTime());
        this.set(DATE, cal.get(DATE));
        this.set(DAY_OF_WEEK, cal.get(DAY_OF_WEEK));
        this.set(MONTH, cal.get(MONTH) + 1);
        this.set(YEAR, cal.get(YEAR));
        this.setTime(gcldr.getTime());
    }

    /**
     * Used to add any number of years to this date
     *
     * @param noOfYears int
     */
    public void addYear(int noOfYears) {
        long timeInMillis = this.getTimeInMillis();
        GregorianCalendar gcldr = new GregorianCalendar();
        gcldr.setTime(new java.util.Date(timeInMillis));
        gcldr.add(YEAR, noOfYears);

        Calendar cal = getInstance();
        cal.setTime(gcldr.getTime());
        this.set(DATE, cal.get(DATE));
        this.set(DAY_OF_WEEK, cal.get(DAY_OF_WEEK));
        this.set(MONTH, cal.get(MONTH) + 1);
        this.set(YEAR, cal.get(YEAR));
        this.setTime(gcldr.getTime());
    }

    /**
     * This would add any number of months from the object date and return another print date
     *
     * @param months int
     * @return PrintDate
     */
    public PrintDate addMonths(int months) {
        java.util.Date dt = this.getTime();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dt);
        gc.add(Calendar.MONTH, months);
        return (new PrintDate(gc.getTime()));
    }

    public int getLeastMaximum(int arg0) {
        return 0;
    }
    public int getGreatestMinimum(int arg0) {
        return 0;
    }
    public int getMaximum(int arg0) {
        return 0;
    }
    public int getMinimum(int arg0) {
        return 0;
    }
    public void roll(int arg0, boolean arg1) { }
    public void add(int arg0, int arg1) { }
    protected void computeFields() { }
    protected void computeTime() { }

    /**
     * Used to Subtract 12 months from current date
     * set computed date in object
     */
    public void sub12Month() {
        long timeInMillis = this.getTimeInMillis();
        GregorianCalendar gcldr = new GregorianCalendar();
        gcldr.setTime(new java.util.Date(timeInMillis));
        Calendar cal = getInstance();
        cal.setTime(gcldr.getTime());
        this.set(YEAR, cal.get(YEAR) - 1);
        this.setTime(gcldr.getTime());
    }

    /**
     * This would subtract any number of months from the object date and return another print date
     *
     * @param months int
     * @return PrintDate
     */
    public PrintDate subtractMonths(int months) {
        return addMonths(-1 * months);
    }

    /**
     * creates an arraylist of printdates that are prior to object date
     *
     * @param noOfMonths int
     * @return ArrayList
     */
    public List<PrintDate> getPriorMonthDates(int noOfMonths) {
        if (noOfMonths < 0) {
            return null;
        }
        ArrayList<PrintDate> priorMonths = new ArrayList<>(noOfMonths);
        java.util.Date dt = this.getTime();
        GregorianCalendar gc = new GregorianCalendar();
        PrintDate pdt;
        for (int i = 0; i < noOfMonths; i++) {
            gc.setTime(dt);
            gc.add(Calendar.MONTH, -1);
            pdt = new PrintDate(gc.getTime());
            priorMonths.add(pdt);
            dt = gc.getTime();
        }
        return priorMonths;
    }

    /**
     * Returns the date in printable format as requested.
     *
     * @param format     int Allowed arguments are PrintDate.HH_MM, PrintDate.MM_DD_YY,
     *                   PrintDate.DAY_DATE_MONTH_YEAR, PrintDate.DATE_MONTH_YEAR and PrintDate.MONTH_YEAR
     * @param dayOfMonth int
     * @return String
     */
    public String getDateToPrint(int format, int dayOfMonth) {
        String printable = null;
        if (format == DATE_MAX_MONTH_YEAR) {
            if (dayOfMonth == 31) {
                printable =
                        dayOfMonth
                                + "st"
                                + " "
                                + this.getMonth()
                                + " "
                                + this.get(YEAR);
            } else {
                printable =
                        dayOfMonth
                                + "th"
                                + " "
                                + this.getMonth()
                                + " "
                                + this.get(YEAR);
            }
        }
        return printable;
    }
}