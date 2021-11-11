package com.deloitte.nextgen.service;

import com.deloitte.nextgen.entities.COCorrespondence;

public interface CoAssembler {
    StringBuilder generateDocument(COCorrespondence coCorrespondence, long templateSeqNo) throws Exception;
    String generateDocumentForBatch(COCorrespondence coCorrespondence);
    void generateCentralPrintXML(COCorrespondence coCorrespondence) throws Exception;
    String generateLocalPrintXML(COCorrespondence coCorrespondence) throws Exception;
    String generateLocalPrintNoMarkXML(COCorrespondence coCorrespondence) throws Exception;
}
