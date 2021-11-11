package com.deloitte.nextgen.appreg.web.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static final String YES_CAPITALIZED = "Yes";

    public static final String NO_CAPITALIZED = "No";

    public static final String YES_CHARACTER = "Y";

    public static final String NO_CHARACTER = "N";

    public static String fetchSSPAppNumbersURL;
    public static String fetchSSPApplicantsURL;
    public static String fetchSSPContactsURL;
    public static String fetchSSPAuthRepURL;
    public static String fetchSSPContactConflictsURL;
    public static String fetchSSPAuthRepConflictsURL;
    public static String fetchSSPSnapDetailsURL;
    public static String fetchSSPProgramURL;

        @Value("${ssp.fetchSSPAppNumbers-service.url}")
    public void setFetchSSPAppNumbersURL(String url){ fetchSSPAppNumbersURL=url; }

    @Value("${ssp.fetchSSPApplicants-service.url}")
    public void setFetchSSPApplicantsURL(String url) {
        fetchSSPApplicantsURL = url;
    }

    @Value("${ssp.fetchSSPContacts-service.url}")
    public void setFetchSSPContactsURL(String url) {
        fetchSSPContactsURL = url;
    }

    @Value("${ssp.fetchSSPAuthRep-service.url}")
    public void setFetchSSPAuthRepURL(String url) {
        fetchSSPAuthRepURL = url;
    }

    @Value("${ssp.fetchSSPContactConflicts-service.url}")
    public void setFetchSSPContactConflictsURL(String url) {
        fetchSSPContactConflictsURL = url;
    }

    @Value("${ssp.fetchSSPAuthRepConflicts-service.url}")
    public void setFetchSSPAuthRepConflictsURL(String url) {
        fetchSSPAuthRepConflictsURL = url;
    }
    @Value("${ssp.fetchSSPSnapDetails-service.url}")
    public void setFetchSSPSnapDetailsURL(String url) {
        fetchSSPSnapDetailsURL = url;
    }

    @Value("${ssp.fetchSSPProgram-service.url}")
    public void setFetchSSPProgramURL(String url) {
        fetchSSPProgramURL = url;
    }


}
