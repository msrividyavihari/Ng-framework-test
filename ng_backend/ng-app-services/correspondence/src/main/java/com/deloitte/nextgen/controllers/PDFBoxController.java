package com.deloitte.nextgen.controllers;

import com.deloitte.nextgen.dto.entities.BatchNotice;
import com.deloitte.nextgen.service.PDFBoxService;
import com.deloitte.nextgen.service.impl.CorrespondenceServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class PDFBoxController {

    @Autowired
    PDFBoxService pdfBoxService;


    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> createPDF(String xmlFile) {
        byte[] fileBytes = pdfBoxService.downloadGeneralCorrespondencePDF(xmlFile);
		InputStream fileInputStream = new ByteArrayInputStream(fileBytes);
		HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "attachment; filename=CorrespondencePDF.pdf");
        return ResponseEntity.
        		ok().
        		headers(headers).
        		contentType(MediaType.APPLICATION_PDF).
        		body(new InputStreamResource(fileInputStream));
    }

    @PostMapping ("/getPdfByteArr")
    public ResponseEntity<byte[]> getPdfByteArr(@RequestBody String xmlStr){
        String str = "caseNum : 122256186\n" +
                "clientId: 112233445\n" +
                "fullName: John Doe\n" +
                "Age: 51\n" +
                "Gender: M";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE));
        byte[] fileBytes = pdfBoxService.downloadGeneralCorrespondencePDF(xmlStr);
        fileBytes = java.util.Base64.getEncoder().encode(fileBytes);
        fileBytes = CorrespondenceServices.addQrCode(fileBytes, str);
        fileBytes = CorrespondenceServices.addBarCode(fileBytes, "12345678");
//        return new ResponseEntity<>(pdfBoxService.downloadGeneralCorrespondencePDF(xmlStr), headers, HttpStatus.OK);
        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }

    @PostMapping("/opentext")
    public ResponseEntity<byte[]> getDocumentOpentext(@RequestBody BatchNotice dto)
            throws Exception {
        ResponseEntity<byte[]> resp = pdfBoxService.getDocument(dto);
        byte[] fileBytes = java.util.Base64.getEncoder().encode(resp.getBody());
        return new ResponseEntity<>(fileBytes, HttpStatus.OK);
    }


}
