package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmHoLogId generated by hbm2java
 */
@Embeddable
public class AmHoLogId  implements java.io.Serializable {


     private BigInteger aplNum;
     private BigInteger hearingSeqNum;

    public AmHoLogId() {
    }

    public AmHoLogId(BigInteger aplNum, BigInteger hearingSeqNum) {
       this.aplNum = aplNum;
       this.hearingSeqNum = hearingSeqNum;
    }
   


    @Column(name="APL_NUM", nullable=false, precision=22, scale=0)
    public BigInteger getAplNum() {
        return this.aplNum;
    }
    
    public void setAplNum(BigInteger aplNum) {
        this.aplNum = aplNum;
    }


    @Column(name="HEARING_SEQ_NUM", nullable=false, precision=22, scale=0)
    public BigInteger getHearingSeqNum() {
        return this.hearingSeqNum;
    }
    
    public void setHearingSeqNum(BigInteger hearingSeqNum) {
        this.hearingSeqNum = hearingSeqNum;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AmHoLogId) ) return false;
		 AmHoLogId castOther = ( AmHoLogId ) other; 
         
		 return ( (this.getAplNum()==castOther.getAplNum()) || ( this.getAplNum()!=null && castOther.getAplNum()!=null && this.getAplNum().equals(castOther.getAplNum()) ) )
 && ( (this.getHearingSeqNum()==castOther.getHearingSeqNum()) || ( this.getHearingSeqNum()!=null && castOther.getHearingSeqNum()!=null && this.getHearingSeqNum().equals(castOther.getHearingSeqNum()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAplNum() == null ? 0 : this.getAplNum().hashCode() );
         result = 37 * result + ( getHearingSeqNum() == null ? 0 : this.getHearingSeqNum().hashCode() );
         return result;
   }   


}


