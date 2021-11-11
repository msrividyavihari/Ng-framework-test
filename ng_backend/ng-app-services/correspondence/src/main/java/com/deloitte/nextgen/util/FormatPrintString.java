package com.deloitte.nextgen.util;

public interface FormatPrintString {
    /**
     * Field NORMAL_TYPE_FACE.
     * (value is ""1"")
     */
    String NORMAL_TYPE_FACE = "1";
    /**
     * Field BOLD_TYPE_FACE.
     * (value is ""21"")
     */
    String BOLD_TYPE_FACE = "21";
    /**
     * Field MICR_TYPE_FACE.
     * (value is ""9"")
     */
    String MICR_TYPE_FACE = "9";
    /**
     * Field INVERSE_TYPE_FACE.
     * (value is ""10"")
     */
    String INVERSE_TYPE_FACE = "10";
    /**
     * Field MANUAL_TYPEFACE.
     * (value is ""11"")
     */
    String MANUAL_TYPEFACE = "11"; //COURIER ITALICS
    /**
     * Field POSTNET_TYPEFACE.
     * (value is ""12"")
     */
    String POSTNET_TYPEFACE = "12";
    /**
     * Field FONT_8_SIZE.
     * (value is ""8"")
     */
    String FONT_8_SIZE = "8";
    /**
     * Field FONT_10_SIZE.
     * (value is ""10"")
     */
    String FONT_10_SIZE = "10";
    /**
     * Field FONT_12_SIZE.
     * (value is ""12"")
     */
    String FONT_12_SIZE = "12";
    /**
     * Field FONT_14_SIZE.
     * (value is ""14"")
     */
    String FONT_14_SIZE = "14";
    /**
     * Field FONT_16_SIZE.
     * (value is ""16"")
     */
    String FONT_16_SIZE = "16";
    /**
     * Field STR_PIPE.
     * (value is ""\u00A6"")
     */
    String STR_PIPE = "\u00A6"; //"¦";
    /**
     * Field DELIMIT_MAIN.
     * (value is ""\u00A4"")
     */
    String DELIMIT_MAIN = "\u00A4"; // "¤";
    /**
     * Field ARIAL.
     * (value is ""1"")
     */
    String ARIAL = "1";
    /**
     * Field GOTHIC.
     * (value is ""2"")
     */
    String GOTHIC = "2";
    /**
     * Field HELVETICA.
     * (value is ""3"")
     */
    String HELVETICA = "3";
    /**
     * Field TIMES.
     * (value is ""4"")
     */
    String TIMES = "4";
    /**
     * Field BAR3OF9.
     * (value is ""5"")
     */
    String BAR3OF9 = "5";
    /**
     * Field POSTAL.
     * (value is ""6"")
     */
    String POSTAL = "6";
    /**
     * Field SIGN.
     * (value is ""7"")
     */
    String SIGN = "7";
    /**
     * Field ARIAL_B.
     * (value is ""21"")
     */
    String ARIAL_B = "21";
    /**
     * Field GOTHIC_B.
     * (value is ""22"")
     */
    String GOTHIC_B = "22";
    /**
     * Field HELVETICA_B.
     * (value is ""23"")
     */
    String HELVETICA_B = "23";
    /**
     * Field TIMES_B.
     * (value is ""24"")
     */
    String TIMES_B = "24";
    /**
     * Field ITALICS.
     * (value is ""8"")
     */
    String ITALICS = "8";
    /**
     * Field SECTION_SIGN.
     * (value is ""\u00A7"")
     */
    String SECTION_SIGN = "\u00A7";

    /**
     * Field dynamicTextBold.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + BOLD_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTextBold =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + BOLD_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;

    String dynamicTextnormal =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;

    String dynamicTextImpired =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_14_SIZE
                    + STR_PIPE;
    /**
     * Field tanfTextEnglish.
     * (value is ""In the State of Texas, an application for assistance is a request for help in finding a job."")
     */
    String tanfTextEnglish =
            "In the State of Texas, an application for assistance is a request for help in finding a job.";
    /**
     * Field tanfTextSpanish.
     * (value is ""En el estado de Texas, una solicitud de asistencia es una solicitud de ayuda para encontrar trabajo."")
     */
    String tanfTextSpanish =
            "En el estado de Texas, una solicitud de asistencia es una solicitud de ayuda para encontrar trabajo.";

    String secondPageString =
            "A"
                    + STR_PIPE
                    + "7.3"
                    + STR_PIPE
                    + "0.3"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE;
    /**
     * Field NodFirstLine.
     * (value is ""For free legal services, contact/ Para servicios gratis de abogado, comuníquese con :"")
     */
    String NodFirstLine =
            "For free legal services, contact/ Para servicios gratis de abogado, comuníquese con :";
    /**
     * Field legalLine.
     * (value is ""For Free Legal Services, contact: "")
     */
    String legalLine = "For Free Legal Services, contact: ";
    /**
     * Field NodLastLine.
     * (value is ""A"
     + STR_PIPE
     + "1.3"
     + STR_PIPE
     + "10.3"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + BOLD_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE
     + "Work today for a better tomorrow!                 ¡Trabaje ahora y le irá mejor mañana!"
     + DELIMIT_MAIN")
     */
    String NodLastLine =
            "A"
                    + STR_PIPE
                    + "1.3"
                    + STR_PIPE
                    + "10.3"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + BOLD_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE
                    + "Work today for a better tomorrow!                 ¡Trabaje ahora y le irá mejor mañana!"
                    + DELIMIT_MAIN;
    /**
     * Field Nod002LastLine.
     * (value is ""A"
     + STR_PIPE
     + "1"
     + STR_PIPE
     + "10.3"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + BOLD_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE
     + "NOTICE TO MEDICAID PROVIDERS: This is not authorization to provide services."
     + DELIMIT_MAIN")
     */
    String Nod002LastLine =
            "A"
                    + STR_PIPE
                    + "1"
                    + STR_PIPE
                    + "10.3"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + BOLD_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE
                    + "NOTICE TO MEDICAID PROVIDERS: This is not authorization to provide services."
                    + DELIMIT_MAIN;
    /**
     * Field dynamicFooterLine.
     * (value is ""T"
     + STR_PIPE
     + "4.3"
     + STR_PIPE
     + "9"
     + STR_PIPE
     + "3.2"
     + STR_PIPE
     + "10.7"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicFooterLine =
            "T"
                    + STR_PIPE
                    + "4.3"
                    + STR_PIPE
                    + "9"
                    + STR_PIPE
                    + "3.2"
                    + STR_PIPE
                    + "10.7"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;
    /**
     * Field nodFooter.
     * (value is ""T"
     + STR_PIPE
     + "8"
     + STR_PIPE
     + "10"
     + STR_PIPE
     + "8.2"
     + STR_PIPE
     + "10.7"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String nodFooter =
            "T"
                    + STR_PIPE
                    + "8"
                    + STR_PIPE
                    + "10"
                    + STR_PIPE
                    + "8.2"
                    + STR_PIPE
                    + "10.7"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;

    /**
     * Field dynamicTextStartLine.
     * (value is ""T"
     + STR_PIPE
     + "0.3"
     + STR_PIPE
     + "4"
     + STR_PIPE
     + "8.2"
     + STR_PIPE
     + "10.7"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTextStartLine =
            "T"
                    + STR_PIPE
                    + "0.3"
                    + STR_PIPE
                    + "4"
                    + STR_PIPE
                    + "8.2"
                    + STR_PIPE
                    + "10.7"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;

    //Initial Coordinates specified for Standard Address Label adjusted for #11 Envelopes
    /**
     * Field initialCoordinate.
     * (value is ""4"")
     */
    String initialCoordinate = "4";
    //inches from the top edge of the sheet
    /**
     * Field dynamicTextFirstLine.
     * (value is ""T"
     + STR_PIPE
     + "0.3"
     + STR_PIPE
     + initialCoordinate
     + STR_PIPE
     + "8.5"
     + STR_PIPE
     + "10.7"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTextFirstLine =
            "T"
                    + STR_PIPE
                    + "0.3"
                    + STR_PIPE
                    + initialCoordinate
                    + STR_PIPE
                    + "8.5"
                    + STR_PIPE
                    + "10.7"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;
    /**
     * Field visullayImpairedTextStartLine.
     * (value is ""T"
     + STR_PIPE
     + "0.3"
     + STR_PIPE
     + initialCoordinate
     + STR_PIPE
     + "8.2"
     + STR_PIPE
     + "10.7"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_16_SIZE
     + STR_PIPE")
     */
    String visullayImpairedTextStartLine =
            "T"
                    + STR_PIPE
                    + "0.3"
                    + STR_PIPE
                    + initialCoordinate
                    + STR_PIPE
                    + "8.2"
                    + STR_PIPE
                    + "10.7"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_16_SIZE
                    + STR_PIPE;

    //Initial Coordinates specified for Standard Address Label adjusted for Half Fold Envelopes
    /**
     * Field initialCoordinateHalfFold.
     * (value is ""5.25"")
     */
    String initialCoordinateHalfFold = "5.25";
    //inches from the top edge of the sheet
    /**
     * Field dynamicTextFirstLineHF.
     * (value is ""T"
     + STR_PIPE
     + "0.3"
     + STR_PIPE
     + initialCoordinateHalfFold
     + STR_PIPE
     + "8.5"
     + STR_PIPE
     + "10.7"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTextFirstLineHF =
            "T"
                    + STR_PIPE
                    + "0.3"
                    + STR_PIPE
                    + initialCoordinateHalfFold
                    + STR_PIPE
                    + "8.5"
                    + STR_PIPE
                    + "10.7"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;
    /**
     * Field visullayImpairedTextStartLineHF.
     * (value is ""T"
     + STR_PIPE
     + "0.3"
     + STR_PIPE
     + initialCoordinateHalfFold
     + STR_PIPE
     + "8.2"
     + STR_PIPE
     + "10.7"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_16_SIZE
     + STR_PIPE")
     */
    String visullayImpairedTextStartLineHF =
            "T"
                    + STR_PIPE
                    + "0.3"
                    + STR_PIPE
                    + initialCoordinateHalfFold
                    + STR_PIPE
                    + "8.2"
                    + STR_PIPE
                    + "10.7"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_16_SIZE
                    + STR_PIPE;
    /**
     * Field dynamicTextSectionBold.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_16_SIZE
     + STR_PIPE")
     */
    String dynamicTextSectionBold =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_16_SIZE
                    + STR_PIPE;
    // TF static line to be used dynamicaly by FormatDocument
    /**
     * Field TABLE_PATTERN_STRING.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "<CODE>"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + "<FONT>"
     + STR_PIPE")
     */
    String TABLE_PATTERN_STRING =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "<CODE>"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + "<FONT>"
                    + STR_PIPE;
    /**
     * Field TABLE_PATTERN_GENERIC_STRING.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "<CODE>"
     + STR_PIPE
     + "<TYPEFC>"
     + STR_PIPE
     + "<PTSIZE>"
     + STR_PIPE")
     */
    String TABLE_PATTERN_GENERIC_STRING =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "<CODE>"
                    + STR_PIPE
                    + "<TYPEFC>"
                    + STR_PIPE
                    + "<PTSIZE>"
                    + STR_PIPE;

    /**
     * Field dynamicTabular54Text.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "54"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTabular54Text =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "54"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;

    /**
     * Field dynamicTextnormalItalics.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + ITALICS
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTextnormalItalics =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + ITALICS
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;

    /**
     * Field blankForm.
     * (value is ""@BLANK_FORM"
     + DELIMIT_MAIN
     + "66666"
     + DELIMIT_MAIN
     + "FXX000"
     + DELIMIT_MAIN
     + "N"
     + DELIMIT_MAIN
     + "B"
     + DELIMIT_MAIN
     + "N"
     + DELIMIT_MAIN
     + "P"
     + DELIMIT_MAIN
     + "B"
     + DELIMIT_MAIN
     + "01"
     + DELIMIT_MAIN
     + "1"
     + DELIMIT_MAIN")
     */
    String blankForm =
            "@BLANK_FORM"
                    + DELIMIT_MAIN
                    + "66666"
                    + DELIMIT_MAIN
                    + "FXX000"
                    + DELIMIT_MAIN
                    + "N"
                    + DELIMIT_MAIN
                    + "B"
                    + DELIMIT_MAIN
                    + "N"
                    + DELIMIT_MAIN
                    + "P"
                    + DELIMIT_MAIN
                    + "B"
                    + DELIMIT_MAIN
                    + "01"
                    + DELIMIT_MAIN
                    + "1"
                    + DELIMIT_MAIN;
    //Empty Dynamic String
    /**
     * Field NO_BREAK_SPACE.
     * (value is ""\u00A0"")
     */
    String NO_BREAK_SPACE = "\u00A0";
    /**
     * Field emptyDynamicString.
     * (value is ""A"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE
     + "V"
     + DELIMIT_MAIN")
     */
    String emptyDynamicString =
            "A"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE
                    + "V"
                    + DELIMIT_MAIN;
    //V is used for back page -- lower end is will appear as a small dot -- else pcl is supressed if no visible text

    /**
     * Field Form1087String.
     * (value is ""The caretaker states that this child has not had a checkup and the family needs assistance to schedule a checkup and/or transportation."")
     */
    String Form1087String =
            "The caretaker states that this child has not had a checkup and the family needs assistance to schedule a checkup and/or transportation.";
    //November 5th
    /**
     * Field dynamicTabular6Text.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "6"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTabular6Text =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "6"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;
    /**
     * Field visullayImpairedTabular6Text.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "6"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_16_SIZE
     + STR_PIPE")
     */
    String visullayImpairedTabular6Text =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "6"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_16_SIZE
                    + STR_PIPE;
    //Nov 22 for Form 1039
    /**
     * Field dynamicTabular7Text.
     * (value is ""T"
     + STR_PIPE
     + "0.63"
     + STR_PIPE
     + "4.625"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "7"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_8_SIZE
     + STR_PIPE")
     */
    String dynamicTabular7Text =
            "T"
                    + STR_PIPE
                    + "0.63"
                    + STR_PIPE
                    + "4.625"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "7"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_8_SIZE
                    + STR_PIPE;
    //Nov 25 for Form 1020
    /**
     * Field dynamic1020TextFirstLine.
     * (value is ""T"
     + STR_PIPE
     + "0.3"
     + STR_PIPE
     + "1"
     + STR_PIPE
     + "8.2"
     + STR_PIPE
     + "10.7"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "63"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamic1020TextFirstLine =
            "T"
                    + STR_PIPE
                    + "0.3"
                    + STR_PIPE
                    + "1"
                    + STR_PIPE
                    + "8.2"
                    + STR_PIPE
                    + "10.7"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "63"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;

    /**
     * Field dynamicTabular53Text.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "53"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTabular53Text =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "53"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;
    /**
     * Field dynamic1020CommentsLine.
     * (value is ""T"
     + STR_PIPE
     + "0.3"
     + STR_PIPE
     + "4"
     + STR_PIPE
     + "8.2"
     + STR_PIPE
     + "10.7"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + MANUAL_TYPEFACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamic1020CommentsLine =
            "T"
                    + STR_PIPE
                    + "0.3"
                    + STR_PIPE
                    + "4"
                    + STR_PIPE
                    + "8.2"
                    + STR_PIPE
                    + "10.7"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + MANUAL_TYPEFACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;
    //Dec 5th 2002
    /**
     * Field replacementWarrantString.
     * (value is ""REPLACEMENT"")
     */
    String replacementWarrantString = "REPLACEMENT";
    //Dec 6th 2002 -- for 1020 may be used whereever English\Spannish Comments Label is required.
    /**
     * Field COMMENTS_LABEL.
     * (value is ""Comments/Comentarios : "")
     */
    String COMMENTS_LABEL = "Comments/Comentarios : ";
    /**
     * Field dynamicTabular61Text.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "61"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTabular61Text =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "61"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;
    /**
     * Field visullayImpairedTabular61Text.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "61"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_16_SIZE
     + STR_PIPE")
     */
    String visullayImpairedTabular61Text =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "61"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_16_SIZE
                    + STR_PIPE;
    // Dec 17th 2002 -- for TF0001
    /**
     * Field FormTF0001textLineForVerTable.
     * (value is ""CLIENT NAME"
     + SECTION_SIGN
     + "VERIFICATION REQUIRED"
     + SECTION_SIGN
     + "DUE DATE"")
     */
    String FormTF0001textLineForVerTable =
            "CLIENT NAME"
                    + SECTION_SIGN
                    + "VERIFICATION REQUIRED"
                    + SECTION_SIGN
                    + "DUE DATE";
    /**
     * Field dynamicTF0001TextLineForVerTable.
     * (value is ""T"
     + STR_PIPE
     + "0.3"
     + STR_PIPE
     + "1"
     + STR_PIPE
     + "8.2"
     + STR_PIPE
     + "10.7"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "31"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTF0001TextLineForVerTable =
            "T"
                    + STR_PIPE
                    + "0.3"
                    + STR_PIPE
                    + "1"
                    + STR_PIPE
                    + "8.2"
                    + STR_PIPE
                    + "10.7"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "31"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;
    /**
     * Field dynamicTabular31Text.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "31"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTabular31Text =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "31"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;
    /**
     * Field visullayImpairedTabular31Text.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "31"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_16_SIZE
     + STR_PIPE")
     */
    String visullayImpairedTabular31Text =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "31"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_16_SIZE
                    + STR_PIPE;

    /**
     * Stage 3 constants declared here
     */
    String DYNAMICTABULAR32TEXT =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "32"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_8_SIZE
                    + STR_PIPE;
    /**
     * Field DYNAMICTABULAR23TEXT.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "23"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_8_SIZE
     + STR_PIPE")
     */
    String DYNAMICTABULAR23TEXT =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "23"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_8_SIZE
                    + STR_PIPE;
    /**
     * Field DYNAMICTABULAR25TEXT.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "25"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_8_SIZE
     + STR_PIPE")
     */
    String DYNAMICTABULAR25TEXT =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "25"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_8_SIZE
                    + STR_PIPE;
    // FORM 2068 LABELS
    /**
     * Field LABEL_APPLICATION.
     * (value is ""APPLICATION FOR COMMUNITY SERVICES"")
     */
    String LABEL_APPLICATION =
            "APPLICATION FOR COMMUNITY SERVICES";
    /**
     * Field LABEL_MONITORING.
     * (value is ""MONITORING FOR COMMUNITY SERVICES"")
     */
    String LABEL_MONITORING =
            "MONITORING FOR COMMUNITY SERVICES";
    /**
     * Field LABEL_REDETERMINATION.
     * (value is ""RE-DETERMINATION FOR COMMUNITY SERVICES"")
     */
    String LABEL_REDETERMINATION =
            "RE-DETERMINATION FOR COMMUNITY SERVICES";
    /**
     * Field LABEL_APPLICATION_SP.
     * (value is ""SOLICITUD DE SERVICIOS DE ATENCIÓN EN LA COMUNIDAD"")
     */
    String LABEL_APPLICATION_SP =
            "SOLICITUD DE SERVICIOS DE ATENCIÓN EN LA COMUNIDAD";
    /**
     * Field LABEL_MONITORING_SP.
     * (value is ""VERIFICACIÓN  DE SERVICIOS"")
     */
    String LABEL_MONITORING_SP = "VERIFICACIÓN  DE SERVICIOS";
    /**
     * Field LABEL_REDETERMINATION_SP.
     * (value is ""LA CONTINUACIÓN DE SERVICIOS"")
     */
    String LABEL_REDETERMINATION_SP =
            "LA CONTINUACIÓN DE SERVICIOS";
    // Form 1235 in FXX014 Labels  Ravinder Walia Jan 23-2002
    /**
     * Field FORM1235_APPTSCHD_EN.
     * (value is ""Notice of Appointment"")
     */
    String FORM1235_APPTSCHD_EN = "Notice of Appointment";
    /**
     * Field FORM1235_APPTSCHD_SP.
     * (value is ""Aviso de cita "")
     */
    String FORM1235_APPTSCHD_SP = "Aviso de cita ";
    /**
     * Field FORM1235_APPTMISS_EN.
     * (value is ""Notice of Appointment or Delay"")
     */
    String FORM1235_APPTMISS_EN =
            "Notice of Appointment or Delay";
    /**
     * Field FORM1235_APPTMISS_SP.
     * (value is ""Aviso de cita o De Retraso"")
     */
    String FORM1235_APPTMISS_SP = "Aviso de cita o De Retraso";
    //Jan 28th 2003 - for 1020
    /**
     * Field dynamicTabular63Text.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "63"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTabular63Text =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "63"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;

    /**
     * Field labelCaseSpecialistEN.
     * (value is ""Case Specialist"")
     */
    String labelCaseSpecialistEN = "Case Specialist";
    /**
     * Field labelCaseSpecialistSP.
     * (value is ""Especialista de caso"")
     */
    String labelCaseSpecialistSP = "Especialista de caso";
    /**
     * Field caseLabelReplacedTextEN.
     * (value is ""Advisor"")
     */
    String caseLabelReplacedTextEN = "Advisor";
    /**
     * Field caseLabelReplaceeTextSP.
     * (value is ""Consejero"")
     */
    String caseLabelReplaceeTextSP = "Consejero";
    //Added for Stage 3
    /**
     * Field FONT_6_SIZE.
     * (value is ""6"")
     */
    String FONT_6_SIZE = "6";
    // FORM DBMD3/CLASS Reason Codes
    /**
     * Field STR_COMMA.
     * (value is "","")
     */
    String STR_COMMA = ",";
    /**
     * Field dbReasonCodeList.
     * (value is ""'DB0001'"
     + STR_COMMA
     + "'DB0002'"
     + STR_COMMA
     + "'DB0003'"
     + STR_COMMA
     + "'DB0004'"
     + STR_COMMA
     + "'DB0005'"
     + STR_COMMA
     + "'DB0006'"
     + STR_COMMA
     + "'DB0007'"
     + STR_COMMA
     + "'DB0008'"
     + STR_COMMA
     + "'DB0009'"
     + STR_COMMA
     + "'DB0010'"")
     */
    String dbReasonCodeList =
            "'DB0001'"
                    + STR_COMMA
                    + "'DB0002'"
                    + STR_COMMA
                    + "'DB0003'"
                    + STR_COMMA
                    + "'DB0004'"
                    + STR_COMMA
                    + "'DB0005'"
                    + STR_COMMA
                    + "'DB0006'"
                    + STR_COMMA
                    + "'DB0007'"
                    + STR_COMMA
                    + "'DB0008'"
                    + STR_COMMA
                    + "'DB0009'"
                    + STR_COMMA
                    + "'DB0010'";
    /**
     * Field classReasonCodeList.
     * (value is ""'CL0001'"
     + STR_COMMA
     + "'CL0002'"
     + STR_COMMA
     + "'CL0003'"
     + STR_COMMA
     + "'CL0004'"
     + STR_COMMA
     + "'CL0005'"
     + STR_COMMA
     + "'CL0006'"
     + STR_COMMA
     + "'CL0007'"
     + STR_COMMA
     + "'CL0008'"
     + STR_COMMA
     + "'CL0009'"
     + STR_COMMA
     + "'CL0010'"
     + STR_COMMA
     + "'CL0011'"
     + STR_COMMA
     + "'CL0012'"
     + STR_COMMA
     + "'CL0013'"")
     */
    String classReasonCodeList =
            "'CL0001'"
                    + STR_COMMA
                    + "'CL0002'"
                    + STR_COMMA
                    + "'CL0003'"
                    + STR_COMMA
                    + "'CL0004'"
                    + STR_COMMA
                    + "'CL0005'"
                    + STR_COMMA
                    + "'CL0006'"
                    + STR_COMMA
                    + "'CL0007'"
                    + STR_COMMA
                    + "'CL0008'"
                    + STR_COMMA
                    + "'CL0009'"
                    + STR_COMMA
                    + "'CL0010'"
                    + STR_COMMA
                    + "'CL0011'"
                    + STR_COMMA
                    + "'CL0012'"
                    + STR_COMMA
                    + "'CL0013'";
    // 1200 CCB
    //String DYNAMICTABULAR39TEXT = "T" + STR_PIPE +   "0" + STR_PIPE + "11" + STR_PIPE +   "0" + STR_PIPE +  "11" + STR_PIPE + "0" + STR_PIPE + "23" + STR_PIPE + NORMAL_TYPE_FACE + STR_PIPE + FONT_8_SIZE + STR_PIPE;

    //to be used in documents that require to mark checkboxes
    /**
     * Field CHECKBOX_MARK.
     * (value is ""X"")
     */
    String CHECKBOX_MARK = "X";

    //Code for Home Visits set in SH_APPOINTMENTS -- used in 1830
    /**
     * Field HOME_VISIT_CD.
     * (value is ""H"")
     */
    String HOME_VISIT_CD = "H";

    //Home Visits Constant -- in case the address for Home Visit is not found -- used in 1830
    /**
     * Field HOME_VISIT.
     * (value is ""Home Visit"")
     */
    String HOME_VISIT = "Home Visit";

    //Code for Phone Interview set in SH_APPOINTMENTS -- used in 1830
    /**
     * Field PHONE_INTERVIEW_CD.
     * (value is ""P"")
     */
    String PHONE_INTERVIEW_CD = "P";

    //Stage -3 ProviderList
    /**
     * Field dynamicTabular20Text.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "20"
     + STR_PIPE
     + BOLD_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTabular20Text =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "20"
                    + STR_PIPE
                    + BOLD_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;
    /**
     * Field dynamicTabular30Text.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "30"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String dynamicTabular30Text =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "30"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;

    /**
     * Field providerSheetLine.
     * (value is ""@PROVLIST_1_EN_1"
     + DELIMIT_MAIN
     + "1"
     + DELIMIT_MAIN
     + "FXX000"
     + DELIMIT_MAIN
     + "N"
     + DELIMIT_MAIN
     + "F"
     + DELIMIT_MAIN
     + "N"
     + DELIMIT_MAIN
     + "P"
     + DELIMIT_MAIN
     + "F"
     + DELIMIT_MAIN
     + "01"
     + DELIMIT_MAIN
     + "1"
     + DELIMIT_MAIN")
     */
    String providerSheetLine =
            "@PROVLIST_1_EN_1"
                    + DELIMIT_MAIN
                    + "1"
                    + DELIMIT_MAIN
                    + "FXX000"
                    + DELIMIT_MAIN
                    + "N"
                    + DELIMIT_MAIN
                    + "F"
                    + DELIMIT_MAIN
                    + "N"
                    + DELIMIT_MAIN
                    + "P"
                    + DELIMIT_MAIN
                    + "F"
                    + DELIMIT_MAIN
                    + "01"
                    + DELIMIT_MAIN
                    + "1"
                    + DELIMIT_MAIN;
    // Version Number is included here
    /**
     * Field providerSheetStLine.
     * (value is ""______________________________________________________________________________________________________"")
     */
    String providerSheetStLine =
            "______________________________________________________________________________________________________";
    /**
     * Field labelProviderSheetMgr.
     * (value is ""Contact Person:"")
     */
    String labelProviderSheetMgr = "Contact Person:";
    /**
     * Field labelProviderSheetPh.
     * (value is ""Ph: "")
     */
    String labelProviderSheetPh = "Ph: ";
    /**
     * Field labelProviderSheetFax.
     * (value is ""Fax: "")
     */
    String labelProviderSheetFax = "Fax: ";
    /**
     * Field labelProviderSheetContract.
     * (value is ""Contract# "")
     */
    String labelProviderSheetContract = "Contract# ";

    /**
     * Field Form1020textLine1SP.
     * (value is ""CLIENT NOMBRE"
     + SECTION_SIGN
     + "EDG #"
     + SECTION_SIGN
     + "PROGRAMA"
     + SECTION_SIGN
     + "TIPO DE COMPROBANTE"
     + SECTION_SIGN
     + "FECHA DE REGRESO"
     + SECTION_SIGN
     + "FECHA DEFINITIVA"")
     */
    String Form1020textLine1SP =
            "CLIENT NOMBRE"
                    + SECTION_SIGN
                    + "EDG #"
                    + SECTION_SIGN
                    + "PROGRAMA"
                    + SECTION_SIGN
                    + "TIPO DE COMPROBANTE"
                    + SECTION_SIGN
                    + "FECHA DE REGRESO"
                    + SECTION_SIGN
                    + "FECHA DEFINITIVA";
    /**
     * Field Form1020textLine1.
     * (value is ""CLIENT NAME"
     + SECTION_SIGN
     + "EDG #"
     + SECTION_SIGN
     + "PROGRAM"
     + SECTION_SIGN
     + "Verification Type"
     + SECTION_SIGN
     + "RETURN DT."
     + SECTION_SIGN
     + "FINAL DT."")
     */
    String Form1020textLine1 =
            "CLIENT NAME"
                    + SECTION_SIGN
                    + "EDG #"
                    + SECTION_SIGN
                    + "PROGRAM"
                    + SECTION_SIGN
                    + "Verification Type"
                    + SECTION_SIGN
                    + "RETURN DT."
                    + SECTION_SIGN
                    + "FINAL DT.";
    /**
     * Field visullayImpairedBold.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + BOLD_TYPE_FACE
     + STR_PIPE
     + FONT_16_SIZE
     + STR_PIPE")
     */
    String visullayImpairedBold =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + BOLD_TYPE_FACE
                    + STR_PIPE
                    + FONT_16_SIZE
                    + STR_PIPE;
    /**
     * Field visullayImpairedTextnormal.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + NORMAL_TYPE_FACE
     + STR_PIPE
     + FONT_16_SIZE
     + STR_PIPE")
     */
    String visullayImpairedTextnormal =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + NORMAL_TYPE_FACE
                    + STR_PIPE
                    + FONT_16_SIZE
                    + STR_PIPE;

    /**
     * Field FormNOD4EngStatic.
     * (value is ""@TF0001_2_EN_1"
     + DELIMIT_MAIN
     + "7777"
     + DELIMIT_MAIN
     + "NOD004"
     + DELIMIT_MAIN
     + "N"
     + DELIMIT_MAIN
     + "F"
     + DELIMIT_MAIN
     + "N"
     + DELIMIT_MAIN
     + "P"
     + DELIMIT_MAIN
     + "F"
     + DELIMIT_MAIN
     + "01"
     + DELIMIT_MAIN
     + "1"
     + DELIMIT_MAIN")
     */
    String FormNOD4EngStatic =
            "@TF0001_2_EN_1"
                    + DELIMIT_MAIN
                    + "7777"
                    + DELIMIT_MAIN
                    + "NOD004"
                    + DELIMIT_MAIN
                    + "N"
                    + DELIMIT_MAIN
                    + "F"
                    + DELIMIT_MAIN
                    + "N"
                    + DELIMIT_MAIN
                    + "P"
                    + DELIMIT_MAIN
                    + "F"
                    + DELIMIT_MAIN
                    + "01"
                    + DELIMIT_MAIN
                    + "1"
                    + DELIMIT_MAIN;

    // Case Specialist Tel # (on address-label)
    /**
     * Field LABEL_CONTACT_TEL_EN.
     * (value is ""Contact Tel #"")
     */
    String LABEL_CONTACT_TEL_EN = "Contact Tel #";
    /**
     * Field LABEL_FXX333_EN_1.
     * (value is ""Note: If you currently do not live, in a county within the && catchment area"")
     */
    String LABEL_FXX333_EN_1 =
            "Note: If you currently do not live, in a county within the && catchment area";
    /**
     * Field LABEL_FXX333_EN_2.
     * (value is ""where services are provided. You must move to a county, inside the catchment area, where services are provided within 120 days of eligibility determination, for CLASS services."")
     */
    String LABEL_FXX333_EN_2 =
            "where services are provided. You must move to a county, inside the catchment area, where services are provided within 120 days of eligibility determination, for CLASS services.";
    /**
     * Field LABEL_FXX151_SP_1.
     * (value is ""Usted está inscrito en Medicare Parte A y desea ayuda para pagar toda la prima de Medicare Parte B o una parte, bajo el programa de Medicaid para Beneficiario de Medicare Especificado de Bajos Ingresos (SLMB) o el programa de Beneficios de Personas Elegibles (QI)."")
     */
    String LABEL_FXX151_SP_1 =
            "Usted está inscrito en Medicare Parte A y desea ayuda para pagar toda la prima de Medicare Parte B o una parte, bajo el programa de Medicaid para Beneficiario de Medicare Especificado de Bajos Ingresos (SLMB) o el programa de Beneficios de Personas Elegibles (QI).";
    /**
     * Field LABEL_COMMENT_EN.
     * (value is ""Comments :"")
     */
    String LABEL_COMMENT_EN = "Comments :";
    /**
     * Field LABEL_COMMENT_SP.
     * (value is ""Comentarios :"")
     */
    String LABEL_COMMENT_SP = "Comentarios :";
    /**
     * Field NodLastLineEN.
     * (value is ""A"
     + STR_PIPE
     + "3.3"
     + STR_PIPE
     + "10.3"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + BOLD_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String NodLastLineEN =
            "A"
                    + STR_PIPE
                    + "3.3"
                    + STR_PIPE
                    + "10.3"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + BOLD_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;
    /**
     * Field Nod3StLineSP.
     * (value is ""A"
     + STR_PIPE
     + "0.3"
     + STR_PIPE
     + "3.35"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + BOLD_TYPE_FACE
     + STR_PIPE
     + FONT_10_SIZE
     + STR_PIPE")
     */
    String Nod3StLineSP =
            "A"
                    + STR_PIPE
                    + "0.3"
                    + STR_PIPE
                    + "3.35"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + BOLD_TYPE_FACE
                    + STR_PIPE
                    + FONT_10_SIZE
                    + STR_PIPE;
    /**
     * Field dynamicText12Bold.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + BOLD_TYPE_FACE
     + STR_PIPE
     + FONT_12_SIZE
     + STR_PIPE")
     */
    String dynamicText12Bold =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + BOLD_TYPE_FACE
                    + STR_PIPE
                    + FONT_12_SIZE
                    + STR_PIPE;
    // TF static line to be used dynamicaly by FormatDocument
    /**
     * Field TABLE_PATTERN_BOLD_STRING.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "<CODE>"
     + STR_PIPE
     + BOLD_TYPE_FACE
     + STR_PIPE
     + "<FONT>"
     + STR_PIPE")
     */
    String TABLE_PATTERN_BOLD_STRING =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "<CODE>"
                    + STR_PIPE
                    + BOLD_TYPE_FACE
                    + STR_PIPE
                    + "<FONT>"
                    + STR_PIPE;
    /**
     * Field dynamicTextnormal14.
     * (value is ""T"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "11"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + "0"
     + STR_PIPE
     + BOLD_TYPE_FACE
     + STR_PIPE
     + FONT_14_SIZE
     + STR_PIPE")
     */
    String dynamicTextnormal14 =
            "T"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "11"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + "0"
                    + STR_PIPE
                    + BOLD_TYPE_FACE
                    + STR_PIPE
                    + FONT_14_SIZE
                    + STR_PIPE;
}
