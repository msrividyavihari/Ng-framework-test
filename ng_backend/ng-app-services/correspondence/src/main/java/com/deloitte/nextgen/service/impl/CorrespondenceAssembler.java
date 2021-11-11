package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.entities.COCorrespondence;
import com.deloitte.nextgen.entities.DcIndv;
import com.deloitte.nextgen.entities.IndividualInformationCargo;
import com.deloitte.nextgen.entities.MoEmployees;
import com.deloitte.nextgen.repository.MoEmployeesRepository;
import com.deloitte.nextgen.service.CoIESBaseAssembler;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoUtil;
import com.deloitte.nextgen.util.PrintDate;
import com.deloitte.nextgen.util.xsd.schema.notices.FormData;
import com.deloitte.nextgen.util.xsd.schema.notices.IESCorrespondence;
import com.deloitte.nextgen.util.xsd.schema.notices.MetaData;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Slf4j
public abstract class CorrespondenceAssembler implements CoIESBaseAssembler {

    @Autowired
    CoDAOServices coDAOServices;
    @Autowired
    MoEmployeesRepository moEmployeesRepository;

    /**
     * Field iesCorrespondence
     */
    private IESCorrespondence iesCorrespondence = new IESCorrespondence();
    /**
     * Field coCorrespondence.
     */
    private COCorrespondence coCorrespondence = new COCorrespondence();
    /**
     * Field HOH IndividualInformationCargo
     */
    private IndividualInformationCargo hohIndividualInformation = new IndividualInformationCargo();
    /**
     * This method is used to populate MetaData & FormData into iesCorrespondence
     * @param coCorrespondence Correspondence
     * @return IESCorrespondence
     * @throws Exception Exception
     */
    public IESCorrespondence getIESCorrespondence(final COCorrespondence coCorrespondence) throws Exception {
        setCoCorrespondence(coCorrespondence);
        // Set MetaData
        iesCorrespondence.setMetaData(getIESMetaData(
                getCoCorrespondence(), getCoCorrespondence().getDocId()));
        // Set FormData
        iesCorrespondence.setFormData(getFormData());
        return iesCorrespondence;
    }

    protected abstract FormData getFormData() throws Exception;

    private MetaData getIESMetaData(final COCorrespondence coCorrespondence, final String templateId) throws Exception {
        final MetaData metaData = new MetaData();

        log.debug("getMetaData-->Print Mode:" + coCorrespondence.getPrintMode());
        // In case of Online Printing, coRptSeq is set to 0
        if (CoConstants.PRINT_MODE_ONLINE == coCorrespondence.getPrintMode()) {
            coCorrespondence.setCoRptSeq(0L);
        }
        metaData.setTemplateId(templateId);
        if (CoConstants.PRINT_MODE_BATCH == coCorrespondence.getPrintMode()) {
            metaData.setRecipientId(String.valueOf(coCorrespondence.getCoRptSeq()));
        }
        metaData.setJobName(coCorrespondence.getJobId());
        if (StringUtils.isEmpty(coCorrespondence.getAsOfDate())) {
            // for online
            metaData.setMailDate(CorrespondenceServices
                    .getYYYYMMDDFormattedDateStr(CoUtil.dateToString(CoUtil.getCurrentDate()),
                            CoConstants.FORMS_DATE_FORMAT));
        } else {
            // for batch
            metaData.setMailDate(coCorrespondence.getAsOfDate());
        }
        metaData.setCorrNum(String.valueOf(coCorrespondence.getCoReqSeq()));
        if (coCorrespondence.isPreviewMode()) {
            metaData.setWatermark(CoConstants.YES_STRING_Y);
        } else {
            metaData.setWatermark(CoConstants.NO_STRING_N);
        }
        if (coCorrespondence.getSecuritySw()!=null && coCorrespondence.getSecuritySw()==CoConstants.CHAR_Y) {
            metaData.setSecurityFlag(CoConstants.YES_STRING_Y);
        } else {
            metaData.setSecurityFlag(CoConstants.NO_STRING_N);
        }
        metaData.setFormNo(coCorrespondence.getDocId());

        metaData.setNoticeType(CorrespondenceServices.getDISDNoticeType(coCorrespondence.getDocId()));
        metaData.setFormVersion("1");
        metaData.setFormTitle(coCorrespondence.getDocName());
        metaData.setEnvelopeId("");
        metaData.setCaseNum(coCorrespondence.getCaseAppNumber());
        metaData.setGoGreen(CoConstants.NO_STRING_N);
        if (CoConstants.CONST_SPANISH_LANG_CD.
                equals(coCorrespondence.getLangCd())) {
            metaData.setAdditionalLanguage(CoConstants.CONST_SPANISH_LANG_CD);
        }
        if(getCoCorrespondence().getCaseAppNumber()!=null){
            getHoh();
        }
        return metaData;
    }


    private void getHoh() throws Exception {
        List<DcIndv> dcIndvObj = coDAOServices.getHOHIndv(getCoCorrespondence().getCaseAppNumber(),getCoCorrespondence().getCaseAppFlag());

        hohIndividualInformation.setName(dcIndvObj.get(0).getFirstName() + CoConstants.SPACE
                + dcIndvObj.get(0).getLastName());
        hohIndividualInformation.setSsn(dcIndvObj.get(0).getSsn());
        hohIndividualInformation.setIndividualID(dcIndvObj.get(0).getIndvId());

        try{
            hohIndividualInformation.setDobPrintDate(
                    new PrintDate(dcIndvObj.get(0).getDobDt()));
        }catch(Exception ex){
            log.debug("Exception occurred while retrieving hohIndividual DOB:" + ex.getMessage(), ex);
        }
    }

    protected List<MoEmployees> getMoEmployeesCargo(final String caseAppNumber, String userId) throws Exception {
        List<MoEmployees> moEmployeesCargos =null;

        try {
            // Getting most recent case worker associated to case or app or case associated to app
            if(StringUtils.isNumeric(caseAppNumber)) {
                moEmployeesCargos = moEmployeesRepository.findByEmployeeByCaseNum(caseAppNumber);
            }else{
               // moEmployeesCargos = moEmployeesRepository.findByEmployeeByAppNum(caseAppNumber);
            }
            if(moEmployeesCargos == null || moEmployeesCargos.size() ==0) {
                // as could not find associated case, trying to get based on create user of notice trigger
                moEmployeesCargos = moEmployeesRepository.findByUserId(userId.toLowerCase());
            }
        }  catch (final Exception ex) {
            throw new Exception(getCoCorrespondence().getCoReqSeq() +" "+ ex.getMessage(), ex) ;
        }
        return moEmployeesCargos;
    }

    protected String getDescription(String tableNm, String code,String spanishColumn, String englishColumn) {
        String description = StringUtils.EMPTY;
        try {
            if (CoConstants.CONST_SPANISH_LANG_CD.equals(getCoCorrespondence().getLangCd())) {
                /*description =  ReferenceTableAccess.getRefDescription(
                        code,tableNm,spanishColumn);*/
            } else {
                /*description =  ReferenceTableAccess.getRefDescription(
                        code,tableNm,englishColumn);*/
            }
        } catch (final Exception e) {

            StringBuilder sb = new StringBuilder("Error while fetching ");
            sb.append(spanishColumn);
            sb.append(" and ");
            sb.append(englishColumn);
            sb.append(" from :");
            sb.append(tableNm);
            sb.append(" for ");
            sb.append(code);
        }
        return description;
    }
}
