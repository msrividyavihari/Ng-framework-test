package com.deloitte.nextgen.appreg.client.request;

import lombok.*;

@Data
@NoArgsConstructor
public class FileClearSsnRequest {
    public String ssn;
    public String mpi;
}
