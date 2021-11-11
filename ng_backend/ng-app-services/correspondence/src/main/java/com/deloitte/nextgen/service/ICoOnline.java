package com.deloitte.nextgen.service;

import com.deloitte.nextgen.entities.COCorrespondence;

public interface ICoOnline {
    int initiateCorrespondence(COCorrespondence coObject) throws Exception;
    String getPdfPath();
    int printPreviewCo(COCorrespondence aCoRequest) throws Exception;
    String printXMLPreviewCo(COCorrespondence aCoRequest) throws Exception;

    public boolean isNewCorrespondence();
}
