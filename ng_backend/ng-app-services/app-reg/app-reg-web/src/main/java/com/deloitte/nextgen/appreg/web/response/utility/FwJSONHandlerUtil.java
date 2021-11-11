package com.deloitte.nextgen.appreg.web.response.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;


import java.util.*;

@Slf4j
public class FwJSONHandlerUtil {


    public static final Map<String,Object> convertJSONToMap(String jsonString) {
       JsonParser jsonParser = JsonParserFactory.getJsonParser();
       return jsonParser.parseMap(jsonString);
    }

    public static final String convertMapToJSON(Map<String,Object> requestMap) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        return objMapper.writeValueAsString(requestMap);
    }
    public static String convertJsonToList(String jsonString) {
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        String smartStreeSuggestAddress ="";
         try {
             // Extract fields
             smartStreeSuggestAddress =  validAddress(jsonParser.parseList(jsonString));
          }catch (Exception e){
            log.error("Error in convertJsonToList method ", e);
        }
        return smartStreeSuggestAddress;
    }


    public static String  validAddress(List<Object> addressList){
        String validAddress = "";
        Map<String,Object> suggstedAddress =  new HashMap<>();
        try {
        if(null!=addressList && !addressList.isEmpty()){
            LinkedHashMap<Object,Object> res =   (LinkedHashMap<Object,Object>)  addressList.get(0);
            LinkedHashMap<Object,Object> componentsType = (LinkedHashMap<Object,Object>)  res.get("components");
            suggstedAddress.put("AddrLine1",(res.get("delivery_line_1")));
            suggstedAddress.put("City",(componentsType.get("city_name")));
            suggstedAddress.put("State",(componentsType.get("state_abbreviation")));
            suggstedAddress.put("Zipcode5",(componentsType.get("zipcode")));
            suggstedAddress.put("Zipcode4",(componentsType.get("plus4_code")));
        }
        validAddress = convertMapToJSON(suggstedAddress);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return validAddress;
    }
}