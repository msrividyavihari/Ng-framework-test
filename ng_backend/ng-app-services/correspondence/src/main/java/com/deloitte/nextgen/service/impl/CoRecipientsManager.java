package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.entities.*;
import com.deloitte.nextgen.framework.commons.exceptions.ApplicationException;
import com.deloitte.nextgen.repository.CoRequestRecipientsRepository;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@Service
@Slf4j
public class CoRecipientsManager {

    final CoDocumentRecipient coDocumentRecipientCargo =new CoDocumentRecipient();

    @Autowired
    CoDAOServices coDaoServices;

    @Autowired
    CoRequestRecipientsRepository coRequestRecipientsRepository;

    /**
     * configures the recipients for that CO_REQ_SEQ, RCPT_ID, updates the
     * CO_REQUEST_RECIPIENTS table with recipients if CO_RPT_ID is null
     * configureRecipientsWithoutRecipientsCheck
     *
     * @param coCorrespondence
     *            COCorrespondence
     * @throws ApplicationException ApplicationException
     */
    public void configureRecipientsForCoReqSeq(COCorrespondence coCorrespondence)
            throws ApplicationException {
        setRecipientsForCorrespondence(coCorrespondence);
        updateCoRequestRecipientsWithRecipients(coCorrespondence);
    }

    /**
     * Sets the recipient cargos for a particular document and for a particular
     * case/application
     *
     * @param coCorrespondence
     *            COCorrespondence
     * @throws ApplicationException ApplicationException
     */
    private void setRecipientsForCorrespondence(
            COCorrespondence coCorrespondence) throws ApplicationException {
        String caseAppNum = coCorrespondence.getCaseAppNumber();
        char caseAppFlag = coCorrespondence.getCaseAppFlag();
        char recipientsReprintSw ;

        coDocumentRecipientCargo.setCoReqSeq(coCorrespondence.getCoReqSeq());
        coDocumentRecipientCargo.setRecipientDocument(coCorrespondence.getDocId());

        // if Reprint all the recipients are copy
        if(coCorrespondence.getHistorySwitch() == CoConstants.HISTORY_TRIGGER_YES){
            recipientsReprintSw = CoConstants.RECIPIENT_COPY_ID;
        } else {
            recipientsReprintSw = CoConstants.RECIPIENT_ORIGINAL_ID;
        }


        if(caseAppFlag == CoConstants.APPLICATION) {
            coDocumentRecipientCargo.setAppNum(caseAppNum);
            setApplicantAsRecipient( recipientsReprintSw ,coCorrespondence );

        } else if (caseAppFlag == CoConstants.CASE) {
            try {
                coDocumentRecipientCargo.setCaseNum(Long
                        .parseLong(caseAppNum));
            } catch (NumberFormatException nfe) {
                log.debug("Not a number", nfe);
            }

            if(CoConstants.RECIPIENT_NH_PROVIDER.equalsIgnoreCase(coCorrespondence.getRecipientType())){
                setNursingHomeAsRecipient(recipientsReprintSw,coCorrespondence);
            }else if(CoConstants.FGG130.equalsIgnoreCase(coCorrespondence.getDocId())){
                setAbsentParentType(recipientsReprintSw,coCorrespondence);
            }else{
                setCaseAsRecipient( recipientsReprintSw,coCorrespondence);
            }
        }
    }
    /**
     * updates the CO_REQUEST_RECIPIENTS table with recipients
     * updateCoRequestRecipientsWithRecipients
     *
     * @param coCorrespondence
     *            COCorrespondence
     */
    private void updateCoRequestRecipientsWithRecipients(
            COCorrespondence coCorrespondence) {
        CoRequestRecipients coRequestRecipients;
        for (CoRecipients recipientCargo: coDocumentRecipientCargo.getRecipientCargosList()) {
            coRequestRecipients = new CoRequestRecipients();
            coRequestRecipients.setCoReqSeq(coDocumentRecipientCargo.getCoReqSeq());
            String recipientType = recipientCargo.getRecipientType();

            coRequestRecipients.setRecipientType(recipientType);
            if (coCorrespondence.getHistorySwitch() == CoConstants.HISTORY_TRIGGER_YES){
                coRequestRecipients.setLocationPath(coCorrespondence.getFileLocation());
            }
            if(coCorrespondence.getPrintMode() == CoConstants.PRINT_MODE_ONLINE){
                coRequestRecipients.setPrintSw(CoConstants.HISTORY_TRIGGER_YES);
                coRequestRecipients.setPrintType(CoConstants.PRINT_MODE_ONLINE);
            }else{
                coRequestRecipients.setPrintSw(CoConstants.HISTORY_TRIGGER_NO);
                coRequestRecipients.setPrintType(CoConstants.PRINT_MODE_BATCH);
            }

//            coRequestRecipients.setCreateDt(CoUtil.getCurrentDate());
            coRequestRecipients.setCreateUserId(coCorrespondence.getRequestUserId());
            coRequestRecipients.setRecipientTypeId(recipientCargo.getRecipientTypeId());

            if (CoConstants.RECIPIENT_AUTHORIZED_REP.equals(recipientType)) {
                coRequestRecipients.setRecipientData(recipientCargo.getRecipientId()
                                + CoConstants.PIPE
                                + recipientCargo.getRecipientData()
                                + (recipientCargo.getHohName() != null ? (CoConstants.PIPE + recipientCargo
                                .getHohName()) : CoConstants.SPACE));
            } else {
                coRequestRecipients.setRecipientData(recipientCargo
                        .getRecipientId()
                        + CoConstants.PIPE
                        + recipientCargo.getRecipientData());
            }
            coRequestRecipientsRepository.save(coRequestRecipients);
            log.info("recipient data before insert into table = " + coRequestRecipients.getRecipientData());
        }
    }


    /**
     * sets the Applicant as Recipient
     *
     */
    private void setApplicantAsRecipient(char recipientsReprintSw,COCorrespondence coCorrespondence ) {
        String recipientData = getPrimaryIndividualData(coCorrespondence,"findIndvByAppNumHohSw");

        CoRecipients recipientCargo = createRecipientCargo(CoConstants.RECIPIENT_CLIENT,
                coDocumentRecipientCargo.getAppNum(), recipientData, recipientsReprintSw);
        coDocumentRecipientCargo.getRecipientCargosList().add(recipientCargo);
    }

    /**
     * returns the HOH for an Application
     *
     * @return String
     */
    private String getPrimaryIndividualData(COCorrespondence coCorrespondence,String dcIndvQuery) {

        String recipientData = CoConstants.EMPTY_STRING;
        DcIndv hohDcIndvCargo = null;
        final Object[] caseAppParams = new Object[1];

        if(coCorrespondence.getHohIndvCargo()==null){
            caseAppParams[0] = coCorrespondence.getCaseAppNumber();
            DcIndv[] dcIndvCargoArray = null;
                    /*= (DcIndv[]) dcIndvColl.select(dcIndvQuery,
                    caseAppParams);*/

            if (dcIndvCargoArray != null && dcIndvCargoArray.length>0){
                hohDcIndvCargo = dcIndvCargoArray[0];
            }
        }else{
            hohDcIndvCargo =coCorrespondence.getHohIndvCargo();
        }

        if(hohDcIndvCargo!=null){
            String applicantName = CorrespondenceServices.getName(
                    hohDcIndvCargo.getFirstName(), hohDcIndvCargo.getMidName(),
                    hohDcIndvCargo.getLastName(), hohDcIndvCargo.getSufxName());

            recipientData = getRecipientData(applicantName,hohDcIndvCargo.getIndvId());

        }
        return recipientData;
    }


    /**
     * sets the Applicant as Recipient as well as Auth Rep
     *
     * @throws ApplicationException
     */
    private void setCaseAsRecipient(char recipientsReprintSw,COCorrespondence coCorrespondence) throws ApplicationException {

        String recipientData = null;
        CoRecipients recipientCargo = null;

        //set primary individual as original recipient and other authreps are copy recipients
        setAuthRepRecipient(CoConstants.RECIPIENT_COPY_ID);

        recipientData = getPrimaryIndividualData(coCorrespondence,"findIndvByCaseNumHohSwNotices");

        recipientCargo = createRecipientCargo(CoConstants.RECIPIENT_CLIENT,
                String.valueOf(coDocumentRecipientCargo.getCaseNum()), recipientData,  recipientsReprintSw);

        coDocumentRecipientCargo.getRecipientCargosList().add(recipientCargo);

    }

    /**
     * sets the Auth Rep as Recipient
     *
     * @param recipientsSw
     * @throws ApplicationException
     */
    private void setAuthRepRecipient(char recipientsSw ) throws ApplicationException{
        Object[] objCaseParams = new Object[2];
        String recipientData = CoConstants.EMPTY_STRING;
        CoRecipients recipientCargo = null;


        Timestamp today = CoUtil.getCurrentDate();
        //authRepCollection.getCargo().setCaseNum(coDocumentRecipientCargo.getCaseNum());
        //objCaseParams[0] = authRepCollection.getCargo();
        //objCaseParams[1] = today;

        DcAuthRep[] dcAuthRep = new DcAuthRep[0];
        //= (DcAuthRep[]) authRepCollection
               // .select("findActiveByCaseNumNotices", objCaseParams);

        if(dcAuthRep != null && dcAuthRep.length > 0) {

            // if authorized representative is present
            for(DcAuthRep authRep:dcAuthRep) {

                recipientData = CorrespondenceServices.getName(
                        authRep.getAuthrepFirstName(), authRep.getAuthrepMidName(),
                        authRep.getAuthrepLastName(),
                        authRep.getAuthrepSufxName());
                if(StringUtils.isBlank(recipientData) && StringUtils.isNotBlank(authRep.getAuthrepOrgName()) ){
                    recipientData = authRep.getAuthrepOrgName();
                }

                recipientData = getRecipientData(recipientData,authRep.getAuthrepSeqNum());

                if(recipientsSw != CoConstants.DEFAULT_CHAR_VALUE) {
                    recipientCargo = createRecipientCargo(CoConstants.RECIPIENT_AUTHORIZED_REP,
                            String.valueOf(coDocumentRecipientCargo.getCaseNum()), recipientData, recipientsSw);
                    coDocumentRecipientCargo.getRecipientCargosList().add(recipientCargo);
                }
            }
        }
    }

    /**
     *
     * @param recipientsReprintSw
     * @param coCorrespondence
     */
    private void setNursingHomeAsRecipient(char recipientsReprintSw,COCorrespondence coCorrespondence) {

        String recipientData = getRecipientData(coCorrespondence.getNursingHomeNm(),coCorrespondence.getProviderId());

        CoRecipients recipientCargo = createRecipientCargo(CoConstants.RECIPIENT_NH_PROVIDER, coCorrespondence.getCaseAppNumber(), recipientData,
                recipientsReprintSw);

        coDocumentRecipientCargo.getRecipientCargosList().add(recipientCargo);
    }

    /**
     * formats recipientData
     * @param hohName
     * @param id
     * @return
     */
    private String getRecipientData(String hohName,long id){
        StringBuffer recipientData = new StringBuffer(hohName);
        recipientData.append(CoConstants.RECIPIENT_DATA_NAME_SEPARATOR);
        recipientData.append(id);
        return  recipientData.toString();
    }

    /**
     * returns a from data passed --Recipient Cargo
     *
     * @param recipientType
     * @param recipientId
     * @param recipientData
     * @return CoRecipientsCargo
     */
    private CoRecipients createRecipientCargo(String recipientType,
                                              String recipientId, String recipientData,
                                              char recipientTypeId) {
        CoRecipients recipientCargo = new CoRecipients();
        recipientCargo.setRecipientType(recipientType);
        recipientCargo.setRecipientId(recipientId);
        recipientCargo.setRecipientData(recipientData);
        recipientCargo.setRecipientTypeId(recipientTypeId);
        return recipientCargo;
    }

    /**
     * configures the recipients for that CO_REQ_SEQ, RCPT_ID, updates the
     * CO_REQUEST_RECIPIENTS table with recipients if CO_RPT_ID is null
     * configureRecipientsWithRecipientsCheck
     *
     * @param coCorrespondence
     *            COCorrespondence
     * @throws Exception
     */
    public void configureRecipientsWithRecipientsCheck(
            COCorrespondence coCorrespondence) throws Exception {
        boolean isTableHaveRecipients = checkIfTableHasRecipients(coCorrespondence
                .getCoReqSeq());
        if (!isTableHaveRecipients) {
            configureRecipientsForCoReqSeq(coCorrespondence);
        }

    }

    /**
     * returns boolean value if recipients are in the table
     *
     * @param coReqSeq
     *            long
     * @return boolean
     */
    private boolean checkIfTableHasRecipients(Long coReqSeq) throws Exception {
        List<CoRequestRecipients> coRequestRecipientsArray = coDaoServices.getCoRequestAllRecipients(coReqSeq);
        if(!CoUtil.isEmpty(coRequestRecipientsArray)) {
            int length = coRequestRecipientsArray.size();
            for (int i = 0; i < length; i++) {
                addRecipientCargoToDocumentRecipients(coRequestRecipientsArray.get(i));
            }
        }
        return true;
    }

    /**
     * adds recipient to the document recipient list
     *
     * @param coRequestRecipientsCargo
     *            CoRequestRecipientsCargo
     */
    private void addRecipientCargoToDocumentRecipients(
            CoRequestRecipients coRequestRecipientsCargo) {
        CoRecipients recipientCargo;
        String recipientId,recipientName;

        Map map = splitRecipientData(coRequestRecipientsCargo
                .getRecipientData());
        recipientId = (String) map.get("recipientId");
        recipientName = (String) map.get("recipientName");
        recipientCargo = createRecipientCargo(
                coRequestRecipientsCargo.getRecipientType(), recipientId,
                recipientName,   coRequestRecipientsCargo.getRecipientTypeId());
        coDocumentRecipientCargo.getRecipientCargosList().add(recipientCargo);
    }

    /**
     * returns map of recipient id and name
     *
     * @param recipientData
     *            String
     * @return HashMap
     */
    private Map splitRecipientData(String recipientData) {
        StringTokenizer tokenizer = new StringTokenizer(recipientData,
                String.valueOf(CoConstants.PIPE));
        String temp = null;
        int countTokens = tokenizer.countTokens();
        int i = 1;
        String recipientId = null;
        String recipientName = null;
        String hohName = null;
        while (tokenizer.hasMoreTokens()) {
            temp =  tokenizer.nextToken();
            if (countTokens == 2) {
                if (i == 1) {
                    recipientId = temp;
                    // case number or app number or vendorId or provider license
                    // number or prescriber number or AR (for authorized rep)
                } else if (i == 2) {
                    recipientName = temp;
                    // case name or app name or vendor name or provider
                    // organization name or prescriber name or Authorized rep
                    // name
                }
                i++;
            } else if (countTokens == 3) {
                if (i == 1) {
                    recipientId = temp;
                    // case number or app number or vendorId or provider license
                    // number or prescriber number or AR (for authorized rep)
                } else if (i == 2) {
                    recipientName = temp;
                    // case name or app name or vendor name or provider
                    // organization name or prescriber name or Authorized rep
                    // name
                } else if (i == 3) {
                    hohName = temp;
                    // hohName when authorized rep is being printed //ccb 188770
                }
                i++;
            }

        }
        Map map = new HashMap(2);
        map.put("recipientId", recipientId);
        map.put("recipientName", recipientName);
        map.put("hohName", hohName);
        return map;
    }

    /**
     * returns the recipients list
     * @return
     *
     * @return ArrayList
     */
    public List<CoRecipients> getRecipientsList() {
        return coDocumentRecipientCargo.getRecipientCargosList();
    }

    /**
     * sets the Absent Parent Type as AP as as well as Auth Rep
     *
     * @throws ApplicationException
     */
    private void setAbsentParentType(char recipientsReprintSw,COCorrespondence coCorrespondence)
            throws  ApplicationException {

        String recipientData = null;
        CoRecipients recipientCargo = null;
        //set primary individual as original recipient and other authreps are copy recipients
        setAuthRepRecipient(CoConstants.RECIPIENT_COPY_ID);
        recipientData = getAbsentParentData(coCorrespondence);
        recipientCargo = createRecipientCargo(CoConstants.ABSENT_PARENT,
                String.valueOf(coDocumentRecipientCargo.getCaseNum()), recipientData,  recipientsReprintSw);
        coDocumentRecipientCargo.getRecipientCargosList().add(recipientCargo);
    }

    /**
     * returns the HOH for an Application
     *
     * @return String
     * @throws ApplicationException
     */
    private String getAbsentParentData(COCorrespondence coCorrespondence)
            throws ApplicationException {

        String recipientData = CoConstants.EMPTY_STRING;
        String absentParentName = CoConstants.EMPTY_STRING;
        /*final CoManualDataCollection coManualDataColl = new CoManualDataCollection();
        CoManualDataCargo coManualDataCargo = new CoManualDataCargo();
        coManualDataCargo.setCoReqSeq(coCorrespondence.getCoReqSeq());
        final Object[] coManualDataParams = new Object[1];
        coManualDataParams[0] = coManualDataCargo;
        CoManualDataCargo[] coManualDataArray = (CoManualDataCargo[]) coManualDataColl.select("findAllBySeqNum",coManualDataParams);
        if (coManualDataArray != null && coManualDataArray.length > 0) {
            for(CoManualDataCargo coManualData:coManualDataArray){
                if(coManualData.getFieldOrderNum()==4){
                    absentParentName = coManualData.getFieldContent();
                }
            }

        }*/

        if(absentParentName!=null){
            recipientData = getRecipientData(absentParentName,coCorrespondence.getCoReqSeq());
        }

        return recipientData;
    }
}
