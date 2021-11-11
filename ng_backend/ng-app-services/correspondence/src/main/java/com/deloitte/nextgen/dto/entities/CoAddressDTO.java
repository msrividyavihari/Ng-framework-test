package com.deloitte.nextgen.dto.entities;

import com.deloitte.nextgen.util.Candidate;
import com.deloitte.nextgen.util.MatchType;
import lombok.Data;

import java.util.List;

@Data
public class CoAddressDTO {


    private String street;

    private String street2;

    private String city;

    private String state;

    private String zipCode;

    private String addressee;

    private String match;

    private String secondary;

    //The maximum number of addresses returned when the input is ambiguous
    private int maxCandidates;

    private List<Candidate> result;

    private char matchCode;


    public char getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(char matchCode) {
        this.matchCode = matchCode;
    }

    public String getStreet() {
        return this.street;
    }

    public String getStreet2() {
        return this.street2;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public String getAddressee() {
        return this.addressee;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public int getMaxCandidates() {
        return maxCandidates;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public List<Candidate> getResult() {
        return this.result;
    }

    public Candidate getResult(int index) {
        return this.result.get(index);
    }

    public CoAddressDTO() {
        this.maxCandidates = 1;
    }

    public CoAddressDTO(String freeformAddress) {
        this();
        this.street = freeformAddress;
    }

    void addToResult(Candidate newCandidate) {
        this.result.add(newCandidate);
    }

    public MatchType getMatch() {
        if (this.match == null)
            return null;
        if (this.match.equals("strict") )
            return MatchType.STRICT;
        if (this.match.equals("range") )
            return MatchType.RANGE;
        if (this.match.equals("invalid") )
            return MatchType.INVALID;
        if (this.match.equals("enhanced") )
            return MatchType.ENHANCED;
        return null;
    }

    public String getMatchString() {
        MatchType match = getMatch();
        if (match != null)
            return match.getName();
        return null;
    }

    public void setResult(List<Candidate> result) {
        this.result = result;
    }

    public void setMatch(MatchType match) {
        this.match = match.getName();
    }

    public void setMaxCandidates(int maxCandidates) throws IllegalArgumentException {
        if (maxCandidates > 0) {
            this.maxCandidates = maxCandidates;
        } else {
            throw new IllegalArgumentException("Max candidates must be a positive integer.");
        }
    }


}
