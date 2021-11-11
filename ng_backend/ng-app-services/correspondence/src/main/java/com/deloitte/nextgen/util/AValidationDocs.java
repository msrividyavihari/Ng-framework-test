package com.deloitte.nextgen.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AValidationDocs {
    /**
     * Field interestListAddresseeType.
     */
    public static final Map<String, String> interestListAddresseeType = new HashMap<>();
    /**
     * Field ispToas.
     */
    public static final List<String> ispToas = new ArrayList<>();
    /**
     * Field copayToas.
     */
    public static final List<String> copayToas = new ArrayList<>();
    /**
     * Field stateSchools.
     */
    public static final List<String> stateSchools = new ArrayList<>();
    /**
     * Field meInFacility.
     */
    public static final List<String> meInFacility = new ArrayList<>();
    /**
     * Field ccadTOAList.
     */
    public static final List<String> ccadTOAList = new ArrayList<>();
    /**
     * Field waiverTOAList.
     */
    public static final List<String> waiverTOAList = new ArrayList<>();
    /**
     * Field pageIdDocIdMap.
     */
    public static final Map<String, String> pageIdDocIdMap = new HashMap<>();

    /**
     * Field medicallyNeedyToaList.
     */
    public static final List<String> medicallyNeedyToaList = new ArrayList<>();
    /**
     * Field texasWorksProgramsList.
     */
    public static final List<String> texasWorksProgramsList = new ArrayList<>();
    /**
     * Field spendDownTps.
     */
    public static final List<String> spendDownTps = new ArrayList<>();
    /**
     * Field suppressList.
     */
    public static final List<String> suppressList = new ArrayList<>();
    /**
     * Field coverSheetNoticeList.
     */
    public static final List<String> coverSheetNoticeList = new ArrayList<>();

    public static final List<String> allowedContentType = new ArrayList<>();

    public static final List<String> mailOnlyNoticeList = new ArrayList<>();

    public static final List<String> individualNoticeList = new ArrayList<>();

    public static final List<String> noSpanishFormList = new ArrayList<>();

    public static final List<String> noManualFieldFormsList = new ArrayList<>();

    public static final List<String> deniedReasonList = new ArrayList<>();

    public static final List<String> indvDeniedReasonList = new ArrayList<>();

    public static final List<String> unableToProcessReasonList = new ArrayList<>();

    public static final List<String> withdrawnReasonList = new ArrayList<>();

    static{
        deniedReasonList.add(CoConstants.EL1005);
        deniedReasonList.add(CoConstants.EL1008);
        deniedReasonList.add(CoConstants.EL1055);
        deniedReasonList.add(CoConstants.EL3006);
        deniedReasonList.add(CoConstants.EL9013);
        deniedReasonList.add(CoConstants.EL9028);
        deniedReasonList.add(CoConstants.EL9051);
        deniedReasonList.add(CoConstants.EL9104);
    }

    static{
        indvDeniedReasonList.add(CoConstants.EL1067);
        indvDeniedReasonList.add(CoConstants.EL9002);
    }

    static{
        unableToProcessReasonList.add(CoConstants.EL0039);
        unableToProcessReasonList.add(CoConstants.EL1025);
        unableToProcessReasonList.add(CoConstants.EL3016);
        unableToProcessReasonList.add(CoConstants.EL5054);
        unableToProcessReasonList.add(CoConstants.EL5056);
        unableToProcessReasonList.add(CoConstants.EL6174);
        unableToProcessReasonList.add(CoConstants.EL9014);
        unableToProcessReasonList.add(CoConstants.EL9032);
        unableToProcessReasonList.add(CoConstants.EL9034);
        unableToProcessReasonList.add(CoConstants.EL9035);
    }

    static{
        withdrawnReasonList.add(CoConstants.EL5023);
    }


    static{
        noSpanishFormList.add(CoConstants.FGGA0009);
        noSpanishFormList.add(CoConstants.FGGA0010);
        noSpanishFormList.add(CoConstants.FGGA0053);
        noSpanishFormList.add(CoConstants.FGGA0054);
    }

    static{
        mailOnlyNoticeList.add("NGGA0046");
        mailOnlyNoticeList.add("NGGA0047");
        mailOnlyNoticeList.add("NGG1056");
        mailOnlyNoticeList.add("NGG0057");
        mailOnlyNoticeList.add(CoConstants.FGGA0055);
    }

    static{
        individualNoticeList.add("NGG1056");
        individualNoticeList.add("NGG0057");
        individualNoticeList.add(CoConstants.NGG0011);
        individualNoticeList.add(CoConstants.NGG0012);
        individualNoticeList.add(CoConstants.NGG0013);
        individualNoticeList.add(CoConstants.NGG0037);
    }

    static{
        allowedContentType.add("image/jpeg");
        allowedContentType.add("image/x-ms-bmp");
        allowedContentType.add("image/gif");
        allowedContentType.add("image/tiff");
        allowedContentType.add("application/pdf");
        allowedContentType.add("image/png");
    }

    static{
        suppressList.add("TP49");
        suppressList.add("TP62");
        suppressList.add("TA23");
        suppressList.add("TP64");
        suppressList.add("TP86");
    }

    static{
        //GAIES MAPPING
        pageIdDocIdMap.put("FGG104","CO556");
        pageIdDocIdMap.put("FGG106","CO557");
        pageIdDocIdMap.put("FGG107","CO558");
        pageIdDocIdMap.put("FGG111C","CO559");
        pageIdDocIdMap.put("FGG126","CO560");
        pageIdDocIdMap.put("FGG129","CO561");
        pageIdDocIdMap.put("FGG130","CO562");
        pageIdDocIdMap.put("FGG135","CO563");
        pageIdDocIdMap.put("FGG138","CO564");
        pageIdDocIdMap.put("FGG139","CO582");
        pageIdDocIdMap.put("FGG160","CO565");
        pageIdDocIdMap.put("FGG162","CO566");
        pageIdDocIdMap.put("FGG17","CO567");
        pageIdDocIdMap.put("FGG173A","CO568");
        pageIdDocIdMap.put("FGG18","CO569");
        pageIdDocIdMap.put("FGG188","CO570");
        pageIdDocIdMap.put("FGG19","CO571");
        pageIdDocIdMap.put("FGG190","CO572");
        pageIdDocIdMap.put("FGG191","CO551");
        pageIdDocIdMap.put("FGG193","CO573");
        pageIdDocIdMap.put("FGG194","CO574");
        pageIdDocIdMap.put("FGG195","CO575");
        pageIdDocIdMap.put("FGG196","CO576");
        pageIdDocIdMap.put("FGG196A","CO577");
        pageIdDocIdMap.put("FGG198","CO578");
        pageIdDocIdMap.put("FGG199","CO579");
        pageIdDocIdMap.put("FGG200","CO580");
        pageIdDocIdMap.put("FGG216","CO581");
        pageIdDocIdMap.put("FGG217","CO584");
        pageIdDocIdMap.put("FGG219","CO585");
        pageIdDocIdMap.put("FGG224","CO586");
        pageIdDocIdMap.put("FGG225","CO587");
        pageIdDocIdMap.put("FGG245","CO588");
        pageIdDocIdMap.put("FGG269","CO589");
        pageIdDocIdMap.put("FGG297","CO590");
        pageIdDocIdMap.put("FGG297A","CO591");
        pageIdDocIdMap.put("FGG297M","CO592");
        pageIdDocIdMap.put("FGG298","CO593");
        pageIdDocIdMap.put("FGG298Q","CO594");
        pageIdDocIdMap.put("FGG299","CO595");
        pageIdDocIdMap.put("FGG315","CO596");
        pageIdDocIdMap.put("FGG329","CO597");
        pageIdDocIdMap.put("FGG332","CO598");
        pageIdDocIdMap.put("FGG333","CO599");
        pageIdDocIdMap.put("FGG335","CO600");
        pageIdDocIdMap.put("FGG339","CO601");
        pageIdDocIdMap.put("FGG354","CO602");
        pageIdDocIdMap.put("FGG482","CO603");
        pageIdDocIdMap.put("FGG486","CO604");
        pageIdDocIdMap.put("FGG489","CO605");
        pageIdDocIdMap.put("FGG493","CO606");
        pageIdDocIdMap.put("FGG494","CO607");
        pageIdDocIdMap.put("FGG495","CO555");
        pageIdDocIdMap.put("FGG504","CO608");
        pageIdDocIdMap.put("FGG505","CO609");
        pageIdDocIdMap.put("FGG506","CO610");
        pageIdDocIdMap.put("FGG508A","CO611");
        pageIdDocIdMap.put("FGG508M","CO612");
        pageIdDocIdMap.put("FGG514","CO583");
        pageIdDocIdMap.put("FGG515","CO613");
        pageIdDocIdMap.put("FGG516","CO614");
        pageIdDocIdMap.put("FGG517","CO615");
        pageIdDocIdMap.put("FGG526","CO616");
        pageIdDocIdMap.put("FGG5459","CO617");
        pageIdDocIdMap.put("FGG551","CO618");
        pageIdDocIdMap.put("FGG5667","CO619");
        pageIdDocIdMap.put("FGG60","CO620");
        pageIdDocIdMap.put("FGG61","CO621");
        pageIdDocIdMap.put("FGG64","CO622");
        pageIdDocIdMap.put("FGG6A","CO623");
        pageIdDocIdMap.put("FGG700","CO624");
        pageIdDocIdMap.put("FGG704","CO625");
        pageIdDocIdMap.put("FGG705","CO626");
        pageIdDocIdMap.put("FGG706","CO627");
        pageIdDocIdMap.put("FGG785","CO550");
        pageIdDocIdMap.put("FGG786","CO628");
        pageIdDocIdMap.put("FGG806","CO629");
        pageIdDocIdMap.put("FGG809","CO630");
        pageIdDocIdMap.put("FGG820","CO631");
        pageIdDocIdMap.put("FGG821","CO632");
        pageIdDocIdMap.put("FGG830","CO633");
        pageIdDocIdMap.put("FGG840","CO634");
        pageIdDocIdMap.put("FGG943","CO635");
        pageIdDocIdMap.put("FGG94A","CO636");
        pageIdDocIdMap.put("FGG94AA","CO637");
        pageIdDocIdMap.put("FGG94AB","CO638");
        pageIdDocIdMap.put("FGG94AC","CO639");
        pageIdDocIdMap.put("FGG958","CO640");
        pageIdDocIdMap.put("FGG970","CO641");
        pageIdDocIdMap.put("FGG985","CO642");
        pageIdDocIdMap.put("FGG986","CO643");
        pageIdDocIdMap.put("FGG991","CO644");
        pageIdDocIdMap.put("FGG992","CO645");
        pageIdDocIdMap.put("FGGA0001","CO646");
        pageIdDocIdMap.put("FGGA0003","CO647");
        pageIdDocIdMap.put("FGGA0004","CO648");
        pageIdDocIdMap.put("FGGA0005","CO649");
        pageIdDocIdMap.put("FGGA0006","CO650");
        pageIdDocIdMap.put("FGGA0008","CO651");
        pageIdDocIdMap.put("FGGA0009","CO652");
        pageIdDocIdMap.put("FGGA0010","CO653");
        pageIdDocIdMap.put("FGGA0011","CO654");
        pageIdDocIdMap.put("FGGA0012","CO655");
        pageIdDocIdMap.put("FGGA0013","CO656");
        pageIdDocIdMap.put("FGGA0014","CO657");
        pageIdDocIdMap.put("FGGA0015","CO658");
        pageIdDocIdMap.put("FGGA0016","CO553");
        pageIdDocIdMap.put("FGGA0017","CO659");
        pageIdDocIdMap.put("FGGA0018","CO660");
        pageIdDocIdMap.put("FGGA0019","CO661");
        pageIdDocIdMap.put("FGGA0020","CO662");
        pageIdDocIdMap.put("FGGA0022","CO663");
        pageIdDocIdMap.put("FGGA0024","CO664");
        pageIdDocIdMap.put("FGGA0026","CO665");
        pageIdDocIdMap.put("FGGA0027","CO666");
        pageIdDocIdMap.put("FGGA0028","CO667");
        pageIdDocIdMap.put("FGGA0029","CO668");
        pageIdDocIdMap.put("FGGA0031","CO669");
        pageIdDocIdMap.put("FGGA0032","CO670");
        pageIdDocIdMap.put("FGGA0035","CO671");
        pageIdDocIdMap.put("FGGA0036","CO554");
        pageIdDocIdMap.put("FGGA0037","CO552");
        pageIdDocIdMap.put("FGGA0038","CO672");
        pageIdDocIdMap.put("FGGA0039","CO673");
        pageIdDocIdMap.put("FGGA0040","CO674");
        pageIdDocIdMap.put("FGGA0041","CO675");
        pageIdDocIdMap.put("FGGA0042","CO676");
        pageIdDocIdMap.put("FGGA0043","CO677");
        pageIdDocIdMap.put("FGGA0044","CO678");
        pageIdDocIdMap.put("FGGA0045","CO679");
        pageIdDocIdMap.put("FGGA0050","CO680");
        pageIdDocIdMap.put("FGGA0051","CO681");
        pageIdDocIdMap.put("FGGA0052","CO682");
        pageIdDocIdMap.put("FGGG03","CO683");
        pageIdDocIdMap.put("FGGG06","CO684");
        pageIdDocIdMap.put("FGGG18E","CO685");
        pageIdDocIdMap.put("FGGG23","CO686");
        pageIdDocIdMap.put("FGGG23A","CO687");
        pageIdDocIdMap.put("FGGG30","CO688");
        pageIdDocIdMap.put("FGGG56","CO689");
        pageIdDocIdMap.put("FGGG61","CO690");
        pageIdDocIdMap.put("FGGG90","CO691");
        pageIdDocIdMap.put("FGGG99","CO692");
        pageIdDocIdMap.put("FGGP04I","CO693");
        pageIdDocIdMap.put("FGGG04I","CO694");
        pageIdDocIdMap.put("FGG5460","CO695");
        pageIdDocIdMap.put("FGG240","CO696");
        pageIdDocIdMap.put("FGG400","CO697");
        pageIdDocIdMap.put("FGG875","CO698");
        pageIdDocIdMap.put("FGG962","CO962");
        pageIdDocIdMap.put("FGG218","CO699");
        pageIdDocIdMap.put("FGG513","CO513");
        pageIdDocIdMap.put("FGG823","CO823");
        pageIdDocIdMap.put("FGGA0054","CO701");
        pageIdDocIdMap.put("FGGA0053","CO702");
        pageIdDocIdMap.put("FGGA0055","CO704");
        pageIdDocIdMap.put("FGG334","CO705");
        pageIdDocIdMap.put("FGG406","CO706");
        pageIdDocIdMap.put("FGG480","CO707");
        pageIdDocIdMap.put("FGG713","CO708");
        pageIdDocIdMap.put("FGG483","CO709");
        pageIdDocIdMap.put("FGG900","CO710");
        pageIdDocIdMap.put("FGG207","CO711");
        pageIdDocIdMap.put("FGG192","CO712");
    }

    static{
        spendDownTps.add("TP56");
        spendDownTps.add("TP32");
    }
    static{
        texasWorksProgramsList.add(CoConstants.PROGRAM_FS);
        texasWorksProgramsList.add(CoConstants.PROGRAM_TF);
        texasWorksProgramsList.add(CoConstants.PROGRAM_MA);
    }

    static{
        medicallyNeedyToaList.add("TP55");//Medically Needy
        medicallyNeedyToaList.add("TP31");		//Medically Needy - Emergency
    }


    static {
        /*
         * DBMD and MDCP are addressed to Authorized Representatives
         * All other types of Assistances are Client addressed.
         */
        interestListAddresseeType.put("TP67", "A");
        interestListAddresseeType.put("TP64", "A");
    }

    static {
        /*
         * TOA's for which ISP's are created
         * so TILE is calculated for the following TOA's
         */
        ispToas.add("TP05");
        ispToas.add("TP62");
        ispToas.add("TP63");
        ispToas.add("TP64");
        ispToas.add("TP67");
    }
    static {
        copayToas.add("TA10"); // ME toa
        copayToas.add("TA12"); // ME toa
        copayToas.add("TA15"); // ME toa
        copayToas.add("TA16"); // ME toa
        copayToas.add("TA17"); // ME toa
        copayToas.add("TP10"); // ME toa
        copayToas.add("TP12"); // ME toa
        copayToas.add("TP15"); // ME toa
        copayToas.add("TP16"); // ME toa
        copayToas.add("TP17"); // ME toa
        copayToas.add("TP51"); // ME toa
        copayToas.add("TP68");
        copayToas.add("TP73"); // CC toa
        copayToas.add("TP82"); // CC toa
    }
    static {
        /*
         * Facility could be IMD, IMH, NF, ICFMR
         * Each of these have a Copay Amt to be paid
         * to the facility in which the client is
         * residing.
         * The ArrayList copayToas must be having everything
         * if not EDBC is not building for that TOA ie) the entry
         * in meInFacility for that TOA is redundant.
         * This list is created by me from Cindy's excel.
         * Correspondence Workgroup decisions: taken decision to NOT print text for any toas
         * 12/14/2004 1-2pm @114 Conf CP
         */
        meInFacility.add("TA02");
        meInFacility.add("TA03");
        meInFacility.add("TA05");
        meInFacility.add("TA06");
        meInFacility.add("TA07");
        meInFacility.add("TA09");
        meInFacility.add("TA12");
        meInFacility.add("TA15");
        meInFacility.add("TA16");
        meInFacility.add("TA17");
        meInFacility.add("TP15");
        meInFacility.add("TP16");
        meInFacility.add("TP17");
        meInFacility.add("TP38");
        meInFacility.add("TP39");
        meInFacility.add("TP46");
        meInFacility.add("TP51");
    }
    static {
        /*
         * The statement "Any Medical services outside the facility
         * will not be paid by this department"
         * Correspondence Workgroup decisions: taken decision to print text for only the following toas
         * 12/14/2004 1-2pm @114 Conf CP
         */
        stateSchools.add("TA03");
        stateSchools.add("TA05");
        stateSchools.add("TA07");
        stateSchools.add("TA09");
        stateSchools.add("TA16");
        stateSchools.add("TP16");
        stateSchools.add("TP39");
        stateSchools.add("TP46");
    }
    /*
     * add ccad toa's into the ccadList
     */
    static {
        ccadTOAList.add("TP73");
        ccadTOAList.add("TP74");
        ccadTOAList.add("TP75");
        ccadTOAList.add("TP76");
        ccadTOAList.add("TP77");
        ccadTOAList.add("TP78");
        ccadTOAList.add("TP79");
        ccadTOAList.add("TA11");
        ccadTOAList.add("TP80");
        ccadTOAList.add("TP81");
        ccadTOAList.add("TP82");
        ccadTOAList.add("TP83");
        ccadTOAList.add("TP84");
        ccadTOAList.add("TP85");
    }
    /*
     * cc waivers are
     */
    static {
        waiverTOAList.add("TP62");
        waiverTOAList.add("TP63");
        waiverTOAList.add("TP05");
        waiverTOAList.add("TP64");
        waiverTOAList.add("TP65");
        waiverTOAList.add("TP66");
        waiverTOAList.add("TP67");
        waiverTOAList.add("TA13");
        waiverTOAList.add("TP68");
    }

    static {
        coverSheetNoticeList.add("NGGA0033");
        coverSheetNoticeList.add("NGGG15");
        coverSheetNoticeList.add("NGGG24");
        coverSheetNoticeList.add("NGGA0002");
        coverSheetNoticeList.add("NGGA830");
        coverSheetNoticeList.add("NGGA823");
        coverSheetNoticeList.add("NGGG52");
    }
    static {
        noManualFieldFormsList.add("FGG551");
        noManualFieldFormsList.add("FGGA0003");
        noManualFieldFormsList.add("FGG5460");
        noManualFieldFormsList.add("FGG297M");
        noManualFieldFormsList.add("FGG216");
        noManualFieldFormsList.add("FGG218");
    }
}
