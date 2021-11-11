package com.deloitte.nextgen.util;

public class Candidate {

    private String inputId;

    private int inputIndex;

    private int candidateIndex;

    private String addressee;

    private String deliveryLine1;

    private String deliveryLine2;

    private String lastLine;

    private String deliveryPointBarcode;

    private Components components;

    private Metadata metadata;

    private Analysis analysis;

    //endregion


    public Candidate() {
    }

    public Candidate(int inputIndex) {
        this.inputIndex = inputIndex;
    }


    //region [ Getters ]

    public Components getComponents() {
        return this.components;
    }

    public Metadata getMetadata() {
        return this.metadata;
    }

    public Analysis getAnalysis() {
        return this.analysis;
    }

    public String getInputId() { return this.inputId; }

    public int getInputIndex() {
        return this.inputIndex;
    }

    public int getCandidateIndex() {
        return this.candidateIndex;
    }

    public String getAddressee() {
        return this.addressee;
    }

    public String getDeliveryLine1() {
        return this.deliveryLine1;
    }

    public String getDeliveryLine2() {
        return this.deliveryLine2;
    }

    public String getLastLine() {
        return this.lastLine;
    }

    public String getDeliveryPointBarcode() {
        return this.deliveryPointBarcode;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId;
    }

    public void setInputIndex(int inputIndex) {
        this.inputIndex = inputIndex;
    }

    public void setCandidateIndex(int candidateIndex) {
        this.candidateIndex = candidateIndex;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public void setDeliveryLine1(String deliveryLine1) {
        this.deliveryLine1 = deliveryLine1;
    }

    public void setDeliveryLine2(String deliveryLine2) {
        this.deliveryLine2 = deliveryLine2;
    }

    public void setLastLine(String lastLine) {
        this.lastLine = lastLine;
    }

    public void setDeliveryPointBarcode(String deliveryPointBarcode) {
        this.deliveryPointBarcode = deliveryPointBarcode;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }
}
