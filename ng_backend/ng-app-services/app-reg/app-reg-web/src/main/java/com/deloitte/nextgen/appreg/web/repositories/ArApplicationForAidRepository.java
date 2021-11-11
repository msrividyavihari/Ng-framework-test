package com.deloitte.nextgen.appreg.web.repositories;

import com.deloitte.nextgen.appreg.web.entities.ArApplicationForAid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArApplicationForAidRepository extends JpaRepository<ArApplicationForAid, String>,AppSearchCustomRepository {


	ArApplicationForAid findByAppNum(String appNum);

	@Query(
			value=" select lastUpdated, HOH, programs, status, casenum, office  from ( select  dcase.Update_dt as lastUpdated, (dcindv.first_name || ' ' || dcindv.last_name) as HOH, (select listagg(Prog_cd,',') within group (order by Prog_cd) cnt from \n" +
					"dc_case_program where case_num=dcase.case_num )as programs  , dcase.case_status_cd as status, dcase.case_num as caseNum  , dcaddr.ADDR_COUNTY_CD as office    from dc_cases dcase \n" +
					"inner join DC_CASE_INDIVIDUAL dcCaseIndv on dcCaseIndv.case_num = dcase.case_num and dcCaseIndv.head_of_household_sw='Y'  inner join dc_indv dcindv on dcindv.indv_id = dcCaseIndv.indv_id left join dc_case_addresses dcaddr on dcaddr.case_num= dcase.case_num and dcaddr.addr_type_cd='PA' \n" +
					" where dcCaseIndv.indv_id= (:indvId) and dcase.case_status_cd != 'DN'  union  select dcase.Update_dt as lastUpdated,(dcindv.first_name || ' ' || dcindv.last_name) \n" +
					"as HOH, (select listagg(Prog_cd,',') within group (order by Prog_cd) cnt  from dc_case_program where case_num=dcase.case_num )as programs , dcase.case_status_cd as status, \n" +
					"dcase.case_num as caseNum  , dcaddr.ADDR_COUNTY_CD as office   from dc_cases dcase  inner join DC_CASE_INDIVIDUAL dcCaseIndv on dcCaseIndv.case_num = dcase.case_num and \n" +
					"dcCaseIndv.head_of_household_sw !='Y' inner join DC_CASE_INDIVIDUAL dcCaseIndvH on dcCaseIndvH.case_num = dcCaseIndv.case_num and dcCaseIndvH.head_of_household_sw='Y'\n" +
					"inner join dc_indv dcindv on dcindv.indv_id = dcCaseIndvH.indv_id left join dc_case_addresses dcaddr on dcaddr.case_num= dcase.case_num and dcaddr.addr_type_cd='PA'  where dcCaseIndv.indv_id= (:indvId) and dcase.case_status_cd != 'DN'  union\n" +
					" select dcase.Update_dt as lastUpdated,(dcindv.first_name || ' ' || dcindv.last_name) \n" +
					"as HOH, (select listagg(Prog_cd,',') within group (order by Prog_cd) cnt  from dc_case_program where case_num=dcase.case_num )as programs , dcase.case_status_cd as status, \n" +
					"dcase.case_num as caseNum  , dcaddr.ADDR_COUNTY_CD as office   from dc_cases dcase  inner join DC_CASE_INDIVIDUAL dcCaseIndv on dcCaseIndv.case_num = dcase.case_num and \n" +
					"dcCaseIndv.head_of_household_sw !='Y' inner join DC_CASE_INDIVIDUAL dcCaseIndvH on dcCaseIndvH.case_num = dcCaseIndv.case_num and dcCaseIndvH.head_of_household_sw='Y'\n" +
					"inner join dc_indv dcindv on dcindv.indv_id = dcCaseIndvH.indv_id left join dc_case_addresses dcaddr on dcaddr.case_num= dcase.case_num and dcaddr.addr_type_cd='PA'  \n" +
					"inner join ar_application_for_aid arAppAid on arAppAid.case_num= dcase.case_num\n" +
					"where arAppAid.app_num= (:appNum) and dcase.case_status_cd != 'DN' ) t  ",
			nativeQuery = true)
	List<Object[]> findAssociateCasesByIndvId(@Param("indvId") Long indvId, @Param("appNum") String appNum);

	@Query(value="SELECT concat(  'T',AR_APPLICATION_FOR_AID_1SQ.nextval) FROM dual",
			nativeQuery = true)
	String getLatestNonSSAppNo();

}
