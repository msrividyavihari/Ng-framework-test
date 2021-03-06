package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmMcoId generated by hbm2java
 */
@Embeddable
public class AmMcoId  implements java.io.Serializable {


     private BigInteger mcoSeqNum;
     private BigInteger aplNum;

    public AmMcoId() {
    }

    public AmMcoId(BigInteger mcoSeqNum, BigInteger aplNum) {
       this.mcoSeqNum = mcoSeqNum;
       this.aplNum = aplNum;
    }
   


    @Column(name="MCO_SEQ_NUM", nullable=false, precision=22, scale=0)
    public BigInteger getMcoSeqNum() {
        return this.mcoSeqNum;
    }
    
    public void setMcoSeqNum(BigInteger mcoSeqNum) {
        this.mcoSeqNum = mcoSeqNum;
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
		 if ( !(other instanceof AmMcoId) ) return false;
		 AmMcoId castOther = ( AmMcoId ) other; 
         
		 return ( (this.getMcoSeqNum()==castOther.getMcoSeqNum()) || ( this.getMcoSeqNum()!=null && castOther.getMcoSeqNum()!=null && this.getMcoSeqNum().equals(castOther.getMcoSeqNum()) ) )
 && ( (this.getAplNum()==castOther.getAplNum()) || ( this.getAplNum()!=null && castOther.getAplNum()!=null && this.getAplNum().equals(castOther.getAplNum()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMcoSeqNum() == null ? 0 : this.getMcoSeqNum().hashCode() );
         result = 37 * result + ( getAplNum() == null ? 0 : this.getAplNum().hashCode() );
         return result;
   }   


}


