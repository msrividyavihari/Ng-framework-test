package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.entities.DataToSource;
import com.deloitte.nextgen.dto.entities.DataToSourceDTO;
import com.deloitte.nextgen.entities.NoticeRequestStatus;
import com.deloitte.nextgen.entities.VNoticeRequestAddress;
import com.deloitte.nextgen.repository.NoticeHistoryRepository;
import com.deloitte.nextgen.repository.VNoticeRequestAddressRepository;
import com.deloitte.nextgen.service.DataToSourceService;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataToSourceServiceImpl implements DataToSourceService {

    @Autowired
    NoticeHistoryRepository noticeHistoryRepository;
    @Autowired
    VNoticeRequestAddressRepository vNoticeRequestAddressRepository;

    @Override
    public ResponseEntity<byte[]> saveToFile(DataToSourceDTO dto) throws Exception {

        validateRequest(dto);

        List<NoticeRequestStatus> noticeRequestStatusList = noticeHistoryRepository.findByCaseNumAndRange(
                dto.getAgencyId(), dto.getStartDate(), dto.getEndDate());

        if(noticeRequestStatusList.size() == 0) {
            throw new Exception("no data in Notice_Request_Status for agency id: " + dto.getAgencyId());
        }

        List<DataToSource> dataList = getDataToSource(noticeRequestStatusList);


        String str = CoUtil.getCurrentDateAsString();

        String location = new FileSystemResource("").getFile().getAbsolutePath() +"" +
                "\\src\\main\\resources\\data\\"+ dto.getAgencyId() +"-Address-"+str+".csv";

        FileOutputStream csvOutputFile = new FileOutputStream(location);
        CsvMapper mapper = new CsvMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true)
                .addColumn("Address Flag")
                .addColumn("Notice Request Id")
                .addColumn("Agency ID")
                .addColumn("Agency Name")
                .addColumn("Case Number")
                .addColumn("Client Name")
                .addColumn("Original Street 1")
                .addColumn("Original Street 2")
                .addColumn("Original City")
                .addColumn("Original State")
                .addColumn("Original Zip 4")
                .addColumn("Original Zip 5")
                .addColumn("Updated Street 1")
                .addColumn("Updated Street 2")
                .addColumn("Updated City")
                .addColumn("Updated State")
                .addColumn("Updated Zip 4")
                .addColumn("Updated Zip 5")
                .build();
        mapper.writerFor(DataToSource.class)
                .with(csvSchema)
                .writeValues(csvOutputFile)
                .writeAll(dataList);

        byte[] fileBytes = null;
        if(Files.exists(Paths.get(location))) {
            fileBytes = Files.readAllBytes(Paths.get(location));
        }
        csvOutputFile.close();

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment; " +
                "filename=AddressData.csv");
        ResponseEntity res = new ResponseEntity(fileBytes, headers, HttpStatus.OK);

        return res;
    }

    @Override
    public ResponseEntity<byte[]> saveEDataToFile(DataToSourceDTO dto) throws Exception {
        validateRequest(dto);
        List<NoticeRequestStatus> noticeRequestStatusList = noticeHistoryRepository.findByEmailId(
                dto.getAgencyId(), dto.getStartDate(), dto.getEndDate());
        if(noticeRequestStatusList.size() == 0) {
            throw new Exception("no data in Notice_Request_Status for agency id: " + dto.getAgencyId());
        }
        List<DataToSource> dataList = getDataToSource(noticeRequestStatusList);
        String str = CoUtil.getCurrentDateAsString();
        String location = new FileSystemResource("").getFile().getAbsolutePath() +"" +
                "\\src\\main\\resources\\data\\"+dto.getAgencyId()+"-EData-"+str+".csv";
        FileOutputStream csvOutputFile = new FileOutputStream(location);
        CsvMapper mapper = new CsvMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true)
                .addColumn("Notice Request Id")
                .addColumn("Agency ID")
                .addColumn("Agency Name")
                .addColumn("Case Number")
                .addColumn("Client Name")
                .addColumn("Email ID")
                .addColumn("Phone Number")
                .addColumn("Preferred Communication")
                .addColumn("Email Delivery Status")
                .addColumn("Text Delivery Status")
                .addColumn("Reason For Failure")
                .build();
        mapper.writerFor(DataToSource.class)
                .with(csvSchema)
                .writeValues(csvOutputFile)
                .writeAll(dataList);

        byte[] fileBytes = null;
        if(Files.exists(Paths.get(location))) {
            fileBytes = Files.readAllBytes(Paths.get(location));
        }
        csvOutputFile.close();

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment; " +
                "filename=AddressData.csv");
        ResponseEntity res = new ResponseEntity(fileBytes, headers, HttpStatus.OK);

        return res;
    }


    private void validateRequest(DataToSourceDTO dto) throws Exception {
        if(dto.getAgencyId() == null || dto.getAgencyId().equals("") || dto.getStartDate() == null || dto.getEndDate() == null){
            throw new Exception("Invalid Request");
        }
    }

    private List<DataToSource> getDataToSource(List<NoticeRequestStatus> noticeRequestStatusList) {
        List<DataToSource> dataList = new ArrayList<>();
        List<Long> noticeIdsList = new ArrayList<>();
        for(NoticeRequestStatus n: noticeRequestStatusList) {
            noticeIdsList.add(n.getNoticeRequestId());
        }
        List<VNoticeRequestAddress> vNoticeRequestAddresses = vNoticeRequestAddressRepository.
                findByNoticeRequestId(noticeIdsList);

        for(VNoticeRequestAddress v: vNoticeRequestAddresses) {
            DataToSource d = new DataToSource();
            String val = v.getAddressUpdated() != null && v.getAddressUpdated() == CoConstants.YES
                    ? "Address Updated" : "Invalid Address";
            d.setAddressFlag(val);
            d.setNoticeRequestId(v.getNoticeRequestId());
            d.setAgencyId(v.getAgencyId());
            d.setAgencyName(v.getAgencyName());
            d.setCaseNum(v.getCaseNum());
            d.setClientName(v.getClientName());
            d.setOrigStreet1(v.getOrigStreet1());
            d.setOrigStreet2(v.getOrigStreet2());
            d.setOrigCity(v.getOrigCity());
            d.setOrigState(v.getOrigState());
            d.setOrigZip4((v.getOrigZip4()));
            d.setOrigZip5(v.getOrigZip5());
            d.setUpdStreet1(v.getUpdStreet1());
            d.setUpdStreet2(v.getUpdStreet2());
            d.setUpdCity(v.getUpdCity());
            d.setUpdCity(v.getUpdCity());
            d.setUpdZip4((v.getUpdZip4()));
            d.setUpdZip5((v.getUpdZip5()));
            d.setEmailDelivery(v.getEmailDeliveryStatus());
            d.setTextDelivery(v.getTextNotificationStatus());
            d.setStatus(v.getStatus());
            d.setEmailId(v.getEmailId());
            d.setPhoneNumber(v.getPhoneNumber());
            d.setReasonForFailure(v.getReasonForFailure());
            d.setPreferredCommunication(v.getPreferredCommunication());
            dataList.add(d);
        }

        return dataList;
    }
}
