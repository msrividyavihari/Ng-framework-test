package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmAppealNotesId generated by hbm2java
 */
@Data
@EqualsAndHashCode
public class AmAppealNotesId  implements java.io.Serializable {


     private BigInteger aplNum;
     private BigInteger aplNotesSeqNum;

   /* public AmAppealNotesId() {
    }

    public AmAppealNotesId(BigInteger aplNum, BigInteger aplNotesSeqNum) {
       this.aplNum = aplNum;
       this.aplNotesSeqNum = aplNotesSeqNum;
    }
   



    public BigInteger getAplNum() {
        return this.aplNum;
    }
    
    public void setAplNum(BigInteger aplNum) {
        this.aplNum = aplNum;
    }



    public BigInteger getAplNotesSeqNum() {
        return this.aplNotesSeqNum;
    }
    
    public void setAplNotesSeqNum(BigInteger aplNotesSeqNum) {
        this.aplNotesSeqNum = aplNotesSeqNum;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AmAppealNotesId) ) return false;
		 AmAppealNotesId castOther = ( AmAppealNotesId ) other; 
         
		 return ( (this.getAplNum()==castOther.getAplNum()) || ( this.getAplNum()!=null && castOther.getAplNum()!=null && this.getAplNum().equals(castOther.getAplNum()) ) )
 && ( (this.getAplNotesSeqNum()==castOther.getAplNotesSeqNum()) || ( this.getAplNotesSeqNum()!=null && castOther.getAplNotesSeqNum()!=null && this.getAplNotesSeqNum().equals(castOther.getAplNotesSeqNum()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAplNum() == null ? 0 : this.getAplNum().hashCode() );
         result = 37 * result + ( getAplNotesSeqNum() == null ? 0 : this.getAplNotesSeqNum().hashCode() );
         return result;
   }   */


}

