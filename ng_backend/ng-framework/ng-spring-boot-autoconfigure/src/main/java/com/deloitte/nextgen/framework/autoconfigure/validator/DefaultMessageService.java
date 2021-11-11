package com.deloitte.nextgen.framework.autoconfigure.validator;

import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.validator.provider.MessageService;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
public class DefaultMessageService implements MessageService {

    private ReferenceTable referenceTable;

    public DefaultMessageService(ReferenceTable referenceTable) {
        this.referenceTable = referenceTable;
    }

    @Override
    public String getMessage(String code, Locale locale) {
        switch (code) {
            case "100":
                return "Message printed from service";
            default:
                return code;
                //throw new IllegalArgumentException("Invalid message code " + code);
        }
    }
}
