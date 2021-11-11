package com.deloitte.nextgen.dto.entities;

import com.deloitte.nextgen.entities.MailingAddress;
import com.deloitte.nextgen.entities.NoticeRequestStatus;
import lombok.Data;

@Data
public class NoticeRequestMailingAddress {
    private NoticeRequestStatus noticeRequestStatus;
    private MailingAddress mailingAddress;
}
