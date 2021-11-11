package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.entities.DcCaseProgram;
import com.deloitte.nextgen.repository.DcCaseProgramRepository;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import com.deloitte.nextgen.util.xsd.schema.notices.FamilyAndChildData;
import com.deloitte.nextgen.util.xsd.schema.notices.Fgg0015;
import com.deloitte.nextgen.util.xsd.schema.notices.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class FGGA0015Assembler extends NewFormAssembler {

    @Autowired
    DcCaseProgramRepository dcCaseProgramRepository;

    @Override
    protected void populateFormSpecificData(FormData formData)
            throws Exception {

        final Map<String, String> inputData = getCoCorrespondence().getFormData();
        formData.setCaseWorkerID(String.valueOf(getCoCorrespondence().getEmpId()));
        FamilyAndChildData familyAndChildData = getFormSpecificData(inputData);
        formData.setFamilyAndChildData(familyAndChildData);

        if(familyAndChildData.getFgg0015().getProgramCode().contains(CoConstants.PROGRAM_CC)) {
            populateCountyAddress(formData);
        }
    }

    /**
     * Method to get FormSpecific Data
     *
     * @param inputData Map
     * @return FamilyAndChildData
     */
    private FamilyAndChildData getFormSpecificData(final Map<String, String> inputData) throws Exception {
        final FamilyAndChildData familyAndChildData = new FamilyAndChildData();
        try {
            final Fgg0015 fgg0015 = new Fgg0015();

            if(!CoUtil.isEmpty(inputData)) {
                fgg0015.setNoticeSalutation(inputData
                        .get(CoConstants.CO_NOTICE_SALUTATION));
                fgg0015.setFreeFormText(inputData
                        .get(CoConstants.CO_PURPOSE_OF_NOTICE));
                fgg0015.setPolicyManualReference(inputData
                        .get(CoConstants.CO_POLICY_MANUAL_REFERENCE));
            }
            String programCds= getProgramCd();
            fgg0015.setProgramCode(programCds);

            familyAndChildData.setFgg0015(fgg0015);

        }catch (Exception e) {
            throw new Exception("Exception occurred in getFormSpecificData:"
                    + e.getMessage());
        }
        return familyAndChildData;
    }

    private String getProgramCd() {
        Set<String> programCodes = new HashSet<>();
        List<DcCaseProgram> dcCaseProgramCargos;
        dcCaseProgramCargos=dcCaseProgramRepository.findByCaseNum(Long.parseLong(getCoCorrespondence().getCaseAppNumber()));

        for(DcCaseProgram cargo:dcCaseProgramCargos ) {
            programCodes.add(cargo.getProgCd());
        }

        return programCodes.toString().replace("[", CoConstants.EMPTY_STRING)
                .replace("]", CoConstants.EMPTY_STRING)
                .replace(", ", CoConstants.COMMA);

    }
}
