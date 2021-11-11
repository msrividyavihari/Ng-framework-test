package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.entities.*;
import com.deloitte.nextgen.dto.vo.OPLogInResponseVO;
import com.deloitte.nextgen.entities.MailingAddress;
import com.deloitte.nextgen.entities.NoticeRequestStatus;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.deloitte.nextgen.repository.MailingAddressRepository;
import com.deloitte.nextgen.repository.NoticeHistoryRepository;
import com.deloitte.nextgen.service.PDFBoxService;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import com.deloitte.nextgen.util.NoticeStatus;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.*;

@Service
@Slf4j
public class PDFBoxServiceImpl implements PDFBoxService {

	@Autowired
	NoticeHistoryRepository noticeHistoryRepository;

	@Autowired
	MailingAddressRepository mailingAddressRepository;

	private String OPENTEXT_LOGIN_URL;
	private String OPENTEXT_GET_DOCUMENT_URL;
	private String USERNAME;
	private String PASSWORD;
	private String CLIENT_ID;
	private String SCOPE;
	private String GRANT_TYPE;

	public PDFBoxServiceImpl() throws Exception{
		populateStaticValues();
	}

	private void populateStaticValues() throws IOException {
		log.info("Fetching values for opentext from .properties file - starts here");

		Resource resource =  new ClassPathResource("externalResource.properties");
		Properties properties = PropertiesLoaderUtils.loadProperties(resource);
		Set<?> obj = properties.keySet();

		for(Object key: obj) {
			String k = (String) key;
			switch(k) {
				case CoConstants.OPENTEXT +
						CoConstants.DOT + CoConstants.GET_DOCUMENT_URL:
					OPENTEXT_GET_DOCUMENT_URL = (String) properties.get(k);
					break;
				case CoConstants.OPENTEXT +
						CoConstants.DOT + CoConstants.USERNAME:
					USERNAME = (String) properties.get(k);
					break;
				case CoConstants.OPENTEXT +
						CoConstants.DOT + CoConstants.PASSWORD:
					PASSWORD =(String) properties.get(k);
					break;
				case CoConstants.OPENTEXT +
						CoConstants.DOT + CoConstants.CLIENT_ID:
					CLIENT_ID = (String) properties.get(k);
					break;
				case CoConstants.OPENTEXT +
						CoConstants.DOT + CoConstants.SCOPE:
					SCOPE = (String) properties.get(k);
					break;
				case CoConstants.OPENTEXT +
						CoConstants.DOT + CoConstants.GRANT_TYPE:
					GRANT_TYPE= (String) properties.get(k);
					break;
				case CoConstants.OPENTEXT +
						CoConstants.DOT + CoConstants.LOGIN_URL:
					OPENTEXT_LOGIN_URL = (String) properties.get(k);
					break;
			}
		}

		log.info("Successfully fetched values for opentext from .properties file");
	}

	@Override
	public byte[] downloadGeneralCorrespondencePDF(String xmlFile) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlFile)));

			PDDocument document = populatePdfDatafromXML(doc);

			// Save the results and ensure that the document is properly closed:
			document.save(outputStream);
			document.close();
		} catch (IOException ie) {
			System.out.println("No template found");
		} catch (Exception s) {
			s.printStackTrace();
			System.out.println("System Crasshh!!");
		}
		return outputStream.toByteArray();
	}


	private PDDocument populatePdfDatafromXML(final Document doc) throws IOException {

		NodeList metaDataNodeList = doc.getElementsByTagName("metaData");
		LinkedHashMap<String, String> metaDataMap = populateMetaData(metaDataNodeList);

		final String templateId = metaDataMap.get("templateId");
		NodeList noticeDataNodeList = doc.getElementsByTagName("formData");

		metaDataMap.putAll(populateFormData(noticeDataNodeList, templateId));
		String location = new FileSystemResource("").getFile().getAbsolutePath();
		String pdfTemplateLocation = "";

		if(templateId.equalsIgnoreCase("FGGA0014")){
			pdfTemplateLocation = location +"\\src\\main\\resources\\pdfTemplate\\MassMailing.pdf";
		}else{
			pdfTemplateLocation = location +"\\src\\main\\resources\\pdfTemplate\\GeneralCorrespondence.pdf";
		}
		PDDocument pdfTemplate = PDDocument.load(new File(pdfTemplateLocation));
		PDAcroForm acroForm = pdfTemplate.getDocumentCatalog().getAcroForm();

		for (Map.Entry<String, String> entry : metaDataMap.entrySet()) {
			if (null != acroForm.getField(entry.getKey())) {
				acroForm.getField(entry.getKey()).setValue(entry.getValue());
			}
		}

		pdfTemplate.addPage(pdfTemplate.getPage(0));
		return pdfTemplate;

	}

	private LinkedHashMap<String, String> populateFormData(final NodeList noticeDataNodeList,
														   final String templateId) {
		Node node1 = noticeDataNodeList.item(0);
		Element element1 = (Element) node1;
		LinkedHashMap<String, String> metaDataMap = new LinkedHashMap<String, String>();
		ArrayList metaDataList;
		try {
			String noticeLocation = new FileSystemResource("").getFile().getAbsolutePath() +"\\src\\main\\resources\\json\\"
					+ templateId + ".json";
			metaDataList = new Gson().fromJson(new FileReader(noticeLocation), ArrayList.class);

			for (Object metaData : metaDataList) {
				if(null != element1.getElementsByTagName(metaData.toString()).item(0)) {
					metaDataMap.put((String) metaData,
							(element1.getElementsByTagName(metaData.toString()).item(0).getTextContent()));
				}
			}
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return metaDataMap;

	}

	private LinkedHashMap<String, String> populateMetaData(final NodeList metaDataNodeList) {
		Node node1 = metaDataNodeList.item(0);
		Element element1 = (Element) node1;
		LinkedHashMap<String, String> metaDataMap = new LinkedHashMap<String, String>();
		ArrayList metaDataList;
		try {
			String location = new FileSystemResource("").getFile().getAbsolutePath() +"\\src\\main\\resources\\json\\CorrespondenceMetaData.json";

			metaDataList = new Gson().fromJson(new FileReader(location), ArrayList.class);

			for (Object metaData : metaDataList) {
				if(null != element1.getElementsByTagName(metaData.toString()).item(0)) {
					metaDataMap.put((String) metaData,
							(element1.getElementsByTagName(metaData.toString()).item(0).getTextContent()));
				}

			}
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return metaDataMap;

	}

	public ClientCommunicationResponse clientCommunication(BatchNotice batchNotice) throws Exception {
		ClientCommunicationResponse clientRes = new ClientCommunicationResponse();
		Boolean result = false;
		MetaData metaData = batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList()
				.getCorr().getCustomer().get(0).getNoticeRequest().getMetaData();
		Character goGreen = metaData.getGoGreen();
		if(goGreen == CoConstants.YES) {
			ClientCommunicationDTO clientComm = sendEmailOrText(batchNotice);
			if(clientComm.getClientCommResponse() == null) {
				throw new Exception("There is no response from Email-Text Service");
			}
			if(metaData.getCommunicationMode() == (CoConstants.CHAR_E)) {
				if(clientComm.getClientCommResponse().equals(CoConstants.SUCCESS)) {
					metaData.setEmailDeliveryStatus(CoConstants.CHAR_F);
					ResponseEntity<byte[]> responseObj = getDocumentFromOpenText(batchNotice);
					metaData.setEmailDeliveryStatus(CoConstants.CHAR_S);
					metaData.setStatus(NoticeStatus.PR.name());
					logNoticeCustomer(batchNotice);
					clientRes.setResponseEntity(responseObj);
					clientRes.setFlag(true);
				}else {
					metaData.setStatus(NoticeStatus.FL.name());
					metaData.setEmailDeliveryStatus(CoConstants.CHAR_F);
					metaData.setReasonForFailure(clientComm.getReasonForFailure());
					logNoticeCustomer(batchNotice);
					clientRes.setFlag(false);
				}
			} else if(metaData.getCommunicationMode() == (CoConstants.CHAR_M)) {
				if(clientComm.getClientCommResponse().equals(CoConstants.SUCCESS)) {
					metaData.setTextNotificationStatus(CoConstants.CHAR_F);
					ResponseEntity<byte[]> responseObj = getDocumentFromOpenText(batchNotice);
					metaData.setTextNotificationStatus(CoConstants.CHAR_S);
					metaData.setStatus(NoticeStatus.PR.name());
					metaData.setTextNotificationStatus(CoConstants.CHAR_S);
					logNoticeCustomer(batchNotice);
					clientRes.setResponseEntity(responseObj);
					clientRes.setFlag(true);
				} else {
					metaData.setStatus(NoticeStatus.FL.name());
					metaData.setTextNotificationStatus(CoConstants.CHAR_F);
					metaData.setReasonForFailure(clientComm.getReasonForFailure());
					logNoticeCustomer(batchNotice);
					clientRes.setFlag(false);
				}
			}
		}
		return clientRes;
	}

	public ClientCommunicationDTO sendEmailOrText(BatchNotice batchNotice) throws Exception {
		ClientCommunicationDTO clientComDto = getClientCommunicationDTO(batchNotice);
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<ClientCommunicationDTO> requestEntity = new HttpEntity<>(clientComDto, headers);
		ResponseEntity<ApiResponse<ClientCommunicationDTO>> responseEntity = restTemplate.exchange(
				"http://localhost:8086/co/api/communication/electronicTrigger",
				HttpMethod.POST, requestEntity,
				new ParameterizedTypeReference<ApiResponse<ClientCommunicationDTO>>(){});

		System.out.println("clientCommunicationDTO: " + responseEntity.getBody().getData());

		if(responseEntity.getBody() == null) {
			throw new Exception("cannot insert data into the table");
		}
		return responseEntity.getBody().getData();
	}

	private ClientCommunicationDTO getClientCommunicationDTO(BatchNotice batchNotice) {
		ClientCommunicationDTO dto = new ClientCommunicationDTO();
		MetaData metaData = batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList()
				.getCorr().getCustomer().get(0).getNoticeRequest().getMetaData();
		dto.setEmailId(metaData.getWorkeremailAddress());
		dto.setCellPhoneNumber(String.valueOf(metaData.getPhoneNumber()));
		dto.setPreferredCommunication(metaData.getCommunicationMode().toString());
		System.out.println(dto);
		return  dto;
	}

	public ResponseEntity<byte[]> getDocument(BatchNotice batchNotice) throws Exception {

		NoticeRequest noticeRequest = batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList()
				.getCorr().getCustomer().get(0).getNoticeRequest();

		if(noticeRequest.getAction() != null) {

			if(noticeRequest.getAction().equals(CoConstants.GEN_PREVIEW)) {

				NoticeRequestMailingAddress res = saveNoticeRequestAndMailingAdd(batchNotice, NoticeStatus.RR.name());
				MailingAddress mailingAddress = res.getMailingAddress();
				validateRequest(batchNotice);
				batchNotice = validateAddressContent(batchNotice, mailingAddress);
				noticeRequest.getMetaData().setStatus(NoticeStatus.PO.name());
				logNoticeCustomer(batchNotice);

			} else if(noticeRequest.getAction().equals(CoConstants.GEN_LOCAL_PRINT)) {

				NoticeRequestMailingAddress res = saveNoticeRequestAndMailingAdd(batchNotice, NoticeStatus.RR.name());
				MailingAddress mailingAddress = res.getMailingAddress();
				validateRequest(batchNotice);
				batchNotice = validateAddressContent(batchNotice, mailingAddress);
				noticeRequest.getMetaData().setStatus(NoticeStatus.LP.name());
				logNoticeCustomer(batchNotice);
				noticeRequest.getMetaData().setStatus(NoticeStatus.PP.name());
				logNoticeCustomer(batchNotice);

			} else if(noticeRequest.getAction().equals(CoConstants.GEN_CENTRAL_PRINT)) {

				NoticeRequestMailingAddress res = saveNoticeRequestAndMailingAdd(batchNotice, NoticeStatus.RR.name());
				MailingAddress mailingAddress = res.getMailingAddress();
				validateRequest(batchNotice);
				batchNotice = validateAddressContent(batchNotice, mailingAddress);
				noticeRequest.getMetaData().setStatus(NoticeStatus.PP.name());

				logNoticeCustomer(batchNotice);
				return new ResponseEntity<>(new byte[0], HttpStatus.OK);

			} else if(noticeRequest.getAction().equals(CoConstants.VIEW_PREVIEW)) {

				batchNotice = getBatchNotice(noticeRequest.getMetaData().getLogRequestId());
				batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList().getCorr().getCustomer()
						.get(0).getNoticeRequest().getMetaData().setStatus(NoticeStatus.PP.name());
				logNoticeCustomer(batchNotice);

			} else if(noticeRequest.getAction().equals(CoConstants.VIEW_LOCAL_PRINT)) {

				batchNotice = getBatchNotice(noticeRequest.getMetaData().getLogRequestId());
				batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList().getCorr().getCustomer()
						.get(0).getNoticeRequest().getMetaData().setStatus(NoticeStatus.LP.name());
				logNoticeCustomer(batchNotice);

			} else if(noticeRequest.getAction().equals(CoConstants.VIEW_CENTRAL_PRINT)) {
				batchNotice = getBatchNotice(noticeRequest.getMetaData().getLogRequestId());
				batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList().getCorr().getCustomer()
						.get(0).getNoticeRequest().getMetaData().setStatus(NoticeStatus.PP.name());
				logNoticeCustomer(batchNotice);
				return new ResponseEntity<>(new byte[0], HttpStatus.OK);
			}
		} else {

			NoticeRequestMailingAddress res = saveNoticeRequestAndMailingAdd(batchNotice, NoticeStatus.RR.name());
			validateRequest(batchNotice);
			ClientCommunicationResponse clientObj = clientCommunication(batchNotice);
			if(clientObj.getFlag() != null && clientObj.getFlag()) {
				return clientObj.getResponseEntity();
			}
			MailingAddress mailingAddress = findByMailingAddressId(res.getMailingAddress().getMailingAddrId());
			// validates address here
			batchNotice = validateAddressContent(batchNotice, mailingAddress);
			noticeRequest.getMetaData().setStatus(NoticeStatus.PP.name());
			Character addressUpdated = batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList().getCorr().getCustomer()
					.get(0).getNoticeRequest().getMetaData().getAddressUpdated();
			if(addressUpdated != null && addressUpdated == 'Y')
				batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList().getCorr()
						.getCustomer().get(0).getNoticeRequest().getMetaData().setAddressUpdated('Y');
			logNoticeCustomer(batchNotice);
		}

		return getDocumentFromOpenText(batchNotice);
	}

	private MailingAddress findByMailingAddressId(Long logId) {
		return mailingAddressRepository.findByMailingAddrId(logId);
	}

	private ResponseEntity<byte[]> getDocumentFromOpenText(BatchNotice batchNotice)
			throws Exception {
		try {
			String token = getToken();
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);
			headers.set("Content-Type", "application/json");
			headers.set("Accept-Encoding", "gzip, deflate, br");
			headers.set("Accept", "application/pdf");
			String str = CoUtil.batchNoticeToJson(batchNotice);
			HttpEntity<String> requestEntity = new HttpEntity<>(str, headers);

			ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(OPENTEXT_GET_DOCUMENT_URL,
					requestEntity, byte[].class);
			return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList().getCorr().getCustomer()
					.get(0).getNoticeRequest().
					getMetaData().setStatus(NoticeStatus.CF.name());
			batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList().getCorr().getCustomer()
					.get(0).getNoticeRequest().
					getMetaData().setReasonForFailure("Open text service is down");
			logNoticeCustomer(batchNotice);
			throw e;
		}
	}

	private BatchNotice getBatchNotice(Long logRequestId) throws Exception {
		List<NoticeRequestStatus> noticeList = noticeHistoryRepository.
				findByLogRequestId(logRequestId);
		if(noticeList == null || noticeList.size() == 0) {
			log.error("no data in NOTICE_REQUEST_STATUS for logRequestId: " + logRequestId);
			throw new Exception("no data in NOTICE_REQUEST_STATUS for logRequestId: " + logRequestId);
		}
		NoticeRequestStatus obj = noticeList.get(0);
		return CoUtil.jsonToBatchNotice(obj.getRequestJson());
	}

	public NoticeRequestMailingAddress saveNoticeRequestAndMailingAdd(BatchNotice batchNotice, String status)
			throws Exception{

		NoticeRequestMailingAddress result = new NoticeRequestMailingAddress();

		NoticeRequest noticeRequest = batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList()
				.getCorr().getCustomer().get(0).getNoticeRequest();
		MailingAddress mailingAddress = getMailingAddress(noticeRequest.getFormData().getMailingAdd(),
				CoConstants.CHAR_O);
		noticeRequest.getMetaData().setStatus(status);
		// insert a row in Notice_Request_Status
		NoticeRequestStatus n = logNoticeCustomer(batchNotice);
		System.out.println("noticeRequestId: " + n.getNoticeRequestId());
		mailingAddress.setNoticeRequestId(n.getNoticeRequestId());
		// insert a row in Mailing_Address
		mailingAddress = logMailingAddress(mailingAddress);

		System.out.println("after insert mailingAdd: " + mailingAddress);

		result.setNoticeRequestStatus(n);
		result.setMailingAddress(mailingAddress);
		return result;
	}

	private MailingAddress updateMailingAddress(MailingAddress mailingAddress)  throws Exception{
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<MailingAddress> requestEntity = new HttpEntity<>(mailingAddress, headers);
		ResponseEntity<MailingAddress> responseEntity = restTemplate.exchange(
				"http://localhost:8086/co/api/correspondence/noticeRequest/mailing-address-update-1",
				HttpMethod.PUT, requestEntity, MailingAddress.class);

		if(responseEntity.getBody() == null) {
			throw new Exception("cannot insert data into the table");
		}
		return responseEntity.getBody();
	}

	private MailingAddress logMailingAddress(MailingAddress mailingAddress)  throws Exception{
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<MailingAddress> requestEntity = new HttpEntity<>(mailingAddress, headers);
		ResponseEntity<MailingAddress> responseEntity = restTemplate.exchange(
				"http://localhost:8086/co/api/correspondence/noticeRequest/mailing-address-update",
				HttpMethod.POST, requestEntity, MailingAddress.class);

		if(responseEntity.getBody() == null) {
			throw new Exception("cannot insert data into the table");
		}
		return responseEntity.getBody();
	}

	private NoticeRequestStatus logNoticeCustomer(BatchNotice batchNotice) throws Exception {
		System.out.println("logNoticeRequest: " + batchNotice);
		NoticeRequest noticeRequest = batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList()
				.getCorr().getCustomer().get(0).getNoticeRequest();
		batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList().getCorr().getCustomer()
				.get(0).getNoticeRequest().setRequestJson(CoUtil.batchNoticeToJson(batchNotice));
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<NoticeRequest> requestEntity = new HttpEntity<>(noticeRequest, headers);
		ResponseEntity<NoticeRequestStatus> responseEntity = restTemplate.exchange(
				"http://localhost:8086/co/api/correspondence/noticeRequest/status-update",
				HttpMethod.POST, requestEntity, NoticeRequestStatus.class);

		if(responseEntity.getBody() == null) {
			throw new Exception("cannot insert data into the table");
		}
		return responseEntity.getBody();
	}

	public String getToken() throws Exception {
		String requestStr = getCredentials();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<String> entity = new HttpEntity<>(requestStr, headers);
		OPLogInResponseVO resObj;
		try {
			resObj = restTemplate.postForObject(OPENTEXT_LOGIN_URL,
					entity, OPLogInResponseVO.class);
		} catch(Exception e) {
			log.error("cannot contact with opentext to get token: "+e.getMessage());
			throw e;
		}
		return resObj.getAccess_token();
	}

	private String getCredentials() {
		return "grant_type=" + GRANT_TYPE +
				"&username=" + USERNAME +
				"&password=" + PASSWORD +
				"&client_id=" + CLIENT_ID +
				"&scope=" + SCOPE;
	}

	public BatchNotice validateAddressContent(BatchNotice batchNotice,
												 MailingAddress mailingAddress) throws Exception{
		NoticeRequest noticeRequest = batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0)
				.getCorrList().getCorr().getCustomer().get(0).getNoticeRequest();
		CoAddressDTO coAddressDTO = getCoAddressDto(noticeRequest);
		Long noticeRequestId = noticeRequest.getMetaData().getRequestId();

		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<CoAddressDTO> requestEntity = new HttpEntity<>(coAddressDTO, headers);
		ResponseEntity<ApiResponse<List<CoAddressDTO>>> responseEntity = restTemplate.exchange(
				"http://localhost:8086/co/api/correspondence/address/validateAddress",
				HttpMethod.POST, requestEntity,
				new ParameterizedTypeReference<ApiResponse<List<CoAddressDTO>>>(){});
		List<CoAddressDTO> coAddressDTOList = responseEntity.getBody().getData();
		if(coAddressDTOList.size() == 0) {
			log.error("no data from address validation service end point");
			throw new Exception("no data from address validation service end point");
		}

		System.out.println("CoAddressDTO: " + coAddressDTO);

		coAddressDTO = coAddressDTOList.get(0);
		Character match = coAddressDTO.getMatchCode();

		// No Match
		if(match == CoConstants.CHAR_U) {
			noticeRequest.getMetaData().setStatus(NoticeStatus.IA.name());
			logNoticeCustomer(batchNotice);
			throw new Exception(NoticeStatus.IA.getStatusVal() + " for request_id: "
					+ noticeRequestId);
		}
		//partial match
		else if (match == CoConstants.CHAR_N) {
			mailingAddress.setUpdStreet1(coAddressDTO.getStreet());
			mailingAddress.setUpdStreet2(coAddressDTO.getStreet2());
			mailingAddress.setUpdCity(coAddressDTO.getCity());
			mailingAddress.setUpdState(coAddressDTO.getState());
			mailingAddress.setUpdZip4(Long.valueOf(coAddressDTO.getZipCode()));
			mailingAddress = updateMailingAddress(mailingAddress);

			noticeRequest.getMetaData().setAddressUpdated(CoConstants.YES);

			MailingAdd updatedAddress = new MailingAdd();

			updatedAddress.setStreet1(mailingAddress.getUpdStreet1());
			updatedAddress.setStreet2(mailingAddress.getUpdStreet2());
			updatedAddress.setCity(mailingAddress.getUpdCity());
			updatedAddress.setState(mailingAddress.getUpdState());
			updatedAddress.setZip4(mailingAddress.getUpdZip4());
			updatedAddress.setZip5(mailingAddress.getUpdZip5());

			noticeRequest.getFormData().setMailingAdd(updatedAddress);

		}
		return batchNotice;
	}


	private NoticeRequestStatus updateAddressNoticeCustomer(NoticeRequest noticeRequest) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<NoticeRequest> requestEntity = new HttpEntity<>(noticeRequest, headers);
		ResponseEntity<NoticeRequestStatus> responseEntity = restTemplate.exchange(
				"http://localhost:8086/co/api/correspondence/noticeRequest/update-request",
				HttpMethod.POST, requestEntity, NoticeRequestStatus.class);

		if(responseEntity.getBody() == null) {
			throw new Exception("cannot update data into the table for logRequestId: " + noticeRequest.getMetaData().getLogRequestId());
		}
		return responseEntity.getBody();
	}


	/**
	 * This method validate incoming request, and if the request is invalid, then it inserts
	 * a row in Notice_Request_Status with status as Invalid Request
	 * @param batchNotice BatchNotice
	 * @throws Exception
	 */
	public void validateRequest(BatchNotice batchNotice) throws Exception {
		NoticeRequest noticeRequest = batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList()
				.getCorr().getCustomer().get(0).getNoticeRequest();
		try {
			validateRequestContent(batchNotice);
		} catch(Exception e) {
			log.error(e.getMessage());
			noticeRequest.getMetaData().setStatus(NoticeStatus.IR.name());
			logNoticeCustomer(batchNotice);
			throw e;
		}
	}

	public void validateRequestContent(BatchNotice batchNotice) throws Exception{
		log.info("Validation of noticeRequest - starts here");

		if(batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0) == null) {
			throw new Exception("Request Invalid");
		}

		NoticeRequest noticeRequest = batchNotice.getBatch().getEnvelopeList().getEnvelope().get(0).getCorrList().getCorr().getCustomer()
				.get(0).getNoticeRequest();
		com.deloitte.nextgen.dto.entities.MetaData metaData = noticeRequest.getMetaData();
		FormData formData = noticeRequest.getFormData();

		Long caseNum = metaData.getCaseNum();
		CoUtil.isEmptyLong(caseNum, "case number");

		String templateId = metaData.getTemplateId();
		CoUtil.isEmptyString(templateId, "template Id");

		String clientName = formData.getClientName();
		CoUtil.isEmptyString(clientName, "client name");

		Long requestId = metaData.getRequestId();
		CoUtil.isEmptyLong(requestId, "request Id");

//		String agencyName = metaData.getAgencyName();
//		CoUtil.isEmptyString(agencyName, "agency name");
//
//		String agencyId = metaData.getAgencyId();
//		CoUtil.isEmptyString(agencyId, "agency Id");

		log.info("Validation of noticeRequest - completed here");
	}

	private MailingAdd getMailingAdd(CoAddressDTO coAddressDTO) {
		MailingAdd mailingAdd = new MailingAdd();
		mailingAdd.setStreet1(coAddressDTO.getStreet());
		mailingAdd.setStreet2(coAddressDTO.getStreet2());
		mailingAdd.setState(coAddressDTO.getState());
		mailingAdd.setCity(coAddressDTO.getCity());
		mailingAdd.setZip4(Long.valueOf(coAddressDTO.getZipCode()));
		return mailingAdd;
	}


	private MailingAddress getMailingAddress(MailingAdd mailingAdd, Character flag) {
		MailingAddress result = new MailingAddress();
		if(flag != null && flag == CoConstants.CHAR_O) {
			result.setOrigStreet1(mailingAdd.getStreet1());
			result.setOrigStreet2(mailingAdd.getStreet2());
			result.setOrigState(mailingAdd.getState());
			result.setOrigCity(mailingAdd.getCity());
			result.setOrigZip4(mailingAdd.getZip4());
			result.setOrigZip5(mailingAdd.getZip5());
		} else {
			result.setUpdStreet1(mailingAdd.getStreet1());
			result.setUpdStreet2(mailingAdd.getStreet2());
			result.setUpdState(mailingAdd.getState());
			result.setUpdCity(mailingAdd.getCity());
			result.setUpdZip4(mailingAdd.getZip4());
			result.setUpdZip5(mailingAdd.getZip5());
		}
		return result;
	}

	public CoAddressDTO getCoAddressDto(NoticeRequest noticeRequest) {
		MailingAdd mailingAdd = noticeRequest.getFormData().getMailingAdd();
		String clientName = noticeRequest.getFormData().getClientName();
		CoAddressDTO coAddressDTO = new CoAddressDTO();
		coAddressDTO.setAddressee(clientName);
		coAddressDTO.setStreet(mailingAdd.getStreet1());
		coAddressDTO.setStreet2(mailingAdd.getStreet2());
		coAddressDTO.setCity(mailingAdd.getCity());
		coAddressDTO.setState(mailingAdd.getState());
		coAddressDTO.setZipCode(String.valueOf(mailingAdd.getZip4()));
		return coAddressDTO;
	}

//	public DataToSource getDataToSourceDTO(MailingAddress mailingAddress) {
//		DataToSource dto = new DataToSource();
//		dto.setNoticeRequestId(mailingAddress.getNoticeRequestId());
////		dto.setAgencyId();
////		dto.setAgencyName();
////		dto.setClientName();
//		dto.setOrigStreet1(mailingAddress.getOrigStreet1());
//		dto.setOrigStreet2(mailingAddress.getOrigStreet2());
//		dto.setOrigCity(mailingAddress.getOrigCity());
//		dto.setOrigState(mailingAddress.getOrigState());
//		dto.setOrigZip4(mailingAddress.getOrigZip4());
//		dto.setOrigZip5(mailingAddress.getOrigZip4());
//		dto.setOrigZip5(mailingAddress.getOrigZip5());
//		dto.setUpdStreet1(mailingAddress.getUpdStreet1());
//		dto.setUpdStreet2(mailingAddress.getUpdStreet2());
//		dto.setUpdCity(mailingAddress.getUpdCity());
//		dto.setUpdState(mailingAddress.getUpdState());
//		dto.setUpdZip4(mailingAddress.getUpdZip4());
//		dto.setUpdZip5(mailingAddress.getUpdZip5());
//		dto.setEmailDelivery(CoConstants.NO);
//		dto.setTextDelivery(CoConstants.YES);
//
//		return dto;
//	}

//
//	/**
//	 * This method will envoke opentext hp extream end point with required json and
//	 * get byte array as response
//	 * @param noticeCustomer - NoticeCustomer
//	 * @return return byte array
//	 */
//	public ResponseEntity<byte[]> getDocument(NoticeCustomer noticeCustomer) throws Exception {
//
//		NoticeRequest dto = noticeCustomer.getCustomer().getNoticeRequest();
//
//		if(dto.getAction() != null) {
//
//			if(dto.getAction().equals(CoConstants.GEN_PREVIEW)) {
//
//				NoticeRequestMailingAddress res = saveNoticeRequestAndMailingAdd(noticeCustomer, NoticeStatus.RR.name());
//				MailingAddress mailingAddress = res.getMailingAddress();
//				validateRequest(noticeCustomer);
//				validateAddressContent(noticeCustomer, mailingAddress);
//				dto.getMetaData().setStatus(NoticeStatus.PO.name());
//				logNoticeCustomer(noticeCustomer);
//
//			} else if(dto.getAction().equals(CoConstants.GEN_LOCAL_PRINT)) {
//
//				NoticeRequestMailingAddress res = saveNoticeRequestAndMailingAdd(noticeCustomer, NoticeStatus.RR.name());
//				MailingAddress mailingAddress = res.getMailingAddress();
//				validateRequest(noticeCustomer);
//				validateAddressContent(noticeCustomer, mailingAddress);
//				dto.getMetaData().setStatus(NoticeStatus.LP.name());
//				logNoticeCustomer(noticeCustomer);
//				dto.getMetaData().setStatus(NoticeStatus.PP.name());
//				logNoticeCustomer(noticeCustomer);
//
//			} else if(dto.getAction().equals(CoConstants.GEN_CENTRAL_PRINT)) {
//
//				NoticeRequestMailingAddress res = saveNoticeRequestAndMailingAdd(noticeCustomer, NoticeStatus.RR.name());
//				MailingAddress mailingAddress = res.getMailingAddress();
//				validateRequest(noticeCustomer);
//				validateAddressContent(noticeCustomer, mailingAddress);
//				dto.getMetaData().setStatus(NoticeStatus.PP.name());
//
//				logNoticeCustomer(noticeCustomer);
//				return new ResponseEntity<>(new byte[0], HttpStatus.OK);
//
//			} else if(dto.getAction().equals(CoConstants.VIEW_PREVIEW)) {
//
//				NoticeCustomer obj = getNoticeCustomer(dto.getMetaData().getLogRequestId());
//				obj.getCustomer().getNoticeRequest().
//						getMetaData().setStatus(NoticeStatus.PP.name());
//				logNoticeCustomer(obj);
//				noticeCustomer = obj;
//
//			} else if(dto.getAction().equals(CoConstants.VIEW_LOCAL_PRINT)) {
//
//				NoticeCustomer obj = getNoticeCustomer(dto.getMetaData().getLogRequestId());
//				obj.getCustomer().getNoticeRequest().
//						getMetaData().setStatus(NoticeStatus.LP.name());
//				logNoticeCustomer(obj);
//				noticeCustomer = obj;
//
//			} else if(dto.getAction().equals(CoConstants.VIEW_CENTRAL_PRINT)) {
//				NoticeCustomer obj = getNoticeCustomer(dto.getMetaData().getLogRequestId());
//				obj.getCustomer().getNoticeRequest().getMetaData().setStatus(NoticeStatus.PP.name());
//				logNoticeCustomer(obj);
//				return new ResponseEntity<>(new byte[0], HttpStatus.OK);
//			}
//		} else {
//			NoticeRequestMailingAddress res = saveNoticeRequestAndMailingAdd(noticeCustomer, NoticeStatus.RR.name());
//			validateRequest(noticeCustomer);
//
//			NoticeRequestStatus updNotice = res.getNoticeRequestStatus();
////			MailingAddress mailingAddress = res.getMailingAddress();
//			MailingAddress mailingAddress = findByMailingAddressId(res.getMailingAddress().getMailingAddrId());
//
//
//			// validates address here
//			NoticeCustomer n = validateAddressContent(noticeCustomer, mailingAddress);
//			dto.getMetaData().setStatus(NoticeStatus.PP.name());
//			if(n.getCustomer().getNoticeRequest().getMetaData().getAddressUpdated() != null && n.getCustomer().getNoticeRequest().getMetaData().getAddressUpdated() == 'Y')
//				noticeCustomer.getCustomer().getNoticeRequest().getMetaData().setAddressUpdated('Y');
//			logNoticeCustomer(noticeCustomer);
//		}
//
//		return getDocumentFromOpenText(noticeCustomer);
//	}

//	private NoticeRequest getNoticeRequest(Long logRequestId) throws Exception {
//		List<NoticeRequestStatus> noticeList = noticeHistoryRepository.
//				findByLogRequestId(logRequestId);
//		if(noticeList == null || noticeList.size() == 0) {
//			log.error("no data in NOTICE_REQUEST_STATUS for logRequestId: " + logRequestId);
//			throw new Exception("no data in NOTICE_REQUEST_STATUS for logRequestId: " + logRequestId);
//		}
//		NoticeRequestStatus obj = noticeList.get(0);
//		return CoUtil.jsonToNoticeRequest(obj.getRequestJson());
//	}


//	private NoticeRequestStatus logNoticeRequest(NoticeRequest dto) throws Exception {
//		dto.setRequestJson(CoUtil.noticeRequestToJson(dto));
//		HttpHeaders headers = new HttpHeaders();
//		RestTemplate restTemplate = new RestTemplate();
//		HttpEntity<NoticeRequest> requestEntity = new HttpEntity<>(dto, headers);
//		ResponseEntity<NoticeRequestStatus> responseEntity = restTemplate.exchange(
//				"http://localhost:8086/co/api/correspondence/noticeRequest/status-update",
//				HttpMethod.POST, requestEntity, NoticeRequestStatus.class);
//
//		if(responseEntity.getBody() == null) {
//			throw new Exception("cannot insert data into the table");
//		}
//		return responseEntity.getBody();
//	}
//
//	private NoticeRequest getNoticeRequest(CoGenerateManualDTO dto) throws Exception {
//
//		NoticeRequest noticeRequest = new NoticeRequest();
//
//		MetaData metaData = new MetaData();
//		FormData formData = new FormData();
////		MassHealthMedicaid MASSHealthMedicaid = new MassHealthMedicaid();
//
//		HeadOfHouse headOfHouse = new HeadOfHouse();
//		noticeRequest.setAction(dto.getAction());
//		metaData.setTemplateId(dto.getDocId());
//		metaData.setMailDate(dto.getMailDate());
//		metaData.setRequestDate(CoUtil.getCurrentDate().toString());
//		metaData.setHohId(dto.getIndivId());
//		metaData.setCaseNum(Long.parseLong(dto.getCaseAppNumber()));
//		metaData.setPreferredLanguage(dto.getPreferredLanguage());
//		metaData.setClientName(dto.getFullName());
//		metaData.setWatermark(dto.getWatermark());
//		metaData.setSecurityFlag(dto.getSecurityFlag());
//		metaData.setFormTitle(dto.getFormTitle());
//		metaData.setGoGreen(dto.getGoGreen());
//		metaData.setAgencyCode(dto.getAgencyCode());
//		noticeRequest.setMetaData(metaData);
//
//		formData.setCaseName(dto.getFullName());
//		formData.setCaseNum(Long.parseLong(dto.getCaseAppNumber()));
//		formData.setClientName(dto.getFullName());
//		formData.setClientId(dto.getIndivId());
//		formData.setSSN(dto.getSSN());
////		headOfHouse.setHohName(dto.g);
//		headOfHouse.setHOHId(String.valueOf(dto.getIndivId()));
//		formData.setHeadOfHouse(headOfHouse);
//		formData.setCaseNum(Long.parseLong(dto.getCaseAppNumber()));
//		MailingAdd mailingAddress = dto.getMailingAdd();
//		formData.setMailingAdd(mailingAddress);
//		formData.setSystemDate(CoUtil.getCurrentDate());
//		MassHealthMedicaid MASSHealthMedicaid = dto.getMASSHealthMedicaid();
//		formData.setMASSHealthMedicaid(MASSHealthMedicaid);
//		noticeRequest.setFormData(formData);
//
//		return noticeRequest;
//	}

//
//	private NoticeRequest getNoticeRequest(ViewPendingDTO dto) {
//		NoticeRequest noticeRequest = new NoticeRequest();
//
//		MetaData metaData = new MetaData();
//		FormData formData = new FormData();
//		HeadOfHouse headOfHouse = new HeadOfHouse();
//
//		noticeRequest.setAction(dto.getAction());
//		metaData.setTemplateId(dto.getDocId());
//		metaData.setMailDate(dto.getMailDate());
//		metaData.setRequestDate(CoUtil.getCurrentDate().toString());
//		metaData.setHohId(dto.getClientId());
//		metaData.setCaseNum(dto.getCaseNum());
//		metaData.setPreferredLanguage(dto.getPreferredLanguage());
//		metaData.setClientName(dto.getFullName());
//		metaData.setWatermark(dto.getWatermark());
//		metaData.setSecurityFlag(dto.getSecurityFlag());
//		metaData.setFormTitle(dto.getFormTitle());
//		metaData.setGoGreen(dto.getGoGreen());
//		metaData.setAgencyCode(dto.getAgencyCode());
//		noticeRequest.setMetaData(metaData);
//
//		formData.setCaseName(dto.getFullName());
//		formData.setCaseNum(dto.getCaseNum());
//		formData.setClientName(dto.getFullName());
//		formData.setClientId(dto.getClientId());
//		formData.setSSN(dto.getSSN());
//		headOfHouse.setHOHId(String.valueOf(dto.getClientId()));
//		formData.setHeadOfHouse(headOfHouse);
//		formData.setCaseNum(dto.getCaseNum());
//		MailingAdd mailingAddress = dto.getMailingAdd();
//		formData.setMailingAdd(mailingAddress);
//		formData.setSystemDate(CoUtil.getCurrentDate());
//		MassHealthMedicaid MASSHealthMedicaid = dto.getMASSHealthMedicaid();
//		formData.setMASSHealthMedicaid(MASSHealthMedicaid);
//		noticeRequest.setFormData(formData);
//
//		return noticeRequest;
//	}

//	private void saveJsonOfNoticeRequest(NoticeRequest dto) throws Exception {
//		String strJson = CoUtil.noticeRequestToJson(dto);
//		dto.setRequestJson(strJson);
//		logNoticeRequest(dto);
//	}
	//	/** This method gets executed from view pending -> preview screen and it takes logRequestId
//	 * @param logRequestId - a unique key for Notice_Request_Status
//	 * @return byteArrayResource
//	 */
//	public ByteArrayResource getDocumentByLogRequestId(Long logRequestId) throws Exception {
//
//		if(logRequestId == null) {
//			log.error("logRequestId cannot be null");
//			throw new Exception("logRequestId cannot be null");
//		}
//
//		List<NoticeRequestStatus> noticeRequestStatusList = noticeHistoryRepository.findByLogRequestId(logRequestId);
//
//		if(noticeRequestStatusList == null || noticeRequestStatusList.size() <= 0) {
//			log.error("no rows in  Notice_Request_Status for logRequestId : " + logRequestId);
//			throw new Exception("no rows in  Notice_Request_Status for logRequestId : " + logRequestId);
//		}
//
//		NoticeRequestStatus noticeRequestStatus = noticeRequestStatusList.get(0);
//		String strJson = noticeRequestStatus.getRequestJson();
//		NoticeRequest noticeRequest = CoUtil.jsonToNoticeRequest(strJson);
//		if(noticeRequest == null) {
//			log.error("retrieved object is null for logRequestId : " + logRequestId);
//			throw new Exception("retrieved object is null for logRequestId : " + logRequestId);
//		}
//		System.out.println("Retrieve JSON: " + noticeRequest);
////		byte[] fileBytes = getDocument(noticeRequest);
////		InputStreamResource inputRes = new InputStreamResource(new ByteArrayInputStream(fileBytes));
////		return inputRes;
////		return new byte[0];
//		return null;
//	}



}
