/**
 * This package is used for Provides reference table access.
 */
package com.deloitte.nextgen.framework.commons.spi;


import com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException;
import com.deloitte.nextgen.framework.commons.exceptions.FwException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


/**
 * The class <code> IReferenceTableManager </code> is Interface
 * has methods to get the Reference Table Data.BaseEntityTypeListener
 */
public interface ReferenceTable {

    /**
     * Gets the Reference Table Data for the given Table Name.
     *
     * @param aRefTableName contains the reference table name.
     * @return Map returns the get the column values for the given code
     * and get the data for that values.
     * @throws com.deloitte.nextgen.framework.commons.exceptions.FwException
     * @throws com.deloitte.nextgen.framework.commons.exceptions.FwApplicationException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    public Map getReferenceTableData(String aRefTableName) throws FwException, FwApplicationException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Data for the given Table Name and Date.
     *
     * @param aRefTableName contains the reference table name.
     * @param aRequestDate  contains the requested date for the table.
     * @return Map returns the get the column values for the given code
     * and get the data for that values.
     * @throws FwException
     * @throws FwApplicationException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    public Map getReferenceTableData(String aRefTableName, Timestamp aRequestDate) throws JsonProcessingException, FwException, FwApplicationException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Row for the given Table Name and Code.
     *
     * @param aRefTableName String
     * @param aCode         String
     * @return Map returns the get the column values for the given code
     * and get the data for that values.
     * @throws FwException
     * @throws FwApplicationException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    public Map getRowForCode(String aRefTableName, String aCode) throws FwException, FwApplicationException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Row for the given Table Name,Code and Date.
     *
     * @param aRefTableName contains the reference table name.
     * @param aCode         String
     * @param aRequestDate  contains the requested date for the table.
     * @return Map returns the get the column values for the given code
     * and get the data for that values.
     * @throws FwException
     * @throws FwApplicationException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    public Map getRowForCode(String aRefTableName, String aCode, Timestamp aRequestDate) throws FwException, FwApplicationException, JsonProcessingException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Row for the given Table Name and Description.
     *
     * @param aRefTableName contains the reference table name.
     * @param aDesc         String
     * @return Map returns the get the column values for the given code
     * and get the data for that values.
     * @throws FwException
     * @throws FwApplicationException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    public Map getRowForDescription(String aRefTableName, String aDesc) throws FwException, FwApplicationException, JsonProcessingException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Row for the given Table Name,
     * Description and Date.
     *
     * @param aRefTableName contains the reference table name.
     * @param aDesc         String
     * @param aRequestDate  contains the requested date for the table.
     * @return Map returns the get the column values for the given code
     * and get the data for that values.
     * @throws FwException
     * @throws FwApplicationException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    public Map getRowForDescription(String aRefTableName, String aDesc, Timestamp aRequestDate) throws FwException, FwApplicationException, JsonProcessingException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Value for a given TableName,
     * Code and ColumnName.
     *
     * @param aRefTableName    contains the reference table name.
     * @param aCode            contains the code of the table.
     * @param aRequestedColumn contains the requested column of the table.
     * @return String returns the column of the table.
     * @throws FwException
     * @throws FwApplicationException
     */

    public String getValueByColumn(String aRefTableName, String aCode, String aRequestedColumn) throws FwException, FwApplicationException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Value for a given TableName,
     * Code and ColumnName.
     *
     * @param aRefTableName    contains the reference table name.
     * @param aCode            contains the code of the table.
     * @param aRequestedColumn contains the requested column of the table.
     * @param aRequestDate     contains the requested date for the table.
     * @return String returns the column of the table.
     * @throws FwException
     * @throws FwApplicationException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    public String getValueByColumn(String aRefTableName, String aCode, String aRequestedColumn, Timestamp aRequestDate) throws FwException, FwApplicationException, JsonProcessingException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Rows for given TableName and Filter.
     *
     * @param aRefTableName contains the reference table name.
     * @param aFilter       contains Table Rows for given TableName and Filter
     * @return Map returns the get the column values for the given code
     * and get the data for that values.
     * @throws FwException
     * @throws FwApplicationException
     */

    public Map getRowsForColumnFilter(String aRefTableName, Map aFilter) throws FwException, FwApplicationException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Rows for given TableName and Filter.
     *
     * @param aRefTableName contains the reference table name.
     * @param aFilter       contains Table Rows for given TableName and Filter
     * @param aRequestDate  contains the requested date for the table.
     * @return Map returns the get the column values for the given code
     * and get the data for that values.
     * @throws FwException
     * @throws FwApplicationException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    public Map getRowsForColumnFilter(String aRefTableName, Map aFilter, Timestamp aRequestDate) throws FwException, FwApplicationException, JsonProcessingException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Rows for the given TableName and ColumnNames.
     * This will get the values for the given column names and get the
     * data for that values (code).
     *
     * @param aRefTableName contains the reference table name
     * @param aColumnName   contains the column name of the table.
     * @return Map returns the get the column values for the given code
     * and get the data for that values.
     * @throws FwException
     * @throws FwApplicationException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    public Map getRowsByColumnName(String aRefTableName, String[] aColumnName) throws FwException, FwApplicationException, JsonProcessingException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Rows for the given TableName,
     * ColumnNames and Date.
     * This will get the values for the given column names and get
     * the data for that values (code).
     *
     * @param aRefTableName contains the reference table name
     * @param aColumnName   contains the column name of the table.
     * @param aRequestDate  contains the requested date for the table.
     * @return Map returns the get the column values for the given code
     * and get the data for that values.
     * @throws FwException
     * @throws FwApplicationException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    public Map getRowsByColumnName(String aRefTableName, String[] aColumnName, Timestamp aRequestDate) throws FwException, FwApplicationException, JsonProcessingException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Rows for the given TableName,
     * Code and ColumnNames.
     * This will get the column values for the given code and
     * get the data for that
     * values (code).
     *
     * @param aRefTableName contains the reference table name
     * @param aCode         contains the code of the reference table.
     * @param aColumnName   contains the column name of the table.
     * @return Map returns the get the column values for the given code
     * and get the data for that values.
     * @throws FwException
     * @throws FwApplicationException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    public Map getRowsForCodeAndColumn(String aRefTableName, String aCode, String[] aColumnName) throws FwException, FwApplicationException, JsonProcessingException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    /**
     * Gets the Reference Table Rows for the given TableName, Code
     * and ColumnNames.
     * This will get the column values for the given code and get the data for
     * that values (code).
     *
     * @param aRefTableName contains the reference table name
     * @param aCode         contains the code of the reference table.
     * @param aColumnName   contains the column name of the table.
     * @param aRequestDate  contains the requested date for the table.
     * @return Map returns the get the column values for the given code
     * and get the data for that values.
     * @throws FwException
     * @throws FwApplicationException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     */

    public Map getRowsForCodeAndColumn(String aRefTableName, String aCode, String[] aColumnName, Timestamp aRequestDate) throws FwException, FwApplicationException, JsonProcessingException;
    //Creation Date : (08/10/2002 2:00:12 PM)

    public List<String> getAllReferenceTableNames() throws FwException, FwApplicationException;

}