package com.deloitte.nextgen.ha.common.repository;

import com.deloitte.nextgen.ha.entity.*;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface AmRepresentativeDetailsRepository  extends JpaRepository<AmRepresentativeDetails, BigInteger>, JpaSpecificationExecutor<AmRepresentativeDetails> {


    @Query(value="SELECT AA.APL_NUM, AA.CLIENT_REP_ID, AR.ADDRESS_ID, AR.HOME_PH_NUM, AR.REL_TO_APPELLANT_CD, AR.OTHER_NUM, AR.REP_TYPE_CD, AR.SUFFIX_NAME, " +
            "AR.PREFIX_NAME,AR.COMPANY_NAME, AR.EMAIL_ID, AR.FIRST_NAME, AR.LAST_NAME, AR.MID_NAME, AR.WORK_PH_NUM, AR.PH_EXT, AD.ADDRESS_LINE_2,AD.APT_NUM, AD.ATTN_CARE_OF, " +
            "AD.CITY_NAME, AD.COUNTY_CD, AD.DWELLING_TYPE,AD.ATTN_CARE_OF,AD.FRACTION, AD.POST_DIRECTION_CD, AD.PRE_DIRECTION_CD, AD.REGION_CD, AD.STATE_CD, AD.STREET_NAME, " +
            "AD.STREET_NUM, AD.STREET_TYPE_CD, AD.ZIP_CD, AD.VALIDATION_REQ_CD, AR.RECV_NOTICE_SW, AR.AR_NOTICE_LANG_CD FROM IE_APP_ONLINE.AM_APL_ASSOCIATION AA," +
            " IE_APP_ONLINE.AM_REPRESENTATIVE_DETAILS AR, IE_APP_ONLINE.AM_ADDRESS AD WHERE AA.CLIENT_REP_ID != 0 AND " +
            "AA.CLIENT_REP_ID = AR.CLIENT_REP_ID AND AR.ADDRESS_ID = AD.ADDRESS_ID AND AA.APL_NUM IN (= :aplNum) ",nativeQuery = true)
    List<AmRepresentativeDetails>  findByAplNum(@Param("aplNum") String aplNum);

    @Query (value="SELECT AR.FIRST_NAME,AR.LAST_NAME,AR.MID_NAME,AR.SUFFIX_NAME,AR.ADDRESS_ID, \n" +
            "AR.HOME_PH_NUM,AR.WORK_PH_NUM,AR.PH_EXT,AR.EMAIL_ID,AR.CLIENT_REP_ID \n" +
            "FROM AM_REPRESENTATIVE_DETAILS AR, AM_APL_ASSOCIATION AA WHERE AR.CLIENT_REP_ID = AA.CLIENT_REP_ID \n" +
            "AND AR.REP_TYPE_CD = 'A' AND AA.APL_NUM = (:aplNum)",nativeQuery = true)
    List<Object[]> findByAplNumAssociation(@Param("aplNum") String aplNum);

    @Override
    Optional<AmRepresentativeDetails> findById(BigInteger bigInteger);




    /* default List<AmRepresentativeDetails> findByAppealNum(@NonNull BigInteger appealNum) {

        Specification<AmRequestDetails> appealNumSpec = (root, query, cb) -> {
            return cb.equal(root.get(AmRepresentativeDetails_.aplNum), appealNum);
        };
        return  findAll(Specification.where(appealNumSpec));
    }*/


   @Query(value="SELECT AR.*  FROM  AM_REPRESENTATIVE_DETAILS AR  WHERE (AR.CLIENT_REP_ID IN (SELECT CLIENT_REP_ID FROM AM_APL_ASSOCIATION WHERE APL_NUM = (:aplNum)) )  "
            , nativeQuery = true)
    List<AmRepresentativeDetails> findByAppealNum(@Param("aplNum") BigInteger aplNum);


}
