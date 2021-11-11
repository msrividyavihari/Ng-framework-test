package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.CoEDMSMetadata;
import com.deloitte.nextgen.dto.vo.CoEDMSUploadVO;
import com.itextpdf.io.source.ByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;

public interface CoEDMSService {
    CoEDMSUploadVO addDocument(CoEDMSMetadata metadata, MultipartFile file);
    byte[] searchDocument(String docId);
}
