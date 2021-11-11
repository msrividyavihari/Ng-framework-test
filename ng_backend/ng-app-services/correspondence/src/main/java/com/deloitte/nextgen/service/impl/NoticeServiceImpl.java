package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.entities.MailingAdd;
import com.deloitte.nextgen.dto.entities.NoticeRequest;
import com.deloitte.nextgen.dto.entities.NoticeStatusRequest;
import com.deloitte.nextgen.dto.entities.NoticeStatusResp;
import com.deloitte.nextgen.entities.DashboardRespDto;
import com.deloitte.nextgen.entities.MailingAddress;
import com.deloitte.nextgen.entities.NoticeReportResp;
import com.deloitte.nextgen.entities.NoticeRequestStatus;
import com.deloitte.nextgen.repository.MailingAddressRepository;
import com.deloitte.nextgen.repository.NoticeDashboardRepository;
import com.deloitte.nextgen.repository.NoticeHistoryRepository;
import com.deloitte.nextgen.repository.NoticeRequestStatusRepository;
import com.deloitte.nextgen.service.NoticeService;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeHistoryRepository repo;

    @Autowired

    MailingAddressRepository mailingAddressRepository;

    @Autowired
    NoticeRequestStatusRepository noticeRepo;

    @Autowired
    NoticeDashboardRepository trackingRepo;


    @Override
    @Transactional
    public NoticeRequestStatus saveNoticeDetails(NoticeRequest coNoticeData) {
        NoticeRequestStatus xmlData = saveNoticeData(coNoticeData);
        System.out.println("xmlData: " + xmlData);
        return repo.save(xmlData);
    }

    @Override
    @Transactional
    public NoticeRequestStatus updateNoticeDetails(NoticeRequest dto) {
//         repo.updateByLogRequestId(dto.getMetaData().getLogRequestId());
//         return null;
        System.out.println("dto. log " + dto.getMetaData().getLogRequestId());
        NoticeRequestStatus xmlData = saveNoticeData(dto);
        System.out.println("xmlData: " + xmlData);
        return repo.save(xmlData);
    }

    @Override
    public List<NoticeRequestStatus> fetchHistoricalStatus(Long noticeRequestId){
        List<NoticeRequestStatus> noticeReqList = repo.findByNoticeRequestIdOrderByLogRequestIdDesc(noticeRequestId);
        return noticeReqList;
    }

    @PersistenceContext
    EntityManager manager;

    @Transactional
    public MailingAddress saveMailingAdd(MailingAddress mailingAddress) {
        System.out.println("mailingAddress: " + mailingAddress);
        return mailingAddressRepository.save(mailingAddress);
    }

    @Override
    @Transactional
    public MailingAddress saveUpdatedMailingAdd(MailingAddress mailingAddress) {
        System.out.println("mailingAddress: " + mailingAddress);
        return mailingAddressRepository.save(mailingAddress);
    }

    private MailingAddress getMailingAddress(MailingAdd mailingAdd) {
        MailingAddress result = new MailingAddress();
        if(mailingAdd.getFlag() == CoConstants.CHAR_O) {
            result.setOrigStreet1(mailingAdd.getStreet1());
            result.setOrigStreet2(mailingAdd.getStreet2());
            result.setOrigState(mailingAdd.getState());
            result.setOrigCity(mailingAdd.getCity());
            result.setOrigZip4(mailingAdd.getZip4());
            result.setOrigZip5(mailingAdd.getZip5());
        } else {
            result.setUpdStreet1(mailingAdd.getStreet1());
            result.setUpdStreet2(mailingAdd.getStreet2());
            result.setUpdState(mailingAdd.getState());
            result.setUpdCity(mailingAdd.getCity());
            result.setUpdZip4(mailingAdd.getZip4());
            result.setUpdZip5(mailingAdd.getZip5());
        }
        return result;
    }

    @Override
    public List<NoticeRequestStatus> recentRequestStatus(NoticeRequest noticeReq){
        System.out.println("startdate: " + noticeReq.getMetaData().getStartDt() + " end date: " + noticeReq.getMetaData().getEndDt());
        List<NoticeRequestStatus> noticeReqList = new ArrayList<>();
        List<NoticeRequestStatus> tempList;
        if (null != noticeReq.getMetaData().getCaseNum() && null != noticeReq.getMetaData().getRequestId()) {
            tempList = repo.findByNoticeRequestIdAndCaseNumOrderByLogRequestIdDesc(noticeReq.getMetaData().getRequestId(), noticeReq.getMetaData().getCaseNum());
            if(!tempList.isEmpty()){
                String statusValue = CoUtil.getNoticeStatusValue(tempList.get(0).getStatus());
                tempList.get(0).setStatus(statusValue);
                noticeReqList.add(tempList.get(0));
            }
        }else if(null != noticeReq.getMetaData().getRequestId()){
            tempList = repo.findByNoticeRequestIdOrderByLogRequestIdDesc(noticeReq.getMetaData().getRequestId());
            if(!tempList.isEmpty()){
                String statusValue = CoUtil.getNoticeStatusValue(tempList.get(0).getStatus());
                tempList.get(0).setStatus(statusValue);
                noticeReqList.add(tempList.get(0));
            }
        }else if(null != noticeReq.getMetaData().getCaseNum() && null != noticeReq.getMetaData().getStartDt() && null != noticeReq.getMetaData().getEndDt()){
            Long[] requestIds = repo.findUniqueRequestId(noticeReq.getMetaData().getCaseNum());
            for(Long id : requestIds){
                LocalDateTime s = noticeReq.getMetaData().getStartDt().toLocalDateTime();
                LocalDateTime d = noticeReq.getMetaData().getEndDt().toLocalDateTime();
                tempList = repo.findByNoticeRequestId(id, s ,d);
                if(!tempList.isEmpty()){
                        String statusValue = CoUtil.getNoticeStatusValue(tempList.get(0).getStatus());
                        tempList.get(0).setStatus(statusValue);
                        noticeReqList.add(tempList.get(0));
                    }
                }
        }else if(null != noticeReq.getMetaData().getLogRequestId()){
            noticeReqList = repo.findByLogRequestId(noticeReq.getMetaData().getLogRequestId());
        }
        return noticeReqList;
    }

    private NoticeRequestStatus saveNoticeData(NoticeRequest coNoticeData) {
        NoticeRequestStatus xmlData = new NoticeRequestStatus();
        xmlData.setNoticeRequestId(coNoticeData.getMetaData().getRequestId());
        if(coNoticeData.getFormData()!= null &&  coNoticeData.getFormData().getClientId() != null)
            xmlData.setHohId(coNoticeData.getFormData().getClientId());
        if(coNoticeData.getMetaData().getHohId() != null)
            xmlData.setHohId(coNoticeData.getMetaData().getHohId());
        xmlData.setTemplateId(coNoticeData.getMetaData().getTemplateId());
        xmlData.setNoticeType(coNoticeData.getMetaData().getNoticeType());
        xmlData.setWaterMark(coNoticeData.getMetaData().getWatermark());
        xmlData.setSecurityFlag(coNoticeData.getMetaData().getSecurityFlag());
        xmlData.setFormTitle(coNoticeData.getMetaData().getFormTitle());
        xmlData.setCaseNum(coNoticeData.getMetaData().getCaseNum());
        if(coNoticeData.getFormData()!= null &&  coNoticeData.getFormData().getClientName() != null)
            xmlData.setClientName(coNoticeData.getFormData().getClientName());
        if(coNoticeData.getMetaData().getClientName() != null)
            xmlData.setClientName(coNoticeData.getMetaData().getClientName());
        xmlData.setGoGreen(coNoticeData.getMetaData().getGoGreen());
        xmlData.setAgencyId(coNoticeData.getMetaData().getAgencyCode());
        xmlData.setAgencyName(coNoticeData.getMetaData().getAgencyName());
        xmlData.setAddressUpdated(coNoticeData.getMetaData().getAddressUpdated());
        xmlData.setLanguage(coNoticeData.getMetaData().getPreferredLanguage());
        xmlData.setAgencyCode(coNoticeData.getMetaData().getAgencyCode());
        xmlData.setRequestJson(coNoticeData.getRequestJson());
        xmlData.setStatus(coNoticeData.getMetaData().getStatus());
//        xmlData.setLogRequestId(coNoticeData.getMetaData().getLogRequestId());
//        xmlData.setCreateDt(coNoticeData.getMetaData().getCreateDt());
//        xmlData.setCreateDt(CoUtil.getCurrentDate());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CoConstants.DATE_FORMAT_YYYYMMDD);
//        Date date = null;
//        try {
//            date = simpleDateFormat.parse(coNoticeData.getMetaData().getRequestDate());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        xmlData.setRequestDate(CoUtil.getCurrentDate());
        xmlData.setEmailId(coNoticeData.getMetaData().getWorkeremailAddress());
        xmlData.setPhoneNumber(coNoticeData.getMetaData().getPhoneNumber());
        xmlData.setEmailDeliveryStatus(coNoticeData.getMetaData().getEmailDeliveryStatus());
        xmlData.setTextNotificationStatus(coNoticeData.getMetaData().getTextNotificationStatus());
        xmlData.setReasonForFailure(coNoticeData.getMetaData().getReasonForFailure());
        xmlData.setPreferredCommunication(coNoticeData.getMetaData().getCommunicationMode());
        xmlData.setRegenerated(coNoticeData.getMetaData().getRegenerated());
        xmlData.setPrintVendor(coNoticeData.getMetaData().getPrintVendor());
        return xmlData;
    }


//    private String checkNoticeStatus(String status) {
//        switch (status){
//            case "RR" :
//                return NoticeStatus.REQUEST_RECEIVED.name();
//            case "IR":
//                return NoticeStatus.INVALID_REQUEST.name();
//            case "IA":
//                return NoticeStatus.INVALID_ADDRESS.name();
//            case "AU":
//                return NoticeStatus.ADDRESS_UPDATED.name();
//            case "PP":
//                return NoticeStatus.PENDING_PRINT.name();
//            case "CF":
//                return NoticeStatus.PDF_CREATION_FAILED.name();
//            default:
//                return status;
//        }
//    }

    @Override
    public NoticeStatusResp fetchNoticeRequestStatus(NoticeStatusRequest dto) throws ParseException {
        //List<NoticeRequestStatus> noticeReqList = noticeRepo.findByLogRequestId(dto.getStatus(),dto.getAgency(),dto.getChannel(),dto.getToDate(),dto.getFromDate());
        List<Object[]>  noticeReqList = noticeRepo.findByLogRequestId(dto);
        NoticeRequestStatus tempObject = new NoticeRequestStatus();
        List<NoticeRequestStatus> tempList = null;
        if(null != noticeReqList) {
            tempList = noticeReqList.stream().map(i -> {
                NoticeRequestStatus apVO = new NoticeRequestStatus();
                apVO.setNoticeRequestId(((BigDecimal) i[0]).longValue());
                apVO.setLogRequestId(((BigDecimal) i[1]).longValue());
                apVO.setHohId(((BigDecimal) i[2]).longValue());
                apVO.setTemplateId((String) i[3]);
                apVO.setNoticeType((String) i[4]);
                apVO.setMailDate((Date) i[5]);
                apVO.setWaterMark((Character) i[6]);
                apVO.setSecurityFlag(((Character) i[7]));
                apVO.setFormTitle((String) i[8]);
                apVO.setCaseNum(((BigDecimal) i[9]).longValue());
                apVO.setCreateDt((LocalDateTime) i[10]);
                apVO.setUpdateDt((LocalDateTime) i[11]);
                apVO.setCreateUserId((String) i[12]);
                apVO.setUpdateUserId((String) i[13]);
                apVO.setGoGreen((Character) i[14]);
                apVO.setLanguage((String) i[15]);
                apVO.setStatus((String) i[16]);
                apVO.setAgencyName((String) i[17]);
                apVO.setAgencyId((String) i[18]);
                apVO.setRequestDate((Date) i[19]);
                apVO.setAddressUpdated((Character) i[20]);
                apVO.setArchiveDt((Date) i[21]);
                apVO.setUniqueTransId(((BigDecimal) i[22]).longValue());
                if(i[23]!=null){apVO.setRequestJson(i[23].toString());}
                apVO.setClientName((String) i[24]);
                apVO.setAgencyCode((String) i[25]);
                apVO.setSentToEdms((Character) i[26]);
                apVO.setEdeliveryStatus((Character) i[27]);
                apVO.setRetryProcess((Character) i[28]);
                apVO.setEmailDeliveryStatus((Character) i[29]);
                apVO.setTextNotificationStatus((Character) i[30]);
                apVO.setReasonForFailure((String) i[31]);
                apVO.setEmailId((String) i[32]);
                if(i[33]!=null){
                    apVO.setPhoneNumber(((BigDecimal) i[33]).longValue());
                }
                return apVO;
            }).collect(Collectors.toList());
        }
        Integer emailSuccessCount=0;
        Integer emailFailureCount=0;
        Integer mailSuccessCount=0;
        Integer mailFailureCount=0;
        Integer textSuccessCount=0;
        Integer textFailureCount=0;
        List<DashboardRespDto> emailSuccessDetails = new ArrayList<>();
        List<DashboardRespDto> emailFailureDetails = new ArrayList<>();
        List<DashboardRespDto> mailSuccessDetails = new ArrayList<>();
        List<DashboardRespDto> mailFailureDetails = new ArrayList<>();
        List<DashboardRespDto> textSuccessDetails = new ArrayList<>();
        List<DashboardRespDto> textFailureDetails = new ArrayList<>();
        NoticeStatusResp response = new NoticeStatusResp();
        DashboardRespDto dashRespDto = null;
        if(tempList.size()>0){
            for(NoticeRequestStatus temp:tempList){
                if(temp.getStatus().equalsIgnoreCase("PR") && temp.getGoGreen().equals('N')){
                    dashRespDto = new DashboardRespDto();
                    dashRespDto.setNoticeRequestId(temp.getNoticeRequestId());
                    String date =new SimpleDateFormat("MM-dd-yyyy").format(temp.getRequestDate());
                    dashRespDto.setRequestDate(date);
                    dashRespDto.setReasonForFailure(temp.getReasonForFailure() != null ? temp.getReasonForFailure() : null);
                    mailSuccessCount++;
                    mailSuccessDetails.add(dashRespDto);
                }
                if(temp.getStatus().equalsIgnoreCase("PR") && temp.getGoGreen().equals('Y')){
                    dashRespDto = new DashboardRespDto();
                    dashRespDto.setNoticeRequestId(temp.getNoticeRequestId());
                    String date =new SimpleDateFormat("MM-dd-yyyy").format(temp.getRequestDate());
                    dashRespDto.setRequestDate(date);
                    dashRespDto.setReasonForFailure(temp.getReasonForFailure() != null ? temp.getReasonForFailure() : null);
                    emailSuccessCount++;
                    emailSuccessDetails.add(dashRespDto);
                }
                if(temp.getStatus().equalsIgnoreCase("PR") && temp.getGoGreen().equals('Y')){
                    dashRespDto = new DashboardRespDto();
                    dashRespDto.setNoticeRequestId(temp.getNoticeRequestId());
                    String date =new SimpleDateFormat("MM-dd-yyyy").format(temp.getRequestDate());
                    dashRespDto.setRequestDate(date);
                    dashRespDto.setReasonForFailure(temp.getReasonForFailure() != null ? temp.getReasonForFailure() : null);
                    textSuccessCount++;
                    textSuccessDetails.add(dashRespDto);
                }
                if(temp.getStatus().equalsIgnoreCase("FL") && temp.getGoGreen().equals('N')){
                    dashRespDto = new DashboardRespDto();
                    dashRespDto.setNoticeRequestId(temp.getNoticeRequestId());
                    String date =new SimpleDateFormat("MM-dd-yyyy").format(temp.getRequestDate());
                    dashRespDto.setRequestDate(date);
                    dashRespDto.setReasonForFailure(temp.getReasonForFailure() != null ? temp.getReasonForFailure() : null);
                    mailFailureCount++;
                    mailFailureDetails.add(dashRespDto);
                }
                if(temp.getStatus().equalsIgnoreCase("FL") && temp.getGoGreen().equals('Y') &&
                        StringUtils.isNotEmpty(temp.getEmailDeliveryStatus().toString()) &&
                        temp.getEmailDeliveryStatus().equals('Y')) {
                    dashRespDto = new DashboardRespDto();
                    dashRespDto.setNoticeRequestId(temp.getNoticeRequestId());
                    String date =new SimpleDateFormat("MM-dd-yyyy").format(temp.getRequestDate());
                    dashRespDto.setRequestDate(date);
                    dashRespDto.setReasonForFailure(temp.getReasonForFailure() != null ? temp.getReasonForFailure() : null);
                    emailFailureCount++;
                    emailFailureDetails.add(dashRespDto);
                }
                if(temp.getStatus().equalsIgnoreCase("FL") && temp.getGoGreen().equals('Y') &&
                        StringUtils.isNotEmpty(temp.getTextNotificationStatus().toString()) &&
                        temp.getTextNotificationStatus().equals('Y')) {
                    dashRespDto = new DashboardRespDto();
                    dashRespDto.setNoticeRequestId(temp.getNoticeRequestId());
                    String date =new SimpleDateFormat("MM-dd-yyyy").format(temp.getRequestDate());
                    dashRespDto.setRequestDate(date);
                    dashRespDto.setReasonForFailure(temp.getReasonForFailure() != null ? temp.getReasonForFailure() : null);
                    textFailureCount++;
                    textFailureDetails.add(dashRespDto);
                }
            }
            response.setEmailSuccessCount(emailSuccessCount);
            response.setEmailFailureCount(emailFailureCount);
            response.setMailSuccessCount(mailSuccessCount);
            response.setMailFailureCount(mailFailureCount);
            response.setTextSuccessCount(textSuccessCount);
            response.setTextFailureCount(textFailureCount);
            response.setEmailSuccessDetails(emailSuccessDetails);
            response.setEmailFailureDetails(emailFailureDetails);
            response.setMailSuccessDetails(mailSuccessDetails);
            response.setMailFailureDetails(mailFailureDetails);
            response.setTextSuccessDetails(textSuccessDetails);
            response.setTextFailureDetails(textFailureDetails);

        }
        return response;
    }

    @Override
    public List<NoticeReportResp> fetchTrackingReport(NoticeStatusRequest dto) throws ParseException {
            String fromDate =new SimpleDateFormat("dd-MMM-yy").format(dto.getFromDate());
            String toDate =new SimpleDateFormat("dd-MMM-yy").format(dto.getToDate());
        //List<Object[]>  noticeReqList = trackingRepo.fetchTrackingReport(dto.getAgency(),fromDate.toUpperCase(),toDate.toUpperCase());
        NoticeRequestStatus tempObject = new NoticeRequestStatus();
        List<NoticeRequestStatus> tempList = repo.fetchTrackingReport(dto.getAgency(),fromDate.toUpperCase(),toDate.toUpperCase());
        /*if(null != noticeReqList) {
            tempList = noticeReqList.stream().map(i -> {
                NoticeRequestStatus apVO = new NoticeRequestStatus();
                apVO.setNoticeRequestId(((BigDecimal) i[0]).longValue());
                apVO.setLogRequestId(((BigDecimal) i[1]).longValue());
                apVO.setHohId(((BigDecimal) i[2]).longValue());
                apVO.setTemplateId((String) i[3]);
                apVO.setNoticeType((String) i[4]);
                apVO.setMailDate((Date) i[5]);
                apVO.setWaterMark((Character) i[6]);
                apVO.setSecurityFlag(((Character) i[7]));
                apVO.setFormTitle((String) i[8]);
                apVO.setCaseNum(((BigDecimal) i[9]).longValue());
                //java.sql.Timestamp cannot be cast to java.time.LocalDateTime
                apVO.setCreateDt(((java.sql.Timestamp) i[10]).toLocalDateTime());
                apVO.setUpdateDt(((java.sql.Timestamp) i[11]).toLocalDateTime());
                apVO.setCreateUserId((String) i[12]);
                apVO.setUpdateUserId((String) i[13]);
                apVO.setGoGreen((Character) i[14]);
                apVO.setLanguage((String) i[15]);
                apVO.setStatus((String) i[16]);
                apVO.setAgencyName((String) i[17]);
                apVO.setAgencyId((String) i[18]);
                apVO.setRequestDate((Date) i[19]);
                apVO.setAddressUpdated((Character) i[20]);
                apVO.setArchiveDt((Date) i[21]);
                apVO.setUniqueTransId(((BigDecimal) i[22]).longValue());
                if(i[23]!=null){apVO.setRequestJson(i[23].toString());}
                apVO.setClientName((String) i[24]);
                apVO.setAgencyCode((String) i[25]);
                apVO.setSentToEdms((Character) i[26]);
                apVO.setEdeliveryStatus((Character) i[27]);
                apVO.setRetryProcess((Character) i[28]);
                if(i[29] != null)
                apVO.setEmailDeliveryStatus(((String) i[29]).charAt(0));
                System.out.println("i[29] : " + i[29] + "  i[30] : " + i[30]);
                if(i[30] != null)
                apVO.setTextNotificationStatus(((String) i[30]).charAt(0));
                apVO.setReasonForFailure((String) i[31]);
                apVO.setEmailId((String) i[32]);
                if(i[33]!=null){
                    apVO.setPhoneNumber(((BigDecimal) i[33]).longValue());
                }
                if(i[34]!=null) {
                    apVO.setPreferredCommunication(((Character) i[34]));
                }
                if(i[35]!=null) {
                    apVO.setRegenerated(((Character) i[35]));
                }
                if(i[36]!=null) {
                    apVO.setPrintVendor(((String) i[36]));
                }
                return apVO;
            }).collect(Collectors.toList());
        }*/
        List<String> allTemplateIds = getTemplateIds();
        Map<String,Integer> totalCount = new HashMap<>();
        Map<String,Integer> heldCount = new HashMap<>();
        Map<String,Integer> suppressedCount = new HashMap<>();
        Map<String,Integer> localPrintCount = new HashMap<>();
        Map<String,Integer> failedCount = new HashMap<>();
        Map<String,Integer> emailedCount = new HashMap<>();
        Map<String,Integer> percentEmailed = new HashMap<>();
        Map<String,Integer> bouncedEmailsCount = new HashMap<>();
        Map<String,Integer> percentUndelivered = new HashMap<>();
        Map<String,Integer> totalPrintedCount = new HashMap<>();
        Map<String,Integer> percentPrinted = new HashMap<>();
        Map<String,Integer> bouncedMailsCount = new HashMap<>();
        Map<String,Integer> percentMailReturned = new HashMap<>();

        List<NoticeReportResp> responseList = new ArrayList<>();
        for(String templateId:allTemplateIds){
            NoticeReportResp response = new NoticeReportResp();
            totalCount.put(templateId,0);
            heldCount.put(templateId,0);
            suppressedCount.put(templateId,0);
            localPrintCount.put(templateId,0);
            failedCount.put(templateId,0);
            emailedCount.put(templateId,0);
            percentEmailed.put(templateId, 0);
            bouncedEmailsCount.put(templateId,0);
            percentUndelivered.put(templateId,0);
            totalPrintedCount.put(templateId,0);
            percentPrinted.put(templateId, 0);
            bouncedMailsCount.put(templateId,0);
            percentMailReturned.put(templateId,0);

            for(NoticeRequestStatus tempObjectData:tempList){
                    if(tempObjectData.getTemplateId().equalsIgnoreCase(templateId)){
                        Integer totalCt = Integer.valueOf(totalCount.get(templateId));
                        totalCount.put(templateId,++totalCt);
                        if(StringUtils.isNotEmpty(tempObjectData.getStatus()) && ObjectUtils.isNotEmpty(tempObjectData.getGoGreen()) && (tempObjectData.getStatus().equalsIgnoreCase("HD")) && String.valueOf(tempObjectData.getGoGreen()).equalsIgnoreCase("N")){
                            Integer tempCount = Integer.valueOf(heldCount.get(templateId));
                            heldCount.put(templateId,++tempCount);
                        }
                        if(StringUtils.isNotEmpty(tempObjectData.getStatus()) && ObjectUtils.isNotEmpty(tempObjectData.getGoGreen()) && (tempObjectData.getStatus().equalsIgnoreCase("SU")) && String.valueOf(tempObjectData.getGoGreen()).equalsIgnoreCase("N")){
                            Integer tempCount = Integer.valueOf(suppressedCount.get(templateId));
                            suppressedCount.put(templateId,++tempCount);
                        }
                        if(StringUtils.isNotEmpty(tempObjectData.getStatus()) && ObjectUtils.isNotEmpty(tempObjectData.getGoGreen()) && (tempObjectData.getStatus().equalsIgnoreCase("LP")) && String.valueOf(tempObjectData.getGoGreen()).equalsIgnoreCase("N")){
                            Integer tempCount = Integer.valueOf(localPrintCount.get(templateId));
                            localPrintCount.put(templateId,++tempCount);
                        }
                        if(StringUtils.isNotEmpty(tempObjectData.getStatus()) && ObjectUtils.isNotEmpty(tempObjectData.getGoGreen()) && ((tempObjectData.getStatus().equalsIgnoreCase("CF")) || (tempObjectData.getStatus().equalsIgnoreCase("FL")))){
                            Integer tempCount = Integer.valueOf(failedCount.get(templateId));
                            failedCount.put(templateId,++tempCount);
                        }
                        if(ObjectUtils.isNotEmpty(tempObjectData.getGoGreen()) && ObjectUtils.isNotEmpty(tempObjectData.getPreferredCommunication()) && ObjectUtils.isNotEmpty(tempObjectData.getEmailDeliveryStatus()) && (String.valueOf(tempObjectData.getGoGreen()).equalsIgnoreCase("Y")) && (String.valueOf(tempObjectData.getPreferredCommunication()).equalsIgnoreCase("E")) ){
                            Integer tempCount = Integer.valueOf(emailedCount.get(templateId));
                            emailedCount.put(templateId,++tempCount);
                        }
                        if(ObjectUtils.isNotEmpty(tempObjectData.getGoGreen()) && ObjectUtils.isNotEmpty(tempObjectData.getPreferredCommunication()) && ObjectUtils.isNotEmpty(tempObjectData.getEmailDeliveryStatus()) && (String.valueOf(tempObjectData.getGoGreen()).equalsIgnoreCase("Y"))  && (String.valueOf(tempObjectData.getPreferredCommunication()).equalsIgnoreCase("E")) && (String.valueOf(tempObjectData.getEmailDeliveryStatus()).equalsIgnoreCase("F"))){
                            Integer tempCount = Integer.valueOf(bouncedEmailsCount.get(templateId));String.valueOf(tempObjectData.getPreferredCommunication());
                            bouncedEmailsCount.put(templateId,++tempCount);
                        }
                        if(StringUtils.isNotEmpty(tempObjectData.getStatus()) && ObjectUtils.isNotEmpty(tempObjectData.getGoGreen()) && (tempObjectData.getStatus().equalsIgnoreCase("RM")) && String.valueOf(tempObjectData.getGoGreen()).equalsIgnoreCase("N")){
                            Integer tempCount = Integer.valueOf(bouncedMailsCount.get(templateId));
                            bouncedMailsCount.put(templateId,++tempCount);
                        }

                    }


            }

            Integer totalPrints = totalCount.get(templateId) - bouncedEmailsCount.get(templateId);
            if(totalPrints<0){
                totalCount.put(templateId, 0);
            }else{
                totalCount.put(templateId, totalPrints);
            }

            Integer totalPrintedEmails = totalPrints - heldCount.get(templateId) - suppressedCount.get(templateId)
                                        - localPrintCount.get(templateId) - failedCount.get(templateId) - (emailedCount.get(templateId) - bouncedEmailsCount.get(templateId));
            if(totalPrintedEmails<0){
                totalPrintedCount.put(templateId, 0);
            }else{
                totalPrintedCount.put(templateId, totalPrintedEmails);
            }

            if(totalCount.get(templateId) > 0){
                Integer perEmailed = (int) (Math.floor((Double.valueOf(emailedCount.get(templateId)) / Double.valueOf(totalCount.get(templateId)))*100));
                percentEmailed.put(templateId, perEmailed);
            }

            if(emailedCount.get(templateId) > 0){
                Integer perUndelivered = (int) (Math.floor((Double.valueOf(bouncedEmailsCount.get(templateId)) / Double.valueOf(emailedCount.get(templateId)))*100));
                percentUndelivered.put(templateId, perUndelivered);
            }

            if(totalCount.get(templateId) > 0){
                Integer perPrinted = (int) (Math.floor((Double.valueOf(totalPrintedCount.get(templateId)) / Double.valueOf(totalCount.get(templateId)))*100));
                percentPrinted.put(templateId, perPrinted);
            }

            if(totalPrintedCount.get(templateId) > 0){
                Integer perMailedRet = (int) (Math.floor((Double.valueOf(bouncedMailsCount.get(templateId)) / Double.valueOf(totalPrintedCount.get(templateId)))*100));
                percentMailReturned.put(templateId, perMailedRet);
            }

            response.setDocumentName(CoUtil.getDocumentName(templateId));
            response.setTotal(totalCount.get(templateId));
            response.setHeld(heldCount.get(templateId));
            response.setSuppressed(suppressedCount.get(templateId));
            response.setLocalPrinted(localPrintCount.get(templateId));
            response.setFailed(failedCount.get(templateId));
            response.setEmailed(emailedCount.get(templateId));
            response.setPercentEmailed(percentEmailed.get(templateId));
            response.setUndeliveredEmail(bouncedEmailsCount.get(templateId));
            response.setPercentUndeliveredEmail(percentUndelivered.get(templateId));
            response.setTotalPrinted(totalPrintedCount.get(templateId));
            response.setPercentPrinted(percentPrinted.get(templateId));
            response.setReturnedMailCount(bouncedMailsCount.get(templateId));
            response.setPercentMailReturned(percentMailReturned.get(templateId));
            responseList.add(response);

        }
        NoticeReportResp response = new NoticeReportResp();
        return responseList;
    }

    private List<String> getTemplateIds(){
        List<String> templateIdList = new ArrayList<>();
        templateIdList.add("MA-APPDENY-001");
        templateIdList.add("MAH-CPN-001");
        templateIdList.add("MA-DOWNGRADE-001");
        templateIdList.add("MA-DTA-001");
        templateIdList.add("MA-MANUAL-001");
        return templateIdList;
    }

}

