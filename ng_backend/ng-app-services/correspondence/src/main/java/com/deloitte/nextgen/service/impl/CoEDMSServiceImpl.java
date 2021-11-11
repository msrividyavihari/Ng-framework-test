package com.deloitte.nextgen.service.impl;


import com.deloitte.nextgen.dto.entities.CoEDMSMetadata;
import com.deloitte.nextgen.dto.vo.CoEDMSUploadMetadata;
import com.deloitte.nextgen.dto.vo.CoEDMSUploadSystem;
import com.deloitte.nextgen.service.CoEDMSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.deloitte.nextgen.dto.vo.CoEDMSUploadVO;


import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CoEDMSServiceImpl implements CoEDMSService {
    @Override
    public CoEDMSUploadVO addDocument(CoEDMSMetadata metadata, MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String filename = uuid.toString() + ".pdf";
        Path targetLocation = Paths.get(filename);
        try{
            Files.copy(file.getInputStream(), targetLocation);
        } catch (Exception e) {
        }
        CoEDMSUploadVO response = new CoEDMSUploadVO();
        response.setDocId(uuid.toString());
        CoEDMSUploadSystem system = new CoEDMSUploadSystem();
        system.setFilename(filename);
        system.setType("application/pdf");
        system.setSize(file.getSize());
        CoEDMSUploadMetadata meta = new CoEDMSUploadMetadata();
        meta.setSystem(system);
        meta.setProperties(metadata);
        response.setMetadata(meta);

        return response;
    }

    @Override
    public byte[] searchDocument(String docId) {
        String filename = docId + ".pdf";
        Path targetLocation = Paths.get(filename);
        byte[] encoded;
        byte[] ba;
        try {
            ba = Files.readAllBytes(targetLocation);
        } catch (Exception e){
            ba = new byte[0];
        }
        return ba;
    }

}
