package com.deloitte.nextgen.ssp.repo;

import com.deloitte.nextgen.ssp.entities.entities.T1053_App_Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppRegSSPInterface extends JpaRepository<T1053_App_Program,String> {

    public List<T1053_App_Program> findByAppNum(String appNum);

    @Query(value="SELECT concat(  'T',T1002_APP_DT_1SQ.nextval) FROM dual",
            nativeQuery = true)
    String getLatestSSAppNo();}
