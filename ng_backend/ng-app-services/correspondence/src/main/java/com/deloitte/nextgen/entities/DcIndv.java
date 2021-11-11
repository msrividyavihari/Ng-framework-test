package com.deloitte.nextgen.entities;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneBaseEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.deloitte.nextgen.repository.DcIndvRepository;
import com.deloitte.nextgen.util.CoUtil;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.StringTokenizer;

@Entity
@Table(name ="DC_INDV")
@EntityType(type= TypeEnum.ONE, customRepository = DcIndvRepository.class)
@Data
@NoArgsConstructor
public class DcIndv  extends TypeOneBaseEntity<Long> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "DC_INDV_1SQ")
    @Column(name = "INDV_ID")
    private Long indvId;
    @Column(name = "SSN")
    private Long ssn;
    @Column(name = "DOB_DT")
    private Timestamp dobDt;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "SUFX_NAME")
    private String sufxName;
    @Column(name = "MID_NAME")
    private String midName;
    @Column(name = "DOB_VRF_CD")
    private String dobVrfCd;
    @Column(name = "RACE_CD")
    private String raceCd;
    @Column(name = "SSA_VALIDATION_SW")
    private Character ssaValidationSw;
    @Column(name = "SSN_VRF_CD")
    private String ssnVrfCd;
    @Column(name = "ETHNICITY_CD")
    private String ethnicityCd;
    @Column(name = "GENDER_CD")
    private Character genderCd;
    @Column(name = "INACTIVE_IND")
    private Character inactiveInd;
    @Column(name = "FILE_CLEARANCE_SW")
    private Character fileClearanceSw;
    @Column(name = "MERGE_SEPARATE_IND")
    private String mergeSeparateInd;
    @Column(name = "DEATH_DT")
    private Timestamp deathDt;
    @Column(name = "MHMR_CASE_NUM")
    private String mhmrCaseNum;
    @Column(name = "DECEASED_DT_VRF_CD")
    private String deceasedDtVrfCd;
    @Column(name = "DELETE_SW")
    private Character deleteSw;
    @Column(name = "CLAIMED_SSN")
    private Long claimedSsn;
    @Column(name = "SSA_VRF_CD")
    private String ssaVrfCd;
    @Column(name = "ESTIMATED_DOB_SW")
    private Character estimatedDobSw;
    @Column(name = "AUTH_REP_SW")
    private Character authRepSw;
    @Column(name = "CITIZENSHIP_VRF")
    private String citizenshipVrf;
    @Column(name = "IDENTITY_VRF")
    private String identityVrf;
    @Column(name = "CITIZENSHIP_DT")
    private Timestamp citizenshipDt;
    @Column(name = "IDENTITY_DT")
    private Timestamp identityDt;
    @Column(name = "FILE_CLEARANCE_DT")
    private Timestamp fileClearanceDt;
    @Column(name = "EBT_SW")
    private Character ebtSw;
    @Column(name = "PREFIX_NAME")
    private String prefixName;
    @Column(name = "VOTE_REG_WISH_SW")
    private Character voteRegWishSw;
    @Column(name = "RES_ADDR_SW")
    private Character resAddrSw;
    @Column(name = "ADDR_FORMAT")
    private String addrFormat;
    @Column(name = "ADDR_LINE1")
    private String addrLine1;
    @Column(name = "ADDR_LINE2")
    private String addrLine2;
    @Column(name = "ADDR_LINE3")
    private String addrLine3;
    @Column(name = "ADDR_COUNTY_CD")
    private String addrCountyCd;
    @Column(name = "ADDR_CITY")
    private String addrCity;
    @Column(name = "ADDR_STATE_CD")
    private String addrStateCd;
    @Column(name = "ADDR_MILITARY")
    private String addrMilitary;
    @Column(name = "APO_FPO_ADDR")
    private String apoFpoAddr;
    @Column(name = "ADDR_ZIP5")
    private String addrZip5;
    @Column(name = "ADDR_ZIP4")
    private String addrZip4;
    @Column(name = "ADDR_CARE_OF_LINE")
    private String addrCareOfLine;
    @Column(name = "TAX_STATUS")
    private String taxStatus;
    @Column(name = "TAX_STATUS_VERF")
    private String taxStatusVerf;
    @Column(name = "INTERPRETER_SW")
    private String interpreterSw;
    @Column(name = "COMMUNICATION_ASST")
    private String communicationAsst;
    @Column(name = "PRIMARY_LANG")
    private String primaryLang;
    @Column(name = "OTHER_LANGUAGE")
    private String otherLanguage;
    @Column(name = "DISABILITY_ACCOM")
    private String disabilityAccom;
    @Column(name = "OTHER_ACCOMODATION")
    private String otherAccomodation;
    @Column(name = "REPRESENTATIVE_SW")
    private String representativeSw;
    @Column(name = "EBT_CARD_SW")
    private String ebtCardSw;
    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;
    @Column(name = "ORGANIZATION_ID")
    private String organizationId;
    @Column(name = "VERF_RECEIVED_DT")
    private Timestamp verfReceivedDt;
    @Column(name = "GMWD_FMONTH_CNT")
    private Long gmwdFmonthCnt;

    public String getAge() {
        int age,mm,dd,yyyy;
        String month,day,year;
        String returnString = "";
        Timestamp today = CoUtil.getCurrentDate();
        mm = Integer.parseInt(getMonth(today));
        dd = Integer.parseInt(getDay(today));
        yyyy = Integer.parseInt(getYear(today));
        String dob = getDob();
        if (dob != null) {
            StringTokenizer stkr = new StringTokenizer(dob, "/");
            if (stkr.countTokens() == 3) {
                month = stkr.nextToken();
                day = stkr.nextToken();
                year = stkr.nextToken();
            } else {
                return "";
            }
            age = yyyy - Integer.parseInt(year) - 1;
            if (mm  > Integer.parseInt(month)) {
                age++;
            } else if (mm == Integer.parseInt(month)
                    && dd >= Integer.parseInt(day)) {
                age++;
            }
            return (Integer.valueOf(age).toString());
        } else {
            return returnString;
        }
    }

    /**
     * This method returns the day part(dd) of the date object
     *
     * @param date contains date
     * @return String returns the day part(dd) of the date object
     */
    private String getDay(Timestamp date) {
        return date.toString().substring(8, 10);
    }

    /**
     * This method returns the month part(mm) of the date object
     *
     * @param date contains date
     * @return String returns the month part(mm) of the date object
     */
    private String getMonth(Timestamp date) {
        return date.toString().substring(5, 7);
    }

    /**
     * This method returns the year part(yyyy) of the date object
     *
     * @param date contains date
     * @return String returns the year part(yyyy) of the date object
     */
    private String getYear(Timestamp date) {
        return date.toString().substring(0, 4);
    }

    public String getName() {
        String name = "";
        String sufxNameValue = "";
        try {
            if (firstName != null) {
                name += firstName.trim();
            }else {
                name += "";
            }
            name += " ";
            if (midName != null) {
                name += midName.trim();
            }else {
                name += "";
            }
            name += " ";
            if (lastName != null) {
                name += lastName.trim();
            } else {
                name += "";
            }
            name += " ";
            if (sufxName != null && sufxName.trim().length()>0){
                //TODO: have to uncomment the below line
               // sufxNameValue = FwReferenceTableManager.getValueByColumn(true,"NAMESUFFIX",String.valueOf(sufxName.trim()),"Description");
                if(sufxNameValue != null){
                    name += sufxNameValue.trim();
                }
            }else{
                name += "";
            }
            name += " ";
            name += getAge();
            if(genderCd!='\u0000') {
                name += String.valueOf(genderCd);
            }
        } catch (Exception e) {

        }
        return name;
    }

    public String getLastNameFirst() {
        StringBuffer name = new StringBuffer();
        String sufxNameValue = "";
        try {
            if (lastName != null){
                name = name.append(lastName.trim());
            }
            name = name.append(" ");
            if (midName != null && midName.length() > 1)
                name = name.append(midName.trim().charAt(0));
            else if (midName != null){
                name = name.append(midName.trim());
            }
            name = name.append(" ");
            if (firstName != null){
                name = name.append(firstName.trim());
            }
            name = name.append(" ");

            if (sufxName != null){
                //TODO: have to uncomment the below line
                //sufxNameValue = FwReferenceTableManager.getValueByColumn(true,"NAMESUFFIX",String.valueOf(sufxName.trim()),"Description");
                if(sufxNameValue != null){
                    name = name.append(sufxNameValue.trim());
                }
            }
            name = name.append(" ");
            name = name.append(getAge());
            name = name.append(genderCd);
        } catch (Exception e) {
            //logger.log(FwConstants.LOGGING_DEFAULT_CATEGORY, ILog.ERROR, e.getMessage(), e);
        }
        return name.toString();
    }

    /**
     * Insert the method's description here. Creation date: (1/9/2002 10:04:02
     * AM) return java.lang.String
     *
     * @return String
     */
    public String getDob() {
        try {
            String dob;
            if (dobDt != null) {
                dob = dobDt.toString();
                String dobMonth;
                String dobDay;
                String dobYear;
                if (dob != null) {
                    dobMonth = dob.substring(5, 7);
                    dobDay = dob.substring(8, 10);
                    dobYear = dob.substring(0, 4);
                    dob = dobMonth + "/" + dobDay + "/" + dobYear;
                }
                return dob;
            } else
                return null;
        } catch (Exception e) {
            return null;
        }
    }
}
