package com.deloitte.nextgen.service.impl;

import com.deloitte.nextgen.entities.COCorrespondence;
import com.deloitte.nextgen.entities.CoRequestRecipients;
import com.deloitte.nextgen.entities.VCoRequest;
import com.deloitte.nextgen.repository.CoRequestRecipientsRepository;
import com.deloitte.nextgen.service.CoAssembler;
import com.deloitte.nextgen.service.ICoOnline;
import com.deloitte.nextgen.util.CoConstants;
import com.deloitte.nextgen.util.CoRequestGenerator;
import com.deloitte.nextgen.util.CoUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ICoOnlineImpl implements ICoOnline {

    @Autowired
    CoAssembler masterAssembler;

    @Autowired
    CoRequestRecipientsRepository requestRecipientsRepository;

    @Autowired
    CoRequestGenerator coRequestGenerator;

    private boolean newCorrespondence = false;
    private VCoRequest retCargo = null;
    private String pdfPath = "";

    /**
     * Method initiates Correspondence
     *
     * @param aCoRequest - COCorrespondence object containing data to generate correspondence
     * @return int - Returns a specific integer depending upon success or failure of initiating correspondence
     */
    @Override
    public int initiateCorrespondence(COCorrespondence aCoRequest) throws Exception {
        if (aCoRequest == null) {
            return 303;
        }

        final String aCoObj = aCoRequest.toString();

        aCoRequest = coRequestGenerator.createRequest(aCoRequest);
        if ((aCoRequest.getHistorySwitch() == '\u0000')
                || (aCoRequest.getHistorySwitch() == 'Y')) {
            coRequestGenerator.setNewCorrespondence(true);
        }
        if (aCoRequest.getDraftSwitch() == CoConstants.YES_CHAR) {
            coRequestGenerator.setNewCorrespondence(false);
        }
        setNewCorrespondence(coRequestGenerator.isNewCorrespondence());
        int rtnCode = 0;
        if (aCoRequest.getPrintMode() == 'I') {
            if (aCoRequest.getDocId().equals("FXX014")) {
                aCoRequest.setManuallyGenerated(true);
            }
            final Object[] objParams = new Object[1];
            List<CoRequestRecipients> coReqRptCargos;
            coReqRptCargos = requestRecipientsRepository.findByCoReqSeqOrderByCoRptSeq(aCoRequest.getCoReqSeq());
            if (!CoUtil.isEmpty(coReqRptCargos)) {
                final String locationOfPDF = coReqRptCargos.get(0)
                        .getLocationPath();
                aCoRequest.setFileLocation(locationOfPDF);
            }
            rtnCode = printPreviewCo(aCoRequest);
        } else if (CoConstants.UPDATE_CPC.equals(aCoRequest.getActionValue())
                || (CoConstants.COVPC + CoConstants.GENERATECO).equals(aCoRequest.getActionValue())) {
            rtnCode = printPreviewCo(aCoRequest);
        }
        return rtnCode;
    }

    /**
     * Method prints preview of Correspondence
     *
     * @param aCoRequest - COCorrespondence object containing data to generate correspondence
     * @return int - Returns a specific integer depending upon success or failure of initiating correspondence
     */
    @Override
    public int printPreviewCo(COCorrespondence aCoRequest)  {
        int errorCode = 0;
        try{
            String errMsg = "";
            StringBuilder sb;
            sb = masterAssembler.generateDocument(aCoRequest, -99);
            if (sb != null) {
                setRetCargo(aCoRequest, sb.toString());
            }

        } catch (Exception e) {
            errorCode = -1;
        }
        return errorCode;
    }

    /**
     * Method prints preview of Correspondence
     *
     * @param aCoRequest - COCorrespondence object containing data to generate correspondence
     * @return int - Returns a specific integer depending upon success or failure of initiating correspondence
     */
    @Override
    public String printXMLPreviewCo(COCorrespondence aCoRequest) throws Exception {

        int errorCode = 0;
        String errMsg = "";
        StringBuilder sb;
        sb = masterAssembler.generateDocument(aCoRequest, -99);
        if (sb != null) {
            setRetCargo(aCoRequest, sb.toString());
        }
        assert sb != null;
        return sb.toString();
    }

    private void setRetCargo(final COCorrespondence aCoRequest,
                             final String pdfFilePath) {
        retCargo = new VCoRequest();
        setPdfPath(pdfFilePath);
        CorrespondenceServices.formatCoObject(retCargo, aCoRequest);
        retCargo.setT2CoReqSeq(aCoRequest.getCoReqSeq());
        retCargo.setCoDetSeq(aCoRequest.getCoDetSeq());
    }
}