package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmHrngImplPgmDtlsAId generated by hbm2java
 */
@Embeddable
public class AmHrngImplPgmDtlsAId  implements java.io.Serializable {


     private BigInteger implPgmSeqNum;
     private BigInteger historySeq;

    public AmHrngImplPgmDtlsAId() {
    }

    public AmHrngImplPgmDtlsAId(BigInteger implPgmSeqNum, BigInteger historySeq) {
       this.implPgmSeqNum = implPgmSeqNum;
       this.historySeq = historySeq;
    }
   


    @Column(name="IMPL_PGM_SEQ_NUM", nullable=false, precision=22, scale=0)
    public BigInteger getImplPgmSeqNum() {
        return this.implPgmSeqNum;
    }
    
    public void setImplPgmSeqNum(BigInteger implPgmSeqNum) {
        this.implPgmSeqNum = implPgmSeqNum;
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
		 if ( !(other instanceof AmHrngImplPgmDtlsAId) ) return false;
		 AmHrngImplPgmDtlsAId castOther = ( AmHrngImplPgmDtlsAId ) other; 
         
		 return ( (this.getImplPgmSeqNum()==castOther.getImplPgmSeqNum()) || ( this.getImplPgmSeqNum()!=null && castOther.getImplPgmSeqNum()!=null && this.getImplPgmSeqNum().equals(castOther.getImplPgmSeqNum()) ) )
 && ( (this.getHistorySeq()==castOther.getHistorySeq()) || ( this.getHistorySeq()!=null && castOther.getHistorySeq()!=null && this.getHistorySeq().equals(castOther.getHistorySeq()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getImplPgmSeqNum() == null ? 0 : this.getImplPgmSeqNum().hashCode() );
         result = 37 * result + ( getHistorySeq() == null ? 0 : this.getHistorySeq().hashCode() );
         return result;
   }   


}


