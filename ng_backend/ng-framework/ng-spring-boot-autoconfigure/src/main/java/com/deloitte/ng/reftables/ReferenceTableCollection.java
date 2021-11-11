package com.deloitte.ng.reftables;

import com.deloitte.ng.utils.FWUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ReferenceTableCollection is used to hold an array of reference table data
 *
 * @author mukepatel on 20/06/2020 2:36 PM
 * @project ng-spring-boot-autoconfigure
 */
@Slf4j
public class ReferenceTableCollection implements Serializable {

    /*
    private static final String EXCEPTION_MSG = "Exception occured in RefTableCollection() ";

    private static final String SORTEDDATA = "sorteddata";

    private static final long serialVersionUID = 8658518362342014126L;

    private String selectedValue;

    private String filterValue;

    private String filterColumn;*/

    private transient RefTableData[] refTableData;

    /**
     * RefTableCollection constructor comment.
     */
    public ReferenceTableCollection() {
        super();
    }

    /**
     * sets the refTableData to newRefTableData
     *
     * @param newRefTableData us.tx.state.dhs.fw.referencetables.Ref_Table_Data[]
     */
    public void setRefTableData(
            RefTableData[] newRefTableData) {
        refTableData = FWUtils.arrayCopy(newRefTableData);
    }

    /**
     * gets the RefTableData from the passed aRowMap
     *
     * @param aRowMap Map
     * @return RefTableData[]
     */
    public static RefTableData[] getRefData(Map aRowMap) {

        try {
            RefTableData[] refData = new RefTableData[aRowMap.size()];
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
                    if ("CODE".equalsIgnoreCase(k)) {
                        refData[i].setRefrTableCode((String) res.get(k));
                    } else if ("DESCRIPTION".equalsIgnoreCase(k)) {
                        refData[i].setRefrTableDesc((String) res.get(k));
                    } else {
                        addiData.put(k, res.get(k));
                    }
                }
                refData[i].setRefrTableAddiData(addiData);
                i++;
            }
            return refData;
        } catch (Exception e) {
            log.debug(e.toString(), e);
        }
        return new RefTableData[0];
    }
}
