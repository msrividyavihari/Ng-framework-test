package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.entities.DocumentIdDTO;
import com.deloitte.nextgen.dto.entities.DocumentUpdateDTO;
import com.deloitte.nextgen.dto.vo.DocumentDetailsVO;
import com.deloitte.nextgen.dto.vo.DocumentLinkVO;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.service.AuthService;
import com.deloitte.nextgen.service.DocumentHandlerService;
import com.deloitte.nextgen.util.CoUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

@Data
@Service
@Slf4j
public class DocumentHandlerServiceImpl implements DocumentHandlerService {

    @Autowired
    AuthService auth;

    private String username;
    private String password;
    private String tokenUrl;
    private String uploadUrl;
    private String viewUrl;
    private String getUrl;
    private String getStreamUrl;
    private String updateDocumentUrl;


    DocumentHandlerServiceImpl() {
        populateValues();
    }

    private void populateValues() {
        Resource resource = new ClassPathResource("externalResource.properties");
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            Set<?> obj = properties.keySet();

            for(Object key: obj) {
                String k = (String) key;
                switch (k) {
                    case "docuEdge.username":
                        this.username = (String) properties.get(k);
                        break;
                    case "docuEdge.password":
                        this.password = (String) properties.get(k);
                        break;
                    case "docuEdge.tokenUrl":
                        this.tokenUrl = (String) properties.get(k);
                        break;
                    case "docuEdge.uploadUrl":
                        this.uploadUrl = (String) properties.get(k);
                        break;
                    case "docuEdge.viewUrl":
                        this.viewUrl = (String) properties.get(k);
                        break;
                    case "docuEdge.getUrl":
                        this.getUrl = (String) properties.get(k);
                        break;
                    case "docuEdge.getStreamUrl":
                        this.getStreamUrl = (String) properties.get(k);
                        break;
                    case "docuEdge.updateDocument" :
                        this.updateDocumentUrl = (String) properties.get(k);
                        break;
                }
            }

        } catch (IOException e) {
            log.error("Problem fetching the externalResource.properties file");
            e.printStackTrace();
        }
    }

    @Override
    public DocumentDetailsVO getDocumentDetailsVO(ResponseEntity<String> responseObj) throws FwApplicationException {
        DocumentDetailsVO detailsObj = null;
        if(responseObj != null) {
            String responseBody = responseObj.getBody();
            Map<String, Object> responseHashMap = jsonToMap(responseBody);
            detailsObj = fillDocumumentDetailsVO(responseHashMap);
        }
        return detailsObj;
    }

    private DocumentDetailsVO fillDocumumentDetailsVO(Map<String, Object> responseHashMap) throws FwApplicationException {
        DocumentDetailsVO detailsObj = new DocumentDetailsVO();
        if(responseHashMap != null) {
            Map<String, Object> metaDataHashMap = (Map<String, Object>) responseHashMap.get("metadata");
            Map<String, Object> systemHashMap = (Map<String, Object>) metaDataHashMap.get("system");
            Map<String, Object> propertiesHashMap = (Map<String, Object>) metaDataHashMap.get("properties");
            detailsObj.setDocumentId((String) responseHashMap.get("documentId"));
            detailsObj.setDocumentName((String) responseHashMap.get("documentName"));
            detailsObj.setDocumentVersionFileName((String) responseHashMap.get("documentVersionFileName"));
            detailsObj.setCategoryId(convertToLongFromDouble((Double) responseHashMap.get("categoryId")));
            detailsObj.setCategoryName((String) responseHashMap.get("categoryName"));
            detailsObj.setSize((double) systemHashMap.get("size"));
            detailsObj.setType((String) systemHashMap.get("type"));
            detailsObj.setFileName((String) systemHashMap.get("fileName"));
            detailsObj.setLastModifiedDate((String)systemHashMap.get("lastModifiedDate"));
            detailsObj.setClientId( convertToLongFromDouble((Double) propertiesHashMap.get("Client Id")));
            detailsObj.setCaseNumber(convertToLongFromDouble((Double) propertiesHashMap.get("Case Number")));
            detailsObj.setDocumentType((String) propertiesHashMap.get("Document Type"));
            detailsObj.setReferenceNumber(convertToLongFromDouble( (Double) propertiesHashMap.get("Reference Number")));
            detailsObj.setApplicationNumber(convertToLongFromDouble( (Double)  propertiesHashMap.get("Application Number")));
            detailsObj.setTransactionNumber((String) propertiesHashMap.get("Transaction Number"));
            detailsObj.setDocumentReceivedDateBeginDate(CoUtil.stringToTimestamp(propertiesHashMap.get("Document Received Date – Begin Date")));
            detailsObj.setDocumentReceivedDateEndDate(CoUtil.stringToTimestamp(propertiesHashMap.get("Document Received Date – End Date")));
        }
        return  detailsObj;
    }

    public ResponseEntity<String> getDocumentsMetaDataResponse(DocumentIdDTO dto, String type) {
        String token = auth.getToken(username, password);
        String requiredUrl = "";
        if(type.equals("view"))
            requiredUrl = viewUrl + dto.getDocId();
        if(type.equals("get"))
            requiredUrl = getUrl + dto.getDocId();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(requiredUrl, HttpMethod.GET, requestEntity, String.class, 1);
        return response;
    }

    public DocumentLinkVO getDocumentLinkVO(ResponseEntity<String> responseObj) {
        String body = responseObj.getBody();
        int begin = body.indexOf("http");
        DocumentLinkVO documentLink = new DocumentLinkVO();
        documentLink.setUrl(body.substring(begin, body.length()-1));
        return documentLink;
    }

    public ResponseEntity<String> uploadDocumentToDocuEdge(String title, String categoryId, String metaDataJson, MultipartFile file) {
//        populateValues();
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();

        String token = auth.getToken(username, password);
        Resource requiredFile = file.getResource();
        bodyMap.add("file", requiredFile);
        bodyMap.add("category_id", categoryId);
        bodyMap.add("meta_data", metaDataJson);
        bodyMap.add("title", title);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(uploadUrl,
                HttpMethod.POST, requestEntity, String.class);
        return response;
    }
    public ResponseEntity<String> uploadDocumentToDocuEdge(String title, String categoryId, String metaData, ByteArrayResource file) {
//        populateValues();
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();

        String token = auth.getToken(username, password);
        Resource requiredFile =  file;
        bodyMap.add("file", requiredFile);
        bodyMap.add("category_id", categoryId);
        bodyMap.add("meta_data", metaData);
        bodyMap.add("title", title);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(uploadUrl,
                HttpMethod.POST, requestEntity, String.class);
        return response;
    }

    public DocumentDetailsVO getDocumentDetailsFromUpload(ResponseEntity<String> response) throws FwApplicationException {
        String responseBody = response.getBody();
        Map<String, Object> responseHashMap = jsonToMap(responseBody);
        DocumentDetailsVO detailsObj = new DocumentDetailsVO();
        if(responseHashMap != null) {
            Map<String, Object> metaDataHashMap = (Map<String, Object>) responseHashMap.get("metadata");
            Map<String, Object> systemHashMap = (Map<String, Object>) metaDataHashMap.get("system");
            Map<String, Object> propertiesHashMap = (Map<String, Object>) metaDataHashMap.get("properties");

            detailsObj.setDocumentId((String) responseHashMap.get("docId"));
            detailsObj.setFileName((String) systemHashMap.get("fileName"));
            detailsObj.setLastModifiedDate((String)systemHashMap.get("lastModifiedDate"));
            detailsObj.setSize((double) systemHashMap.get("size"));
            detailsObj.setType((String) systemHashMap.get("type"));
            detailsObj.setCaseNumber(convertToLongFromDouble( (double) propertiesHashMap.get("Case Number") ));
            detailsObj.setApplicationNumber(convertToLongFromDouble( (double) propertiesHashMap.get("Application Number")));
            detailsObj.setClientId(convertToLongFromDouble( (double) propertiesHashMap.get("Client Id")) );
            detailsObj.setReferenceNumber(convertToLongFromDouble( (double) propertiesHashMap.get("Reference Number")) );
            detailsObj.setTransactionNumber((String) propertiesHashMap.get("Transaction Number"));
            detailsObj.setDocumentType((String) propertiesHashMap.get("Document Type"));
            detailsObj.setDocumentReceivedDateBeginDate(CoUtil.stringToTimestamp(propertiesHashMap.get("Document Received Date – Begin Date")));
            detailsObj.setDocumentReceivedDateEndDate(CoUtil.stringToTimestamp(propertiesHashMap.get("Document Received Date – End Date")));
            detailsObj.setTags(responseHashMap.get("tags").toString() );

        }

        return detailsObj;
    }

    public long convertToLongFromDouble(double doubleValue) {
        return Double.valueOf(doubleValue).longValue();
    }

    public Map<String, Object> jsonToMap(String responseJson)  {
        Map<String, Object> responseHashMap = null;
        if(!responseJson.equals("")) {
            responseHashMap = new Gson().fromJson(
                    responseJson, new TypeToken<HashMap<String, Object>>() {}.getType());
        }
        return responseHashMap;
    }

    public String mapToJson(Map<String, Object> metaData) {
        Gson gson = new Gson();
        Type gsonType = new TypeToken<HashMap>(){}.getType();
        String gsonString = gson.toJson(metaData,gsonType);
        return gsonString;
    }

    @SneakyThrows
    public byte[] getDocumentStream(String documentId) {
//        populateValues();
        String requiredUrl = getStreamUrl + documentId;
        System.out.println("requiredUrl: " + requiredUrl);
        String token = auth.getToken(username, password);
        HttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        List<HttpMessageConverter<?>> httpMessageConverter = new ArrayList<>();
        httpMessageConverter.add(stringHttpMessageConverter);
        URL url = new URL(requiredUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "Bearer " + token);
        conn.setRequestMethod("GET");
        InputStream is = conn.getInputStream();

        byte[] byteArray = IOUtils.toByteArray(is);
        return byteArray;
    }

    public ResponseEntity<String> updateDocument(DocumentUpdateDTO documentUpdateDTO) throws FwApplicationException {
        String requiredUrl = updateDocumentUrl;
        String token = auth.getToken(username, password);

        RestTemplate restTemplate = new RestTemplate();
        DocumentIdDTO documentIdDTO = new DocumentIdDTO();
        documentIdDTO.setDocId(documentUpdateDTO.getDocuedgeDocumentId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DocumentIdDTO> httpEntity = new HttpEntity<>(documentIdDTO, headers);
        ResponseEntity<ApiResponse<DocumentDetailsVO>> responseEntity = restTemplate.exchange("http://15.207.142.199/co/api/documentHandler/getDocument",
                HttpMethod.POST, new HttpEntity<>(documentIdDTO), new ParameterizedTypeReference<ApiResponse<DocumentDetailsVO>>(){});
        ApiResponse<DocumentDetailsVO> apiResponse = responseEntity.getBody();
        DocumentDetailsVO previousObj = apiResponse.getData();

        Map<String, Object> aMap = new HashMap<>();
        aMap.put("docId", documentUpdateDTO.getDocuedgeDocumentId());
        aMap.put("category_id", 260);
        Map<String, Object> properties = new HashMap<>();

        if(documentUpdateDTO.getCaseNum() != null && documentUpdateDTO.getCaseNum() > 0) {
            properties.put("Case Number", documentUpdateDTO.getCaseNum());
        } else {
            properties.put("Case Number", previousObj.getCaseNumber());
        }
        if(documentUpdateDTO.getAppNum() != null && documentUpdateDTO.getAppNum() > 0) {
            properties.put("Application Number", documentUpdateDTO.getAppNum());
        } else {
            properties.put("Application Number", previousObj.getApplicationNumber());
        }
        if(documentUpdateDTO.getClientId() != null && documentUpdateDTO.getClientId() > 0) {
            properties.put("Client Id", documentUpdateDTO.getClientId());
        } else {
            properties.put("Client Id", previousObj.getClientId());
        }
        if(documentUpdateDTO.getReferenceNumber() != null && documentUpdateDTO.getReferenceNumber() > 0) {
            properties.put("Reference Number", documentUpdateDTO.getReferenceNumber());
        } else {
            properties.put("Reference Number", previousObj.getReferenceNumber());
        }
        if(documentUpdateDTO.getTransactionNumber() != null && !documentUpdateDTO.getTransactionNumber().equals("")) {
            properties.put("Transaction Number", documentUpdateDTO.getTransactionNumber());
        } else {
            properties.put("Transaction Number", previousObj.getTransactionNumber());
        }
        if(documentUpdateDTO.getDocumentDescription() != null && !documentUpdateDTO.getDocumentDescription().equals("")) {
            properties.put("Document Type", documentUpdateDTO.getDocumentDescription());
        } else {
            properties.put("Document Type", previousObj.getDocumentType());
        }
        if(documentUpdateDTO.getDocumentReceivedBeginDate() != null) {
            properties.put("Document Received Date – Begin Date", CoUtil.dateToString(documentUpdateDTO.getDocumentReceivedBeginDate()));
        } else {
            properties.put("Document Received Date – Begin Date", CoUtil.dateToString(previousObj.getDocumentReceivedDateBeginDate()));
        }
        if(documentUpdateDTO.getDocumentReceivedEndDate() != null) {
            properties.put("Document Received Date – End Date", CoUtil.dateToString(documentUpdateDTO.getDocumentReceivedEndDate()));
        } else {
            properties.put("Document Received Date – End Date", CoUtil.dateToString(previousObj.getDocumentReceivedDateEndDate()));
        }
        aMap.put("properties", properties);
        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(aMap, headers);
        ResponseEntity<String> response = restTemplate.exchange(requiredUrl,
                HttpMethod.PUT, requestEntity, String.class);
        return response;
    }
}

