package com.deloitte.nextgen.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CoDocumentRecipient implements Serializable {
    private String recipientDocument;
    private List<CoRecipients> recipientCargosList;
    private String appNum;
    private long caseNum;
    private long coReqSeq;
    private String hohName;
    private boolean isMinor;
    private boolean isAuthRepFound;

    public List<CoRecipients> getRecipientCargosList() {
        if(recipientCargosList==null){
            recipientCargosList = new ArrayList<>();
        }
        return recipientCargosList;
    }
}
