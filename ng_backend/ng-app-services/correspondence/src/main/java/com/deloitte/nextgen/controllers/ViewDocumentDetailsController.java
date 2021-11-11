package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.dto.entities.ViewDocumentDetailsDTO;
import com.deloitte.nextgen.dto.vo.ViewDocumentDetailsVO;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.CoViewDocumentDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/correspondence/documentDetails")
public class ViewDocumentDetailsController {

    @Autowired
    CoViewDocumentDetailsService coViewDocumentDetailsService;

    @PostMapping("/metaDataChange")
    public ResponseEntity<ApiResponse<ViewDocumentDetailsVO>> metaDataChange(@RequestBody ViewDocumentDetailsDTO dto){
        ViewDocumentDetailsVO vo = null;
        try {
            vo = coViewDocumentDetailsService.metaDataChange(dto);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.error(103, e.getMessage() ).notify(true).data(null);
        }
        if(vo != null )
            return ApiResponse.success(100).data(vo);
        return ApiResponse.error(103, "Cannot do this operation").
                notify(true).data(null);
    }

    @PostMapping("/DelinkAndLink")
    public ResponseEntity<ApiResponse<ViewDocumentDetailsVO>> DelinkAndLink(
            @RequestBody ViewDocumentDetailsDTO dto) {
        ViewDocumentDetailsVO vo = null;
        try {
            vo = coViewDocumentDetailsService.delinkAndLink(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(vo != null )
            return ApiResponse.success(100).data(vo);
        return ApiResponse.error(103, "Cannot do this operation").
                notify(true).data(null);
    }

}
