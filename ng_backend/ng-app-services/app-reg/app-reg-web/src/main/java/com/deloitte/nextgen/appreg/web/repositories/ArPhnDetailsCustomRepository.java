package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.client.entities.ArPhnDetailsDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ArPhnDetailsCustomRepository {
    List<ArPhnDetailsDto> findPhnDetailsForCaseAndPhoneSrcTyp(Long caseNum, String phoneSrcTyp);
}
