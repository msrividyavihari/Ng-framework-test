package com.deloitte.nextgen.dto.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonPropertyOrder({"Address Flag","Notice Request Id", "Agency ID", "Agency Name", "Case Number", "Client Name", "Original Street 1",
        "Original Street 2", "Original City",
        "Original State", "Original Zip 4", "Original Zip 5", "Updated Street 1", "Updated Street 2", "Updated City",
        "Updated State", "Updated Zip 4",
        "Updated Zip 5", "Status", "Email ID", "Phone Number", "Preferred Communication",
        "Email Delivery Status", "Text Delivery Status"})
public class DataToSource {
    @JsonProperty("Address Flag")
    private String addressFlag;
    @JsonProperty("Notice Request Id")
    private Long noticeRequestId;
    @JsonProperty("Agency ID")
    private String agencyId;
    @JsonProperty("Agency Name")
    private String agencyName;
    @JsonProperty("Case Number")
    private Long caseNum;
    @JsonProperty("Client Name")
    private String clientName;
    @JsonProperty("Original Street 1")
    private String origStreet1;
    @JsonProperty("Original Street 2")
    private String origStreet2;
    @JsonProperty("Original City")
    private String origCity;
    @JsonProperty("Original State")
    private String origState;
    @JsonProperty("Original Zip 4")
    private Long origZip4;
    @JsonProperty("Original Zip 5")
    private Long origZip5;
    @JsonProperty("Updated Street 1")
    private String updStreet1;
    @JsonProperty("Updated Street 2")
    private String updStreet2;
    @JsonProperty("Updated City")
    private String updCity;
    @JsonProperty("Updated State")
    private String updState;
    @JsonProperty("Updated Zip 4")
    private Long updZip4;
    @JsonProperty("Updated Zip 5")
    private Long updZip5;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Email ID")
    private String emailId;
    @JsonProperty("Phone Number")
    private Long phoneNumber;
    @JsonProperty("Preferred Communication")
    private Character preferredCommunication;
    @JsonProperty("Email Delivery Status")
    private Character emailDelivery;
    @JsonProperty("Text Delivery Status")
    private Character textDelivery;
    @JsonProperty("Reason For Failure")
    private String reasonForFailure;

}
