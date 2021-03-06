package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmDocketDetailsAId generated by hbm2java
 */
@Embeddable
public class AmDocketDetailsAId  implements java.io.Serializable {


     private BigInteger docketId;
     private BigInteger historySeq;

    public AmDocketDetailsAId() {
    }

    public AmDocketDetailsAId(BigInteger docketId, BigInteger historySeq) {
       this.docketId = docketId;
       this.historySeq = historySeq;
    }
   


    @Column(name="DOCKET_ID", nullable=false, precision=22, scale=0)
    public BigInteger getDocketId() {
        return this.docketId;
    }
    
    public void setDocketId(BigInteger docketId) {
        this.docketId = docketId;
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
		 if ( !(other instanceof AmDocketDetailsAId) ) return false;
		 AmDocketDetailsAId castOther = ( AmDocketDetailsAId ) other; 
         
		 return ( (this.getDocketId()==castOther.getDocketId()) || ( this.getDocketId()!=null && castOther.getDocketId()!=null && this.getDocketId().equals(castOther.getDocketId()) ) )
 && ( (this.getHistorySeq()==castOther.getHistorySeq()) || ( this.getHistorySeq()!=null && castOther.getHistorySeq()!=null && this.getHistorySeq().equals(castOther.getHistorySeq()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDocketId() == null ? 0 : this.getDocketId().hashCode() );
         result = 37 * result + ( getHistorySeq() == null ? 0 : this.getHistorySeq().hashCode() );
         return result;
   }   


}


