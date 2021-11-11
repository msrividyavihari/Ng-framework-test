package com.deloitte.nextgen.ha.entity;
// Generated Jan 20, 2021 8:19:48 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AmDocketTypeId generated by hbm2java
 */
@Embeddable
public class AmDocketTypeId  implements java.io.Serializable {


     private BigInteger docketTypeId;
     private LocalDate efectStartDt;

    public AmDocketTypeId() {
    }

    public AmDocketTypeId(BigInteger docketTypeId, LocalDate efectStartDt) {
       this.docketTypeId = docketTypeId;
       this.efectStartDt = efectStartDt;
    }
   


    @Column(name="DOCKET_TYPE_ID", nullable=false, precision=22, scale=0)
    public BigInteger getDocketTypeId() {
        return this.docketTypeId;
    }
    
    public void setDocketTypeId(BigInteger docketTypeId) {
        this.docketTypeId = docketTypeId;
    }


    @Column(name="EFECT_START_DT", nullable=false, length=7)
    public LocalDate getEfectStartDt() {
        return this.efectStartDt;
    }
    
    public void setEfectStartDt(LocalDate efectStartDt) {
        this.efectStartDt = efectStartDt;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AmDocketTypeId) ) return false;
		 AmDocketTypeId castOther = ( AmDocketTypeId ) other; 
         
		 return ( (this.getDocketTypeId()==castOther.getDocketTypeId()) || ( this.getDocketTypeId()!=null && castOther.getDocketTypeId()!=null && this.getDocketTypeId().equals(castOther.getDocketTypeId()) ) )
 && ( (this.getEfectStartDt()==castOther.getEfectStartDt()) || ( this.getEfectStartDt()!=null && castOther.getEfectStartDt()!=null && this.getEfectStartDt().equals(castOther.getEfectStartDt()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDocketTypeId() == null ? 0 : this.getDocketTypeId().hashCode() );
         result = 37 * result + ( getEfectStartDt() == null ? 0 : this.getEfectStartDt().hashCode() );
         return result;
   }   


}

