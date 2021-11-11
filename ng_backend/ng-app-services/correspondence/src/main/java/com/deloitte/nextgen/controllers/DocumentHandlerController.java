package com.deloitte.nextgen.controllers;


import com.deloitte.nextgen.dto.entities.DocumentIdDTO;
import com.deloitte.nextgen.dto.entities.DocumentUpdateDTO;
import com.deloitte.nextgen.dto.vo.DocumentDetailsVO;
import com.deloitte.nextgen.dto.vo.DocumentLinkVO;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.DocumentHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/documentHandler")
public class DocumentHandlerController {


    @Autowired
    DocumentHandlerService docService;

    @PostMapping("/getDocument")
    public ResponseEntity<ApiResponse<DocumentDetailsVO>> getDocumentsMetaData(@RequestBody DocumentIdDTO dto) {
        DocumentDetailsVO detailsObj = null;
        try {

            ResponseEntity<String> responseObj = docService.getDocumentsMetaDataResponse(dto, "get");

            detailsObj = docService.getDocumentDetailsVO(responseObj);
            } catch (Exception e) {
            return ApiResponse.error(103, e.getMessage()).notify(true).data(null);
        }
        if (detailsObj != null)
            return ApiResponse.success(100).data(detailsObj);
        else
            return ApiResponse.error(103, "Invalid Document UUID").notify(true).data(null);
    }

    @GetMapping("/viewDocument")
    public ResponseEntity<ApiResponse<DocumentLinkVO>> viewDocument(@RequestBody DocumentIdDTO dto) {

        ResponseEntity<String> responseObj = docService.getDocumentsMetaDataResponse(dto, "view");
        DocumentLinkVO linkObj = docService.getDocumentLinkVO(responseObj);
        if(linkObj != null )
            return ApiResponse.success(100).data(linkObj);
        else
            return ApiResponse.error(103, "Invalid Document UUID").notify(true).data(null);
    }

    @PostMapping(value = "/uploadDocument", headers = "Accept=multipart/form-data", consumes = {"multipart/form-data"})
    public ResponseEntity uploadDocument(@RequestParam("title") String title,
                                                                  @RequestParam("category_id") String categoryId,
                                                                  @RequestParam("meta_data") String metaDataJson,
                                                                  @RequestParam("file") MultipartFile file) {
        try {
            if (title != null && categoryId != null && metaDataJson != null && file != null) {

                ResponseEntity<String> response = docService.uploadDocumentToDocuEdge(title, categoryId, metaDataJson, file);
                DocumentDetailsVO obj = docService.getDocumentDetailsFromUpload(response);

                if (obj != null)
                    return ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(obj);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NO_CONTENT);
        }

        return  ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("Provide all the values");

    }

    @PostMapping("/getDocumentStream")
    public ResponseEntity<byte[]> getDocumentStream(@RequestBody DocumentIdDTO dto) {

        byte[] fileInputStream = docService.getDocumentStream(dto.getDocId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE));
//        if(null!= dto.getAction() && dto.getAction().equalsIgnoreCase("view")){
//            fileInputStream = java.util.Base64.getEncoder().encode(fileInputStream);
//        }
        fileInputStream = java.util.Base64.getEncoder().encode(fileInputStream);
        return new ResponseEntity<>(fileInputStream, headers, HttpStatus.OK);
    }

    @PostMapping("/documentDetailsUpdate")
    public ResponseEntity<String> documentDetailsUpdate(@RequestBody DocumentUpdateDTO dto) {
        ResponseEntity<String> responseEntity;
        try {
            responseEntity = docService.updateDocument(dto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getMessage()));
        }
        return responseEntity;
    }
}

