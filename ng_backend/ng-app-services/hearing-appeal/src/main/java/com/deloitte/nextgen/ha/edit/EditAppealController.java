package com.deloitte.nextgen.ha.edit;

import com.deloitte.nextgen.ha.edit.dto.EditAppealDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.math.BigInteger;

@RestController
@RequestMapping("/v1/appeals")
@Slf4j
@RequiredArgsConstructor
/*@Api(tags="Appeals")*/
public class EditAppealController {

    private final EditAppealService editAppealService;

    @PostMapping("/edit")
    public ResponseEntity<com.deloitte.nextgen.framework.web.response.ApiResponse<String>> findByAppealNum(@RequestBody EditAppealDto editAppealDto) {
        editAppealService.editAppealNum(editAppealDto);
        return com.deloitte.nextgen.framework.web.response.ApiResponse.success(100).data("{}");
    }

}