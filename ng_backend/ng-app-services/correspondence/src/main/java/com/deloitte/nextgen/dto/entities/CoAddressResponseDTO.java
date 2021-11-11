package com.deloitte.nextgen.dto.entities;

public class CoAddressResponseDTO {


    private String addressee;

    private String deliveryLine1;

    private String deliveryLine2;

    private String lastLine;

    private boolean addressValid;

    public CoAddressResponseDTO() {
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

    public boolean isAddressValid() { return addressValid;}

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

    public void setAddressValid(boolean addressValid) {
        this.addressValid = addressValid;
    }
}
