package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.dto.entities.CoAddressDTO;
import com.deloitte.nextgen.service.CoAddressValidationService;
import com.deloitte.nextgen.util.MatchType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CoAddressValidationServiceImpl  implements CoAddressValidationService {
    @Override
    public List<CoAddressDTO> validateAddress(CoAddressDTO addressDTO) {

        List<CoAddressDTO>  CoAddressDTOList = new ArrayList<CoAddressDTO>();
        CoAddressDTO coAddressResponseDTO = new CoAddressDTO();

        //condition based on name / zip / state
        if(StringUtils.isNotBlank(addressDTO.getAddressee()) && addressDTO.getAddressee().equalsIgnoreCase("John Doe")) {
            coAddressResponseDTO.setAddressee(addressDTO.getAddressee());
            coAddressResponseDTO.setStreet(addressDTO.getStreet());
            coAddressResponseDTO.setAddressee("John Doe");
            coAddressResponseDTO.setStreet(addressDTO.getStreet());
            coAddressResponseDTO.setStreet2(addressDTO.getStreet2());
            coAddressResponseDTO.setSecondary(addressDTO.getSecondary());
            coAddressResponseDTO.setCity("Cambridge");
            coAddressResponseDTO.setState(addressDTO.getState());
            coAddressResponseDTO.setZipCode(addressDTO.getZipCode());
            coAddressResponseDTO.setMaxCandidates(1);
            coAddressResponseDTO.setMatch(MatchType.STRICT);
            coAddressResponseDTO.setMatchCode('N');
            CoAddressDTOList.add(coAddressResponseDTO);
//
//            coAddressResponseDTO.setAddressee(addressDTO.getAddressee());
//            coAddressResponseDTO.setStreet("APT 12");
//            coAddressResponseDTO.setAddressee("John Doe");
//            coAddressResponseDTO.setStreet(addressDTO.getStreet());
//            coAddressResponseDTO.setStreet2(addressDTO.getStreet2());
//            coAddressResponseDTO.setSecondary(addressDTO.getSecondary());
//            coAddressResponseDTO.setCity(addressDTO.getCity());
//            coAddressResponseDTO.setState(addressDTO.getState());
//            coAddressResponseDTO.setZipCode(addressDTO.getZipCode());
//            coAddressResponseDTO.setMaxCandidates(3);
//            coAddressResponseDTO.setMatch(MatchType.STRICT);
//            coAddressResponseDTO.setMatchCode('N');
//            CoAddressDTOList.add(coAddressResponseDTO);
//
//            coAddressResponseDTO.setAddressee(addressDTO.getAddressee());
//            coAddressResponseDTO.setStreet(addressDTO.getStreet());
//            coAddressResponseDTO.setAddressee("John Doe");
//            coAddressResponseDTO.setStreet(addressDTO.getStreet());
//            coAddressResponseDTO.setStreet2(addressDTO.getStreet2());
//            coAddressResponseDTO.setSecondary(addressDTO.getSecondary());
//            coAddressResponseDTO.setCity(addressDTO.getCity());
//            coAddressResponseDTO.setState(addressDTO.getState());
//            coAddressResponseDTO.setZipCode("0212");
//            coAddressResponseDTO.setMaxCandidates(3);
//            coAddressResponseDTO.setMatch(MatchType.STRICT);
//            coAddressResponseDTO.setMatchCode('N');
//            CoAddressDTOList.add(coAddressResponseDTO);
        } else if(StringUtils.isNotBlank(addressDTO.getZipCode()) && addressDTO.getZipCode().equalsIgnoreCase("10745")) {
            coAddressResponseDTO.setAddressee(addressDTO.getAddressee());
            coAddressResponseDTO.setStreet(addressDTO.getStreet());
            coAddressResponseDTO.setStreet(addressDTO.getStreet());
            coAddressResponseDTO.setStreet2(addressDTO.getStreet2());
            coAddressResponseDTO.setSecondary(addressDTO.getSecondary());
            coAddressResponseDTO.setCity(addressDTO.getZipCode());
            coAddressResponseDTO.setState(addressDTO.getState());
            coAddressResponseDTO.setZipCode(addressDTO.getZipCode());
            coAddressResponseDTO.setMaxCandidates(1);
            coAddressResponseDTO.setMatch(MatchType.STRICT);
            coAddressResponseDTO.setMatchCode('Y');
            CoAddressDTOList.add(coAddressResponseDTO);
        } else {
            coAddressResponseDTO.setAddressee(addressDTO.getAddressee());
            coAddressResponseDTO.setStreet(addressDTO.getStreet());
            coAddressResponseDTO.setStreet(addressDTO.getStreet());
            coAddressResponseDTO.setStreet2(addressDTO.getStreet2());
            coAddressResponseDTO.setSecondary(addressDTO.getSecondary());
            coAddressResponseDTO.setCity(addressDTO.getZipCode());
            coAddressResponseDTO.setState(addressDTO.getState());
            coAddressResponseDTO.setZipCode(addressDTO.getZipCode());
            coAddressResponseDTO.setMaxCandidates(1);
            coAddressResponseDTO.setMatch(MatchType.STRICT);
            coAddressResponseDTO.setMatchCode('U');
            CoAddressDTOList.add(coAddressResponseDTO);
        }


        return CoAddressDTOList;
    }
}
