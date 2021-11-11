package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.client.entities.ArEmailDetailsDto;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ArEmailDetailsCustomRepository {

    List<ArEmailDetailsDto> findEmailDetailsForCaseAndEmailScrTyp(Long caseNum, String emailSrcTyp);
}
