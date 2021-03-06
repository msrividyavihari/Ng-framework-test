package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmAdminReviewAId generated by hbm2java
 */
@Embeddable
public class AmAdminReviewAId  implements java.io.Serializable {


     private String aplNum;
     private BigInteger historySeq;

    public AmAdminReviewAId() {
    }

    public AmAdminReviewAId(String aplNum, BigInteger historySeq) {
       this.aplNum = aplNum;
       this.historySeq = historySeq;
    }
   


    @Column(name="APL_NUM", nullable=false, length=10)
    public String getAplNum() {
        return this.aplNum;
    }
    
    public void setAplNum(String aplNum) {
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
		 if ( !(other instanceof AmAdminReviewAId) ) return false;
		 AmAdminReviewAId castOther = ( AmAdminReviewAId ) other; 
         
		 return ( (this.getAplNum()==castOther.getAplNum()) || ( this.getAplNum()!=null && castOther.getAplNum()!=null && this.getAplNum().equals(castOther.getAplNum()) ) )
 && ( (this.getHistorySeq()==castOther.getHistorySeq()) || ( this.getHistorySeq()!=null && castOther.getHistorySeq()!=null && this.getHistorySeq().equals(castOther.getHistorySeq()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAplNum() == null ? 0 : this.getAplNum().hashCode() );
         result = 37 * result + ( getHistorySeq() == null ? 0 : this.getHistorySeq().hashCode() );
         return result;
   }   


}


