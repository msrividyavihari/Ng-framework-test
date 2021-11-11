package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmDocketOutcomeAId generated by hbm2java
 */
@Embeddable
public class AmDocketOutcomeAId  implements java.io.Serializable {


     private BigInteger outcomeSeqNum;
     private BigInteger historySeq;

    public AmDocketOutcomeAId() {
    }

    public AmDocketOutcomeAId(BigInteger outcomeSeqNum, BigInteger historySeq) {
       this.outcomeSeqNum = outcomeSeqNum;
       this.historySeq = historySeq;
    }
   


    @Column(name="OUTCOME_SEQ_NUM", nullable=false, precision=22, scale=0)
    public BigInteger getOutcomeSeqNum() {
        return this.outcomeSeqNum;
    }
    
    public void setOutcomeSeqNum(BigInteger outcomeSeqNum) {
        this.outcomeSeqNum = outcomeSeqNum;
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
		 if ( !(other instanceof AmDocketOutcomeAId) ) return false;
		 AmDocketOutcomeAId castOther = ( AmDocketOutcomeAId ) other; 
         
		 return ( (this.getOutcomeSeqNum()==castOther.getOutcomeSeqNum()) || ( this.getOutcomeSeqNum()!=null && castOther.getOutcomeSeqNum()!=null && this.getOutcomeSeqNum().equals(castOther.getOutcomeSeqNum()) ) )
 && ( (this.getHistorySeq()==castOther.getHistorySeq()) || ( this.getHistorySeq()!=null && castOther.getHistorySeq()!=null && this.getHistorySeq().equals(castOther.getHistorySeq()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getOutcomeSeqNum() == null ? 0 : this.getOutcomeSeqNum().hashCode() );
         result = 37 * result + ( getHistorySeq() == null ? 0 : this.getHistorySeq().hashCode() );
         return result;
   }   


}


