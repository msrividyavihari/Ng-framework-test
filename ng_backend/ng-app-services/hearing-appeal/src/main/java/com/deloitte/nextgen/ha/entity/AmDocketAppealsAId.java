package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmDocketAppealsAId generated by hbm2java
 */
@Embeddable
public class AmDocketAppealsAId  implements java.io.Serializable {


     private BigInteger docketSeqNum;
     private BigInteger historySeq;

    public AmDocketAppealsAId() {
    }

    public AmDocketAppealsAId(BigInteger docketSeqNum, BigInteger historySeq) {
       this.docketSeqNum = docketSeqNum;
       this.historySeq = historySeq;
    }
   


    @Column(name="DOCKET_SEQ_NUM", nullable=false, precision=22, scale=0)
    public BigInteger getDocketSeqNum() {
        return this.docketSeqNum;
    }
    
    public void setDocketSeqNum(BigInteger docketSeqNum) {
        this.docketSeqNum = docketSeqNum;
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
		 if ( !(other instanceof AmDocketAppealsAId) ) return false;
		 AmDocketAppealsAId castOther = ( AmDocketAppealsAId ) other; 
         
		 return ( (this.getDocketSeqNum()==castOther.getDocketSeqNum()) || ( this.getDocketSeqNum()!=null && castOther.getDocketSeqNum()!=null && this.getDocketSeqNum().equals(castOther.getDocketSeqNum()) ) )
 && ( (this.getHistorySeq()==castOther.getHistorySeq()) || ( this.getHistorySeq()!=null && castOther.getHistorySeq()!=null && this.getHistorySeq().equals(castOther.getHistorySeq()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDocketSeqNum() == null ? 0 : this.getDocketSeqNum().hashCode() );
         result = 37 * result + ( getHistorySeq() == null ? 0 : this.getHistorySeq().hashCode() );
         return result;
   }   


}


