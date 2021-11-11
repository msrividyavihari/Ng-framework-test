import lombok.Data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The Class ReferenceTableVersionTest.
 *
 * @author rarathore on 15/09/2021 9:36 PM
 * @project ng-spring-boot-autoconfigure
 */
@Data
public class ReferenceTableVersionTest {

    /**
     * The reference version.
     */
    private String VERSION;

    /**
     * The effective begin date.
     */
    private String EFFBEGINDATE;

    /**
     * The effective end date.
     */
    private String EFFENDDATE;

    /**
     * The data.
     */

    private Set<Map<String, String>> data;

    /**
     * Instantiates a new reference table version.
     *
     * @param version the version
     */
    public ReferenceTableVersionTest(String version) {

        Set<Map<String, String>> dataMap = new HashSet<Map<String, String>>();
        this.VERSION = version;
        this.data = dataMap;
    }

    public String getEFFBEGINDATE() {
        return EFFBEGINDATE;
    }

    public void setEFFBEGINDATE(String EFFBEGINDATE) {
        this.EFFBEGINDATE = EFFBEGINDATE;
    }

    public Set<Map<String, String>> getDATA() {
        return data;
    }

    public void setDATA(Set<Map<String, String>> DATA) {
        this.data = DATA;
    }

    @Override
    public String toString() {
        return "{" +
                "VERSION=" + VERSION +
                ", EFFBEGDATE=" + EFFBEGINDATE +
                ", EFFENDDATE=" + EFFENDDATE +
                ", data=" + data +
                '}';
    }
}
