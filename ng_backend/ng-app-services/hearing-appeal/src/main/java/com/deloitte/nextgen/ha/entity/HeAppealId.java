package com.deloitte.nextgen.ha.entity;
// Generated Nov 2, 2020 9:40:32 PM by Hibernate Tools 5.4.22.Final


import java.math.BigInteger;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * HeAppealId generated by hbm2java
 */
@Embeddable
public class HeAppealId  implements java.io.Serializable {


     private BigInteger caseNum;
     private BigInteger appealGroupId;
     private LocalDate effBeginDt;

    public HeAppealId() {
    }

    public HeAppealId(BigInteger caseNum, BigInteger appealGroupId, LocalDate effBeginDt) {
       this.caseNum = caseNum;
       this.appealGroupId = appealGroupId;
       this.effBeginDt = effBeginDt;
    }
   


    @Column(name="CASE_NUM", nullable=false, precision=22, scale=0)
    public BigInteger getCaseNum() {
        return this.caseNum;
    }
    
    public void setCaseNum(BigInteger caseNum) {
        this.caseNum = caseNum;
    }


    @Column(name="APPEAL_GROUP_ID", nullable=false, precision=22, scale=0)
    public BigInteger getAppealGroupId() {
        return this.appealGroupId;
    }
    
    public void setAppealGroupId(BigInteger appealGroupId) {
        this.appealGroupId = appealGroupId;
    }


    @Column(name="EFF_BEGIN_DT", nullable=false, length=7)
    public LocalDate getEffBeginDt() {
        return this.effBeginDt;
    }
    
    public void setEffBeginDt(LocalDate effBeginDt) {
        this.effBeginDt = effBeginDt;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof HeAppealId) ) return false;
		 HeAppealId castOther = ( HeAppealId ) other; 
         
		 return ( (this.getCaseNum()==castOther.getCaseNum()) || ( this.getCaseNum()!=null && castOther.getCaseNum()!=null && this.getCaseNum().equals(castOther.getCaseNum()) ) )
 && ( (this.getAppealGroupId()==castOther.getAppealGroupId()) || ( this.getAppealGroupId()!=null && castOther.getAppealGroupId()!=null && this.getAppealGroupId().equals(castOther.getAppealGroupId()) ) )
 && ( (this.getEffBeginDt()==castOther.getEffBeginDt()) || ( this.getEffBeginDt()!=null && castOther.getEffBeginDt()!=null && this.getEffBeginDt().equals(castOther.getEffBeginDt()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCaseNum() == null ? 0 : this.getCaseNum().hashCode() );
         result = 37 * result + ( getAppealGroupId() == null ? 0 : this.getAppealGroupId().hashCode() );
         result = 37 * result + ( getEffBeginDt() == null ? 0 : this.getEffBeginDt().hashCode() );
         return result;
   }   


}


