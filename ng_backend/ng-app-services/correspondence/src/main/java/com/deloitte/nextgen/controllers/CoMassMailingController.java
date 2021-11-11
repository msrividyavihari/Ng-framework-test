package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.dto.entities.CoMassMailingSummaryDTO;
import com.deloitte.nextgen.dto.vo.MassMailingSummaryVO;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.CoMassMailingService;
import com.deloitte.nextgen.util.CoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/correspondence/massMailing/")
public class CoMassMailingController {

    @Autowired
    CoMassMailingService coMassMailingService;

    @RequestMapping(value = "/summary", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<MassMailingSummaryVO>>> massMailingSummary() {
        System.out.println("Inside massMailingSummary() : ");
        List<MassMailingSummaryVO> massSummaryList = new ArrayList<>();
        try {
            massSummaryList = coMassMailingService.fetchSummaryCO();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (massSummaryList != null)
            return ApiResponse.success(100).data(massSummaryList);
        else
            return ApiResponse.error(103, "Invalid Details").notify(true).data(null);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<ApiResponse<MassMailingSummaryVO>> deleteMassMailing(@RequestBody CoMassMailingSummaryDTO coMassMailingSummaryDTO) {
        MassMailingSummaryVO vo = null;
        try {
            vo = coMassMailingService.deleteMassMailing(coMassMailingSummaryDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (vo != null)
            return ApiResponse.success(100).data(vo);
        else
            return ApiResponse.error(103, "Something went wrong").notify(true).data(null);
    }

    @PostMapping("/createOrUpdateRequest")
    public ResponseEntity<ApiResponse<MassMailingSummaryVO>> processMassMailing(@RequestBody CoMassMailingSummaryDTO coMassMailingSummaryDTO) {
        MassMailingSummaryVO result = null;
        try {
            result = coMassMailingService.processCOMRO(coMassMailingSummaryDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if (result != null)
            return ApiResponse.success(100).data(result);
        else
            return ApiResponse.error(103, "Something went wrong").notify(true).data(null);
    }

    @RequestMapping(value = "/preview", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> massMailingPreview
            (@RequestBody CoMassMailingSummaryDTO dto) throws Exception {
        String xmlString = coMassMailingService.preview(dto);
        System.out.println("Preview");
        return getInputStreamResourceResponseEntity(xmlString);
    }

    private ResponseEntity<InputStreamResource> getInputStreamResourceResponseEntity(String xmlString) {
        if (!CoUtil.isEmpty(xmlString)) {
            byte[] fileBytes;
            String str = "caseNum : 122256186\n" +
                    "clientId: 112233445\n" +
                    "fullName: John Doe\n" +
                    "Age: 51\n" +
                    "Gender: M";
            RestTemplate restTemplate = new RestTemplate();
            fileBytes = restTemplate.postForObject("http://15.207.142.199/co/getPdfByteArr", xmlString, byte[].class);
            if (null != fileBytes && fileBytes.length > 0) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE));
                InputStream fileInputStream = new ByteArrayInputStream(fileBytes);
                headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                headers.add("Content-Disposition", "attachment; filename=MassMailing.pdf");
                return ResponseEntity.
                        ok().
                        headers(headers).
                        contentType(MediaType.APPLICATION_PDF).
                        body(new InputStreamResource(fileInputStream));
            }
        }
        return ResponseEntity.noContent().build();
    }
}
