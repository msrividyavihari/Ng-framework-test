package com.deloitte.nextgen.util;

import com.deloitte.nextgen.dto.entities.BatchNotice;
import com.deloitte.nextgen.dto.entities.NoticeCustomer;
import com.deloitte.nextgen.dto.entities.NoticeRequest;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.util.xsd.schema.notices.Address;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class CoUtil {

    public static String getDateForWhereClauseANSI(Object obj) throws FwApplicationException {
        if (obj == null) {
            return null;
        }
        try {
            String sqlDate = (obj instanceof Timestamp) ? String.valueOf(
                    obj).substring(0, 10) : obj.toString();
            return stringHyphenToDateAnsi(sqlDate) ;
        } catch (ParseException e) {
            throw new FwApplicationException(e.getMessage());
        }

    }
    public static Timestamp stringToTimestamp(Object date) throws FwApplicationException {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Timestamp time = null;
        try {
            Date d = format.parse((String)date);
            time = new Timestamp(d.getTime());
        } catch (ParseException e) {
            throw new FwApplicationException(e.getMessage());
        }
        return time;
    }

    public static String stringHyphenToDateAnsi(String sqlDate) throws ParseException {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MMM-yyyy");
        Date newDate = sdf2.parse(sqlDate);
        return sdf3.format(newDate);
    }

    public static Timestamp getCurrentDate(){
        Calendar calendar= Calendar.getInstance();
        Date dt = calendar.getTime();
        return new Timestamp(dt.getTime());
    }

    public static String getCurrentDateAsString(){
        String d = "";
        Calendar c = Calendar.getInstance();
        c.setTime(c.getTime());
        d += c.get(Calendar.YEAR) +  "-";
        d += c.get(Calendar.MONTH)+ 1 +  "-";
        d += c.get(Calendar.DATE);
        return d;
    }

    public static long calculateDOB(Timestamp DOB) {
        //expecting DOB in yyyy-mm-dd format eg: 2020-02-01

        //converting timestamp to string
        String dob = DOB.toString();
        int age = 0;
        LocalDateTime ldt = LocalDateTime.now();
        String curDate = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = sdf.parse(dob);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int date = c.get(Calendar.DATE);
            LocalDate l1 = LocalDate.of(year, month, date);
            LocalDate now1 = LocalDate.now();
            Period diff1 = Period.between(l1, now1);
            age = diff1.getYears();
        }catch (ParseException e) {
            return age;
        }
        return age;
    }


    public static <T extends Map> boolean isEmpty(T map) {
        return ((null == map) || (map.isEmpty()));
    }

    public static <T> boolean isEmpty(T[] inputArr) {
        return isArrayEmpty(inputArr);
    }

    public static <T> boolean isArrayEmpty(T[] inputArr) {
        return (null == inputArr || inputArr.length == 0);
    }

    public static boolean isNotEmpty(String obj) {
        return obj != null && !"".equals(obj.trim());
    }

    public static boolean isEmpty(String obj) {
        return obj == null || "".equals(obj.trim());
    }

    public static <T extends Collection> boolean isEmpty(T col) { return ((null == col) || (col.isEmpty())); }

    public static String dateToString(Date aDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        if(null != aDate){
            return (sdf.format(aDate));
        }
        return CoConstants.EMPTY_STRING;
    }


    public static void isEmptyLong(Long val, String str) throws Exception {
        if(val == null) {
            throw new Exception(str + " cannot be null");
        }
    }
    public static void isEmptyString(String obj, String str) throws Exception{
        if(isEmpty(obj)) {
            log.error(obj + " is empty/null");
            throw new Exception(str+ " cannot be empty/null");
        }
    }

    public static String noticeRequestToJson(NoticeRequest noticeRequest) {
        Gson gson = new Gson();
        Type gsonType = new TypeToken<NoticeRequest>(){}.getType();
        return gson.toJson(noticeRequest, gsonType);
    }

    public static NoticeRequest jsonToNoticeRequest(String json) {
        return new Gson().fromJson(json, NoticeRequest.class);
    }

    public static String noticeCustomerToJson(NoticeCustomer noticeCustomer) {
        return new Gson().toJson(noticeCustomer, NoticeCustomer.class);
    }

    public static String batchNoticeToJson(BatchNotice batch) {
        return new Gson().toJson(batch, BatchNotice.class);
    }

    public static BatchNotice jsonToBatchNotice(String str) {
        return new Gson().fromJson(str, BatchNotice.class);
    }

    public static NoticeCustomer jsonToNoticeCustomer(String json) {
        return new Gson().fromJson(json, NoticeCustomer.class);
    }
    
    public static Address jsonToAddress(String json) throws Exception{
//        if(isEmpty(json)) {
//            throw new Exception("Address is empty/null");
//        }
        return new Gson().fromJson(json, Address.class);
    }
    public static Long generateNoticeRequestId() {
        return ThreadLocalRandom.current().nextLong(9999999);
    }


    public static String getNoticeStatusValue(String status) {
        switch (status){
            case "RR" :
                return NoticeStatus.RR.getStatusVal();
            case "IR":
                return NoticeStatus.IR.getStatusVal();
            case "IA":
                return NoticeStatus.IA.getStatusVal();
            case "AU":
                return NoticeStatus.AU.getStatusVal();
            case "PP":
                return NoticeStatus.PP.getStatusVal();
            case "CF":
                return NoticeStatus.CF.getStatusVal();
            case "SU":
                return NoticeStatus.SU.getStatusVal();
            case "HD":
                return NoticeStatus.HD.getStatusVal();
            case "PO" :
                return NoticeStatus.PO.getStatusVal();
            case "LP":
                return NoticeStatus.LP.getStatusVal();
            case "PR":
                return NoticeStatus.PR.getStatusVal();
            case "FL" :
                return NoticeStatus.FL.getStatusVal();
            case "RM" :
                return NoticeStatus.RM.getStatusVal();
            default:
                throw new UnsupportedOperationException(status+" : Invalid status");
        }
    }



    public static String getDocumentName(String templateId) {
        switch (templateId){
            case "MA-APPDENY-001" :
                return DocumentName.MA_APPDENY_001.getStatusVal();

            case "MAH-CPN-001":
                return DocumentName.MAH_CPN_001.getStatusVal();

            case "MA-DOWNGRADE-001":
                return DocumentName.MA_DOWNGRADE_001.getStatusVal();

            case "MA-DTA-001":
                return DocumentName.MA_DTA_001.getStatusVal();

            case "MA-MANUAL-001":
                return DocumentName.MA_MANUAL_001.getStatusVal();

            default:
                throw new UnsupportedOperationException(templateId+" : Invalid templateId");
        }
    }


}
