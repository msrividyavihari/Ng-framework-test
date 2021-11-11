package com.deloitte.nextgen.util;

import com.deloitte.nextgen.entities.COCorrespondence;
import com.deloitte.nextgen.entities.CoMaster;
import com.deloitte.nextgen.entities.MoEmployeeCases;
import com.deloitte.nextgen.service.impl.CoDAOServices;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Data
@Service
@Slf4j
public class CoRequestGenerator {

    @Autowired
    CoDAOServices coDAOServices;

    private boolean newCorrespondence = false;

    public COCorrespondence createRequest(COCorrespondence aCoObj) throws Exception {
        if (aCoObj.getActionCode() != CoConstants.CHAR_C) {
            aCoObj = validateRequest(aCoObj);
        }
        aCoObj = processRequest(aCoObj);
        return aCoObj;

    }

    public COCorrespondence processRequest(COCorrespondence aCoObj) {
        int rtnCode;
        char hisSw = aCoObj.getHistorySwitch();
        long coReqSeqNum = aCoObj.getCoReqSeq();

        if (Character.toUpperCase(aCoObj.getActionCode()) == CoConstants.CHAR_C) {
            processCancel(aCoObj);
            return aCoObj;
        } else if (Character.toUpperCase(aCoObj.getActionCode()) == CoConstants.CHAR_M) {
            processEmpTransfer(aCoObj);
            return aCoObj;
        } else if (Character.toUpperCase(aCoObj.getActionCode()) == CoConstants.CHAR_I) {
            processIndvTransfer(aCoObj);
            return aCoObj;
        } else if (Character.toUpperCase(aCoObj.getActionCode()) == CoConstants.CHAR_O) {
            processOfficeTransfer(aCoObj);
            return aCoObj;
        }

        if (((coReqSeqNum == 0) && (hisSw == 'N'))
                || ((aCoObj.getHistorySwitch() == 'Y') && (aCoObj
                .getPrintMode() == 'B'))
                || ((aCoObj.getHistorySwitch() == 'Y') && (aCoObj
                .getPrintMode() == 'O'))) {
            rtnCode = coDAOServices.insertOriginalRequest(aCoObj);
        }
        return aCoObj;
    }

    private void processOfficeTransfer(COCorrespondence aCoObj) {
        if (aCoObj.getOfficeNumber() > 0) {
            coDAOServices.transferRequestedOffice(aCoObj);
        }
    }

    private void processIndvTransfer(COCorrespondence aCoObj) {
        if (aCoObj.getIndvId() > 0) {
            coDAOServices.transferRequestedIndividual(aCoObj);
        }
    }

    private void processEmpTransfer(COCorrespondence aCoObj) {
        if (aCoObj.getEmpId() > 0) {
            coDAOServices.transferRequestedEmployee(aCoObj);
        }
    }

    private void processCancel(COCorrespondence aCoObj) {
        if (aCoObj.getHistorySwitch() == CoConstants.CHAR_Y) {
            aCoObj.setActionCode(CoConstants.CHAR_N);
        }
        int rtnCode = 0;
        if ("FXX172".equals(aCoObj.getDocId())) {
            rtnCode = coDAOServices.cancel3797PendingTrigger(aCoObj);
        } else {
            rtnCode = coDAOServices.cancelRequestedTrigger(aCoObj);
        }
    }

    public COCorrespondence validateRequest(COCorrespondence aCoObj) throws Exception {
        if (!(aCoObj.getActionCode() == CoConstants.CHAR_M)
                && !(aCoObj.getActionCode() == CoConstants.CHAR_I)
                && !(aCoObj.getActionCode() == CoConstants.CHAR_O)) {
            if (aCoObj.getTypeOfAssistanceList() == null) {
                aCoObj.setTypeOfAssistanceList(CoConstants.SPACE);
            }
            Character uPrintMode = aCoObj.getPrintMode();
            if (uPrintMode == 0) {
                throw new Exception("Validate of PrintMode failed as PrintMode not set in Co Obj");
            } else {
                uPrintMode = String.valueOf(uPrintMode).toUpperCase().charAt(0);
                if (!(uPrintMode == CoConstants.CHAR_B)
                        && !(uPrintMode == CoConstants.CHAR_O)
                        && !(uPrintMode == CoConstants.CHAR_I)) {
                    throw new Exception(
                            "Validate of PrintMode failed as PrintMode not B or O or I");
                } else {
                    aCoObj.setPrintMode(uPrintMode);
                }
            }
            // Language Code validation
            Character dbType = 0;
            String uDocId = aCoObj.getDocId();
            if ((uDocId == null) || uDocId.equals("")) {
                throw new Exception("Invalid Document-ID in Correspondence Request");
            } else {
                uDocId = uDocId.toUpperCase();
                aCoObj.setDocId(uDocId);
                List<CoMaster> masterCargo = coDAOServices.getCoMasterData(uDocId);
                if (!CoUtil.isEmpty(masterCargo)) {
                    dbType = masterCargo.get(0).getDocTypeCd();
                } else  {
                    throw new Exception("No Data for DocId in CoMaster ");
                }
            }
            aCoObj.setDocType(dbType);
            String requestUserId = aCoObj.getRequestUserId();
            if (CoUtil.isEmpty(requestUserId)) {
                aCoObj.setRequestUserId(" ");
            }
            if (aCoObj.getCoReqSeq() == 0) {
                aCoObj.setHistorySwitch(CoConstants.CHAR_N);
            }
            char uCaseAppFlag = aCoObj.getCaseAppFlag();
            if (uCaseAppFlag == 0) {
                throw new Exception(
                        "Case App Flag is 0  - Valid values are A, C or L");
            } else {
                uCaseAppFlag = String.valueOf(uCaseAppFlag).toUpperCase().charAt(0);
                if (!(uCaseAppFlag == CoConstants.CHAR_A)
                        && !(uCaseAppFlag == CoConstants.CHAR_C)
                        && !(uCaseAppFlag == CoConstants.CHAR_L)
                        && !(uCaseAppFlag == CoConstants.CHAR_S)
                        && !(uCaseAppFlag == CoConstants.CHAR_P)) {
                    throw new Exception(
                            "Case App Flag not set correctly - Valid values are A, C,P or L");
                }
            }
            // Assistance Program Code Validation
            String pgmCode = aCoObj.getAssistanceProgramCode();
            if (CoUtil.isEmpty(pgmCode)) {
                aCoObj.setAssistanceProgramCode(" ");
            }
            // Action Code Validation
            char uActCd = aCoObj.getActionCode();
            if (uActCd == 0) {
                aCoObj.setActionCode(' ');
            } else {
                aCoObj.setActionCode(String.valueOf(uActCd).toUpperCase().charAt(0));
            }
            // Reason Code List Validation
            String uRsnCdLst = aCoObj.getReasonCdList();
            if (CoUtil.isEmpty(uRsnCdLst)) {
                aCoObj.setReasonCdList(" ");
            } else if (!"NOD003".equals(aCoObj.getDocId())) {
                aCoObj.setReasonCdList(uRsnCdLst.toUpperCase());
            }

            char uDrftSw = aCoObj.getDraftSwitch();
            if (uDrftSw != CoConstants.CHAR_Y) {
                aCoObj.setDraftSwitch(CoConstants.CHAR_N);
            }
            if (aCoObj.getCaseAppFlag() != 'P') {
                if (aCoObj.getEmpId() == 0) {
                    if (aCoObj.getCaseAppFlag() == 'C') {
                        List<MoEmployeeCases> temp = coDAOServices.getMoEmployeeCasesFromRequest(Long.valueOf(
                                aCoObj.getCaseAppNumber()));
                        if (!CoUtil.isEmpty(temp)) {
                            aCoObj.setEmpId(temp.get(0).getEmpId());
                        } else {
                            log.debug("Case worker is not assigned to the case number: " + aCoObj.getCaseAppNumber());
                        }
                    } else if (aCoObj.getCaseAppFlag() == 'A') {
                        /*MoEmployeeApps[] temp = coDAOServices.getMoEmployeeApps(aCoObj.getCaseAppNumber());
                        aCoObj.setEmpId(temp[0].getEmpId());*/
                    }
                }
            }

            String uMiscParms = aCoObj.getMiscParameters();
            if (CoUtil.isEmpty(uMiscParms)) {
                aCoObj.setMiscParameters(" ");
            }
        }
        return aCoObj;
    }
}
