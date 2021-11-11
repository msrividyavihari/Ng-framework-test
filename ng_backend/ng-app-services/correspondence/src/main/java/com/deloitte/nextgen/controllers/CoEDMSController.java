package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.dto.vo.CoEDMSUploadVO;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.deloitte.nextgen.dto.entities.CoEDMSMetadata;
import com.deloitte.nextgen.service.CoEDMSService;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/correspondence/edms/")
public class CoEDMSController {
    @Autowired
    CoEDMSService coEDMSService;

    @PostMapping(value={"/addDocument"})
    public ResponseEntity<ApiResponse<CoEDMSUploadVO>> addDocument(@RequestParam("title") String title,
                                                                   @RequestParam("category_id") String categoryId,
                                                                   @RequestParam("meta_data") String metadata,
                                                                   @RequestParam("file") MultipartFile file) {
        CoEDMSMetadata inMeta;
        CoEDMSUploadVO response = new CoEDMSUploadVO();
        try{
            Gson gson = new Gson();
            inMeta = gson.fromJson(metadata, CoEDMSMetadata.class);
            response = coEDMSService.addDocument(inMeta, file);
            return ApiResponse.success(100).data(response);
        } catch (Exception e) {
            return ApiResponse.error(400).data(response);
        }


    }

    @PostMapping(value={"/search"})
    public ResponseEntity<ApiResponse<byte[]>> searchForDocument(@RequestParam String docId) {
        byte[] response = coEDMSService.searchDocument(docId);
        return ApiResponse.success(100).data(response);
    }

}

