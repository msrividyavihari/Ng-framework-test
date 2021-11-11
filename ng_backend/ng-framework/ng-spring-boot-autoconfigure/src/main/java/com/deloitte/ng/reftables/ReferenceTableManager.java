package com.deloitte.ng.reftables;

import com.deloitte.nextgen.framework.commons.exceptions.ApplicationException;
import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.framework.commons.exceptions.FwException;
import com.deloitte.nextgen.framework.commons.spi.ReferenceTable;
import com.deloitte.nextgen.framework.properties.ApplicationProperties;
import com.deloitte.ng.constants.FwConstants;
import com.deloitte.ng.constants.IReferenceConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The class <code> ReferenceTableManager </code> is used for to get the
 * Refernece Table Data. Get the rows and column values of the reference table.
 *
 * @author mukepatel on 20/06/2020 2:36 PM
 * @project ng-spring-boot-autoconfigure
 */
@Slf4j
public class ReferenceTableManager implements ReferenceTable {

    public static final String REFERENCE_TABLE_NOT_FOUND = "Reference table %s not found";

    private RestTemplate restTemplate;

    private String serviceUrl;

    private String serviceUrlForPreLoad;

    private List<String> preloadList;

    private Boolean preloadFlag;

    //@Autowired
    //private CacheManager cacheManager;

    public ReferenceTableManager(ApplicationProperties.ReferenceTable properties, RestTemplate restTemplate) {
        this.serviceUrl = properties.getServiceUrl();
        this.serviceUrlForPreLoad = properties.getServiceUrlForPreLoad();
        this.preloadList = properties.getPreloadList();
        this.preloadFlag = properties.isPreload();

        this.restTemplate = restTemplate;
        //this.cacheManager = cacheManager;
    }


    /**
     * Gets the table.
     *
     * @param aRefTableName the a ref table name
     * @return the table
     * @throws com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException
     */
    public Map getTable(String aRefTableName) throws FwApplicationException {

        log.info("Entering method ReferenceTableManager.getTable() for table: " + aRefTableName);
        try {
            return (Map) Optional
                    .ofNullable(restTemplate.getForObject(serviceUrl, ResponseVO.class, aRefTableName))
                    .filter(respVO -> respVO.getStatus().equals("0"))
                    .map(ResponseVO::getData)
                    .orElseThrow(() -> new FwApplicationException(String.format(REFERENCE_TABLE_NOT_FOUND, aRefTableName)));
        } catch (Exception e) {
            log.error("ReferenceTable.getTable(String) for " + aRefTableName + " returning null versionMap");
            throw new FwApplicationException(String.format(REFERENCE_TABLE_NOT_FOUND, aRefTableName), e);
        }
    }

    public Map getTables(List<String> aRefTableNames) throws FwApplicationException {

        log.info("Entering method ReferenceTableManager.getTables() for tables: " + aRefTableNames);

        try {
            return (Map) Optional
                    .ofNullable(restTemplate.postForObject(serviceUrlForPreLoad, aRefTableNames, ResponseVO.class))
                    .filter(respVO -> !respVO.getStatus().equals("1"))
                    .map(ResponseVO::getData)
                    .orElseThrow(() -> new FwApplicationException(String.format(REFERENCE_TABLE_NOT_FOUND, aRefTableNames.toString())));
        } catch (Exception e) {
            log.error("ReferenceTable.getTables(List<String>) for " + aRefTableNames + " returning null versionMap", e);
            throw new FwApplicationException(String.format(REFERENCE_TABLE_NOT_FOUND, aRefTableNames.toString()), e);
        }
    }

    /**
     * Field CODE.
     */
    private static final String CODE = "CODE";

    /**
     * Field DESC.
     */
    private static final String DESC = "DESCRIPTION";

    //START - Changes made to optimize RT Data Extraction
    /** The Constant DATA. */
    public static final String DATA = "data";

    /** The Constant S_DATA. */
    public static final String S_DATA = "sorteddata";

    /** The Constant US_DATA. */
    public static final String US_DATA = "unsorteddata";

    /** The Constant RANK. */
    public static final String RANK = "RANK";
    //END - Changes made to optimize RT Data Extraction

    /**
     * Returns Reference Table Data for the given tableName and date.
     *
     * @param aRefTableName Contains the reference table name.
     * @param currDate      contains the date of the reference table creation.
     * @return Map contains the value of the rows and column.
     * @throws com.deloitte.nextgen.framework.commons.exceptions.FwException
     * @throws FwApplicationException
     */
    //@Cacheable(value = "refTableCache", key = "#aRefTableName")
    public Map getReferenceTableData(String aRefTableName, Timestamp currDate)
            throws FwException, FwApplicationException {
        // Creation Date : (08/10/2002 2:00:12 PM)

        Map versionMap = getTable(aRefTableName);
        Map dataMap = null;
        String beginDate = null;
        String endDate = null;


        for (Object obj : versionMap.keySet()) {
            dataMap = (Map) versionMap.get(obj);

            //START - Changes made to optimize RT Data Extraction
            Map<String, Map<String, Map<String,String>>> sortedDataMap = new HashMap<String, Map<String, Map<String,String>>>();
            sortedDataMap.put(US_DATA, new HashMap<String, Map<String, String>>());
            sortedDataMap.put(S_DATA, new TreeMap<String, Map<String, String>>());

            Set<Map<String, String>> dataSet = new HashSet<Map<String, String>>();
            Set<Map<String, String>> rowSet = new HashSet<Map<String, String>>();


            Map refDataMap = null;
            Map<String, String> rowMap = new HashMap<String,String>();

            rowSet.addAll((ArrayList)dataMap.get(DATA));

            Iterator j = rowSet.iterator();

            while (j.hasNext()){

                rowMap = (Map) j.next();

                boolean isRankColumnPresent = rowMap.containsKey(RANK);

                sortedDataMap.get(US_DATA).put(rowMap.get(CODE), rowMap);

                if (isRankColumnPresent) {
                    sortedDataMap.get(S_DATA).put(String.format("%1$5s", rowMap.get(RANK)).replace(' ','0'), rowMap);
                } else {
                    sortedDataMap.get(S_DATA).put(rowMap.get(DESC), rowMap);
                }
            }

            //END - Changes made to optimize RT Data Extraction

            beginDate = (String) dataMap.get(IReferenceConstants.BEGIN_DATE);
            endDate = (String) dataMap.get(IReferenceConstants.END_DATE);
            if (checkDate(beginDate, endDate, currDate)) {
                //START - Changes made to optimize RT Data Extraction
//                return (HashMap) dataMap.get(IReferenceConstants.DATA);
                return sortedDataMap;
                //END - Changes made to optimize RT Data Extraction
            }
        }
        log.debug("ReferenceTable.getReferenceTableData(String) for " + aRefTableName + " not returning data");
        throw new FwApplicationException(String.format(REFERENCE_TABLE_NOT_FOUND, aRefTableName));
    }

    /**
     * Checks whether current date is within the given Begin and End dates.
     *
     * @param aBeginDate contains the given begin date.
     * @param aEndDate   contains the end date.
     * @param currDate   contains the today's date.
     * @return boolean if value is true the current date is within the given begin
     * and end dates.
     */
    private boolean checkDate(String aBeginDate, String aEndDate, Timestamp currDate) {
        // Creation Date : (08/10/2002 2:00:12 PM)

        return (currDate.after(new Timestamp(Timestamp.valueOf(aBeginDate).getTime() - 24 * 60 * 60 * 1000))
                && (aEndDate == null || "null".equalsIgnoreCase(aEndDate) || currDate
                .before(new Timestamp(Timestamp.valueOf(aEndDate).getTime() + 24 * 60 * 60 * 1000))));
    }

    /**
     * Method getReferenceTableData is used for gets the Reference Table Data for
     * the given Table Name and Date.
     *
     * @param aRefTableName contains the reference table name.
     * @return Map contains the requested date of the table.
     * @throws FwException
     * @throws FwApplicationException
     */
    @Cacheable(value = "refTableCache", key = "#aRefTableName")
    public Map getReferenceTableData(String aRefTableName) throws FwException, FwApplicationException {

        return getReferenceTableData(aRefTableName, getCurrentTimestamp());
    }

    /*
     * public Map getReferenceTables(List<String> aRefTableNames) throws
     * FwException, FwApplicationException {
     *
     * return getReferenceTables(aRefTableNames, getCurrentTimestamp()); }
     */

    /**
     * Gets the reference tables.
     *
     * @param
     * @param
     * @return the reference tables
     * @throws FwApplicationException the fw application exception
     */
    @PostConstruct
    //@Cacheable(value = "refTableCache", condition = "#preloadFlag")
    private Map getReferenceTables() throws FwApplicationException {

        Map rtData = new HashMap();
        if (Boolean.TRUE.equals(preloadFlag)) {
            try {
                Map tableMap = getTables(preloadList);

                for (String aReftTableName : preloadList) {

                    Map versionMap = (Map) tableMap.get(aReftTableName);
                    Map dataMap = null;
                    String beginDate = null;
                    String endDate = null;

                    for (Object obj : versionMap.keySet()) {
                        dataMap = (Map) versionMap.get(obj);
                        beginDate = (String) dataMap.get(IReferenceConstants.BEGIN_DATE);
                        endDate = (String) dataMap.get(IReferenceConstants.END_DATE);
                        if (checkDate(beginDate, endDate, getCurrentTimestamp())) {
                            rtData.put(aReftTableName, dataMap.get(IReferenceConstants.DATA));
                        }
                    }
                    //ReferenceTable data will be stored in refTableCache on server startup
                    //cacheManager.getCache("refTableCache").put(aReftTableName, dataMap);
                }
            } catch (Exception e) {
                log.error("ReferenceTable.getReferenceTableData(List<String>) for " + preloadList + " not returning data", e);
                throw new FwApplicationException(String.format(REFERENCE_TABLE_NOT_FOUND, preloadList.toString()), e);
            }
        }
        return rtData;
    }

    /**
     * Gets the Reference Table Row for the given Table Name,Code and Date
     *
     * @param aRefTableName contains the reference table name.
     * @param aCode         contains the code for the table.
     * @return Map contains the requested date of the table.
     * @throws FwException
     * @throws FwApplicationException
     */
    public Map getRowForCode(String aRefTableName, String aCode) throws FwException, FwApplicationException {

        return getRowForColumn(aRefTableName, CODE, aCode, getCurrentTimestamp());
    }

    /**
     * Returns Reference Table Row for given Code or Description. Additionally, date
     * is specified to retrieve data based on the effectivity dates.
     *
     * @param aRefTableName Contains the reference table name.
     * @param aColumnName   Contains the column name of the table.
     * @param aColumnValue  Contains the column value of the table.
     * @param currDate      contains the date of the reference table creation.
     * @return Map contains the value of the rows and column.
     * @throws FwException
     * @throws FwApplicationException
     */
    public Map getRowForColumn(String aRefTableName, String aColumnName, String aColumnValue, Timestamp currDate)
            throws FwException, FwApplicationException {
        // Creation Date : (08/10/2002 2:00:12 PM)

        Map mRowMap = getReferenceTableData(aRefTableName, currDate);
        Map rowMap = null;
        Map columnMap;
        if (aColumnName.equalsIgnoreCase(IReferenceConstants.CODE)) {
            rowMap = (HashMap) mRowMap.get(IReferenceConstants.US_DATA);
            if (rowMap != null) {
                columnMap = (Map) rowMap.get(aColumnValue);
                if (columnMap != null) {
                    return columnMap;
                }
            }
        } else if (aColumnName.equalsIgnoreCase(IReferenceConstants.DESC)) {
            rowMap = (TreeMap) mRowMap.get(IReferenceConstants.S_DATA);
            if (rowMap != null) {
                columnMap = (Map) rowMap.get(aColumnValue);
                if (columnMap != null) {
                    return columnMap;
                } else {

                    Optional<Map> colMap = rowMap.keySet()
                            .stream()
                            .map(rowMap::get)
                            //.map(Map.class::cast)
                            .filter(c -> ((Map<?, ?>) c).get(aColumnName) != null
                                    && ((String)((Map<?, ?>) c).get(aColumnName)).equalsIgnoreCase(aColumnValue))
                            .findFirst();

                    if(colMap.isPresent()) {
                        return colMap.get();
                    }

                    /*Iterator rowIt = rowMap.keySet().iterator();
                    while (rowIt.hasNext()) {
                        Object key = rowIt.next();
                        columnMap = (Map) rowMap.get(key);
                        String value = (String) columnMap.get(aColumnName);
                        if (value != null && value.equalsIgnoreCase(aColumnValue)) {
                            return columnMap;
                        }
                    }*/
                }
            }
        }

        log.debug("ReferenceTable.getData(String, String, String) - Row not found in reference table " + aRefTableName
                + " for " + aColumnName + " value " + aColumnValue);
        throw new FwApplicationException(
                "Row not found in reference table " + aRefTableName + " for " + aColumnName + " value " + aColumnValue);
    }

    /**
     * Gets the Reference Table Row for the given Table Name,Code and Date
     *
     * @param aRefTableName contains the reference table name.
     * @param aCode         contains the code for the table.
     * @param aRequestDate  contains the requested date of the table.
     * @return Map returns the keys of rows and get the values of the column.
     * @throws FwException
     * @throws FwApplicationException
     */
    public Map getRowForCode(String aRefTableName, String aCode, Timestamp aRequestDate)
            throws FwException, FwApplicationException {

        return getRowForColumn(aRefTableName, CODE, aCode, aRequestDate);
    }

    /**
     * Method getRowForDescription is used for gets the Reference Table Row for the
     * given Table Name,Description and Date.
     *
     * @param aRefTableName contains the reference table name.
     * @param aDesc         contains the description of the table.
     * @return Map returns the keys of rows and get the values of the column.
     * @throws FwException
     * @throws FwApplicationException
     */
    public Map getRowForDescription(String aRefTableName, String aDesc) throws FwException, FwApplicationException {

        return getRowForColumn(aRefTableName, DESC, aDesc, getCurrentTimestamp());
    }

    /**
     * Gets the Reference Table Row for the given Table Name,Description and Date.
     *
     * @param aRefTableName contains the reference table name.
     * @param aDesc         contains the description of the table.
     * @param aRequestDate  contains the requested date of the table.
     * @return Map
     * @throws FwException
     * @throws FwApplicationException
     */
    public Map getRowForDescription(String aRefTableName, String aDesc, Timestamp aRequestDate)
            throws FwException, FwApplicationException {

        return getRowForColumn(aRefTableName, DESC, aDesc, aRequestDate);
    }

    /**
     * Method getValueByColumn is used for gets the Reference Table Value for a
     * given TableName, Code and ColumnName.
     *
     * @param aRefTableName    contains the reference table name.
     * @param aCode            contains the code of the table.
     * @param aRequestedColumn contains the column name and value.
     * @return String returns the table name and column name.
     * @throws FwException
     * @throws FwApplicationException
     */
    public String getValueByColumn(String aRefTableName, String aCode, String aRequestedColumn)
            throws FwException, FwApplicationException {
        return getValueByColumn(aRefTableName, aCode, aRequestedColumn, getCurrentTimestamp());
    }

    /**
     * Method getValueByColumn is used for gets the Reference Table Value for a
     * given TableName, Code and ColumnName.
     *
     * @param aRefTableName    contains the reference table name.
     * @param aCode            contains the code of the table.
     * @param aRequestedColumn contains the column name and value.
     * @param aRequestDate     contains the requested date of the table.
     * @return String returns the keys of rows and get the values of the column.
     * @throws FwException
     * @throws FwApplicationException
     */
    public String getValueByColumn(String aRefTableName, String aCode, String aRequestedColumn, Timestamp aRequestDate)
            throws FwException, FwApplicationException {

        try {
            Map columnMap = getRowForColumn(aRefTableName, IReferenceConstants.CODE, aCode, aRequestDate);
            if (columnMap != null) {
                String name = null;
                for (Object obj : columnMap.keySet()) {
                    name = (String) obj;
                    if (null != columnMap.get(name) && name.equalsIgnoreCase(aRequestedColumn)) {
                        return (String) columnMap.get(name);
                    } else {
                        return FwConstants.EMPTY;
                    }
                }
            }
        } catch (FwApplicationException fae) {
            log.debug("ReferenceTable.getValueByColumn(String, String, String) - Reference table (" + aRefTableName
                    + ") column (" + aRequestedColumn + ") value not found for Code " + aCode);
            log.debug(
                    "FwApplicationException in ReferenceTableManager.getValueByColumn(String, String, String, Timestamp): "
                            + fae.toString(),
                    fae);
            log.debug("Table: " + aRefTableName + " Code: " + aCode + " RequestedColumn " + aRequestedColumn
                    + " Timestamp " + aRequestDate);
            log.debug(
                    "Returning empty string back from ReferenceTableManager.getValueByColumn(String, String, String, Timestamp)");
            return FwConstants.EMPTY;
        }
        return FwConstants.EMPTY;
    }

    /**
     * Method getRowsForColumnFilter is used for gets the Reference Table Rows for
     * given TableName and Filter.
     *
     * @param aRefTableName contains the reference table name.
     * @param aFilter       contains the rows for given table name and Filter.
     * @return Map returns the keys of rows and get the values of the column.
     * @throws FwException
     * @throws FwApplicationException
     */
    public Map getRowsForColumnFilter(String aRefTableName, Map aFilter) throws FwException, FwApplicationException {
        return getRowsForColumnFilter(aRefTableName, aFilter, getCurrentTimestamp());
    }

    /**
     * Method getRowsForColumnFilter is used for gets the Reference Table Rows for
     * given TableName and Filter.
     *
     * @param aRefTableName contains the reference table name.
     * @param aFilter       contains the rows for given table name and Filter.
     * @param aRequestDate  contains the requested date of the table.
     * @return Map returns the keys of rows and get the values of the column.
     * @throws FwException
     * @throws FwApplicationException
     */
    public Map getRowsForColumnFilter(String aRefTableName, Map aFilter, Timestamp aRequestDate)
            throws FwException, FwApplicationException {
        Map result = null;
        Map mRowMap = getReferenceTableData(aRefTableName, aRequestDate);
        Map rowMap = (TreeMap) mRowMap.get(IReferenceConstants.S_DATA);
        boolean aFlag = false;
        int count = 0;

        if (rowMap != null) {
            Map columnMap = null;
            String name = null;
            String value = null;
            String columnName = null;
            String columnValue = null;
            for (Object key : rowMap.keySet()) {
                columnMap = (Map) rowMap.get(key);
                aFlag = true;
                count = 0;
                for (Object colIt : columnMap.keySet()) {
                    if (!aFlag) {
                        break;
                    }
                    name = (String) colIt;
                    value = (String) columnMap.get(name);
                    for (Object filterIt : aFilter.keySet()) {
                        columnName = (String) filterIt;
                        columnValue = (String) aFilter.get(columnName);
                        if (name.equalsIgnoreCase(columnName)) {
                            count++;
                            if (aFlag && value != null && value.equalsIgnoreCase(columnValue)) {
                                aFlag = true;
                            } else {
                                aFlag = false;
                                break;
                            }
                        }
                    }
                }
                if ((count == aFilter.size()) && aFlag) {
                    if (result == null) {
                        result = new TreeMap();
                    }
                    result.put(key, columnMap);
                }
            }
        }
        return result;
    }

    /**
     * Method getRowsByColumnName is used for Gets the Reference Table Rows for the
     * given TableName and ColumnNames. This will get the values for the given
     * column names and get the data for that values (code).
     *
     * @param aRefTableName contains the reference table name.
     * @param aColumnName   contains the column name of the reference table.
     * @return Map returns the keys of rows and get the values of the column.
     * @throws FwException
     * @throws FwApplicationException
     */
    public Map getRowsByColumnName(String aRefTableName, String[] aColumnName)
            throws FwException, FwApplicationException {
        return getRowsByColumnName(aRefTableName, aColumnName, getCurrentTimestamp());
    }

    /**
     * Method getRowsByColumnName is used for Gets the Reference Table Rows for the
     * given TableName and ColumnNames. This will get the values for the given
     * column names and get the data for that values (code).
     *
     * @param aRefTableName contains the reference table name.
     * @param aColumnName   contains the column name of the reference table.
     * @param aRequestDate  contains the requested date of the table.
     * @return Map returns the keys of rows and get the values of the column.
     * @throws FwException
     * @throws FwApplicationException
     */
    public Map getRowsByColumnName(String aRefTableName, String[] aColumnName, Timestamp aRequestDate)
            throws FwException, FwApplicationException {

        Map result = new HashMap();
        Set codeSet = new TreeSet();

        Set res = null;
        for (int i = 0, length = aColumnName.length; i < length; i++) {
            try {
                res = getValuesByColumnName(aRefTableName, aColumnName[i], aRequestDate);
            } catch (Exception e) {
                log.debug(e.toString(), e);
            }
            if (res != null) {
                codeSet.addAll(res);
            }
        }
        int count = 0;
        String key = null;
        String code = null;
        Map row = null;
        for (Object obj : codeSet) {
            count++;
            key = String.valueOf(count);
            code = (String) obj;
            if (code != null) {
                row = getRowForCode(aRefTableName, code, aRequestDate);
                result.put(key, row);
            }
        }
        return result;
    }

    /**
     * Returns Reference Table Values as a Set for the given table and column name.
     *
     * @param aRefTableName Contains the reference table name.
     * @param aColumnName   Contains the column name of the table.
     * @param currDate      Timestamp
     * @return result returns the table and column name.
     * @throws FwException
     * @throws FwApplicationException
     */
    public Set getValuesByColumnName(String aRefTableName, String aColumnName, Timestamp currDate)
            throws FwException, FwApplicationException {
        // Creation Date : (08/10/2002 2:00:12 PM)

        Set result = new TreeSet();

        Map mRowMap = getReferenceTableData(aRefTableName, currDate);
        Map rowMap = (TreeMap) mRowMap.get(IReferenceConstants.S_DATA);
        if (rowMap != null) {
            Map columnMap = null;
            String name = null;
            Object columnMapName = null;
            for (Object rowIt : rowMap.keySet()) {
                columnMap = (Map) rowMap.get(rowIt);
                for (Object colIt : columnMap.keySet()) {
                    name = (String) colIt;
                    columnMapName = columnMap.get(name);
                    if (name.equalsIgnoreCase(aColumnName) && columnMapName != null) {
                        result.add(columnMapName);
                    }
                }
            }
            return result;
        }
        log.debug("ReferenceTable.getValuesByColumnName(String, String) - Error reading " + aRefTableName
                + " reference table for the column name " + aColumnName);
        throw new FwApplicationException(
                "Error reading " + aRefTableName + " reference table for the column name " + aColumnName);
    }

    /**
     * Method getRowsForCodeAndColumn is used ofr gets the Reference Table Rows for
     * the given TableName, Code and ColumnNames. This will get the column values
     * for the given code and get the data for that values (code).
     *
     * @param aRefTableName contains the reference table name.
     * @param aCode         contains the code of the table.
     * @param aColumnName   contains the column name of the reference table.
     * @return Map returns the keys and values of the column.
     * @throws FwException
     * @throws FwApplicationException
     */
    public Map getRowsForCodeAndColumn(String aRefTableName, String aCode, String[] aColumnName)
            throws FwException, FwApplicationException {
        return getRowsForCodeAndColumn(aRefTableName, aCode, aColumnName, getCurrentTimestamp());
    }

    /**
     * Method getRowsForCodeAndColumn is used for Gets the Reference Table Rows for
     * the given TableName, Code and ColumnNames. This will get the column values
     * for the given code and get the data for that values (code)
     *
     * @param aRefTableName contains the reference table name.
     * @param aCode         contains the code of the table.
     * @param aColumnName   contains the column name of the reference table.
     * @param aRequestDate  contains the requested date of the table.
     * @return Map returns the keys and values of the column.
     * @throws FwException
     * @throws FwApplicationException
     */
    public Map getRowsForCodeAndColumn(String aRefTableName, String aCode, String[] aColumnName, Timestamp aRequestDate)
            throws FwException, FwApplicationException {

        Map result = new HashMap();
        String columnValue = null;

        for (int i = 0; i < aColumnName.length; i++) {
            // columnValue = refTable.getValueByColumn(aRefTableName, aCode, aColumnName[i])
            columnValue = getValueByColumn(aRefTableName, aCode, aColumnName[i], aRequestDate);
            Map columnMap = null;
            try {
                columnMap = getRowForColumn(aRefTableName, CODE, columnValue, aRequestDate);
            } catch (Exception e) {
                log.debug(e.toString(), e);
            }
            result.put(String.valueOf(i), columnMap);
        }
        return result;
    }

    /**
     * Sets the today's date as currentDate.
     *
     * @return Timestamp returns the today's date.
     * @throws FwApplicationException
     */
    private Timestamp getCurrentTimestamp() throws FwApplicationException {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
            String date = dateFormatter.format(new GregorianCalendar().getTime());
            return new Timestamp(dateFormatter.parse(date).getTime());
        } catch (Exception e) {
            log.debug("Exception occured in ReferenceTableManager.getCurrentTimestamp(): " + e.toString(), e);
            throw new FwApplicationException(
                    "Exception occured in ReferenceTableManager.getCurrentTimestamp(): " + e.toString());
        }

    }

    /**
     * Converts the aRowMao into a RefTableData object and returns it as an array.
     *
     * @param aRowMap Map
     * @return RefTableData[]
     */
    private static RefTableData[] getRefData(Map aRowMap) {
        RefTableData[] refData = null;
        try {
            refData = new RefTableData[aRowMap.size()];
            int i = 0;
            Iterator it = aRowMap.keySet().iterator();
            while (it.hasNext()) {

                Object obj = it.next();
                Map res = (Map) aRowMap.get(obj);
                Map addiData = new HashMap();
                refData[i] = new RefTableData();
                Iterator iter = res.keySet().iterator();
                while (iter.hasNext()) {
                    String k = (String) iter.next();
                    if (k.equalsIgnoreCase(CODE)) {
                        refData[i].setRefrTableCode((String) res.get(k));
                    } else if (k.equalsIgnoreCase(DESC)) {
                        refData[i].setRefrTableDesc((String) res.get(k));
                    } else {
                        addiData.put(k, res.get(k));
                    }
                }
                refData[i].setRefrTableAddiData(addiData);
                i++;
            }
        } catch (Exception e) {
            log.debug("Exception in ReferenceTableManager.getRefData(Map): " + e.toString(), e);
        }
        return refData;
    }

    /**
     * COnverts the aColumnMap into RefTableData object and returns it
     *
     * @param aColumnMap Map
     * @return RefTableData
     */
    private static RefTableData getRow(Map aColumnMap) {
        RefTableData refData = new RefTableData();
        Map addiData = new HashMap();
        if (aColumnMap != null) {
            Iterator it = aColumnMap.keySet().iterator();
            while (it.hasNext()) {
                String k = (String) it.next();
                if (k.equalsIgnoreCase(CODE)) {
                    refData.setRefrTableCode((String) aColumnMap.get(k));
                } else if (k.equalsIgnoreCase(DESC)) {
                    refData.setRefrTableDesc((String) aColumnMap.get(k));
                } else {
                    addiData.put(k, aColumnMap.get(k));
                }
            }
            refData.setRefrTableAddiData(addiData);
        }
        return refData;
    }

    /**
     * Gets the RefTableData from the aRowMap and returns a map populated with the
     * RefTableData
     *
     * @param aRowMap Map
     * @return Map
     */
    public static Map getMap(Map aRowMap) {

        // Map resultMap = new HashMap()
        Map resultMap = new TreeMap();

        Iterator it = aRowMap.keySet().iterator();
        while (it.hasNext()) {

            Map resColMap = new HashMap();
            RefTableData refData = new RefTableData();

            Object obj = it.next();
            Map columnMap = (Map) aRowMap.get(obj);
            Iterator iter = columnMap.keySet().iterator();
            while (iter.hasNext()) {
                String columnName = (String) iter.next();
                if (columnName.equalsIgnoreCase(CODE)) {
                    refData.setRefrTableCode((String) columnMap.get(columnName));
                } else if (columnName.equalsIgnoreCase(DESC)) {
                    refData.setRefrTableDesc((String) columnMap.get(columnName));
                } else {
                    resColMap.put(columnName, columnMap.get(columnName));
                }
            }
            refData.setRefrTableAddiData(resColMap);
            resultMap.put(obj, refData);
        }
        return resultMap;
    }

    /**
     * gets the reference table data for the table name passed. The RT version date
     * will be considered as aRequestDate
     *
     * @param aRefTableName String
     * @param aRequestDate  java.sql.Timestamp
     * @return ReferenceTableCollection
     * @throws java.lang.Exception
     */
    public ReferenceTableCollection getReferenceTableDataCollection(String aRefTableName,
                                                                    java.sql.Timestamp aRequestDate) {
        ReferenceTableCollection refCollection = new ReferenceTableCollection();
        try {
            Map mRowMap = getReferenceTableData(aRefTableName, aRequestDate);
            Map rowMap = (TreeMap) mRowMap.get("sorteddata");
            if (rowMap != null) {
                RefTableData[] refData = getRefData(rowMap);
                refCollection.setRefTableData(refData);
            }
        } catch (Exception e) {
            log.debug("Exception-1 in ReferenceTableManager.getReferenceTableData(String , java.sql.Timestamp): "
                    + e.toString(), e);
        }
        return refCollection;
    }

    /**
     * Gets the Reference Table Value for a given TableName, Code and ColumnName
     *
     * @param aFlag         boolean
     * @param aRefTableName String
     * @param aKeyCode      String
     * @param aColumnName   String
     * @return String
     * @throws FwApplicationException
     * @throws FwException
     * @throws java.lang.Exception
     */
    public String getValueByColumn(boolean aFlag, String aRefTableName, String aKeyCode, String aColumnName)
            throws FwException, FwApplicationException {
        return getValueByColumn(aFlag, aRefTableName, aKeyCode, aColumnName, getCurrentTimestamp());
    }

    /**
     * Gets the Reference Table Value for a given TableName, Code, ColumnName and
     * req. date
     *
     * @param aFlag         boolean
     * @param aRefTableName String
     * @param aKeyCode      String
     * @param aColumnName   String
     * @param aRequestDate  java.sql.Timestamp
     * @return String
     * @throws FwApplicationException
     * @throws FwException
     * @throws java.lang.Exception
     */
    public String getValueByColumn(boolean aFlag, String aRefTableName, String aKeyCode, String aColumnName,
                                   java.sql.Timestamp aRequestDate) throws FwException, FwApplicationException {
        return getValueByColumn(aRefTableName, aKeyCode, aColumnName, aRequestDate);
    }

    /**
     * Gets the Reference Table Row for the given Table Name and Description
     *
     * @param aRefTableName     String
     * @param aDescriptionValue String
     * @return RefTableData
     * @throws FwApplicationException
     * @throws Exception
     */
    public RefTableData getValueForDescription(String aRefTableName, String aDescriptionValue)
            throws FwApplicationException {
        return getValueForDescription(aRefTableName, aDescriptionValue, getCurrentTimestamp());
    }

    /**
     * Gets the Reference Table Row for the given Table Name, Description and req.
     * date
     *
     * @param aRefTableName     String
     * @param aDescriptionValue String
     * @param aRequestDate      java.sql.Timestamp
     * @return RefTableData
     * @throws java.lang.Exception
     */
    public RefTableData getValueForDescription(String aRefTableName, String aDescriptionValue,
                                               java.sql.Timestamp aRequestDate) {
        Map columnMap = null;
        try {
            columnMap = getRowForDescription(aRefTableName, aDescriptionValue, aRequestDate);
        } catch (Exception e) {
            log.debug("Exception-1 in ReferenceTableManager.getValueForDescription(String, String, Timestamp): "
                    + e.toString(), e);
        }
        RefTableData refTableData = null;
        if (columnMap != null) {
            refTableData = getRow(columnMap);
        }
        return refTableData;
    }

    /**
     * Gets the Reference Table Row for the given Table Name,Code
     *
     * @param aFlag         boolean
     * @param aRefTableName String
     * @param aKeyCode      String
     * @return RefTableData
     * @throws FwApplicationException
     * @throws java.lang.Exception
     */
    public RefTableData getValuesForCode(boolean aFlag, String aRefTableName, String aKeyCode)
            throws FwApplicationException {
        return getValuesForCode(aRefTableName, aKeyCode, getCurrentTimestamp());
    }

    /**
     * Gets the Reference Table Row for the given Table Name,Code and Date
     *
     * @param aRefTableName String
     * @param aKeyCode      String
     * @param aRequestDate  java.sql.Timestamp
     * @return RefTableData
     * @throws java.lang.Exception
     */
    public RefTableData getValuesForCode(String aRefTableName, String aKeyCode, java.sql.Timestamp aRequestDate) {
        Map columnMap = null;
        try {
            columnMap = getRowForCode(aRefTableName, aKeyCode, aRequestDate);
        } catch (Exception e) {
            log.debug(
                    "Exception-1 in ReferenceTableManager.getValuesForCode(String, String, Timestamp): " + e.toString(),
                    e);
        }
        RefTableData refTableRow = null;
        if (columnMap != null) {
            refTableRow = getRow(columnMap);
        }
        return refTableRow;
    }

    /**
     * Gets the Reference Table Rows for given TableName and Filter
     *
     * @param aFlag         boolean
     * @param aRefTableName String
     * @param aColumnName   String
     * @param aColumnValue  String
     * @return Map
     * @throws FwApplicationException
     * @throws FwException
     * @throws java.lang.Exception
     */
    public Map getValuesForColumnFilter(boolean aFlag, String aRefTableName, String aColumnName, String aColumnValue)
            throws FwApplicationException, FwException {
        return getValuesForColumnFilter(aRefTableName, aColumnName, aColumnValue, getCurrentTimestamp());
    }

    /**
     * Gets the Reference Table Rows for given TableName and Filter
     *
     * @param aRefTableName String
     * @param aColumnName   String
     * @param aColumnValue  String
     * @param aRequestDate  java.sql.Timestamp
     * @return Map
     * @throws FwApplicationException
     * @throws FwException
     * @throws java.lang.Exception
     */
    public Map getValuesForColumnFilter(String aRefTableName, String aColumnName, String aColumnValue,
                                        java.sql.Timestamp aRequestDate) throws FwException, FwApplicationException {
        Map args = new HashMap();
        args.put(aColumnName, aColumnValue);
        Map rowMap = getRowsForColumnFilter(aRefTableName, args, aRequestDate);
        // Map resMap = new HashMap()
        Map resMap = new TreeMap();
        if (rowMap != null) {
            resMap = getMap(rowMap);
        }
        return resMap;
    }

    /**
     * gets the reference table data for the table name passed. The RT version date
     * will be considered as aRequestDate.
     *
     * @param aFlag         boolean
     * @param aRefTableName String
     * @return Map
     * @throws FwApplicationException
     * @throws FwException
     * @throws java.lang.Exception
     */
    public Map getReferenceTableRow(boolean aFlag, String aRefTableName) throws FwApplicationException, FwException {
        return getReferenceTableRow(aRefTableName, getCurrentTimestamp());
    }

    /**
     * gets the reference table data for the table name passed. The RT version date
     * will be considered as aRequestDate.
     *
     * @param aRefTableName String
     * @param aRequestDate  java.sql.Timestamp
     * @return Map
     * @throws FwApplicationException
     * @throws FwException
     * @throws java.lang.Exception
     */
    public Map getReferenceTableRow(String aRefTableName, java.sql.Timestamp aRequestDate)
            throws FwException, FwApplicationException {
        Map mRowMap = getReferenceTableData(aRefTableName);
        Map rowMap = (TreeMap) mRowMap.get("sorteddata");
        // Map resMap = new HashMap()
        Map resMap = new TreeMap();
        if (rowMap != null) {
            resMap = getMap(rowMap);
        }
        return resMap;
    }

    /**
     * Gets the Reference Table Rows for the given TableName,ColumnNames and Current
     * Date This will get the values for the given column names and get the data for
     * that values (code)
     *
     * @param aRefTableName String
     * @param aColumnName   String
     * @return RefTableData[]
     * @throws FwApplicationException
     * @throws FwException
     * @throws java.lang.Exception
     */
    public RefTableData[] getRowsByColumn(String aRefTableName, String aColumnName)
            throws FwApplicationException, FwException {
        return getRowsByColumn(aRefTableName, aColumnName, getCurrentTimestamp());
    }

    /**
     * Gets the Reference Table Rows for the given TableName,ColumnNames and Current
     * Date This will get the values for the given column names and get the data for
     * that values (code)
     *
     * @param aRefTableName String
     * @param aColumnName1  String
     * @param aColumnName2  String
     * @return RefTableData[]
     * @throws FwApplicationException
     * @throws FwException
     * @throws java.lang.Exception
     */
    public RefTableData[] getRowsByColumn(String aRefTableName, String aColumnName1, String aColumnName2)
            throws FwApplicationException, FwException {
        return getRowsByColumn(aRefTableName, aColumnName1, aColumnName2, getCurrentTimestamp());
    }

    /**
     * Gets the Reference Table Rows for the given TableName,ColumnNames and
     * Req.Date This will get the values for the given column names and get the data
     * for that values (code)
     *
     * @param aRefTableName String
     * @param aColumnName   String
     * @param aRequestDate  java.sql.Timestamp
     * @return RefTableData[]
     * @throws FwApplicationException
     * @throws FwException
     * @throws java.lang.Exception
     */
    public RefTableData[] getRowsByColumn(String aRefTableName, String aColumnName, java.sql.Timestamp aRequestDate)
            throws FwException, FwApplicationException {

        String[] args = {aColumnName};
        Map rowMap = getRowsByColumnName(aRefTableName, args, aRequestDate);
        // RefTableData refData[] = getRefData(rowMap)
        return getRefData(rowMap);
    }

    /**
     * Gets the Reference Table Rows for the given TableName,ColumnNames and Req.
     * Date This will get the values for the given column names and get the data for
     * that values (code)
     *
     * @param aRefTableName String
     * @param aColumnName1  String
     * @param aColumnName2  String
     * @param aRequestDate  java.sql.Timestamp
     * @return RefTableData[]
     * @throws FwApplicationException
     * @throws FwException
     * @throws java.lang.Exception
     */
    public RefTableData[] getRowsByColumn(String aRefTableName, String aColumnName1, String aColumnName2,
                                          java.sql.Timestamp aRequestDate) throws FwException, FwApplicationException {

        String[] args = {aColumnName1, aColumnName2};
        Map rowMap = getRowsByColumnName(aRefTableName, args, aRequestDate);
        // RefTableData refData[] = getRefData(rowMap)
        return getRefData(rowMap);
    }

    /**
     * Gets the Reference Table Rows for the given TableName, Code and ColumnNames.
     * This will get the column values for the given code and get the data for that
     * values (code)
     *
     * @param aRefTableName String
     * @param aKeyCode      String
     * @param aColumnName   String
     * @return RefTableData
     * @throws FwApplicationException
     * @throws FwException
     * @throws java.lang.Exception
     */
    public RefTableData getValuesByKeyCode(String aRefTableName, String aKeyCode, String aColumnName)
            throws ApplicationException, FwException, FwApplicationException {

        RefTableData[] refData = getValuesByKeyCode(aRefTableName, aKeyCode, aColumnName, null);
        if (refData != null && refData.length > 0) {
            return refData[0];
        }
        throw new ApplicationException("Reference Table does not exists.");
    }

    /**
     * Gets the Reference Table Rows for the given TableName, Code and ColumnNames.
     * This will get the column values for the given code and get the data for that
     * values (code)
     *
     * @param aRefTableName String
     * @param aKeyCode      String
     * @param aColumnName1  String
     * @param aColumnName2  String
     * @return RefTableData[]
     * @throws FwApplicationException
     * @throws FwException
     * @throws java.lang.Exception
     */
    public RefTableData[] getValuesByKeyCode(String aRefTableName, String aKeyCode, String aColumnName1,
                                             String aColumnName2) throws FwException, FwApplicationException {
        String[] args = null;
        if (aColumnName2 == null) {
            args = new String[1];
            args[0] = aColumnName1;
        } else {
            args = new String[2];
            args[0] = aColumnName1;
            args[1] = aColumnName2;
        }

        Map rowMap = getRowsForCodeAndColumn(aRefTableName, aKeyCode, args);
        return getRefData(rowMap);
    }

    /**
     * gets the reference table data for the table name passed. The RT version date
     * will be considered as current date
     *
     * @param aFlag         boolean
     * @param aRefTableName String
     * @return ReferenceTableCollection
     * @throws FwApplicationException
     * @throws Exception
     */
    public ReferenceTableCollection getReferenceTableData(boolean aFlag, String aRefTableName)
            throws FwApplicationException {
        return getReferenceTableDataCollection(aRefTableName, getCurrentTimestamp());
    }

    @Override
    public List<String> getAllReferenceTableNames() throws FwException, FwApplicationException {
        return Collections.emptyList();
    }

}

