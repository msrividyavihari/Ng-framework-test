package com.deloitte.nextgen.repository;

import com.deloitte.nextgen.entities.MoEmployees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoEmployeesRepository extends JpaRepository<MoEmployees,Long> {

    @Query(value=  "FROM com.deloitte.nextgen.entities.MoEmployees  " +
            "WHERE EMP_ID=(:empId) AND eff_begin_dt < TO_DATE(:effBegDt) " +
            "AND (eff_end_dt > TO_DATE(:effBegDt) or eff_end_dt is null)")
    List<MoEmployees> findByEmpIdDates(Long empId, String effBegDt);

    @Query(
            value= "Select A.* from mo_employees A, mo_employee_cases B  where B.case_num = (:caseAppNumber) "+
                    "and A.emp_id = B.emp_id and A.HIST_NAV_IND in ('S','P') and A.EFF_END_DT is null " +
                    "ORDER BY B.ASSIGN_BEGIN_DT desc ",nativeQuery = true)
    List<MoEmployees> findByEmployeeByCaseNum(String caseAppNumber);

    @Query(value= "FROM com.deloitte.nextgen.entities.MoEmployees where "+
                    "Lower(USER_ID) = (:userId) and HIST_NAV_IND in ('S','P') and EFF_END_DT is null ")
    List<MoEmployees> findByUserId(String userId);

    /*@Query(
            value= "Select B.ASSIGN_BEGIN_DT,A.* from mo_employees A, mo_employee_apps B where B.app_num = (:caseAppNumber) "+
                    "and A.emp_id = B.emp_id and A.HIST_NAV_IND in ('S','P') and A.EFF_END_DT is null UNION " +
                    "Select B.ASSIGN_BEGIN_DT,A.*, A.rowid  from mo_employees A, mo_employee_cases B  " +
                    "where B.case_num in(select ara.case_num from AR_APPLICATION_FOR_AID ara where ara.app_num = ? )  "
            ,nativeQuery = true)
    List<MoEmployees> findByEmployeeByAppNum(String caseAppNumber);*/

    @Query(value = " From com.deloitte.nextgen.entities.MoEmployees  " +
            "WHERE 1=1 " +
            "AND PRI_OFFICE_NUM IN (SELECT DISTINCT officeNum FROM com.deloitte.nextgen.entities.MoOffices " +
            "WHERE " +
            "OFFICE_TYPE_CD = 'DPH' " +
            "AND EFF_END_DT IS NULL) " +
            "AND HIST_NAV_IND IN ('S', 'P') " +
            "AND ACTIVE_STATUS_CD = 'AC' " +
            "AND EMP_ID = (:empId) ")
    MoEmployees findByDphUserUsingEmpId(Long empId);
}
