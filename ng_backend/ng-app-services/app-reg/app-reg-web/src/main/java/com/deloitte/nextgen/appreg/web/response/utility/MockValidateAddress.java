package com.deloitte.nextgen.appreg.web.response.utility;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MockValidateAddress {

    @Value("${address.smartystreet-service.url}")
    private String url;
    @Value("${address.smartystreet-service.auth-id}")
    private String authID;
    @Value("${address.smartystreet-service.auth-token}")
    private String authToken;

    private static final String ADDR_LINE_1 = "addrLine1";
    private static final String ADDR_LINE_2 = "addrLine2";
    private static final String CITY = "city";
    private static final String ZIP_CODE_4 = "zipCode4";
    private static final String ZIP_CODE_5 = "zipCode5";
    private static final String STATE_CD = "stateCd";
    private static final String COUNTRY_CD = "countryCd";
    private static final String DPVMATCH_CODE = "DPVMatchCode";
    private static final String DVP_MATCH_CD = "dvpMatchCd";
    private static final String BATON_ROUGE = "Baton Rouge";

    public String getValidAddressOne() throws JsonProcessingException {
        Map<String, Object> addressMap = new LinkedHashMap<>();
        addressMap.put(ADDR_LINE_1, "2000 Louisiana Ave");
        addressMap.put(ADDR_LINE_2, "2000 Louisiana Ave");
        addressMap.put(CITY, "New Orleans");
        addressMap.put(ZIP_CODE_4, "9998");
        addressMap.put(ZIP_CODE_5, "70115");
        addressMap.put(STATE_CD, "LA");
        addressMap.put(COUNTRY_CD, "US");
        addressMap.put(DPVMATCH_CODE, DVP_MATCH_CD);
        return  FwJSONHandlerUtil.convertMapToJSON(addressMap);
    }

    public String getValidAddressTwo() throws JsonProcessingException {
        Map<String, Object> addressMap = new LinkedHashMap<>();
        addressMap.put(ADDR_LINE_1, "627 N. Fourth St. ");
        addressMap.put(ADDR_LINE_2, "");
        addressMap.put(CITY, BATON_ROUGE);
        addressMap.put(ZIP_CODE_4, "");
        addressMap.put(ZIP_CODE_5, "70802");
        addressMap.put(STATE_CD, "LA");
        addressMap.put(COUNTRY_CD, "US");
        addressMap.put(DPVMATCH_CODE, DVP_MATCH_CD);
        return  FwJSONHandlerUtil.convertMapToJSON(addressMap);
    }

    public String getValidAddressThree() throws JsonProcessingException {
        Map<String, Object> addressMap = new LinkedHashMap<>();
        addressMap.put(ADDR_LINE_1, "P.O. Box 77308");
        addressMap.put(ADDR_LINE_2, "9125");
        addressMap.put(CITY, BATON_ROUGE);
        addressMap.put(ZIP_CODE_4, "");
        addressMap.put(ZIP_CODE_5, "70879");
        addressMap.put(STATE_CD, "LA");
        addressMap.put(COUNTRY_CD, "US");
        addressMap.put(DPVMATCH_CODE, DVP_MATCH_CD);
        return  FwJSONHandlerUtil.convertMapToJSON(addressMap);
    }

    public String getValidAddressFour() throws JsonProcessingException {
        Map<String, Object> addressMap = new LinkedHashMap<>();
        addressMap.put(ADDR_LINE_1, "8585 Archives Ave");
        addressMap.put(ADDR_LINE_2, "");
        addressMap.put(CITY, BATON_ROUGE);
        addressMap.put(ZIP_CODE_4, "9125");
        addressMap.put(ZIP_CODE_5, "70879");
        addressMap.put(STATE_CD, "LA");
        addressMap.put(COUNTRY_CD, "US");
        addressMap.put(DPVMATCH_CODE, DVP_MATCH_CD);
        return  FwJSONHandlerUtil.convertMapToJSON(addressMap);
    }
    public String getValidAddressFive() throws JsonProcessingException {
        Map<String, Object> addressMap = new LinkedHashMap<>();
        addressMap.put(ADDR_LINE_1, "7667 Independence Blvd");
        addressMap.put(ADDR_LINE_2, "");
        addressMap.put(CITY, BATON_ROUGE);
        addressMap.put(ZIP_CODE_4, "");
        addressMap.put(ZIP_CODE_5, "70806");
        addressMap.put(STATE_CD, "LA");
        addressMap.put(COUNTRY_CD, "US");
        addressMap.put(DPVMATCH_CODE, DVP_MATCH_CD);
        return  FwJSONHandlerUtil.convertMapToJSON(addressMap);
    }

    public String getValidAddressSix() throws JsonProcessingException {
        Map<String, Object> addressMap = new LinkedHashMap<>();
        addressMap.put(ADDR_LINE_1, "P. O. Box 629");
        addressMap.put(ADDR_LINE_2, "");
        addressMap.put(CITY, BATON_ROUGE);
        addressMap.put(ZIP_CODE_4, "0629");
        addressMap.put(ZIP_CODE_5, "70821");
        addressMap.put(STATE_CD, "LA");
        addressMap.put(COUNTRY_CD, "US");
        addressMap.put(DPVMATCH_CODE, DVP_MATCH_CD);
        return  FwJSONHandlerUtil.convertMapToJSON(addressMap);
    }


    public  String inputAddressRequest(String street1,String street2,String city,String state,String zipCode) throws JsonProcessingException{
        // invoke service call
        String suggestedAddress = "";
        StringBuilder strbuilder = new StringBuilder(getUrl());
        strbuilder.append("auth-id="+getAuthID());
        strbuilder.append("&");
        strbuilder.append("auth-token="+getAuthToken());
        strbuilder.append("&");
        strbuilder.append("street="+street1);
        strbuilder.append("&city="+city);
        strbuilder.append("&state="+state);
        strbuilder.append("&zipCode="+zipCode);
        RestTemplate restTemplate = new RestTemplate();
        String str =   restTemplate.getForObject(strbuilder.toString(),String.class);
        suggestedAddress =  FwJSONHandlerUtil.convertJsonToList(str);
        return  suggestedAddress;
    }
}
