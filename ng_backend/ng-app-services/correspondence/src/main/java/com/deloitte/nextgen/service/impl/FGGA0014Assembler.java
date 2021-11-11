package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.repository.CoMassMailingRepository;
import com.deloitte.nextgen.util.CoCommonCode;
import com.deloitte.nextgen.util.CoUtil;
import com.deloitte.nextgen.util.xsd.schema.notices.FamilyAndChildData;
import com.deloitte.nextgen.util.xsd.schema.notices.Fgga0014;
import com.deloitte.nextgen.util.xsd.schema.notices.FormData;
import com.deloitte.ng.reftables.ReferenceTableManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.deloitte.nextgen.util.xsd.schema.notices.StandardText;
import com.deloitte.nextgen.entities.COCorrespondence;
import com.deloitte.nextgen.entities.CoMassMailingReq;
import com.deloitte.nextgen.util.CoConstants;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FGGA0014Assembler extends NoticeAssembler {

    private static final String EXCEPTION_FETCHING = "Exception while fetching FGGA0014 data ";

    @Autowired
    CoMassMailingRepository coMassMailingRepo;

    ReferenceTableManager referenceTableManager;

    @Override
    protected FormData getFormData() throws Exception {
        FormData formData = new FormData();

        /* It will go in if loop if the request is from online application  and will go in else block if it is running through batch job.In else block we will use notice assembler form data  method
         * to  populate from data.
         *
         */
        if (CoConstants.PRINT_MODE_ONLINE == getCoCorrespondence().getPrintMode()) {
               formData.setSystemDate(CoCommonCode
                       .getAdobeCompliantXmlGregCal(CoUtil.getCurrentDate()));

            final FamilyAndChildData familyAndChildData = new FamilyAndChildData();
            Fgga0014 fgga0014 = getFgga0014Data(getCoCorrespondence());
            familyAndChildData.setFgga0014(fgga0014);
            formData.setFamilyAndChildData(familyAndChildData);
        } else {
            formData = super.getFormData();
        }

        return formData;

    }

    @Override
    public void populateNoticesSpecificData(final FormData formData ) throws Exception {

        final FamilyAndChildData familyAndChildData = new FamilyAndChildData();
        Fgga0014 fgga0014 = getFgga0014Data(getCoCorrespondence().getMassMailingId());
        familyAndChildData.setFgga0014(fgga0014);
        formData.setFamilyAndChildData(familyAndChildData);


    }

    /**
     * Method to get FormSpecific Data
     *
     * @param massMailingId
     * @return
     * @throws Exception
     */
    private Fgga0014 getFgga0014Data(final long massMailingId)
            throws Exception {
        final Fgga0014 fgga0014 = new Fgga0014();
        // set interview date
        try{
            final CoMassMailingReq coMassMailingReq = getMassMailingDetails(massMailingId);
            fgga0014.setNoticeText(coMassMailingReq.getNoticeTxt());
            fgga0014.setNoticeTitle(coMassMailingReq.getNoticeTitle());
            fgga0014.setPolicyManualReference(coMassMailingReq.getLegalCites());

            //fgga0014.setStandardText(getStandardDesc(coMassMailingReqCargo.getStdTextLst()));
            getStandardDesc(fgga0014,coMassMailingReq.getStdTextLst());

            fgga0014.setLogo(coMassMailingReq.getLogoCd());
            setProgramCdValue(fgga0014);
        }catch(Exception e){
            log.error(EXCEPTION_FETCHING, e);
            throw new Exception(EXCEPTION_FETCHING + e.getMessage());
        }

        return fgga0014;
    }


    private void getStandardDesc( Fgga0014 fgga0014, String stdTxtList) throws Exception {

        if(StringUtils.isNotBlank(stdTxtList)){
            String[] programs = stdTxtList.split(CoConstants.COMMA);
            for (String programCode : programs) {
                StandardText stdText = new StandardText();
                 stdText.setStandardText(referenceTableManager.getValueByColumn(true,
                        CoConstants.MASS_MAIL_TEXT, programCode, CoConstants.DESCRIPTION));
                fgga0014.getStandardText().add(stdText);
            }
        }

    }
    /**
     * @param fgga0014
     */
    private void setProgramCdValue(final Fgga0014 fgga0014) throws Exception {

        /*List<String> programCdList;
        programLst = coMassMailingReq.getProgramLst();
        programCdList = Arrays.asList(programLst.split(","));*/
        String programCd="";
        if(getCoCorrespondence()!=null && getCoCorrespondence().getMiscParameters()!=null){
            programCd= getMiscParam(getCoCorrespondence().getMiscParameters());
        }
        fgga0014.setFoodStamps(CoConstants.ZERO_STRING);
        fgga0014.setMedicaid(CoConstants.ZERO_STRING);
        fgga0014.setTanf(CoConstants.ZERO_STRING);
        fgga0014.setPeachCare(CoConstants.ZERO_STRING);
        fgga0014.setChildCare(CoConstants.ZERO_STRING);


        switch(programCd) {
            case CoConstants.PROGRAM_FS :
                fgga0014.setFoodStamps(CoConstants.VALUE_ONE);
                break;
            case CoConstants.PROGRAM_MA :
                fgga0014.setMedicaid(CoConstants.VALUE_ONE);
                break;
            case CoConstants.PROGRAM_TF :
                fgga0014.setTanf(CoConstants.VALUE_ONE);
                break;

            case CoConstants.PEACH_CARE:
                fgga0014.setPeachCare(CoConstants.VALUE_ONE);
                break;

            case CoConstants.CO_CHILD_CARE:
                fgga0014.setChildCare(CoConstants.VALUE_ONE);
                break;
            default :
                log.debug("Program code not found.");
        }


    }

    /**
     * @param massMailingId
     * @return
     * @throws Exception
     */
    private CoMassMailingReq getMassMailingDetails(final Long massMailingId)
            throws Exception {
        final CoMassMailingReq coMassMailingReq = new CoMassMailingReq();
        coMassMailingReq.setMassMailingSeqNum(massMailingId);
        List<CoMassMailingReq> cargos = coMassMailingRepo.findByMassMailingReqSeq(coMassMailingReq.getMassMailingSeqNum());

        if(cargos==null || cargos.size()==0){
            throw new Exception("No data found in coMassMailingReq for  MASS_MAILING_BY_SEQ_NUM "+massMailingId );
        }
        return cargos.get(0);
    }


    /**
     * This Method sets Formdata obtained from MassMailingRequest and returns
     * formData
     *
     * @param coCorrespondence
     * @return FormData
     */
    private Fgga0014 getFgga0014Data(final COCorrespondence coCorrespondence) throws Exception {

        final Map<String, String> inputData = coCorrespondence.getFormData();

        Fgga0014 fgga0014 = new Fgga0014();

        fgga0014.setNoticeText((String) inputData.get(CoConstants.NOTICE_TEXT));
        fgga0014.setNoticeTitle((String) inputData
                .get(CoConstants.NOTICE_TITLE));
        fgga0014.setPolicyManualReference((String) inputData
                .get(CoConstants.POLICY_MANUAL_REFERENCE));
        fgga0014.setChildCare((String) inputData
                .get(CoConstants.MAIL_CHILD_CARE));
        fgga0014.setFoodStamps((String) inputData
                .get(CoConstants.MAIL_FOOD_STAMPS));
        fgga0014.setPeachCare((String) inputData
                .get(CoConstants.MAIL_PEACH_CARE));
        fgga0014.setTanf((String) inputData.get(CoConstants.MAIL_TANF));
        fgga0014.setLogo((String) inputData
                .get(CoConstants.RECIPIENT_POPULATION));

        try {
            getStandardDesc(fgga0014,(String) inputData.get(CoConstants.STANDARD_TEXT_COLLEC));
        } catch (Exception e) {
            throw new Exception("Exception from getFgga0014Data method "+e);
        }

        final String medicaid1 =  inputData
                .get(CoConstants.MAIL_FAMILY_MEDICAID);
        final String medicaid2 =  inputData
                .get(CoConstants.MAIL_ADULT_MEDICAID);

        if ((medicaid1 != null) || (medicaid2 != null)) {
            fgga0014.setMedicaid(CoConstants.VALUE_ONE);
        }

        return fgga0014;

    }

    private String getMiscParam(final String miscParams) throws Exception {
        final CoDAOServices services = new CoDAOServices();

        try {
            List<Object> misc  = services.getDataFromMiscParameters(miscParams);
            Map<String, String> misParmsMap  = (Map<String, String>) misc.get(0);
            return misParmsMap.get("PROGRAMCD");
        } catch (final Exception e) {
            log.debug(e.getMessage(), e);
            throw new Exception("Error while getting miscParams - CoReqSeq :"+getCoCorrespondence().getCoReqSeq());
        }


    }
}