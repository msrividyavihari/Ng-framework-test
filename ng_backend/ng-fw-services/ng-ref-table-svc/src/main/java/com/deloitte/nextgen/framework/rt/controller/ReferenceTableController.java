package com.deloitte.nextgen.framework.rt.controller;

import com.deloitte.nextgen.framework.rt.exception.ReferenceTableNotFoundException;
import com.deloitte.nextgen.framework.rt.service.ReferenceTableService;
import com.deloitte.nextgen.framework.rt.vo.ReferenceTableVersion;
import com.deloitte.nextgen.framework.rt.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The Class ReferenceTableController.
 */
@Slf4j
@RestController
@CrossOrigin
public class ReferenceTableController {

	/** The reference table service. */
	@Autowired
	private ReferenceTableService referenceTableService;

	/**
	 * Gets the reference table.
	 *
	 * @param referenceTableName the reference table name
	 * @return the reference table
	 */
	@GetMapping("/getReferenceTable/{referenceTableName}")
	public ResponseVO /* Map<String, ReferenceTableVersion> */ getReferenceTable(
			@PathVariable String referenceTableName) {

		log.info("Entering method getReferenceTable of ReferenceTableController");
		log.info("Getting data for Reference Table: " + referenceTableName);
		Map<String, ReferenceTableVersion> rtMap = referenceTableService.getReferenceTable(referenceTableName);
		log.debug("Reference Table Data: " + rtMap);

		return (null != rtMap ? (new ResponseVO(rtMap, "0"))
				: (new ResponseVO("Reference Table not found - " + referenceTableName, "1")));

//		return Optional.ofNullable(referenceTableService.getReferenceTable(referenceTableName))
//				.orElseThrow(() -> new ReferenceTableNotFoundException("Refcmderence Table not found - " + referenceTableName));
	}

	@PostMapping("/getReferenceTables")
	public ResponseVO/* Map<String, Map<String, ReferenceTableVersion>> */ getReferenceTables(
			@RequestBody List<String> referenceTableNames) {

		log.info("Entering method getReferenceTables of ReferenceTableController");
		log.info("Getting data for Reference Tables: " + referenceTableNames);
		Map<String, Map<String, ReferenceTableVersion>> rtMap = referenceTableService
				.getReferenceTables(referenceTableNames);
		log.debug("Reference Table Data: " + rtMap);
		String status = "";

		Map<String, Map<String, ReferenceTableVersion>> collect = rtMap.entrySet()
				.stream()
				.filter(key -> key.getValue() != null)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		if (!collect.isEmpty() && collect.size() == rtMap.size()) {
			status = "0"; //no errors
		} else if (collect.size() < rtMap.size()) {
			status = "2"; //partial data found
		} else {
			status = "1"; //no data found
		}

		return new ResponseVO(rtMap, status);
//		return Optional.ofNullable(referenceTableService.getReferenceTables(referenceTableNames)).orElseThrow(
//				() -> new ReferenceTableNotFoundException("Reference Tables not found - " + referenceTableNames));
	}
	
	/**
	 * Gets the reference table data.
	 *
	 * @param referenceTableNames the reference table names
	 * @return the reference table data
	 * @throws ParseException 
	 * @throws ReferenceTableNotFoundException 
	 */
	@PostMapping("/getReferenceTableData")
	public Map<String, Map> getReferenceTableData(
			@RequestBody List<String> referenceTableNames) throws ReferenceTableNotFoundException, ParseException {
		return Optional.ofNullable(referenceTableService.getRefTableData(referenceTableNames)).orElseThrow(
		() -> new ReferenceTableNotFoundException("Reference Tables not found - " + referenceTableNames));
		
	}

	/**
	 * Handle exception.
	 *
	 * @param exc the exc
	 * @return the response entity
	 */
	@ExceptionHandler
	public ResponseEntity<Object> handleException(Exception exc) {

		log.error("Exception occurred in ReferenceTableService!", exc);
		return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);

	}

}
