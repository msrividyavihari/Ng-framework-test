package com.deloitte.nextgen.controllers;


import com.deloitte.nextgen.dto.entities.DocumentInfoCollection;
import com.deloitte.nextgen.dto.entities.DocumentManagementSearchHistoryDTO;
import com.deloitte.nextgen.dto.vo.DocumentDetailsVO;
import com.deloitte.nextgen.dto.vo.DocumentManagementSearchHistoryVO;
import com.deloitte.nextgen.entities.InDisDocMaster;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.DocumentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/documentManagement/")
public class DocumentManagementController {

    @Autowired
    DocumentManagementService documentManagementService;

    @PostMapping(value = "/upload")
    public ResponseEntity<ApiResponse<ArrayList<DocumentDetailsVO>>> upload(@RequestBody DocumentInfoCollection documentInfoCollection) {
        ArrayList<DocumentDetailsVO> documentDetailsVOArrayList = null;
        try {
            documentDetailsVOArrayList = documentManagementService.uploadDocument(documentInfoCollection);
        } catch (Exception e) {
            return ApiResponse.error(103, e.getMessage()).notify(true).data(null);
        }
        if(documentDetailsVOArrayList != null )
            return ApiResponse.success(100).data(documentDetailsVOArrayList);
        else
            return ApiResponse.error(103, "Invalid Document UUID").notify(true).data(null);
    }


    @PostMapping(value = "/search")
    public ResponseEntity<ApiResponse<List<DocumentManagementSearchHistoryVO>>> search(@RequestBody DocumentManagementSearchHistoryDTO documentManagementSearchHistoryDTO) throws ParseException {
        try {
            List<InDisDocMaster>  inDisDocMasterList = documentManagementService.searchDocuments(documentManagementSearchHistoryDTO);
            List<DocumentManagementSearchHistoryVO> resultList = documentManagementService.processInDisDocMaster(inDisDocMasterList);
            if(resultList != null) {
                return ApiResponse.success(100).data(resultList);
            } else {
                return ApiResponse.error(103, "Something went wrong, Please enter correct details").notify(true).data(null);
            }
        } catch(FwApplicationException | ParseException e) {
            return ApiResponse.error(103, e.getMessage()).notify(true).data(null);
        }
    }

}
