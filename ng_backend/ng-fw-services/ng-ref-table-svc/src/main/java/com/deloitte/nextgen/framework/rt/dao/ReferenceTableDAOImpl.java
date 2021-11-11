/*
 * 
 */
package com.deloitte.nextgen.framework.rt.dao;

import com.deloitte.nextgen.framework.rt.vo.ReferenceTableVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.deloitte.nextgen.framework.rt.vo.ReferenceTableVersion.*;

/**
 * The Class ReferenceTableDAOImpl.
 */
@Transactional
@Repository
@Slf4j
public class ReferenceTableDAOImpl implements ReferenceTableDAO {

	/** The Constant COLUMNVALUE. */
	public static final String COLUMNVALUE = "COLUMNVALUE";

	/** The Constant COLUMNNAME. */
	public static final String COLUMNNAME = "COLUMNNAME";

	/** The Constant ROW_ID. */
	public static final String ROW_ID = "ROW_ID";
	
	/** The Constant CODE. */
	public static final String CODE = "CODE";

	/** The Constant DESCRIPTION. */
	public static final String DESCRIPTION = "DESCRIPTION";

	/** The Constant RANK. */
	public static final String RANK = "RANK";
	
	/** The Constant SQL_QUERY. */
	public static final String SQL_QUERY = "select tablename,VERSION,EFFBEGDATE,EFFENDDATE,COLUMNNAME,COLUMNVALUE,ROW_ID from rt_all_mv where tablename = ? ORDER BY TABLENAME, VERSION, ROW_ID, FIELDID";

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

//START - Changes made to optimize RT Data Extraction
//	/**
//	 * Gets the reference table.
//	 *
//	 * @param aRefTableName the a ref table name
//	 * @return the reference table
//	 */
//	@Override
//	//@Cacheable(value = "referenceTable", key = "#aRefTableName")
//	public Map<String, ReferenceTableVersion> getReferenceTable(String aRefTableName) {
//
//		log.info("Inside ReferenceTableDaoImpl.getReferenceTable() getting data for table:"+aRefTableName);
//		return jdbcTemplate.query(
//				SQL_QUERY,
//				rs -> {
//
//					if (!rs.next()) return null;
//
//					Map<String, ReferenceTableVersion> versionMap = new HashMap<>();
//					Map<String, Map<String, String>> rowidMap = new HashMap<>();
//					Map<String, String> rowMap = null;
//					ReferenceTableVersion rtv = null;
//
//					do {
//
//						rtv = versionMap.computeIfAbsent(rs.getString(VERSION), ReferenceTableVersion::new);
//						rtv.setEffectiveBeginDate(rs.getString(EFFBEGINDATE));
//						rtv.setEffectiveEndDate(rs.getString(EFFENDDATE));
//
//						rowMap =  rowidMap.computeIfAbsent(rs.getString(VERSION) + "/" + rs.getString(ROW_ID), s -> new HashMap<>());
//						rowMap.put(rs.getString(COLUMNNAME), rs.getString(COLUMNVALUE));
//
//						switch(rs.getString(COLUMNNAME)) {
//							case  CODE:
//								rtv.getRefData().get(US_DATA).put(rowMap.get(CODE), rowMap);
//								break;
//							case  DESCRIPTION:
//								rtv.getRefData().get(S_DATA).put(rowMap.get(DESCRIPTION), rowMap);
//								break;
//							case  RANK:
//								rtv.getRefData().get(S_DATA).remove(rowMap.get(DESCRIPTION));
//								rtv.getRefData().get(S_DATA).put(String.format("%1$5s", rowMap.get(RANK)).replace(' ','0'), rowMap);
//								break;
//							default:
//						}
//
//					} while ((!rs.isAfterLast() && !rs.isBeforeFirst()) && rs.next());
//
//					log.info("Reference Table: "+aRefTableName+ " Data: "+versionMap);
//
//					return versionMap;
//				},
//				aRefTableName);
//
//	}

	/**
	 * Gets the reference table.
	 *
	 * @param aRefTableName the a ref table name
	 * @return the reference table
	 */
	@Override
	public Map<String, ReferenceTableVersion> getReferenceTableData(String aRefTableName) {

		log.info("Inside ReferenceTableDaoImpl.getReferenceTableData() getting data for table:"+aRefTableName);

		return jdbcTemplate.query(
				SQL_QUERY,
				rs -> {
					if (!rs.next()) return null;

					Map<String, ReferenceTableVersion> versionMap = new HashMap<>();
					Map<String, Map<String, String>> rowidMap = new HashMap<>();
					Map<String, String> rowMap = null;
					ReferenceTableVersion rtv = null;

					do {

						rtv = versionMap.computeIfAbsent(rs.getString(VERSION), ReferenceTableVersion::new);

						rtv.setEffectiveBeginDate(rs.getString(EFFBEGINDATE));

						rtv.setEffectiveEndDate(rs.getString(EFFENDDATE));

						rowMap =  rowidMap.computeIfAbsent(rs.getString(VERSION) + "/" + rs.getString(ROW_ID), s -> new HashMap<>());


						if (rowMap.isEmpty()){
							rtv.getRefData().add(rowMap);
						}

						rowMap.put(rs.getString(COLUMNNAME), rs.getString(COLUMNVALUE));

					} while ((!rs.isAfterLast() && !rs.isBeforeFirst()) && rs.next());

					return versionMap;
				},
				aRefTableName);

	}
	//END - Changes made to optimize RT Data Extraction

	/**
	 * Gets the reference tables.
	 *
	 * @param tableNames the table names
	 * @return the reference tables
	 */
	@Override
	public Map<String, Map<String, ReferenceTableVersion>> getReferenceTables(List<String> tableNames) {
		
		log.info("Inside ReferenceTableServiceImpl.getReferenceTables(): ");
		//START - Changes made to optimize RT Data Extraction
//		return tableNames.stream().collect(toMapOfNullables(key -> key, this::getReferenceTable));
		return tableNames.stream().collect(toMapOfNullables(key -> key, this::getReferenceTableData));
		//END - Changes made to optimize RT Data Extraction
		
	}
	
	public static <T, K, U> Collector<T, ?, Map<K, U>> toMapOfNullables(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
	    return Collectors.collectingAndThen(
	        Collectors.toList(),
	        list -> {
	            Map<K, U> map = new LinkedHashMap<>();
	            list.forEach(item -> {
	                K key = keyMapper.apply(item);
	                if (map.containsKey(key)) {
	                    throw new IllegalStateException(String.format("Duplicate key %s", key));
	                }
	                map.put(key, valueMapper.apply(item));
	            });
	            return map;
	        }
	    );
	}

}
