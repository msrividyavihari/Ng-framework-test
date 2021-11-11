package com.deloitte.nextgen.framework.rt.service;

import com.deloitte.nextgen.framework.rt.dao.ReferenceTableDAO;
import com.deloitte.nextgen.framework.rt.vo.ReferenceTableVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReferenceTableServiceImpl implements ReferenceTableService {

	@Autowired
	private ReferenceTableDAO referenceTableDAO;

	/**
	 * Gets the reference table.
	 *
	 * @param tableName the table name
	 * @return the reference table
	 */
	@Override
	@Cacheable(
	value = "RefTable",
	key = "new org.springframework.cache.interceptor.SimpleKey(#tableName)"
			,cacheManager = "secondaryCacheManger"
	)

	public Map<String, ReferenceTableVersion> getReferenceTable(String tableName) {

		log.info("Inside ReferenceTableServiceImpl.getReferenceTable()");
		//START - Changes made to optimize RT Data Extraction
//		return referenceTableDAO.getReferenceTable(tableName);
		return referenceTableDAO.getReferenceTableData(tableName);
		//END - Changes made to optimize RT Data Extraction
	}

	/**
	 * Gets the reference tables.
	 *
	 * @param tableNames the table names
	 * @return the reference tables
	 */
	@Override
	@Cacheable(cacheNames = "test:getting all"//cacheManager = "secondaryCacheManger"
	)
	public Map<String, Map<String, ReferenceTableVersion>> getReferenceTables(List<String> tableNames) {

		//START - Changes made to optimize RT Data Extraction
//		return tableNames.stream().collect(toMapOfNullables(key -> key, key -> referenceTableDAO.getReferenceTable(key)));
		return tableNames.stream().collect(toMapOfNullables(key -> key, key -> referenceTableDAO.getReferenceTableData(key)));
		//END - Changes made to optimize RT Data Extraction

	}
	
	/**
	 * Gets the ref table data.
	 *
	 * @param tableNames the table names
	 * @return the ref table data
	 * @throws ParseException the parse exception
	 */
	@Override
		@Cacheable(cacheNames = "RefTable"
			,key="#tableNames"
			,cacheManager = "secondaryCacheManger"//"initRedisCacheManager"
	)
	public Map<String, Map> getRefTableData(List<String> tableNames) throws ParseException {

		Map<String, Map<String, ReferenceTableVersion>> refTableMap = getReferenceTables(tableNames);
		Map rtData = new HashMap();
		log.info("Inside getRefTableData Method");
		for (String aReftTableName : tableNames) {

			Map versionMap = refTableMap.get(aReftTableName);
			ReferenceTableVersion dataMap = null;
			String beginDate = null;
			String endDate = null;

			//START - Changes made to optimize RT Data Extraction
			Set<Map<String, String>> mRowSet = null;
			//END - Changes made to optimize RT Data Extraction

			if (null != versionMap) {
				for (Object obj : versionMap.keySet()) {
					dataMap = (ReferenceTableVersion) versionMap.get(obj);
					beginDate = dataMap.getEffectiveBeginDate();
					endDate = dataMap.getEffectiveEndDate();
					if (checkDate(beginDate, endDate, getCurrentTimestamp())) {

						//START - Changes made to optimize RT Data Extraction
						mRowSet = dataMap.getRefData();

						log.info("mRowSet:" + mRowSet);

//						sortedDataSet.addAll(mRowSet);
//						log.info("sortedDataSet:" + sortedDataSet);

//						Map mRowMap = dataMap.getRefData();
//						Map rowMap = (TreeMap) mRowMap.get("sorteddata");
//
//						int i = 0;
//
//						Iterator it = rowMap.keySet().iterator();
//						Map[] addiData = new Map[rowMap.size()];
//						while (it.hasNext()) {
//
//							Object data = it.next();
//							Map res = (Map) rowMap.get(data);
//							addiData[i] = res;
//							i++;
//						}
//						rtData.put(aReftTableName, addiData);
						rtData.put(aReftTableName, mRowSet);
						//END - Changes made to optimize RT Data Extraction
						break;
					}
				}
			}
		}
		return rtData;
	}

	/**
	 * Sets the today's date as currentDate.
	 * 
	 * @return Timestamp returns the today's date.
	 * @throws ParseException
	 */
	private Timestamp getCurrentTimestamp() throws ParseException {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
		String date = dateFormatter.format(new GregorianCalendar().getTime());
		try {
			return new Timestamp(dateFormatter.parse(date).getTime());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured in ReferenceTableManager.getCurrentTimestamp():" + e.toString(), e);
			throw e;
		}
	}

	/**
	 * Checks whether current date is within the given Begin and End dates.
	 * 
	 * @param aBeginDate contains the given begin date.
	 * @param aEndDate   contains the end date.
	 * @param currDate   contains the today's date.
	 * @return boolean if value is true the current date is within the given begin
	 *         and end dates.
	 */
	private boolean checkDate(String aBeginDate, String aEndDate, Timestamp currDate) {

		return (currDate.after(new Timestamp(Timestamp.valueOf(aBeginDate).getTime() - 24 * 60 * 60 * 1000))
				&& (aEndDate == null || "null".equalsIgnoreCase(aEndDate) || currDate
						.before(new Timestamp(Timestamp.valueOf(aEndDate).getTime() + 24 * 60 * 60 * 1000))));
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
