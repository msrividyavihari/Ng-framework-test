package com.deloitte.nextgen.ha.create;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigInteger;

@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AmAplGrpDto {


    private BigInteger aplGroupNum;
    private BigInteger aplNum;
    private Character primarySw;
    private Character activeInGroupSw;

}
