package com.deloitte.nextgen.framework.rt.service;

import com.deloitte.nextgen.framework.rt.vo.ReferenceTableVersion;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * The Interface ReferenceTableService.
 */
public interface ReferenceTableService {

	/**
	 * Gets the reference table.
	 *
	 * @param tableName the table name
	 * @return the reference table
	 */
	public Map<String, ReferenceTableVersion> getReferenceTable(String tableName);

	/**
	 * Gets the reference tables.
	 *
	 * @param tableNames the table names
	 * @return the reference tables
	 */
	public Map<String, Map<String, ReferenceTableVersion>> getReferenceTables(List<String> tableNames);
	
	
	/**
	 * Gets the ref table data in the format.
	 *
	 * @param tableNames the table names
	 * @return the ref table data
	 * @throws ParseException 
	 */
	public Map<String, Map> getRefTableData(List<String> tableNames) throws ParseException;

}
