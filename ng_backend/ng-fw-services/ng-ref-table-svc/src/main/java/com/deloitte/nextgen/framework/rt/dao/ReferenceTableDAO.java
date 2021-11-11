package com.deloitte.nextgen.framework.rt.dao;

import com.deloitte.nextgen.framework.rt.vo.ReferenceTableVersion;

import java.util.List;
import java.util.Map;

/**
 * The Interface ReferenceTableDAO.
 */
public interface ReferenceTableDAO {

	/**
	 * Gets the reference table.
	 *
	 * @param tableName the table name
	 * @return the reference table
	 */
//	public Map<String, ReferenceTableVersion> getReferenceTable(String tableName);
	public Map<String, ReferenceTableVersion> getReferenceTableData(String tableName);
	
	/**
	 * Gets the reference tables.
	 *
	 * @param tableNames the table names
	 * @return the reference tables
	 */
	public Map<String, Map<String, ReferenceTableVersion>> getReferenceTables(List<String> tableNames);
	
}
