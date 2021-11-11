package com.deloitte.nextgen.util.xsd.schema.notices;

import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for familyAndChildData complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="familyAndChildData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Appointment_Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Appointment_Time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AuthorizationForRelease" type="{}authorizationForRelease"/>
 *         &lt;element name="Benefit_Month" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Benefit_Year" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Benefit_Month_Year" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Collateral" type="{}collateral"/>
 *         &lt;element name="FGG0015" type="{}fgg0015"/>
 *         &lt;element name="FGG6A" type="{}fgg6a"/>
 *         &lt;element name="FGG17" type="{}fgg17"/>
 *         &lt;element name="FGG18" type="{}fgg18"/>
 *         &lt;element name="FGG19" type="{}fgg19"/>
 *         &lt;element name="FGG107" type="{}fgg107"/>
 *         &lt;element name="FGG135" type="{}fgg135"/>
 *         &lt;element name="FGG191" type="{}fgg191"/>
 *         &lt;element name="FGG195" type="{}fgg195"/>
 *         &lt;element name="FGG129" type="{}fgg129"/>
 *         &lt;element name="FGG130" type="{}fgg130"/>
 *         &lt;element name="FGG139" type="{}fgg139"/>
 *         &lt;element name="FGG188" type="{}fgg188"/>
 *         &lt;element name="FGG192" type="{}fgg192"/>
 *         &lt;element name="FGG198" type="{}fgg198"/>
 *         &lt;element name="FGG199" type="{}fgg199"/>
 *         &lt;element name="FGG207" type="{}fgg207"/>
 *         &lt;element name="FGG225" type="{}fgg225"/>
 *         &lt;element name="FGG245" type="{}fgg245"/>
 *         &lt;element name="FGG269" type="{}fgg269"/>
 *         &lt;element name="FGG329" type="{}fgg329"/>
 *         &lt;element name="FGG297" type="{}fgg297"/>
 *         &lt;element name="FGG297A" type="{}fgg297a"/>
 *         &lt;element name="FGG333" type="{}fgg333"/>
 *         &lt;element name="FGG400" type="{}fgg400"/>
 *         &lt;element name="FGG486" type="{}fgg486"/>
 *         &lt;element name="FGG508M" type="{}fgg508m"/>
 *         &lt;element name="FGG517" type="{}fgg517"/>
 *         &lt;element name="FGG875" type="{}fgg875"/>
 *         &lt;element name="FGG900" type="{}fgg900"/>
 *         &lt;element name="FGG943" type="{}fgg943"/>
 *         &lt;element name="FGG958" type="{}fgg958"/>
 *         &lt;element name="FGG962" type="{}fgg962"/>
 *         &lt;element name="FGG991" type="{}fgg991"/>
 *         &lt;element name="FGG5667" type="{}fgg5667"/>
 *         &lt;element name="FGG809" type="{}fgg809"/>
 *         &lt;element name="FGGA0004" type="{}fgga0004"/>
 *         &lt;element name="FGGA0009" type="{}fgga0009"/>
 *         &lt;element name="FGGA0010" type="{}fgga0010"/>
 *         &lt;element name="FGGA0012" type="{}fgga0012"/>
 *         &lt;element name="FGGA0013" type="{}fgga0013"/>
 *         &lt;element name="FGGA0014" type="{}fgga0014"/>
 *         &lt;element name="FGGA0016" type="{}fgga0016"/>
 *         &lt;element name="FGGA0020" type="{}fgga0020"/>
 *         &lt;element name="FGGA0022" type="{}fgga0022"/>
 *         &lt;element name="FGGA0024" type="{}fgga0024"/>
 *         &lt;element name="FGGA0026" type="{}fgga0026"/>
 *         &lt;element name="FGGA0032" type="{}fgga0032"/>
 *         &lt;element name="FGGA0053" type="{}fgga0053"/>
 *         &lt;element name="FGGA0054" type="{}fgga0054"/>
 *         &lt;element name="FGGA0055" type="{}fgga0055"/>
 *         &lt;element name="NGG0019" type="{}ngg0019"/>
 *         &lt;element name="NGG060" type="{}ngg060"/>
 *         &lt;element name="NGG0011" type="{}ngg0011"/>
 *         &lt;element name="NGG0012" type="{}ngg0012"/>
 *         &lt;element name="NGG0013" type="{}ngg0013"/>
 *         &lt;element name="NGG0014" type="{}ngg0014"/>
 *         &lt;element name="NGG0053" type="{}ngg0053"/>
 *         &lt;element name="NGG0057" type="{}ngg0057"/>
 *         &lt;element name="NGG0059" type="{}ngg0059"/>
 *         &lt;element name="NGG0027" type="{}ngg0027"/>
 *         &lt;element name="NGG0016" type="{}ngg0016"/>
 *         &lt;element name="NGG0033" type="{}ngg0033"/>
 *         &lt;element name="NGG0037" type="{}ngg0037"/>
 *         &lt;element name="NGG0045" type="{}ngg0045"/>
 *         &lt;element name="NGGA826" type="{}ngga826"/>
 *         &lt;element name="NGGA826A" type="{}ngga826a"/>
 *         &lt;element name="NGGA0048" type="{}ngga0048"/>
 *         &lt;element name="NGGA0049" type="{}ngga0049"/>
 *         &lt;element name="NGGA0050" type="{}ngga0050"/>
 *         &lt;element name="NGGA0051" type="{}ngga0051"/>
 *         &lt;element name="NGGA0052" type="{}ngga0052"/>
 *         &lt;element name="NGGA0054" type="{}ngga0054"/>
 *         &lt;element name="NGGA0055" type="{}ngga0055"/>
 *         &lt;element name="NGGA0002" type="{}ngga0002"/>
 *         &lt;element name="NGGA0023" type="{}ngga0023"/>
 *         &lt;element name="NGGA0033" type="{}ngga0033"/>
 *         &lt;element name="NGGA0034" type="{}ngga0034"/>
 *         &lt;element name="NGGA0030" type="{}ngga0030"/>
 *         &lt;element name="NGGA0046" type="{}ngga0046"/>
 *         &lt;element name="NGGG52" type="{}nggg52"/>
 *         &lt;element name="NGGT0048" type="{}nggt0048"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "familyAndChildData", propOrder = {
        "appointmentDate",
        "appointmentTime",
        "authorizationForRelease",
        "benefitMonth",
        "benefitYear",
        "benefitMonthYear",
        "collateral",
        "fgg0015",
        "fgg6A",
        "fgg17",
        "fgg18",
        "fgga0014",
        /*"fgg19",
        "fgg107",
        "fgg135",
        "fgg191",
        "fgg195",
        "fgg129",
        "fgg130",
        "fgg139",
        "fgg188",
        "fgg192",
        "fgg198",
        "fgg199",
        "fgg207",
        "fgg225",
        "fgg245",
        "fgg269",
        "fgg329",
        "fgg297",
        "fgg297A",
        "fgg333",
        "fgg400",
        "fgg486",
        "fgg508M",
        "fgg517",
        "fgg875",
        "fgg900",
        "fgg943",
        "fgg958",
        "fgg962",
        "fgg991",
        "fgg5667",
        "fgg809",
        "fgga0004",
        "fgga0009",
        "fgga0010",
        "fgga0012",
        "fgga0013",
        "fgga0016",
        "fgga0020",
        "fgga0022",
        "fgga0024",
        "fgga0026",
        "fgga0032",
        "fgga0053",
        "fgga0054",
        "fgga0055",
        "ngg0019",
        "ngg060",
        "ngg0011",
        "ngg0012",
        "ngg0013",
        "ngg0014",
        "ngg0053",
        "ngg0057",
        "ngg0059",
        "ngg0027",
        "ngg0016",
        "ngg0033",
        "ngg0037",
        "ngg0045",
        "ngga826",
        "ngga826A",
        "ngga0048",
        "ngga0049",
        "ngga0050",
        "ngga0051",
        "ngga0052",
        "ngga0054",
        "ngga0055",
        "ngga0002",
        "ngga0023",
        "ngga0033",*/
        "ngga0034"
        /*,
        "ngga0030",
        "ngga0046",
        "nggg52",
        "nggt0048"*/
})

@Data
public class FamilyAndChildData {
    @XmlElement(name = "Appointment_Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar appointmentDate;
    @XmlElement(name = "Appointment_Time", required = true)
    protected String appointmentTime;
    @XmlElement(name = "AuthorizationForRelease", required = true)
    protected AuthorizationForRelease authorizationForRelease;
    @XmlElement(name = "Benefit_Month", required = true)
    protected String benefitMonth;
    @XmlElement(name = "Benefit_Year")
    protected int benefitYear;
    @XmlElement(name = "Benefit_Month_Year", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar benefitMonthYear;
    @XmlElement(name = "Collateral", required = true)
    protected Collateral collateral;
    @XmlElement(name = "FGG0015", required = true)
    protected Fgg0015 fgg0015;
    @XmlElement(name = "FGGA0014", required = true)
    protected Fgga0014 fgga0014;
    @XmlElement(name = "FGG6A", required = true)
    protected Fgg6A fgg6A;
    @XmlElement(name = "FGG17", required = true)
    protected Fgg17 fgg17;
    @XmlElement(name = "FGG18", required = true)
    protected Fgg18 fgg18;
   /* @XmlElement(name = "FGG19", required = true)
    protected Fgg19 fgg19;
    @XmlElement(name = "FGG107", required = true)
    protected Fgg107 fgg107;
    @XmlElement(name = "FGG135", required = true)
    protected Fgg135 fgg135;
    @XmlElement(name = "FGG191", required = true)
    protected Fgg191 fgg191;
    @XmlElement(name = "FGG195", required = true)
    protected Fgg195 fgg195;
    @XmlElement(name = "FGG129", required = true)
    protected Fgg129 fgg129;
    @XmlElement(name = "FGG130", required = true)
    protected Fgg130 fgg130;
    @XmlElement(name = "FGG139", required = true)
    protected Fgg139 fgg139;
    @XmlElement(name = "FGG188", required = true)
    protected Fgg188 fgg188;
    @XmlElement(name = "FGG192", required = true)
    protected Fgg192 fgg192;
    @XmlElement(name = "FGG198", required = true)
    protected Fgg198 fgg198;
    @XmlElement(name = "FGG199", required = true)
    protected Fgg199 fgg199;
    @XmlElement(name = "FGG207", required = true)
    protected Fgg207 fgg207;
    @XmlElement(name = "FGG225", required = true)
    protected Fgg225 fgg225;
    @XmlElement(name = "FGG245", required = true)
    protected Fgg245 fgg245;
    @XmlElement(name = "FGG269", required = true)
    protected Fgg269 fgg269;
    @XmlElement(name = "FGG329", required = true)
    protected Fgg329 fgg329;
    @XmlElement(name = "FGG297", required = true)
    protected Fgg297 fgg297;
    @XmlElement(name = "FGG297A", required = true)
    protected Fgg297A fgg297A;
    @XmlElement(name = "FGG333", required = true)
    protected Fgg333 fgg333;
    @XmlElement(name = "FGG400", required = true)
    protected Fgg400 fgg400;
    @XmlElement(name = "FGG486", required = true)
    protected Fgg486 fgg486;
    @XmlElement(name = "FGG508M", required = true)
    protected Fgg508M fgg508M;
    @XmlElement(name = "FGG517", required = true)
    protected Fgg517 fgg517;
    @XmlElement(name = "FGG875", required = true)
    protected Fgg875 fgg875;
    @XmlElement(name = "FGG900", required = true)
    protected Fgg900 fgg900;
    @XmlElement(name = "FGG943", required = true)
    protected Fgg943 fgg943;
    @XmlElement(name = "FGG958", required = true)
    protected Fgg958 fgg958;
    @XmlElement(name = "FGG962", required = true)
    protected Fgg962 fgg962;
    @XmlElement(name = "FGG991", required = true)
    protected Fgg991 fgg991;
    @XmlElement(name = "FGG5667", required = true)
    protected Fgg5667 fgg5667;
    @XmlElement(name = "FGG809", required = true)
    protected Fgg809 fgg809;
    @XmlElement(name = "FGGA0004", required = true)
    protected Fgga0004 fgga0004;
    @XmlElement(name = "FGGA0009", required = true)
    protected Fgga0009 fgga0009;
    @XmlElement(name = "FGGA0010", required = true)
    protected Fgga0010 fgga0010;
    @XmlElement(name = "FGGA0012", required = true)
    protected Fgga0012 fgga0012;
    @XmlElement(name = "FGGA0013", required = true)
    protected Fgga0013 fgga0013;
    @XmlElement(name = "FGGA0014", required = true)
    protected Fgga0014 fgga0014;
    @XmlElement(name = "FGGA0016", required = true)
    protected Fgga0016 fgga0016;
    @XmlElement(name = "FGGA0020", required = true)
    protected Fgga0020 fgga0020;
    @XmlElement(name = "FGGA0022", required = true)
    protected Fgga0022 fgga0022;
    @XmlElement(name = "FGGA0024", required = true)
    protected Fgga0024 fgga0024;
    @XmlElement(name = "FGGA0026", required = true)
    protected Fgga0026 fgga0026;
    @XmlElement(name = "FGGA0032", required = true)
    protected Fgga0032 fgga0032;
    @XmlElement(name = "FGGA0053", required = true)
    protected Fgga0053 fgga0053;
    @XmlElement(name = "FGGA0054", required = true)
    protected Fgga0054 fgga0054;
    @XmlElement(name = "FGGA0055", required = true)
    protected Fgga0055 fgga0055;
    @XmlElement(name = "NGG0019", required = true)
    protected Ngg0019 ngg0019;
    @XmlElement(name = "NGG060", required = true)
    protected Ngg060 ngg060;
    @XmlElement(name = "NGG0011", required = true)
    protected Ngg0011 ngg0011;
    @XmlElement(name = "NGG0012", required = true)
    protected Ngg0012 ngg0012;
    @XmlElement(name = "NGG0013", required = true)
    protected Ngg0013 ngg0013;
    @XmlElement(name = "NGG0014", required = true)
    protected Ngg0014 ngg0014;
    @XmlElement(name = "NGG0053", required = true)
    protected Ngg0053 ngg0053;
    @XmlElement(name = "NGG0057", required = true)
    protected Ngg0057 ngg0057;
    @XmlElement(name = "NGG0059", required = true)
    protected Ngg0059 ngg0059;
    @XmlElement(name = "NGG0027", required = true)
    protected Ngg0027 ngg0027;
    @XmlElement(name = "NGG0016", required = true)
    protected Ngg0016 ngg0016;
    @XmlElement(name = "NGG0033", required = true)
    protected Ngg0033 ngg0033;
    @XmlElement(name = "NGG0037", required = true)
    protected Ngg0037 ngg0037;
    @XmlElement(name = "NGG0045", required = true)
    protected Ngg0045 ngg0045;
    @XmlElement(name = "NGGA826", required = true)
    protected Ngga826 ngga826;
    @XmlElement(name = "NGGA826A", required = true)
    protected Ngga826A ngga826A;
    @XmlElement(name = "NGGA0048", required = true)
    protected Ngga0048 ngga0048;
    @XmlElement(name = "NGGA0049", required = true)
    protected Ngga0049 ngga0049;
    @XmlElement(name = "NGGA0050", required = true)
    protected Ngga0050 ngga0050;
    @XmlElement(name = "NGGA0051", required = true)
    protected Ngga0051 ngga0051;
    @XmlElement(name = "NGGA0052", required = true)
    protected Ngga0052 ngga0052;
    @XmlElement(name = "NGGA0054", required = true)
    protected Ngga0054 ngga0054;
    @XmlElement(name = "NGGA0055", required = true)
    protected Ngga0055 ngga0055;
    @XmlElement(name = "NGGA0002", required = true)
    protected Ngga0002 ngga0002;
    @XmlElement(name = "NGGA0023", required = true)
    protected Ngga0023 ngga0023;
    @XmlElement(name = "NGGA0033", required = true)
    protected Ngga0033 ngga0033;
    */
    @XmlElement(name = "NGGA0034", required = true)
    protected Ngga0034 ngga0034;
    /*
    @XmlElement(name = "NGGA0030", required = true)
    protected Ngga0030 ngga0030;
    @XmlElement(name = "NGGA0046", required = true)
    protected Ngga0046 ngga0046;
    @XmlElement(name = "NGGG52", required = true)
    protected Nggg52 nggg52;
    @XmlElement(name = "NGGT0048", required = true)
    protected Nggt0048 nggt0048;*/
}
