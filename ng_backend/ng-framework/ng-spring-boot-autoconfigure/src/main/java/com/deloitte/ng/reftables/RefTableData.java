package com.deloitte.ng.reftables;

import lombok.Data;

import java.util.Map;

/**
 * This is a java bean representing a reference table
 *
 * @author mukepatel on 20/06/2020 2:36 PM
 * @project ng-spring-boot-autoconfigure
 */
@Data
public class RefTableData  {

    /**
     * Field refrTableCode.
     */
    private String refrTableCode;
    /**
     * Field refrTableDesc.
     */
    private String refrTableDesc;
    /**
     * Field refrTableRank.
     */
    private String refrTableRank;
    /**
     * Field selectedValue.
     */
    private String selectedValue;
    /**
     * Field refrTableAddiData.
     */
    private Map refrTableAddiData = new java.util.HashMap();
}