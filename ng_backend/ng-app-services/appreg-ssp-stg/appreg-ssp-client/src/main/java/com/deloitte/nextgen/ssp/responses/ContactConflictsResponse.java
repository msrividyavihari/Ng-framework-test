package com.deloitte.nextgen.ssp.responses;

import lombok.*;

@Data
@NoArgsConstructor
public class ContactConflictsResponse {
    WeekDayPreferenceConflictsResponse weekDayPreferenceConflictsResponse;
    PhoneConflictsResponse phoneConflictsResponse;
    AddrConflictsResponse addrConflictsResponse;
}
