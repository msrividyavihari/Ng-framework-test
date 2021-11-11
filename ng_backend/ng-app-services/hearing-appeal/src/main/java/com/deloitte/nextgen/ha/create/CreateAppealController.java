package com.deloitte.nextgen.ha.create;

import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.ha.create.dto.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping({"/v1/appeals"})
public class CreateAppealController {

    @Autowired
    private CreateAppealService createAppealServiceImpl;

    /** create an appeal **/
    @PutMapping("/createAppeal")
    @ApiOperation(
            value = "Appeal number is created"
            ,notes = " User can select an individuals and provides a demographic information to create an appeal"
            ,code = 201
    )
    @ApiResponses( {
             @io.swagger.annotations.ApiResponse(code=500, message="System (Technical) Error")
            ,@io.swagger.annotations.ApiResponse(code=401, message="Unauthorized Access")
            ,@io.swagger.annotations.ApiResponse(code=201, message="Create an appeal information.", responseContainer = "Set")
    })
    public ResponseEntity<ApiResponse<CreateAppealResonseDto>> createAppeal(@RequestBody FilingCreateAppealDto filingCreateAppealDto){

        CreateAppealResonseDto responseDto = createAppealServiceImpl.createAppeal(filingCreateAppealDto);
        return ApiResponse
                .success(100)
                .data(responseDto);
    }

    /** create an Authorized and program details **/
    @PutMapping("/{appealNum}/filing/update")
    @ApiOperation(
            value = "Create authorized details"
            ,notes = " User can select an individuals and provides a demographic information to create an appeal"
            ,code = 201
    )
    @ApiResponses( {
            @io.swagger.annotations.ApiResponse(code=500, message="System (Technical) Error")
            ,@io.swagger.annotations.ApiResponse(code=401, message="Unauthorized Access")
            ,@io.swagger.annotations.ApiResponse(code=201, message="Create an appeal information.", responseContainer = "Set")
    })
    public ResponseEntity<ApiResponse<String>> createAuthRepInfo(@NonNull @PathVariable("appealNum") BigInteger appealNum, @RequestBody FilingUpdateAppealDto filingUpdateAppealDto){

        filingUpdateAppealDto.setAppealNum(appealNum);
        createAppealServiceImpl.createAuthRepData(filingUpdateAppealDto);
        return ApiResponse.success(100).data("{}");
    }

    /** retrieve an appeal **/
    @GetMapping("/retrieveAppeal/{appealNum}")
    @ApiOperation(
            value = "Retrieves the created appeal number details"
            ,notes = "User when click on back button or navigates from appeal overview page will fetch the demographic,individual and appeal details "
            + " If no appeal information found exception is thrown"
            ,code = 200
    )
    @ApiResponses( {
            @io.swagger.annotations.ApiResponse(code=400, message="Invalid Appeal Number")
            ,@io.swagger.annotations.ApiResponse(code=500, message="System (Technical) Error")
            ,@io.swagger.annotations.ApiResponse(code=401, message="Unauthorized Access")
            ,@io.swagger.annotations.ApiResponse(code=200, message="OK. Fetches the appeal information", responseContainer = "Set")
    })
    public ResponseEntity<ApiResponse<FilingCreateAppealResponseDto>>  retrieveAppeal(@NonNull  @PathVariable("appealNum") BigInteger appealNum){

        FilingCreateAppealResponseDto appealResponseDto = createAppealServiceImpl.retrieveAppeal(appealNum);

        return ApiResponse
                .success(100)
                .data(appealResponseDto);
    }



    /** Retrieve the  authorized information **/
    @GetMapping("/retrieveAuthRepDetails/{appealNum}")
    @ApiOperation(
            value = "Retrieves the Authorized details and program information"
            ,notes = "User when click on back button or navigates from appeal overview page will fetch the all existing authorized reps and program info" +
            "associated with appeal "
            + " If no appeal information found exception is thrown"
            ,code = 200
    )
    @ApiResponses( {
            @io.swagger.annotations.ApiResponse(code=400, message="Invalid Appeal Number")
            ,@io.swagger.annotations.ApiResponse(code=500, message="System (Technical) Error")
            ,@io.swagger.annotations.ApiResponse(code=401, message="Unauthorized Access")
            ,@io.swagger.annotations.ApiResponse(code=200, message="OK. Fetches the appeal information", responseContainer = "Set")
    })
    public ResponseEntity<ApiResponse<FilingCreateAppealResponseDto>>  retrieveAuthRepDetails(@NonNull  @PathVariable("appealNum") BigInteger appealNum){

       FilingCreateAppealResponseDto filingCreateResponse = createAppealServiceImpl.retrieveAuthRepInfo(appealNum);
        return ApiResponse
                .success(100)
                .data(filingCreateResponse);
    }


}

