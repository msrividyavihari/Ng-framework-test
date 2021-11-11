package com.deloitte.nextgen.dc.authrep.dto;

import com.deloitte.nextgen.dc.common.dto.*;
import lombok.*;


@Data
@EqualsAndHashCode
@NoArgsConstructor
public class AuthRepDto {

     private long authRepId;
     @NonNull
     private IndividualNameDto name;
     @NonNull
     private AddressDto address;
     private String phoneNum1;
     private String phoneExt;
     private String email;
}
