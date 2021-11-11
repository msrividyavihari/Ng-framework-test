package com.deloitte.nextgen.ha.common.repository;

import com.deloitte.nextgen.ha.entity.AmAplGrpId;
import com.deloitte.nextgen.ha.entity.AmAplGrp;
import com.deloitte.nextgen.ha.entity.AmAplGrp_;
import com.deloitte.nextgen.ha.entity.AmAppealNotes;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AmAplGrpRepository extends JpaRepository<AmAplGrp, AmAplGrpId>, JpaSpecificationExecutor<AmAplGrp> {


    List<AmAplGrp> findByAplNum(BigInteger aplNum);



    @Query(value = "select AM_APL_GRP_1SQ.NEXTVAL from dual" ,nativeQuery = true)
    public List<Object[]> findByNextGroupSeq();

    @Query(value = "SELECT APL_GROUP_NUM FROM AM_APL_GRP WHERE APL_NUM=(:appNum) AND ACTIVE_IN_GROUP_SW ='Y'" ,nativeQuery = true)
    public List<Object[]> findGrpNumByAppNum(@Param("appNum") BigInteger appNum);

    @Query(value = "SELECT G.APL_GROUP_NUM,G.APL_NUM,G.PRIMARY_SW FROM AM_APL_GRP G where G.APL_GROUP_NUM = (:aplGroupNum) " +
            "AND G.ACTIVE_IN_GROUP_SW = 'Y'",
            nativeQuery = true)
    public List<Object[]> findActiveByGrpNum(@Param("aplGroupNum") String aplGroupNum);

    /*default List<AmAplGrp> findActiveByGrpNum(final String aplGroupNum) {

        Specification<AmAplGrp> groupSpec = (root, query, cb) -> {

            return cb.equal(root.get(AmAplGrp_.aplGroupNum), aplGroupNum);
        };
        Specification<AmAplGrp> activeSwSpec = (root, query, cb) -> {
            return cb.equal(cb.upper(root.get(AmAplGrp_.ACTIVE_IN_GROUP_SW)), 'Y');
        };
        return findAll(Specification.where(groupSpec).and(activeSwSpec));
    }*/

    @Query(value="select * from am_apl_grp" +
            " WHERE apl_group_num = (select distinct apl_group_num from " +
            "am_apl_grp where APL_NUM = (:aplNum) and active_in_group_sw = 'Y')"
            ,nativeQuery = true)
    List<AmAplGrp> findByAplNumActive(@Param("aplNum") BigInteger aplNum);

    default List<AmAplGrp> findByAplNumActiveDel(final String aplNum,final String grpNum) {

        Specification<AmAplGrp> groupSpec = (root, query, cb) -> {

            return cb.equal(root.get(AmAplGrp_.aplGroupNum), grpNum);
        };
        Specification<AmAplGrp> activeSwSpec = (root, query, cb) -> {
            return cb.equal(cb.upper(root.get(AmAplGrp_.ACTIVE_IN_GROUP_SW)), 'Y');
        };
        Specification<AmAplGrp> aplNumSpec = (root, query, cb) -> {
            return cb.equal(root.get(AmAplGrp_.aplNum), aplNum);
        };
        return findAll(Specification.where(aplNumSpec).and(groupSpec).and(activeSwSpec));
    }



    /*@Query(value="select * from am_apl_grp WHERE apl_group_num =  (:grpNum) AND APL_NUM = (:aplNum) and active_in_group_sw = 'N')" ,nativeQuery = true)
    List<AmAplGrp> findByAplNumInActive(@Param("aplNum") BigInteger aplNum,@Param("aplNum") BigInteger grpNum);*/
    default List<AmAplGrp> findByAplNumInActive(final String aplNum,final String grpNum) {

        Specification<AmAplGrp> groupSpec = (root, query, cb) -> {

            return cb.equal(root.get(AmAplGrp_.aplGroupNum), grpNum);
        };
        Specification<AmAplGrp> activeSwSpec = (root, query, cb) -> {
            return cb.equal(cb.upper(root.get(AmAplGrp_.ACTIVE_IN_GROUP_SW)), 'N');
        };
        Specification<AmAplGrp> aplNumSpec = (root, query, cb) -> {
            return cb.equal(root.get(AmAplGrp_.aplNum), aplNum);
        };
        return findAll(Specification.where(aplNumSpec).and(groupSpec).and(activeSwSpec));
    }



}
