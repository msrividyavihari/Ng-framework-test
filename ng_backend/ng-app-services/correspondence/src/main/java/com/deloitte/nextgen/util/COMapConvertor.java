package com.deloitte.nextgen.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class COMapConvertor {

    private static final String INPUT_VALUES = " input values:";
    private static final String NOTICES_FORM_PAGE_ID = "Notices Form PAGE_ID:";


    public COMapConvertor() {
    }

    public static Map<String, String> getFormA0015Data(
            final Map<String, String> request) {
        final Map<String, String> formData = new HashMap<>();

        formData.put(CoConstants.CO_NOTICE_SALUTATION,
                request.get("noticeSalutation"));
        formData.put(CoConstants.CO_PURPOSE_OF_NOTICE,
                request.get("coPurposeOfNotice"));
        formData.put(CoConstants.CO_POLICY_MANUAL_REFERENCE,
                request.get("coPolicyManualReference"));
        log.debug(NOTICES_FORM_PAGE_ID
                + request.get(CoConstants.PAGE_ID) + INPUT_VALUES
                + formData.toString());
        return formData;
    }
}
