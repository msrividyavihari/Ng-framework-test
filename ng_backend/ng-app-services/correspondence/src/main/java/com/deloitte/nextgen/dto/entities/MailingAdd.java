package com.deloitte.nextgen.dto.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailingAdd {
    private String recipientName;
    private String Street1;
    private String Street2;
    private String City;
    private String State;
    private Long Zip5;
    private Long Zip4;
    private Character flag;
}
