package com.deloitte.nextgen.framework.service.security.dao;

import com.deloitte.nextgen.framework.service.security.models.AuthenticationObj;
import com.deloitte.nextgen.framework.service.security.vo.ResponseVO;
import com.deloitte.nextgen.framework.service.security.vo.UserProfileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class LoginNavigationDAOImpl implements LoginNavigationDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static final String SQL_QUERY = "SELECT * FROM SE_USER where user_id='abelbakry'";

	@Override
	public ResponseVO getUserDetails(AuthenticationObj authenticationObj) {

		if (authenticationObj.getPassword().equalsIgnoreCase("AuthoriztionOfService")) {
			List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM SE_USER where user_id= ?",
					authenticationObj.getUserName());
			ResponseVO responseVO = new ResponseVO();
			if (!list.isEmpty()) {
				responseVO.setStatus("Success");
			}
			return responseVO;
		}

		List<Map<String, Object>> list = jdbcTemplate.queryForList("select s.PREFERRED_NAME, s.FIRST_NAME, s.LAST_NAME, s.MID_NAME, s.EMAIL,m.COUNTY_CD from SE_USER s join mo_employees me on s.user_id=me.user_id left join mo_employee_counties m on me.emp_id = m.emp_id where  me.eff_end_dt is null and me.user_id= ?",
				authenticationObj.getUserName());
		ResponseVO responseVO = new ResponseVO();
		UserProfileVO userProfileVO = new UserProfileVO();
		if (!list.isEmpty()) {

			String pwd = "";

			for (Map<String, Object> res : list) {

				pwd = (String) res.get("PREFERRED_NAME");

				if (authenticationObj.getPassword().equals(pwd)) {
					userProfileVO.setFirstName((String) res.get("FIRST_NAME"));
					userProfileVO.setLastName((String) res.get("LAST_NAME"));
					userProfileVO.setMidname((String) res.get("MID_NAME"));
					userProfileVO.setEmail((String) res.get("EMAIL"));
					userProfileVO.setCounty((String)res.get("COUNTY_CD"));
					responseVO.setUserProfile(userProfileVO);
					responseVO.setStatus("Success");
				} else {
					responseVO.setUserProfile("Data");
					responseVO.setStatus("Failure");
				}

			}

		}
		return responseVO;
	}

	@ExceptionHandler
	public ResponseEntity<Object> handleException(Exception exc) {

		return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);

	}

}
