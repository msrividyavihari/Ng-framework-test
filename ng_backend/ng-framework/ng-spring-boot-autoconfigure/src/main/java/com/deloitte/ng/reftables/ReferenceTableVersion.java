package com.deloitte.ng.reftables;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Class ReferenceTableVersion.
 *
 * @author mukepatel on 20/06/2020 2:36 PM
 * @project ng-spring-boot-autoconfigure
 */
@Data
public class ReferenceTableVersion {

    /**
     * The Constant EFFENDDATE.
     */
    public static final String EFFENDDATE = "EFFENDDATE";

    /**
     * The Constant EFFBEGINDATE.
     */
    public static final String EFFBEGINDATE = "EFFBEGDATE";

    /**
     * The Constant VERSION.
     */
    public static final String VERSION = "VERSION";

    /**
     * The Constant S_DATA.
     */
    public static final String S_DATA = "sorteddata";

    /**
     * The Constant US_DATA.
     */
    public static final String US_DATA = "unsorteddata";

    /**
     * The Constant DATA.
     */
    public static final String DATA = "data";

    /**
     * The reference version.
     */
    @JsonProperty(VERSION)
    private String refVersion;

    /**
     * The effective begin date.
     */
    @JsonProperty(EFFBEGINDATE)
    private String effectiveBeginDate;

    /**
     * The effective end date.
     */
    @JsonProperty(EFFENDDATE)
    private String effectiveEndDate;

    /**
     * The data.
     */
    @JsonProperty(DATA)
    private Map<String, Map<String, Map<String, String>>> refData;

    /**
     * Instantiates a new reference table version.
     *
     * @param version the version
     */
    public ReferenceTableVersion(String version) {
        Map<String, Map<String, Map<String, String>>> dataMap = new HashMap<>();
        dataMap.put(S_DATA, new TreeMap<>());
        dataMap.put(US_DATA, new HashMap<>());
        this.refData = dataMap;
        this.refVersion = version;
    }

}
