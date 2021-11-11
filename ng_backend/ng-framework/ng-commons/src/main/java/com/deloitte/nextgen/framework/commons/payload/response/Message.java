package com.deloitte.nextgen.framework.commons.payload.response;

import com.deloitte.nextgen.framework.commons.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nishmehta on 27/03/2021 12:12 PM
 * @project ng-framework
 */
@Data
@NoArgsConstructor
public class Message {

    private int code;

    private String description;

    private MessageType type;

    private boolean notify;

    @JsonIgnore
    private String[] messageParameters;

    public Message(int messageCode) {
        this.code = messageCode;
    }

    public Message(int messageCode, String message) {
        this.code = messageCode;
        this.description = message;
    }
}

