package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmKidsChStagingAId generated by hbm2java
 */
@Embeddable
public class AmKidsChStagingAId  implements java.io.Serializable {


     private BigInteger chId;
     private BigInteger historySeq;

    public AmKidsChStagingAId() {
    }

    public AmKidsChStagingAId(BigInteger chId, BigInteger historySeq) {
       this.chId = chId;
       this.historySeq = historySeq;
    }
   


    @Column(name="CH_ID", nullable=false, precision=22, scale=0)
    public BigInteger getChId() {
        return this.chId;
    }
    
    public void setChId(BigInteger chId) {
        this.chId = chId;
    }


    @Column(name="HISTORY_SEQ", nullable=false, precision=22, scale=0)
    public BigInteger getHistorySeq() {
        return this.historySeq;
    }
    
    public void setHistorySeq(BigInteger historySeq) {
        this.historySeq = historySeq;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AmKidsChStagingAId) ) return false;
		 AmKidsChStagingAId castOther = ( AmKidsChStagingAId ) other; 
         
		 return ( (this.getChId()==castOther.getChId()) || ( this.getChId()!=null && castOther.getChId()!=null && this.getChId().equals(castOther.getChId()) ) )
 && ( (this.getHistorySeq()==castOther.getHistorySeq()) || ( this.getHistorySeq()!=null && castOther.getHistorySeq()!=null && this.getHistorySeq().equals(castOther.getHistorySeq()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getChId() == null ? 0 : this.getChId().hashCode() );
         result = 37 * result + ( getHistorySeq() == null ? 0 : this.getHistorySeq().hashCode() );
         return result;
   }   


}


