package com.deloitte.nextgen.appreg.client.response;

import lombok.*;

@Data
@NoArgsConstructor
public class ContactConflictsResponse {
    WeekDayPreferenceConflictsResponse weekDayPreferenceConflictsResponse;
    PhoneConflictsResponse phoneConflictsResponse;
    AddrConflictsResponse addrConflictsResponse;
}

