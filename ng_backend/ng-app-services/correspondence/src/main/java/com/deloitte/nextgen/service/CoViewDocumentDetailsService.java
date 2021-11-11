package com.deloitte.nextgen.service;

import com.deloitte.nextgen.dto.entities.ViewDocumentDetailsDTO;
import com.deloitte.nextgen.dto.vo.ViewDocumentDetailsVO;

public interface CoViewDocumentDetailsService {
    ViewDocumentDetailsVO metaDataChange(ViewDocumentDetailsDTO dto) throws Exception;
    ViewDocumentDetailsVO delinkAndLink(ViewDocumentDetailsDTO dto) throws Exception;
}
