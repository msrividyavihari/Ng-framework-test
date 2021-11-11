package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.dto.entities.BatchNotice;
import com.deloitte.nextgen.dto.entities.CoManualDataDTO;
import com.deloitte.nextgen.dto.entities.ViewPendingDTO;
import com.deloitte.nextgen.dto.vo.DocumentDetailsVO;
import com.deloitte.nextgen.dto.vo.ViewPendingVO;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.CoViewPendingService;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * @author aachala
 */

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/correspondence")
public class ViewPendingController {

    @Autowired
    CoViewPendingService viewService;

    @PostMapping ("/viewPendingCorrespondence")
    public ResponseEntity<ApiResponse<List<ViewPendingVO>>> fetchForPendingCO(@RequestBody ViewPendingDTO pendingDTO) throws FwApplicationException {
        if(pendingDTO!=null){
            String curDt = CoUtil.getDateForWhereClauseANSI(CoUtil.getCurrentDate());
            List<ViewPendingVO> caseList = this.viewService.fetchPendingCO(pendingDTO, curDt);

            if(caseList!=null && !caseList.isEmpty()){
                return ApiResponse.success(100).data(caseList);
            }else{
                return ApiResponse.error(103, "No records found").notify(true).data(null);
            }
        }
        return null;
    }

    @PostMapping ("/viewPendingCorrespondence/manualData")
    public ResponseEntity<ApiResponse<List<CoManualDataDTO>>> manualData(@RequestBody ViewPendingDTO pendingDTO) throws Exception {
        if(pendingDTO!=null){
            List<CoManualDataDTO> caseList = this.viewService.fetchManualData(pendingDTO.getCoReqSeq().longValue());
            if(caseList!=null && !caseList.isEmpty()){
                return ApiResponse.success(100).data(caseList);
            }
        }
        return null;
    }

    @RequestMapping(value = "/viewPendingCorrespondence/preview", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> viewPendingCorrPreview
            (@RequestBody ViewPendingDTO pendingDTO) throws Exception {
//        NoticeCustomer n = viewService.getNoticeCustomer(pendingDTO);
        BatchNotice batchNotice = viewService.getBatchNotice(pendingDTO);
        if(pendingDTO.getAction().equals(CoConstants.VIEW_PREVIEW)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<BatchNotice> httpEntity = new HttpEntity<>(batchNotice, headers);
            ResponseEntity<byte[]> response = restTemplate
                    .postForEntity("http://localhost:8086/co/opentext",
                            httpEntity, byte[].class);

            if(response.getBody() == null) {
                return new ResponseEntity<>(new ByteArrayResource(new byte[0]), HttpStatus.OK);
            } else {
                return ResponseEntity.
                        ok().
                        headers(headers).
                        contentType(MediaType.APPLICATION_PDF).
                        body(new ByteArrayResource(response.getBody()));
            }
        }
        String xmlString =viewService.viewPendingDetailService(pendingDTO.getCoReqSeq().longValue(),pendingDTO.getDocId(),pendingDTO.getPreviewVal(),pendingDTO.getGenerateManualDTO());
        return getInputStreamResourceResponseEntity(xmlString);
    }

    @PostMapping ("/viewPendingCorrespondence/centralPrint")
    public ResponseEntity<ApiResponse<String>> viewPendingCorrCentralPrint(@RequestBody ViewPendingDTO pendingDTO) throws Exception{
//        NoticeCustomer n = viewService.getNoticeCustomer(pendingDTO);
        BatchNotice batchNotice = viewService.getBatchNotice(pendingDTO);
        if(pendingDTO.getAction().equals(CoConstants.VIEW_CENTRAL_PRINT)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<BatchNotice> httpEntity = new HttpEntity<>(batchNotice, headers);
            ResponseEntity<byte[]> response = restTemplate
                    .postForEntity("http://localhost:8086/co/opentext",
                            httpEntity, byte[].class);
            return ApiResponse.success(100).data("Central Print Completed Successfully");
        }
        String result;
        result = viewService.centralPrint(pendingDTO);
        if(result!=null && (!result.isEmpty()||result.equals("")))
            return ApiResponse.success(100).data("Central Print Completed Successfully");
        else
            return ApiResponse.error(103, "Cannot do this operation at this moment").notify(true).data(null);
    }

    @RequestMapping(value = "/viewPendingCorrespondence/localPrint", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> viewPendingCorrLocalPrint
            (@RequestBody ViewPendingDTO pendingDTO) throws Exception {

//        NoticeCustomer n = viewService.getNoticeCustomer(pendingDTO);
//        System.out.println("view pending local print " + n);
        BatchNotice batchNotice = viewService.getBatchNotice(pendingDTO);
        if(pendingDTO.getAction().equals(CoConstants.VIEW_LOCAL_PRINT)) {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<BatchNotice> httpEntity = new HttpEntity<>(batchNotice, headers);
            ResponseEntity<byte[]> response = restTemplate
                    .postForEntity("http://localhost:8086/co/opentext",
                            httpEntity, byte[].class);

            if(response.getBody() == null) {
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
        String xmlString =viewService.viewPendingDetailService(pendingDTO.getCoReqSeq().longValue(),pendingDTO.getDocId(),pendingDTO.getPreviewVal(),pendingDTO.getGenerateManualDTO());
        return getInputStreamResourceResponseEntity(xmlString);
    }

    private ResponseEntity<ByteArrayResource> getInputStreamResourceResponseEntity(String xmlString) {
        if(!CoUtil.isEmpty(xmlString)) {
            byte[] fileBytes;
            RestTemplate restTemplate = new RestTemplate();
            fileBytes = restTemplate.postForObject("http://localhost:8086/co/getPdfByteArr", xmlString, byte[].class);
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
//                        body(new InputStreamResource(fileInputStream));
            }
        }
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/viewPendingCorrespondence/suppressUnsuppress", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<List<ViewPendingDTO>>> viewPendingSuppressUnSuppress
            (@RequestParam("isSuppress") String isSuppress ,@RequestBody ViewPendingDTO pendingDTO) throws Exception {
        List<ViewPendingDTO> response =viewService.suppressUnsuppress(pendingDTO);
        if(response!=null && !response.isEmpty()){
            return ApiResponse.success(100).data(response);
        }else{
            return ApiResponse.error(103, "No records found").notify(true).data(null);
        }
    }


}
