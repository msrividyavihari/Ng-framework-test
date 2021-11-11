package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.dto.entities.NoticeRequest;

import com.deloitte.nextgen.entities.MailingAddress;

import com.deloitte.nextgen.dto.entities.NoticeStatusRequest;
import com.deloitte.nextgen.dto.entities.NoticeStatusResp;
import com.deloitte.nextgen.entities.NoticeReportResp;

import com.deloitte.nextgen.entities.NoticeRequestStatus;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author priya
 */

@RestController
@Slf4j
@RequestMapping("/api/correspondence/noticeRequest")
@CrossOrigin
public class NoticeRequestController {

    @Autowired
    NoticeService noticeService;

    @PostMapping (value = "/status-update")
    public ResponseEntity<NoticeRequestStatus> saveNoticeRequest(@RequestBody NoticeRequest dto){

        return new ResponseEntity<>(noticeService.saveNoticeDetails(dto), HttpStatus.OK);
    }

    @PutMapping(value = "/mailing-address-update-1")
    public ResponseEntity<MailingAddress> updateMailingAddress(@RequestBody MailingAddress mailingAddress) {
       return new ResponseEntity<>(noticeService.saveUpdatedMailingAdd(mailingAddress), HttpStatus.OK);
    }

    @PostMapping(value = "/mailing-address-update")
    public ResponseEntity<MailingAddress> saveMailingAddress(@RequestBody MailingAddress mailingAddress) {
        return new ResponseEntity<>(noticeService.saveMailingAdd(mailingAddress), HttpStatus.OK);
    }

    @PostMapping(value = "/fetch-historical-status")
    public ResponseEntity<ApiResponse<List<NoticeRequestStatus>>> fetchHistoricalStatus(@RequestBody NoticeRequest dto){
        List<NoticeRequestStatus> noticeReqStatusList = noticeService.fetchHistoricalStatus(dto.getMetaData().getRequestId());
        if(!noticeReqStatusList.isEmpty()) {
            return ApiResponse.success(100).data(noticeReqStatusList);
        } else {
            return ApiResponse.error(103, "No records found").notify(true).data(null);
        }
    }

    @PostMapping(value = "/latest-status")
    public ResponseEntity<ApiResponse<List<NoticeRequestStatus>>> recentRequestStatus(@RequestBody NoticeRequest noticeReq){
//        System.out.println("controller start date: " + );
        List<NoticeRequestStatus> noticeReqStatus = noticeService.recentRequestStatus(noticeReq);
        if(!noticeReqStatus.isEmpty()) {
            return ApiResponse.success(100).data(noticeReqStatus);
        } else {
            return ApiResponse.error(103, "No records found").notify(true).data(null);
        }
    }

    @PostMapping (value = "/update-request")
    public ResponseEntity<NoticeRequestStatus> updateNoticeRequest(@RequestBody NoticeRequest dto){

        return new ResponseEntity<>(noticeService.saveNoticeDetails(dto), HttpStatus.OK);
    }




    @PostMapping(value = "/fetch-status")
    public ResponseEntity<ApiResponse<NoticeStatusResp>> noticeRequestStatus(@RequestBody NoticeStatusRequest dto) throws ParseException {
        System.out.println("------------"+new Date()+"---------------");
            NoticeStatusResp noticeStatusList = noticeService.fetchNoticeRequestStatus(dto);
        return ApiResponse.success(100).data(noticeStatusList);
    }

    @PostMapping(value = "/fetch-tracking-report")
    public ResponseEntity<ApiResponse<List<NoticeReportResp>>> fetchTrackingReport(@RequestBody NoticeStatusRequest dto) throws ParseException {
        System.out.println("------Fetch Tracking Report---------------");
        List<NoticeReportResp> trackingReport = noticeService.fetchTrackingReport(dto);
        return ApiResponse.success(100).data(trackingReport);
    }

}
