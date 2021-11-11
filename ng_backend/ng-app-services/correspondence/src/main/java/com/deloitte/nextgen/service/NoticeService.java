package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.NoticeRequest;

import com.deloitte.nextgen.entities.MailingAddress;

import com.deloitte.nextgen.dto.entities.NoticeStatusRequest;
import com.deloitte.nextgen.dto.entities.NoticeStatusResp;
import com.deloitte.nextgen.entities.NoticeReportResp;

import com.deloitte.nextgen.entities.NoticeRequestStatus;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

public interface NoticeService {
    NoticeRequestStatus  saveNoticeDetails(NoticeRequest coNoticeDto);
    MailingAddress saveUpdatedMailingAdd(MailingAddress mailingAddress);
    NoticeRequestStatus updateNoticeDetails(NoticeRequest mailingAddress);
    List<NoticeRequestStatus> fetchHistoricalStatus(Long requestId);

    List<NoticeRequestStatus> recentRequestStatus(NoticeRequest noticeReq);
    MailingAddress saveMailingAdd(MailingAddress mailingAddress);

//    List<NoticeRequestStatus> recentRequestStatus(Long requestId, Long caseNum);
//    List<NoticeRequestStatus> fetchLogData(Long logRequestId);
    NoticeStatusResp fetchNoticeRequestStatus(NoticeStatusRequest dto) throws ParseException;
    List<NoticeReportResp> fetchTrackingReport(NoticeStatusRequest dto) throws ParseException;

}
