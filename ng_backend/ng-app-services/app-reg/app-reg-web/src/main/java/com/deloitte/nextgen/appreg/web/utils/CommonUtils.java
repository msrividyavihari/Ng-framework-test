package com.deloitte.nextgen.appreg.web.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;

public interface CommonUtils {

    static int calculateAge(Timestamp object) {
        LocalDate today = LocalDate.now();                          //Today's date
        LocalDate birthday = object.toLocalDateTime().toLocalDate();  //Birth date
        Period p = Period.between(birthday, today);
        return p.getYears();
    }
}
