package com.deloitte.nextgen.ha.model;

import com.google.common.collect.ImmutableSet;
import lombok.ToString;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

/**
 * Enum to maintain all Appeal status codes, description and the display value in Worker portal
 * <p>
 * The Enum constants are defined based on the order of their priorities defined
 * Modifying or adding new constants in Enum might have an impact to existing
 * implementation.
 */
@ToString
public enum AppealStatusCode implements CodeDescriptionPair<String, String> {

    /**
     * WARNING: Do not change the order of definition.
     * <p>
     * Can't override compareTo method in enums as they are final and uses the Enum Ordinal
     * (the order enum constants declared) for comparison
     */
    FILING_IN_PROGRESS("FIP", "Filing in Progress", "Review In Progress")
    , FILED("FL", "Filed", "Review In Progress")
    , REGISTRATION_IN_PROGRESS("RG", "Registration in Progress", "Review In Progress")
    , PENDING_CLIENT_DELAY("PCD", "Pending Client - Delay", "Review In Progress")
    , VFD_IN_PROGRESS("VF", "VFD in Progress", "Review In Progress")
    , RESOLUTION_IN_PROGRESS("RIP", "Resolution in Progress", "Review In Progress")
    , RENEWAL_APPEAL_IN_PROGRESS("RAP", "Renewal Appeal in Progress", "")
    , PENDING_RENEWAL("PR", "Pending - Renewal", "")
    , PENDING_ELIGIBILITY("PE", "Pending - Eligibility", "")
    , PENDING_CLIENT_VFD("PCE", "Pending Client - VFD", "Review In Progress")
    , TO_BE_SCHEDULED("TBS", "To Be Scheduled", "Scheduling in Progress")
    , SCHEDULED("S", "Scheduled", "Scheduling in Progress")
    , READY_FOR_HEARING("RH", "Ready for Hearing", "Scheduled for Hearing")
    , PENDING_ORDER("PO", "Pending Order", "Scheduled for Hearing")
    , CONTINUED_APPELLANT("CA", "Continued- Appellant", "Scheduled for Hearing")
    , CONTINUED_DEPARTMENT("CD", "Continued - Department", "Scheduled for Hearing")
    , RECORD_HELD_OPEN("RHO", "Record Held Open", "Hearing Held")
    , DEFAULTED("DEF", "Defaulted", "Hearing Held")
    , HEARING_HELD("HH", "Hearing Held", "Hearing Held")
    , DISMISSED("DIS", "Dismissed", "Hearing Held")
    , INITIAL_ORDER_ISSUED("IO", "Initial Order Issued", "Initial Hearing Order Issued")
    , PETITION_ORDER_ISSUED("POI", "Petition Order Issued", "Order Issued")
    , FINAL_ORDER_ISSUED("FOI", "Final Order Issued", "Order Issued")
    , PENDING_JUDGE_REVIEW("PJR", "Pending Judge Review", "Petition Under Review")
    , PENDING_CDU_ORDER("PCO", "Pending CDU Order", "Petition Under Review")
    , CDU_ORDER_ISSUED("COI", "CDU Order Issued", "Order Issued")
    , IMPLEMENTED("IM", "Implemented", "Order Implemented")
    , TO_BE_RESOLVED("TBR", "To Be Resolved", "")
    , PROCESSED_DELAY_APPLICATION("PDA", "Processed - Delay Application", "Resolved")
    , RESOLVED("RES", "Resolved", "Appeal Closed", StatusType.COMPLETE)
    , DISPOSED("DI", "Disposed", "Appeal Closed", StatusType.COMPLETE);

    /**
     * Immutable constants .. it's ok to be public
     * But prefer to use appropriate getter methods.
     */
    private static final Set<AppealStatusCode> COMPLETE_APPEAL_STATUS_SET;
    private static final Set<AppealStatusCode> IN_COMPLETE_APPEAL_STATUS_SET;
    private static final Collection<String> COMPLETE_APPEAL_STATUS_CODES;
    private static final Collection<String> IN_COMPLETE_APPEAL_STATUS_CODES;

    static {
        ImmutableSet.Builder<AppealStatusCode> completeTypes = ImmutableSet.builder();
        ImmutableSet.Builder<AppealStatusCode> inCompleteTypes = ImmutableSet.builder();

        for (AppealStatusCode element : EnumSet.allOf(AppealStatusCode.class)) {
            if (StatusType.INCOMPLETE.equals(element.statusType)) inCompleteTypes.add(element);
            if (StatusType.COMPLETE.equals(element.statusType)) completeTypes.add(element);
        }
        COMPLETE_APPEAL_STATUS_SET = completeTypes.build();
        IN_COMPLETE_APPEAL_STATUS_SET = inCompleteTypes.build();

        COMPLETE_APPEAL_STATUS_CODES = COMPLETE_APPEAL_STATUS_SET
                                            .stream()
                                            .map(AppealStatusCode::getCode)
                                            .collect(ImmutableSet.toImmutableSet());

        IN_COMPLETE_APPEAL_STATUS_CODES = IN_COMPLETE_APPEAL_STATUS_SET
                                            .stream()
                                            .map(AppealStatusCode::getCode)
                                            .collect(ImmutableSet.toImmutableSet());
    }

    private final String code;
    private final String description;
    private final String displayDescription;
    private final StatusType statusType;

    private AppealStatusCode(String code, String description, String displayDescription) {
        this(code, description, displayDescription, StatusType.INCOMPLETE);
    }
    private AppealStatusCode(String code, String description, String displayDescription, StatusType statusType) {
        this.code = code;
        this.description = description;
        this.displayDescription = displayDescription;
        this.statusType = statusType;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    protected enum StatusType {
        COMPLETE, INCOMPLETE;
    }

    public static Set<AppealStatusCode> getCompletedAppealStatus(){
        return COMPLETE_APPEAL_STATUS_SET;
    }

    public static Set<AppealStatusCode> getInCompletedAppealStatus(){
        return IN_COMPLETE_APPEAL_STATUS_SET;
    }

    public static Collection<String> getCompleteAppealStatusCodes(){
        return COMPLETE_APPEAL_STATUS_CODES;
    }

    public static Collection<String> getInCompleteAppealStatusCodes(){
        return IN_COMPLETE_APPEAL_STATUS_CODES;
    }
}