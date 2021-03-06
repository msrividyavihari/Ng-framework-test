package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmLegalreviewAId generated by hbm2java
 */
@Embeddable
public class AmLegalreviewAId  implements java.io.Serializable {


     private long aplNum;
     private BigInteger historySeq;

    public AmLegalreviewAId() {
    }

    public AmLegalreviewAId(long aplNum, BigInteger historySeq) {
       this.aplNum = aplNum;
       this.historySeq = historySeq;
    }
   


    @Column(name="APL_NUM", nullable=false, precision=10, scale=0)
    public long getAplNum() {
        return this.aplNum;
    }
    
    public void setAplNum(long aplNum) {
        this.aplNum = aplNum;
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
		 if ( !(other instanceof AmLegalreviewAId) ) return false;
		 AmLegalreviewAId castOther = ( AmLegalreviewAId ) other; 
         
		 return (this.getAplNum()==castOther.getAplNum())
 && ( (this.getHistorySeq()==castOther.getHistorySeq()) || ( this.getHistorySeq()!=null && castOther.getHistorySeq()!=null && this.getHistorySeq().equals(castOther.getHistorySeq()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getAplNum();
         result = 37 * result + ( getHistorySeq() == null ? 0 : this.getHistorySeq().hashCode() );
         return result;
   }   


}


