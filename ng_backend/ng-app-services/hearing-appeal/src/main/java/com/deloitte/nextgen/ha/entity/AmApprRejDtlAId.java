package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmApprRejDtlAId generated by hbm2java
 */
@Embeddable
public class AmApprRejDtlAId  implements java.io.Serializable {


     private BigInteger amApprRejDtlSeqNum;
     private BigInteger historySeq;

    public AmApprRejDtlAId() {
    }

    public AmApprRejDtlAId(BigInteger amApprRejDtlSeqNum, BigInteger historySeq) {
       this.amApprRejDtlSeqNum = amApprRejDtlSeqNum;
       this.historySeq = historySeq;
    }
   


    @Column(name="AM_APPR_REJ_DTL_SEQ_NUM", nullable=false, precision=22, scale=0)
    public BigInteger getAmApprRejDtlSeqNum() {
        return this.amApprRejDtlSeqNum;
    }
    
    public void setAmApprRejDtlSeqNum(BigInteger amApprRejDtlSeqNum) {
        this.amApprRejDtlSeqNum = amApprRejDtlSeqNum;
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
		 if ( !(other instanceof AmApprRejDtlAId) ) return false;
		 AmApprRejDtlAId castOther = ( AmApprRejDtlAId ) other; 
         
		 return ( (this.getAmApprRejDtlSeqNum()==castOther.getAmApprRejDtlSeqNum()) || ( this.getAmApprRejDtlSeqNum()!=null && castOther.getAmApprRejDtlSeqNum()!=null && this.getAmApprRejDtlSeqNum().equals(castOther.getAmApprRejDtlSeqNum()) ) )
 && ( (this.getHistorySeq()==castOther.getHistorySeq()) || ( this.getHistorySeq()!=null && castOther.getHistorySeq()!=null && this.getHistorySeq().equals(castOther.getHistorySeq()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAmApprRejDtlSeqNum() == null ? 0 : this.getAmApprRejDtlSeqNum().hashCode() );
         result = 37 * result + ( getHistorySeq() == null ? 0 : this.getHistorySeq().hashCode() );
         return result;
   }   


}


