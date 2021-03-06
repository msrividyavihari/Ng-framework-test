package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmAplStatLogId generated by hbm2java
 */
//@Embeddable
@Data
@EqualsAndHashCode
public class AmAplStatLogId  implements java.io.Serializable {


    private BigInteger statLogSeq;
    private BigInteger aplNum;

    /*public AmAplStatLogId() {
    }

    public AmAplStatLogId(BigInteger statLogSeq, BigInteger aplNum) {
        this.statLogSeq = statLogSeq;
        this.aplNum = aplNum;
    }



    @Column(name="STAT_LOG_SEQ", nullable=false, precision=22, scale=0)
    public BigInteger getStatLogSeq() {
        return this.statLogSeq;
    }

    public void setStatLogSeq(BigInteger statLogSeq) {
        this.statLogSeq = statLogSeq;
    }


    @Column(name="APL_NUM", nullable=false, precision=22, scale=0)
    public BigInteger getAplNum() {
        return this.aplNum;
    }

    public void setAplNum(BigInteger aplNum) {
        this.aplNum = aplNum;
    }


    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( (other == null ) ) return false;
        if ( !(other instanceof AmAplStatLogId) ) return false;
        AmAplStatLogId castOther = ( AmAplStatLogId ) other;

        return ( (this.getStatLogSeq()==castOther.getStatLogSeq()) || ( this.getStatLogSeq()!=null && castOther.getStatLogSeq()!=null && this.getStatLogSeq().equals(castOther.getStatLogSeq()) ) )
                && ( (this.getAplNum()==castOther.getAplNum()) || ( this.getAplNum()!=null && castOther.getAplNum()!=null && this.getAplNum().equals(castOther.getAplNum()) ) );
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + ( getStatLogSeq() == null ? 0 : this.getStatLogSeq().hashCode() );
        result = 37 * result + ( getAplNum() == null ? 0 : this.getAplNum().hashCode() );
        return result;
    }

*/
}

