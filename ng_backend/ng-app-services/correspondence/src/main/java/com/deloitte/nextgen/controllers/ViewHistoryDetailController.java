package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.dto.entities.ViewHistoryDetailDTO;
import com.deloitte.nextgen.dto.vo.ViewHistoryDetailVO;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.CoViewHistoryDetailService;
import com.deloitte.nextgen.util.COPrintPreviewManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author aachala
 */


@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/correspondence/historyDetail")
public class ViewHistoryDetailController {

    @Autowired
    CoViewHistoryDetailService coViewHistoryDetailService;

    @Autowired
    COPrintPreviewManager coPrintPreviewManager;

    @PostMapping("/preview")
    public ResponseEntity<byte[]> viewHistoryPreview
            (@RequestBody ViewHistoryDetailDTO viewHistoryDetailDTO) {
        byte[] fileArray =coViewHistoryDetailService.viewHistoryDetailService(viewHistoryDetailDTO.getDisDocMstrSeqNum().toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE));
        if(fileArray != null && fileArray.length > 0) {
//            byte[] encodedBytes = java.util.Base64.getEncoder().encode(fileArray);
            return new ResponseEntity<>(fileArray, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new byte[0], headers, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/retrieve")
    public ResponseEntity<ApiResponse<ViewHistoryDetailVO>> retrieve(@RequestBody ViewHistoryDetailDTO viewHistoryDetailDTO) {
        ViewHistoryDetailVO viewHistoryDetailVO = null;
        if(viewHistoryDetailDTO != null) {
            viewHistoryDetailVO = coViewHistoryDetailService.retrieveHistoryDetails(viewHistoryDetailDTO);
        }
        if(viewHistoryDetailVO != null)
            return ApiResponse.success(100).data(viewHistoryDetailVO);
        else
            return ApiResponse.error(103, "Invalid details").notify(true).data(null);
    }

    @PostMapping("/central-reprint")
    public ResponseEntity<ApiResponse<String>> centralReprint(@RequestBody ViewHistoryDetailDTO viewHistoryDetailDTO) {
        boolean result = false;
        try {
            result = coPrintPreviewManager.reprintHistory(viewHistoryDetailDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result)
            return ApiResponse.success(100).data("Central Re-Print Completed Successfully");
        else
            return ApiResponse.error(103, "Cannot do this operation at this moment").notify(true).data(null);
    }

    @PostMapping("/local-reprint")
    public ResponseEntity<byte[]> localReprint(@RequestBody ViewHistoryDetailDTO viewHistoryDetailDTO) {
        byte[] fileArray = null;
        try {
            fileArray = coViewHistoryDetailService.generate(viewHistoryDetailDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE));
        if(fileArray != null && fileArray.length > 0) {
//            byte[] encodedBytes = java.util.Base64.getEncoder().encode(fileArray);
            return new ResponseEntity<>(fileArray, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new byte[0], headers, HttpStatus.NO_CONTENT);
        }


    }

}
