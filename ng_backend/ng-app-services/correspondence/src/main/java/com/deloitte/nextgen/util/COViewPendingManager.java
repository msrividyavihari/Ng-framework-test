package com.deloitte.nextgen.util;

import com.deloitte.nextgen.entities.CoRequestRecipients;
import com.deloitte.nextgen.framework.commons.exceptions.FwException;
import com.deloitte.nextgen.service.impl.CoDAOServices;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.ng.reftables.ReferenceTableManager;
import org.springframework.beans.factory.annotation.Autowired;

public class COViewPendingManager {

    ReferenceTableManager tableManager;

    @Autowired
    CoDAOServices coDAOServices;


   /* public int updateCoReqHistoryComments(ViewPendingDTO pendingDTO) throws Exception {

        int commitFlag = 0;
        final String comment = (String) request.get("comment");
        final long coReqSeq = Long.valueOf((String) request.get("coReqSeq"));
        final Object[] objParams = new Object[1];
        col.getCargo().setCoReqSeq(coReqSeq);
        objParams[0] = col.getCargo();

        final Object[] params = new Object[] { comment, coReqSeq };
        try {
            col.select("updateCoSpecialNotes", params);
            CoDebugger.debugInformation("End updateCoReqHistory = " + col);
            commitFlag = 1;
        } catch (final Exception e) {
            commitFlag = 0;
            CoDebugger.debugException(e.getMessage(), e);
        }
        return commitFlag;
    }*/

    public int suppressUnsuppress(final long coReqSeq, final String actionVal,
                                  final String docId,final String supUserId) throws Exception {

        int errorCode = 0;
        CoRequestRecipients[] coReqRecipientCargo = null;
        String fileName = CoConstants.EMPTY_STRING;
        String filePath = CoConstants.EMPTY_STRING;
        String result = CoConstants.EMPTY_STRING;
        String[] bits = null;
        if (checkForNotice(docId)) {
            errorCode = coDAOServices.updateCoReqHistoryStatusSwfornotice(coReqSeq, actionVal, supUserId);
        } else /*{
            coReqRecipientCargo = (CoRequestRecipients[]) coDAOService
                    .getCoReqRecipientForReqSeq(coReqSeq);

            filePath = coReqRecipientCargo[0].getLocationPath();

            if (filePath.contains("/")) {
                bits = filePath.split("/");
            } else {
                bits = filePath.split("//");
            }
            final String lastOne = bits[bits.length - 1];
            if ((filePath != null) && !"".equals(filePath)) {
                fileName = lastOne;
            }
            if ((actionVal != null) && actionVal.equals(CoConstants.SUPPRESS)) {
                CentralPrintSuppressResponse centralPrintSuppressResponse = null;
                InvokeResponse invokeResponse = null;
                XML xmlObj = null;
                final Invoke invoke = new Invoke();
                final SuppressBO suppressBO = new SuppressBO();
                invoke.setStrFileName(fileName);
                try {
                    centralPrintSuppressResponse = suppressBO
                            .adobeSuppress(invoke);
                } catch (Exception e) {
                    errorCode = Integer.parseInt(CoConstants.STRING_16915);
*//*                    CoDebugger.debugException(
                            "Error while invoking AdobeSuppress webservice API : "
                                    + e.getMessage(), e);*//*
                }
                if (centralPrintSuppressResponse != null) {
                    invokeResponse = centralPrintSuppressResponse
                            .getInvokeResponse();
                    if (invokeResponse != null) {
                        xmlObj = invokeResponse.getXmlResult();
                        result = xmlObj.getDocument();
                    }
                } else {
                    errorCode = Integer.parseInt(CoConstants.STRING_16915);
*//*                    CoDebugger
                            .debugMessage("AdobeSuppress webservice API invokeResponseHolder value : "
                                    + centralPrintSuppressResponse);*//*
                }

            } else {
                CentralPrintUnsuppressResponse centralPrintUnsuppressResponse = null;
                gov.state.nextgen.ejb.business.services.unsuppress.invoke.InvokeResponse invokeResponse = null;
                gov.state.nextgen.ejb.business.services.unsuppress.invoke.XML xmlObj = null;
                final gov.state.nextgen.ejb.business.services.unsuppress.invoke.Invoke invoke = new gov.state.nextgen.ejb.business.services.unsuppress.invoke.Invoke();
                invoke.setStrFileName(fileName);
                final UnSuppressBO unSuppressBO = new UnSuppressBO();
                try {
                    centralPrintUnsuppressResponse = unSuppressBO
                            .adobeUnSuppress(invoke);
                } catch (ApplicationException | FrameworkException e) {
                    errorCode = Integer.parseInt(CoConstants.STRING_16915);
*//*                    CoDebugger.debugException(
                            "Error while invoking AdobeUnsuppress webservice API : "
                                    + e.getMessage(), e);*//*
                }
                if (centralPrintUnsuppressResponse != null) {
                    invokeResponse = centralPrintUnsuppressResponse
                            .getInvokeResponse();
                    if (invokeResponse != null) {
                        xmlObj = invokeResponse.getXmlResult();
                        result = xmlObj.getDocument();
                    }
                } else {
                    errorCode = Integer.parseInt(CoConstants.STRING_16915);
*//*                    CoDebugger
                            .debugMessage("AdobeUnsuppress webservice API invokeResponseHolder value : "
                                    + centralPrintUnsuppressResponse);*//*
                }

            }
            if (result.indexOf(CoConstants.RESULT_DONE_XML) > 0) {
                errorCode = updateCoReqHistoryStatusSw(coReqSeq, actionVal,supUserId);
            } else {
                errorCode = Integer.parseInt(CoConstants.STRING_16915);
*//*                CoDebugger
                        .debugMessage("Adobe Suppress/Unsuppress Webservice API result : "
                                + result);*//*
            }
        }*/

        if (errorCode == Integer.parseInt(CoConstants.STRING_16915)) {
            throw new Exception(Integer.valueOf(errorCode).toString());
        }


        return errorCode;
    }

    /**
     * @param docId
     * @return
     * @throws Exception
     */
    private boolean checkForNotice(final String docId) throws FwException, FwApplicationException {
        boolean isNotice = false;
        try {
            if (tableManager.getValueByColumn(true, "CORRESPNAMES",
                    docId, "NOTICE_YN").equalsIgnoreCase(
                    "Y")) {
                isNotice = true;
            }
        } catch (Exception ex) {
            isNotice = docId.startsWith(String
                    .valueOf(CoConstants.CO_DOC_TYPE_CD_N));
            throw ex;
        }
        return isNotice;
    }



}
