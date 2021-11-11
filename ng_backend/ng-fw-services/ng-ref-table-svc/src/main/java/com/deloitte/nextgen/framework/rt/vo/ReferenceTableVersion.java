package com.deloitte.nextgen.framework.rt.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * The Class ReferenceTableVersion.
 */
@Data
public class ReferenceTableVersion implements Serializable {

	/** The Constant EFFENDDATE. */
	public static final String EFFENDDATE = "EFFENDDATE";

	/** The Constant EFFBEGINDATE. */
	public static final String EFFBEGINDATE = "EFFBEGDATE";

	/** The Constant VERSION. */
	public static final String VERSION = "VERSION";

	/** The Constant S_DATA. */
	public static final String S_DATA = "sorteddata";

	/** The Constant US_DATA. */
	public static final String US_DATA = "unsorteddata";

	/** The Constant DATA. */
	public static final String DATA = "data";

	/** The reference version. */
	@JsonProperty(VERSION)
	private String refVersion;

	/** The effective begin date. */
	@JsonProperty(EFFBEGINDATE)
	private String effectiveBeginDate;

	/** The effective end date. */
	@JsonProperty(EFFENDDATE)
	private String effectiveEndDate;

	/** The data. */
	@JsonProperty(DATA)
	//START - Changes made to optimize RT Data Extraction
//	private Map<String, Map<String, Map<String, String>>> refData;
	private Set<Map<String, String>> refData;
	//END - Changes made to optimize RT Data Extraction

	/**
	 * Instantiates a new reference table version.
	 *
	 * @param version the version
	 */
	public ReferenceTableVersion(String version) {
		//START - Changes made to optimize RT Data Extraction
//		Map<String, Map<String, Map<String, String>>> dataMap = new HashMap<>();
		Set<Map<String, String>> dataMap = new HashSet<Map<String, String>>();
//		dataMap.put(S_DATA, new TreeMap<>());
//		dataMap.put(US_DATA, new HashMap<>());
		//END - Changes made to optimize RT Data Extraction
		this.refData = dataMap;
		this.refVersion = version;
	}

}
