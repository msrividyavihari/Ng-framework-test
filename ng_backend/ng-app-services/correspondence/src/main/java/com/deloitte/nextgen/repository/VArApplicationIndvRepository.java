package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.VArApplicationIndv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VArApplicationIndvRepository extends JpaRepository<VArApplicationIndv,Long> {

    @Query(value = "FROM VArApplicationIndv WHERE t1AppNum = :t1AppNum AND headOfHouseholdSw = :headOfHouseholdSw")
    List<VArApplicationIndv> findByHeadOfHousehold(String t1AppNum, char headOfHouseholdSw);

    @Query(value = "FROM VArApplicationIndv WHERE t1AppNum = :t1AppNum")
    List<VArApplicationIndv> findByAppNum(String t1AppNum);

    @Query(value = "FROM VArApplicationIndv WHERE t2AppNum = :appNum AND t2IndvId = :indvId")
    List<VArApplicationIndv> findByAppNumberAndIndvId(String appNum, long indvId);
}
