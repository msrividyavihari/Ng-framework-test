package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.dto.entities.BatchNotice;
import com.deloitte.nextgen.dto.entities.CoGenerateManualDTO;
import com.deloitte.nextgen.dto.vo.DocumentDetailsVO;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.CoGenerateManualService;
import com.deloitte.nextgen.service.CoViewPendingService;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/correspondence/generateManual/")
public class CoGenerateManualController {

    @Autowired
    CoGenerateManualService generateManualService;

    @Autowired
    CoViewPendingService viewService;

    @PostMapping(value = {"/saveAsDraft"})
    public ResponseEntity<ApiResponse<List<CoGenerateManualDTO>>> saveAsDraftCorr(@RequestBody CoGenerateManualDTO generateManualReqDTO) throws Exception {
        List<CoGenerateManualDTO>  generateManualRespDTO = generateManualService.saveManualCorrDetails(generateManualReqDTO);
        if(!generateManualRespDTO.isEmpty()) {
            return ApiResponse.success(100).data(generateManualRespDTO);
        } else {
            return ApiResponse.error(103, "No records found").notify(true).data(null);
        }
    }

    @PostMapping(value = {"/initialize"})
    public ResponseEntity<ApiResponse<CoGenerateManualDTO>>  initializeManualCorr(@RequestBody CoGenerateManualDTO generateManualDTO) throws Exception {
        CoGenerateManualDTO generateManualRespDTO = generateManualService.initialize(generateManualDTO);
        if(generateManualRespDTO!=null) {
            return ApiResponse.success(100).data(generateManualRespDTO);
        }
        return null;
    }

    @RequestMapping(value = "/preview", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> manualCorrPreview
            (@RequestBody CoGenerateManualDTO generateManualDTO) throws Exception {

//        NoticeCustomer n = generateManualService.getNoticeRequest(generateManualDTO);
        BatchNotice batchNotice = generateManualService.getBatchNotice(generateManualDTO);
        if(generateManualDTO.getAction().equals(CoConstants.GEN_PREVIEW)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<BatchNotice> httpEntity = new HttpEntity<>(batchNotice, headers);
            ResponseEntity<byte[]> response = restTemplate
                    .postForEntity("http://localhost:8086/co/opentext",
                            httpEntity, byte[].class);

            if(response.getBody() == null) {
//                return ResponseEntity.noContent().build();
                return new ResponseEntity<>(new ByteArrayResource(new byte[0]), HttpStatus.OK);
            } else {
                return ResponseEntity.
                        ok().
                        headers(headers).
                        contentType(MediaType.APPLICATION_PDF).
                        body(new ByteArrayResource(response.getBody()));
            }
        }
        String xmlString =generateManualService.manualCorr(generateManualDTO);
        System.out.println("Preview through AdditionalInfo");
        return getInputStreamResourceResponseEntity(xmlString);
    }

    @RequestMapping(value = "/centralPrint", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> manualCorrCentralPrint
            (@RequestBody CoGenerateManualDTO generateManualDTO) throws Exception {

//        NoticeCustomer n = generateManualService.getNoticeRequest(generateManualDTO);
        BatchNotice batchNotice = generateManualService.getBatchNotice(generateManualDTO);
        if(generateManualDTO.getAction().equals(CoConstants.GEN_CENTRAL_PRINT)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<BatchNotice> httpEntity = new HttpEntity<>(batchNotice, headers);
            ResponseEntity<byte[]> response = restTemplate
                    .postForEntity("http://localhost:8086/co/opentext",
                            httpEntity, byte[].class);

            if(response.getBody() == null) {
//                return ResponseEntity.noContent().build();
                return new ResponseEntity<>(new ByteArrayResource(new byte[0]), HttpStatus.OK);
            } else {
                return ResponseEntity.
                        ok().
                        headers(headers).
                        contentType(MediaType.APPLICATION_PDF).
                        body(new ByteArrayResource(response.getBody()));
            }
        }

        String xmlString =generateManualService.manualCorr(generateManualDTO);
        System.out.println("Central print through AdditionalInfo");
        return getInputStreamResourceResponseEntity(xmlString);
    }

    @RequestMapping(value = "/localPrint", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> manualCorrLocalPrint
            (@RequestBody CoGenerateManualDTO generateManualDTO) throws Exception {

//        NoticeCustomer n = generateManualService.getNoticeRequest(generateManualDTO);
        BatchNotice batchNotice = generateManualService.getBatchNotice(generateManualDTO);
        if(generateManualDTO.getAction().equals(CoConstants.GEN_LOCAL_PRINT)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<BatchNotice> httpEntity = new HttpEntity<>(batchNotice, headers);
            ResponseEntity<byte[]> response = restTemplate
                    .postForEntity("http://localhost:8086/co/opentext",
                            httpEntity, byte[].class);

            if(response.getBody() == null) {
//                return ResponseEntity.noContent().build();
                return new ResponseEntity<>(new ByteArrayResource(new byte[0]), HttpStatus.OK);
            } else {
                DocumentDetailsVO docDetails = viewService.uploadFileToEDMS(response.getBody());
                viewService.insertDocumentDetails(docDetails);
                return ResponseEntity.
                        ok().
                        headers(headers).
                        contentType(MediaType.APPLICATION_PDF).
                        body(new ByteArrayResource(response.getBody()));
            }
        }

        String xmlString =generateManualService.manualCorr(generateManualDTO);
        System.out.println("Local Print through AdditionalInfo");
        return getInputStreamResourceResponseEntity(xmlString);
    }
    private ResponseEntity<ByteArrayResource> getInputStreamResourceResponseEntity(String xmlString) {
        if(CoUtil.isNotEmpty(xmlString)) {
            byte[] fileBytes;
            RestTemplate restTemplate = new RestTemplate();
            fileBytes = restTemplate.postForObject("http://15.207.142.199/co/getPdfByteArr", xmlString, byte[].class);
            if (null != fileBytes && fileBytes.length > 0) {
//                byte[] encodedBytes = java.util.Base64.getEncoder().encode(fileBytes);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE));
//                InputStream fileInputStream = new ByteArrayInputStream(fileBytes);
                ByteArrayResource fileInputStream = new ByteArrayResource(fileBytes);
                headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                headers.add("Content-Disposition", "attachment; filename=CorrespondencePDF.pdf");
                return ResponseEntity.
                        ok().
                        headers(headers).
                        contentType(MediaType.APPLICATION_PDF).
                        body(fileInputStream);
            }
        }
        return ResponseEntity.noContent().build();
    }
}
